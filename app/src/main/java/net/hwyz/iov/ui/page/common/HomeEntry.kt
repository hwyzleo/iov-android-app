package net.hwyz.iov.ui.page.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import net.hwyz.iov.theme.AppTheme
import net.hwyz.iov.ui.page.splash.SplashPage

@ExperimentalComposeUiApi
@Composable
fun HomeEntry() {
    var isSplash by remember { mutableStateOf(true) }
    AppTheme {
        if (isSplash) {
            SplashPage { isSplash = false }
        } else {
            AppScaffold()
        }
    }
}

