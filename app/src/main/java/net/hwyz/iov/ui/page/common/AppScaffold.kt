package net.hwyz.iov.ui.page.common

import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import net.hwyz.iov.ui.page.login.LoginPage
import net.hwyz.iov.ui.page.my.MyPage
import net.hwyz.iov.ui.widgets.EmptyView
import net.hwyz.iov.ui.widgets.bar.AppSnackBar

/**
 * APP框架
 */
@ExperimentalComposeUiApi
@Composable
fun AppScaffold() {
    val navCtrl = rememberNavController()
    val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        bottomBar = {
            when (currentDestination?.route) {
                RouteName.EXPLORE -> BottomNavBar(navCtrl = navCtrl)
                RouteName.SERVICE -> BottomNavBar(navCtrl = navCtrl)
                RouteName.VEHICLE -> BottomNavBar(navCtrl = navCtrl)
                RouteName.MALL -> BottomNavBar(navCtrl = navCtrl)
                RouteName.MY -> BottomNavBar(navCtrl = navCtrl)
            }
        },
        content = {
            var homeIndex = remember { 0 }
            var categoryIndex = remember { 0 }

            NavHost(
                modifier = Modifier.background(MaterialTheme.colors.background),
                navController = navCtrl,
                startDestination = RouteName.MY
            ) {
                // 探索
                composable(route = RouteName.EXPLORE) {
                    EmptyView(
                        tips = "待解锁",
                        imageVector = Icons.Default.Lock
                    ) {
                    }
                }
                // 服务
                composable(route = RouteName.SERVICE) {
                    EmptyView(
                        tips = "待解锁",
                        imageVector = Icons.Default.Lock
                    ) {
                    }
                }
                // 爱车
                composable(route = RouteName.VEHICLE) {
                    EmptyView(
                        tips = "待解锁",
                        imageVector = Icons.Default.Lock
                    ) {
                    }
                }
                // 商城
                composable(route = RouteName.MALL) {
                    EmptyView(
                        tips = "待解锁",
                        imageVector = Icons.Default.Lock
                    ) {
                    }
                }
                // 我的
                composable(route = RouteName.MY) {
                    MyPage(navCtrl, scaffoldState)
                }
                // 登录
                composable(route = RouteName.LOGIN) {
                    LoginPage(navCtrl, scaffoldState)
                }

            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = scaffoldState.snackbarHostState
            ) { data ->
                println("actionLabel = ${data.actionLabel}")
                AppSnackBar(data = data)
            }
        }
    )
}