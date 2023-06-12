package net.hwyz.iov.ui.page.login

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.collect
import net.hwyz.iov.theme.AppTheme
import net.hwyz.iov.ui.widgets.LoginEditView
import net.hwyz.iov.ui.widgets.bar.SNACK_ERROR
import net.hwyz.iov.ui.widgets.bar.TopTitleBar
import net.hwyz.iov.ui.widgets.bar.popupSnackBar
import net.hwyz.iov.ui.widgets.button.AppButton
import net.hwyz.iov.utils.RouteUtils.back

@ExperimentalComposeUiApi
@Composable
fun LoginPage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val viewStates = viewModel.viewStates
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineState = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.viewEvents.collect {
            if (it is LoginViewEvent.PopBack) {
                navCtrl.popBackStack()
            } else if (it is LoginViewEvent.ErrorMessage) {
                popupSnackBar(coroutineState, scaffoldState, label = SNACK_ERROR, it.message)
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.themeUi)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        keyboardController?.hide()
                    }
                )
            },
    ) {
        item {
            TopTitleBar(
                title = "登录 / 注册",
                onBack = { navCtrl.back() }
            )
        }
        if (!viewStates.isSendVerifyCode) {
            item {
                LoginEditView(
                    text = viewStates.mobile,
                    labelText = "手机号",
                    hintText = "请输入手机号",
                    onValueChanged = {
                        viewModel.dispatch(LoginIntent.UpdateMobileIntent(it))
                    },
                    onDeleteClick = {
                        viewModel.dispatch(LoginIntent.ClearMobileIntent)
                    }
                )
            }
            item {
                AppButton(
                    text = "获取验证码",
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    keyboardController?.hide()
                    viewModel.dispatch(LoginIntent.SendVerifyCodeIntent)
                }
            }
        } else {
            item {
                LoginEditView(
                    text = viewStates.verifyCode,
                    labelText = "验证码",
                    hintText = "请输入验证码",
                    onValueChanged = {
                        viewModel.dispatch(LoginIntent.UpdateVerifyCodeIntent(it))
                    },
                    onDeleteClick = {
                        viewModel.dispatch(LoginIntent.ClearVerifyCodeIntent)
                    }
                )
            }
            item {
                AppButton(
                    text = "登录",
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    keyboardController?.hide()
                    viewModel.dispatch(LoginIntent.VerifyCodeLoginIntent)
                }
            }
        }
    }
}