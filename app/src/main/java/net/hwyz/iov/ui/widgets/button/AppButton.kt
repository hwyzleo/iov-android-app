package net.hwyz.iov.ui.widgets.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import net.hwyz.iov.theme.AppTheme
import net.hwyz.iov.theme.buttonCorner
import net.hwyz.iov.theme.buttonHeight
import net.hwyz.iov.ui.widgets.TextContent

@Composable
fun AppButton(
    text: String,
    modifier: Modifier = Modifier,
    bgColor: Color = AppTheme.colors.secondBtnBg,
    textColor: Color = AppTheme.colors.textPrimary,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(buttonHeight)
            .background(color = bgColor, shape = RoundedCornerShape(buttonCorner))
            .clickable {
                onClick()
            }
    ) {
        TextContent(text = text, color = textColor, modifier = Modifier.align(Alignment.Center))
    }
}