package net.hwyz.iov.ui.page.my.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.hwyz.iov.theme.AppTheme
import net.hwyz.iov.ui.page.common.LoadingPage
import net.hwyz.iov.ui.page.login.LoginIntent
import net.hwyz.iov.ui.widgets.bar.TopTitleBar
import net.hwyz.iov.ui.widgets.item.TextFieldItem
import net.hwyz.iov.ui.widgets.list.ContentList
import net.hwyz.iov.utils.CommonUtil
import net.hwyz.iov.utils.RouteUtils.back

@Composable
fun ProfilePage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val viewStates = viewModel.viewStates

    LaunchedEffect(Unit) {
//        viewModel.viewEvents.collect {
//            if (it is LoginViewEvent.PopBack) {
//                navCtrl.popBackStack()
//            } else if (it is LoginViewEvent.ErrorMessage) {
//                popupSnackBar(coroutineState, scaffoldState, label = SNACK_ERROR, it.message)
//            }
//        }
        viewModel.intent(ProfileIntent.OnLaunched)
    }

    when (viewStates.result) {
        ProfileResult.DisplayLoading -> LoadingPage()
        ProfileResult.DisplayProfile -> {
            ProfileScreen(
                navCtrl,
                { intent: ProfileIntent -> viewModel.intent(intent) },
                viewStates.nickname,
                viewStates.gender
            )
        }
        ProfileResult.DisplayNickname -> {
            NicknameScreen(
                intent = { intent: ProfileIntent -> viewModel.intent(intent) },
                nickname = viewStates.nickname
            )
        }
        ProfileResult.DisplayGender -> {
            GenderScreen(
                intent = { intent: ProfileIntent -> viewModel.intent(intent) },
                gender = viewStates.gender
            )
        }
    }

}

@Composable
fun ProfileScreen(
    navCtrl: NavHostController,
    intent: (ProfileIntent) -> Unit,
    nickname: String,
    gender: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.themeUi)
    ) {
        TopTitleBar(
            onBack = { navCtrl.back() }
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 80.dp)
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
        }
        Column {
            ContentList(title = "昵称", valueText = nickname) {
                intent(ProfileIntent.OnTapNickname)
            }
            ContentList(title = "性别", valueText = CommonUtil.genderStr(gender)) {
                intent(ProfileIntent.OnTapGender)
            }
        }
        Spacer(modifier = Modifier.padding(bottom = 20.dp))
    }
}

@Composable
fun NicknameScreen(
    intent: (ProfileIntent) -> Unit,
    nickname: String
) {
    var textValue by remember { mutableStateOf(nickname) }
    Column(modifier = Modifier.background(AppTheme.colors.themeUi)) {
        TopTitleBar(onBack = { intent(ProfileIntent.OnTapBackProfile) })
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "你的昵称")
        }
        TextFieldItem(
            text = textValue,
            onValueChanged = { textValue = it }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            TextButton(
                onClick = { intent(ProfileIntent.OnTapNicknameSaveButton(textValue)) },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    backgroundColor = Color.Gray
                ),
                modifier = Modifier
                    .padding(start = 12.dp)
                    .width(100.dp)
            ) {
                Text(
                    text = "保存"
                )
            }
        }
    }
}

@Composable
fun GenderScreen(
    intent: (ProfileIntent) -> Unit,
    gender: String
) {
    var expanded = remember { mutableStateOf(false) }
    val genderLabel = listOf("男", "女", "未知")
    val genderCode = listOf("MALE", "FEMALE", "UNKNOWN")
    var selectedIndex = remember {
        var i = 0;
        genderCode.forEachIndexed { index, code ->
            if (code == gender) {
                i = index
            }
        }
        mutableStateOf(i)
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.themeUi),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TopTitleBar(onBack = { intent(ProfileIntent.OnTapBackProfile) })
            Text(text = "你的性别")
            TextButton(
                onClick = { expanded.value = true },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    backgroundColor = Color.Transparent
                ),
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(
                    text = genderLabel[selectedIndex.value]
                )
            }
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }) {
                genderLabel.forEachIndexed { index, s ->
                    DropdownMenuItem(onClick = {
                        expanded.value = false
                        selectedIndex.value = index
                    }) {
                        Text(text = s)
                    }
                }
            }
            TextButton(
                onClick = { intent(ProfileIntent.OnTapGenderSaveButton(genderCode[selectedIndex.value])) },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    backgroundColor = Color.Gray
                ),
                modifier = Modifier
                    .padding(start = 12.dp)
                    .width(100.dp)
            ) {
                Text(
                    text = "保存"
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfilePagePreview() {
    val navCtrl = rememberNavController()
    ProfileScreen(navCtrl, {}, "hwyz_leo", "MALE")
//    NicknameScreen({}, "hwyz_leo")
//    GenderScreen(intent = {}, gender = "UNKNOWN")
}