package net.hwyz.iov.ui.page.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.placeholder.material.placeholder
import net.hwyz.iov.R
import net.hwyz.iov.data.bean.UserInfo
import net.hwyz.iov.theme.AppTheme
import net.hwyz.iov.theme.BottomNavBarHeight
import net.hwyz.iov.ui.page.common.RouteName
import net.hwyz.iov.ui.widgets.bar.TopTitleBar
import net.hwyz.iov.ui.widgets.EmptyView
import net.hwyz.iov.ui.widgets.MainTitle
import net.hwyz.iov.ui.widgets.dialog.CommonAlertDialog
import net.hwyz.iov.ui.widgets.list.ArrowRightListItem
import net.hwyz.iov.utils.RouteUtils
import timber.log.Timber

@Composable
fun ProfilePage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val viewStates = viewModel.viewStates
    val isLogged = viewStates.isLogged
    val userInfo = viewStates.userInfo

    DisposableEffect(Unit) {
        Timber.d("Profile页启动")
        viewModel.dispatch(ProfileAction.OnStart)
        onDispose {
        }
    }

    if (viewStates.showLogout) {
        CommonAlertDialog(
            title = "提示",
            content = "确定退出登录吗？",
            onConfirmClick = {
                viewModel.dispatch(ProfileAction.Logout)
            },
            onDismiss = {
                viewModel.dispatch(ProfileAction.DismissLogoutDialog)
            }
        )
    }

    Column(
        modifier = Modifier
            .padding(bottom = BottomNavBarHeight)
            .fillMaxSize()
            .background(color = AppTheme.colors.mainColor)
    ) {
        if (isLogged && userInfo != null) {
            LazyColumn() {
                item {
                    HeaderPart(
                        navCtrl = navCtrl,
                        userInfo = userInfo,
                        messageCount = 0
                    )
                }
                item {
                    FooterPart(
                        navCtrl = navCtrl,
                        messageCount = 0,
                        onLogout = {
                            viewModel.dispatch(ProfileAction.ShowLogoutDialog)
                        })
                }
            }
        } else {
            TopTitleBar(title = "我的")
            EmptyView(
                tips = "点击登录",
                imageVector = Icons.Default.Face
            ) {
                RouteUtils.navTo(navCtrl, RouteName.LOGIN)
            }
        }
    }
}

//基本操作
@Composable
fun FooterPart(
    navCtrl: NavHostController,
    messageCount: Int,
    onLogout: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        ArrowRightListItem(
            iconRes = painterResource(R.drawable.ic_message),
            title = "退出登录"
        ) {
            onLogout.invoke()
        }
    }
}

//用户信息
@Composable
fun HeaderPart(
    navCtrl: NavHostController,
    userInfo: UserInfo,
    messageCount: Int,
) {
    Column {
        UserInfoItem(userInfo)
    }
}

//用户基本信息
@Composable
private fun UserInfoItem(userInfo: UserInfo?) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(AppTheme.colors.themeUi)
    ) {

        val (icon, info) = createRefs()

        Image(
            painter = painterResource(id = R.mipmap.ic_account),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(start = 20.dp)
                .width(48.dp)
                .height(48.dp)
                .clip(RoundedCornerShape(24.dp))
                .placeholder(
                    visible = userInfo == null,
                    color = AppTheme.colors.placeholder
                )
                .constrainAs(icon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
        )

        Column(
            modifier = Modifier
                .padding(start = 10.dp, bottom = 20.dp)
                .constrainAs(info) {
                    start.linkTo(icon.end)
                    top.linkTo(parent.top)
                }
        ) {
            MainTitle(
                title = userInfo?.username ?: "",
                modifier = Modifier.placeholder(
                    visible = userInfo == null,
                    color = AppTheme.colors.placeholder
                )
            )
        }
    }

}