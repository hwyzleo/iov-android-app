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
import net.hwyz.iov.ui.page.main.profile.ProfilePage
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
                RouteName.LOCK1 -> BottomNavBar(navCtrl = navCtrl)
                RouteName.LOCK2 -> BottomNavBar(navCtrl = navCtrl)
                RouteName.LOCK3 -> BottomNavBar(navCtrl = navCtrl)
                RouteName.PROFILE -> BottomNavBar(navCtrl = navCtrl)
            }
        },
        content = {
            var homeIndex = remember { 0 }
            var categoryIndex = remember { 0 }

            NavHost(
                modifier = Modifier.background(MaterialTheme.colors.background),
                navController = navCtrl,
                startDestination = RouteName.PROFILE
            ) {
                composable(route = RouteName.LOCK1) {
                    EmptyView(
                        tips = "待解锁",
                        imageVector = Icons.Default.Lock
                    ) {
                    }
                }

                composable(route = RouteName.LOCK2) {
                    EmptyView(
                        tips = "待解锁",
                        imageVector = Icons.Default.Lock
                    ) {
                    }
                }

                composable(route = RouteName.LOCK3) {
                    EmptyView(
                        tips = "待解锁",
                        imageVector = Icons.Default.Lock
                    ) {
                    }
                }

                // 我的
                composable(route = RouteName.PROFILE) {
                    ProfilePage(navCtrl, scaffoldState)
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