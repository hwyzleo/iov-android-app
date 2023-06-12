package net.hwyz.iov.ui.page.common

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import net.hwyz.iov.R

sealed class BottomNavRoute(
    var routeName: String,
    @StringRes var stringId: Int,
    var icon: ImageVector
) {
    object Lock1 : BottomNavRoute(RouteName.LOCK1, R.string.lock, Icons.Default.Lock)
    object Lock2 : BottomNavRoute(RouteName.LOCK2, R.string.lock, Icons.Default.Lock)
    object Lock3 : BottomNavRoute(RouteName.LOCK3, R.string.lock, Icons.Default.Lock)
    object Profile : BottomNavRoute(RouteName.PROFILE, R.string.profile, Icons.Default.Person)
}