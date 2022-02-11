package com.revolhope.ui.utils

import com.revolhope.domain.common.time.utils.currentMillis
import com.splanes.core.base.component.contract.UiComponentContract

typealias UiEvent = UiComponentContract.Event
typealias UiModel = UiComponentContract.UiModel
typealias UiSideEffect = UiComponentContract.SideEffect.Effect
typealias UiNavSideEffect = UiComponentContract.SideEffect.Navigation

typealias UiState<T> = UiComponentContract.State<T>
typealias UiStateUninitialized<T> = UiComponentContract.State.Uninitialized<T>
typealias UiStateReady<T> = UiComponentContract.State.Ready<T>
typealias UiStateError<T> = UiComponentContract.State.Error<T>
typealias UiStateLoading<T> = UiComponentContract.State.Loading<T>

fun <T : UiModel> uiStateLoading(timeout: Long? = null): UiStateLoading<T> =
    UiStateLoading(startedOn = currentMillis(), timeout)