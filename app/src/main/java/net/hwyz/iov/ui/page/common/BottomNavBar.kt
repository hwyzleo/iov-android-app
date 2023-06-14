package net.hwyz.iov.ui.page.common

import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import net.hwyz.iov.theme.AppTheme
import timber.log.Timber

/**
 * 底部导航栏
 */
@Composable
fun BottomNavBar(navCtrl: NavHostController) {
    val bottomNavList = listOf(
        BottomNavRoute.Explore,
        BottomNavRoute.Service,
        BottomNavRoute.Vehicle,
        BottomNavRoute.Mall,
        BottomNavRoute.Profile
    )
    BottomNavigation {
        val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavList.forEach { screen ->
            BottomNavigationItem(
                modifier = Modifier.background(AppTheme.colors.themeUi),
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = null,
                        tint = AppTheme.colors.textPrimary
                    )
                },
                label = {
                    Text(
                        text = stringResource(screen.stringId),
                        color = AppTheme.colors.textPrimary
                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == screen.routeName } == true,
                onClick = {
                    Timber.d("BottomNavView当前路由 ===> ${currentDestination?.hierarchy?.toList()}")
                    Timber.d("当前路由栈 ===> ${navCtrl.graph.nodes}")
                    if (currentDestination?.route != screen.routeName) {
                        navCtrl.navigate(screen.routeName) {
                            popUpTo(navCtrl.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                })
        }
    }
}