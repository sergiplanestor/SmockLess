package com.revolhope.ui.features.onboard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.revolhope.ui.features.onboard.OnBoardActivity
import com.revolhope.ui.features.onboard.contract.OnBoardUiEvent
import com.revolhope.ui.features.onboard.contract.OnBoardUiModel
import com.revolhope.ui.features.onboard.contract.OnBoardUiSideEffect
import com.revolhope.ui.features.onboard.viewmodel.OnBoardViewModel
import com.revolhope.ui.utils.*
import com.splanes.core.base.component.contract.UiComponentContract
import com.splanes.core.theme.Theme.AppTheme
import com.splanes.core.theme.Theme.Colors
import com.splanes.core.theme.Theme.Distances
import com.splanes.core.theme.Theme.Typographies
import com.splanes.core.theme.distance.default.DefaultMarginHorizontal
import com.splanes.core.theme.distance.default.DefaultMarginVertical
import com.splanes.core.theme.typography.default.DefaultFontFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import com.revolhope.ui.R.raw as Raw
import com.revolhope.ui.R.string as Strings

@Composable
fun OnBoardActivity.OnBoardComponent(navigate: (route: String) -> Unit) {

    val viewModel: OnBoardViewModel = hiltViewModel()

    val uiState = viewModel.uiState.value
    val uiSideEffectFlow = viewModel.uiSideEffect
    val onUiEvent: (OnBoardUiEvent) -> Unit = viewModel::onUiEvent
    val onNavigate: (OnBoardUiSideEffect) -> Unit = { effect ->
        if (effect is OnBoardUiSideEffect.RedirectToDashboard) {
            navigate(OnBoardActivity.Route.DASHBOARD)
        }
    }

    AppTheme { StatusBarColor(Colors.primary) }


    LaunchedEffect("OnBoardActivity.SplashComponent.SideEffect") {
        uiSideEffectFlow.onEach { effect -> onNavigate(effect) }.collect()
    }

    OnBoardView(
        uiState = uiState,
        onAnimEnd = { onUiEvent(OnBoardUiEvent.AnimationEnded) },
        onUiEvent = onUiEvent
    )
}

@Composable
private fun OnBoardView(
    uiState: UiComponentContract.State<OnBoardUiModel>,
    onAnimEnd: () -> Unit,
    onUiEvent: (OnBoardUiEvent) -> Unit
) {
    OnBoardLayout(uiState) { itemIndex, listState, coroutineScope ->

        when (itemIndex) {
            0 -> OnBoardHeader(onAnimEnd = onAnimEnd)
            1 -> when (uiState) {
                is UiStateUninitialized,
                is UiStateLoading -> {
                    OnBoardLoader()
                }
                is UiStateReady -> {
                    when (val model = uiState.data) {
                        is OnBoardUiModel.CreateUser -> {
                            OnBoardUserFormDescription()
                        }
                        is OnBoardUiModel.UserFound -> {
                            OnBoardWelcomeBack(model.user.name)
                        }
                    }
                }
                is UiStateError -> TODO()
            }
            2 -> if (uiState is UiStateReady) {
                val model = uiState.data
                if (model is OnBoardUiModel.CreateUser) {
                    OnBoardUserForm(
                        uiModel = model,
                        onUiEvent = onUiEvent,
                        itemIndex,
                        listState,
                        coroutineScope
                    )
                }
            }
        }
    }
}

