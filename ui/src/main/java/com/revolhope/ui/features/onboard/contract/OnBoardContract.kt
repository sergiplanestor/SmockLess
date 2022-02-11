package com.revolhope.ui.features.onboard.contract

import com.revolhope.domain.feature.user.model.User
import com.revolhope.ui.utils.UiEvent
import com.revolhope.ui.utils.UiModel
import com.revolhope.ui.utils.UiSideEffect

sealed class OnBoardUiEvent : UiEvent {
    object AnimationEnded : OnBoardUiEvent()
    data class InputChanged(val input: String) : OnBoardUiEvent()
    data class SubmitForm(val username: String) : OnBoardUiEvent()
}

sealed class OnBoardUiModel : UiModel {
    data class UserFound(val user: User) : OnBoardUiModel()
    data class CreateUser(val input: String = "", val isValid: Boolean = true) : OnBoardUiModel()
}

sealed class OnBoardUiSideEffect : UiSideEffect {
    object RedirectToDashboard : OnBoardUiSideEffect()
}