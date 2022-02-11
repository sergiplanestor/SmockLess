package com.revolhope.ui.features.onboard.viewmodel

import androidx.lifecycle.viewModelScope
import com.revolhope.domain.common.usecase.UseCase
import com.revolhope.domain.common.usecase.map
import com.revolhope.domain.feature.user.exceptions.UserNotFoundError
import com.revolhope.domain.feature.user.exceptions.UserUpdateError
import com.revolhope.domain.feature.user.model.User
import com.revolhope.domain.feature.user.usecase.CreateUserUseCase
import com.revolhope.domain.feature.user.usecase.GetUserUseCase
import com.revolhope.ui.features.onboard.contract.OnBoardUiEvent
import com.revolhope.ui.features.onboard.contract.OnBoardUiModel
import com.revolhope.ui.features.onboard.contract.OnBoardUiSideEffect
import com.revolhope.ui.utils.UiState
import com.revolhope.ui.utils.UiStateError
import com.revolhope.ui.utils.UiStateReady
import com.revolhope.ui.utils.uiStateLoading
import com.splanes.core.base.component.viewmodel.BaseComponentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val createUserUseCase: CreateUserUseCase
) : BaseComponentViewModel<OnBoardUiModel, OnBoardUiEvent, OnBoardUiSideEffect>() {

    override fun onUiEventHandled(uiEvent: OnBoardUiEvent) {
        when (uiEvent) {
            OnBoardUiEvent.AnimationEnded -> fetchUser()
            is OnBoardUiEvent.InputChanged -> updateUiState {
                UiStateReady(
                    OnBoardUiModel.CreateUser(
                        input = uiEvent.input,
                        isValid = uiEvent.input.isBlank()
                    )
                )
            }
            is OnBoardUiEvent.SubmitForm -> submit(uiEvent.username)
        }
    }

    private fun fetchUser() {
        viewModelScope.launch {
            updateUiState { uiStateLoading() }
            val state = withContext(Dispatchers.IO) {
                getUserUseCase
                    .invoke(UseCase.Request(id = GetUserUseCase.ID, params = Unit))
                    .map(onSuccess = ::onUserFetched, onError = ::onUserFetchError)
            }
            updateUiState { state }
        }
    }

    private fun onUserFetched(user: User): UiState<OnBoardUiModel> =
        UiStateReady(OnBoardUiModel.UserFound(user))

    private fun onUserFetchError(error: Throwable?): UiState<OnBoardUiModel> =
        if (error is UserNotFoundError) {
            UiStateReady(OnBoardUiModel.CreateUser())
        } else {
            UiStateError(error)
        }

    private fun submit(username: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                createUserUseCase.invoke(UseCase.Request(CreateUserUseCase.ID, username))
            }.map(
                onSuccess = { onSubmitSuccess() },
                onError = { onSubmitError(username, it) }
            )
        }
    }

    private fun onSubmitSuccess() = onUiSideEffect { OnBoardUiSideEffect.RedirectToDashboard }

    private fun onSubmitError(username: String, error: Throwable?) = updateUiState {
        if (error is UserUpdateError.InvalidInput) {
            UiStateReady(OnBoardUiModel.CreateUser(input = username, isValid = false))
        } else {
            UiStateError(error)
        }
    }
}