@Composable
private fun OnBoardLayout(
    uiState: UiComponentContract.State<OnBoardUiModel>,
    contentByIndex: @Composable LazyItemScope.(Int, LazyListState, CoroutineScope) -> Unit
) {

    AppTheme {
        val listState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Colors.primary),
            contentPadding = PaddingValues(
                horizontal = DefaultMarginHorizontal,
                vertical = DefaultMarginVertical
            ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
        ) {

            val count = 1 + when (uiState) {
                is UiStateUninitialized,
                is UiStateLoading -> 1
                is UiStateReady -> {
                    when (val model = uiState.data) {
                        is OnBoardUiModel.CreateUser -> 2
                        is OnBoardUiModel.UserFound -> 1
                    }
                }
                is UiStateError -> TODO()
            }

            items(count = count) { index -> contentByIndex(index, listState, coroutineScope) }
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun LazyItemScope.OnBoardHeader(onAnimEnd: () -> Unit) {
    val spec = LottieCompositionSpec.RawRes(Raw.smokless_splash_anim)
    val composition: LottieComposition? by rememberLottieComposition(spec)
    val progress by animateLottieCompositionAsState(composition = composition)

    Spacer(modifier = Modifier.height(32.dp))

    LottieAnimation(composition, modifier = Modifier.size(150.dp))

    Spacer(modifier = Modifier.height(42.dp))

    Text(
        modifier = Modifier.padding(
            top = Distances.large,
            start = Distances.medium,
            end = Distances.medium
        ),
        text = stringResource(id = Strings.app_name),
        style = Typographies.display.XL.copy(
            fontFamily = DefaultFontFamily.apercuPro,
            fontSize = 54.sp,
            fontWeight = FontWeight.Light
        ),
        color = Colors.onPrimary
    )

    Spacer(modifier = Modifier.height(32.dp))

    if (progress == 1f) onAnimEnd()
}

@Composable
private fun LazyItemScope.OnBoardLoader() {

    CircularProgressIndicator(color = Colors.onPrimary)

    Spacer(modifier = Modifier.height(24.dp))

    Text(
        text = "Loading user...",
        style = Typographies.body.XL,
        color = Colors.onPrimary
    )
}

@Composable
private fun LazyItemScope.OnBoardWelcomeBack(username: String) {
    Text(
        text = "Welcome back",
        style = Typographies.body.XL,
        color = Colors.onPrimary
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = username,
        style = Typographies.headline.M,
        color = Colors.onSecondary
    )
}

@Composable
private fun LazyItemScope.OnBoardUserFormDescription() {
    Text(
        text = "Awesome!",
        style = Typographies.headline.M,
        color = Colors.onSecondary
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        modifier = Modifier.padding(
            start = Distances.medium,
            end = Distances.medium
        ),
        text = "If you're here is because you desire to become healthier, that's the first and one of the most important step.\n" +
                "Please, tell us how should we name you.",
        style = Typographies.body.L,
        color = Colors.onSecondary,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun LazyItemScope.OnBoardUserForm(
    uiModel: OnBoardUiModel.CreateUser,
    onUiEvent: (OnBoardUiEvent) -> Unit,
    item: Int,
    listState: LazyListState,
    coroutineScope: CoroutineScope
) {

    Spacer(modifier = Modifier.height(36.dp))

    OutlinedTextField(
        modifier = Modifier.onFocusChanged { if (it.hasFocus) coroutineScope.launch { listState.animateScrollToItem(item) } },
        value = uiModel.input,
        label = { UserFormInputLabel() },
        onValueChange = { onUiEvent(OnBoardUiEvent.InputChanged(it)) },
        textStyle = Typographies.title.M,
        colors = inputColors(),
        singleLine = true,
        isError = uiModel.isValid
    )

    Spacer(modifier = Modifier.height(32.dp))

    OutlinedButton(
        enabled = uiModel.isValid,
        onClick = { onUiEvent(OnBoardUiEvent.SubmitForm(uiModel.input)) },
        colors = buttonColors()
    ) {
        Text(
            text = "SUBMIT",
            style = Typographies.title.S,
            color = Colors.onPrimary
        )
    }
}

@Composable
private fun UserFormInputLabel() {
    Text(text = "Username", style = Typographies.body.L, color = Colors.onPrimary)
}

@Composable
private fun inputColors(): TextFieldColors =
    TextFieldDefaults.outlinedTextFieldColors(
        unfocusedBorderColor = Colors.onPrimary,
        unfocusedLabelColor = Colors.onPrimary,
        focusedBorderColor = Colors.onPrimary,
        focusedLabelColor = Colors.onPrimary
    )

@Composable
private fun buttonColors(): ButtonColors =
    ButtonDefaults.outlinedButtonColors(
        backgroundColor = Colors.primary,
        contentColor = Colors.onPrimary,
        disabledContentColor = Colors.primary.copy(alpha = 0.6f).compositeOver(Colors.surface),
    )

