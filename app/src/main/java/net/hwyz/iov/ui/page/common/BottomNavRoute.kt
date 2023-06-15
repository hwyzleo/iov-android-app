package net.hwyz.iov.ui.page.common

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import net.hwyz.iov.R

sealed class BottomNavRoute(
    var routeName: String,
    @StringRes var stringId: Int,
    var icon: ImageVector
) {
    object Explore : BottomNavRoute(RouteName.EXPLORE, R.string.explore, Icons.Default.Lock)
    object Service : BottomNavRoute(RouteName.SERVICE, R.string.service, Icons.Default.Lock)
    object Vehicle : BottomNavRoute(RouteName.VEHICLE, R.string.vehicle, Icons.Default.Lock)
    object Mall : BottomNavRoute(RouteName.MALL, R.string.mall, Icons.Default.Lock)
    object My : BottomNavRoute(RouteName.MY, R.string.my, Icons.Default.Person)
}