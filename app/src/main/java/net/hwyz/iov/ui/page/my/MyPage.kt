package net.hwyz.iov.ui.page.my

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.hwyz.iov.ui.page.common.RouteName
import net.hwyz.iov.ui.widgets.list.ArrowRightListItem
import net.hwyz.iov.utils.AppUserUtil
import net.hwyz.iov.utils.RouteUtils

@Composable
fun MyPage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: MyViewModel = hiltViewModel()
) {
    val viewStates = viewModel.viewStates
    viewStates.isLogged = AppUserUtil.isLogged
    viewStates.userInfo = AppUserUtil.userInfo
    MyScreen(navCtrl, viewStates)
}

@Composable
fun MyScreen(
    navCtrl: NavHostController,
    viewState: MyState
) {
    var isLogin = remember { mutableStateOf(viewState.isLogged) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        if (!isLogin.value) {
            Button(
                onClick = {
                    RouteUtils.navTo(
                        navCtrl = navCtrl,
                        destinationName = RouteName.LOGIN
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent
                ),
                elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp)
            ) {
                Column() {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(top = 80.dp)
                            .size(100.dp)
                    )
                    Text(text = "注册/登录", fontSize = 18.sp, modifier = Modifier.padding(5.dp))
                    Spacer(modifier = Modifier.padding(bottom = 10.dp))
                }
            }
        }

        Column {
            ArrowRightListItem(iconRes = Icons.Default.List, title = "我的积分") {}
            ArrowRightListItem(iconRes = Icons.Default.ShoppingCart, title = "我的订单") {}
        }
        Spacer(modifier = Modifier.padding(bottom = 20.dp))
        Column {
            ArrowRightListItem(iconRes = Icons.Default.Settings, title = "设置") {}
            if (isLogin.value) {
                ArrowRightListItem(iconRes = Icons.Default.Settings, title = "退出") {
                    AppUserUtil.onLogOut()
                    isLogin.value = false
                }
            }
        }
    }
}

@Preview
@Composable
fun MyPagePreview() {
    val navCtrl = rememberNavController()
    var viewState = MyState(userInfo = null)
    MyScreen(navCtrl, viewState)
}