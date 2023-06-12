package net.hwyz.iov.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.placeholder.material.placeholder
import net.hwyz.iov.theme.AppShapes
import net.hwyz.iov.theme.AppTheme
import net.hwyz.iov.theme.H6
import net.hwyz.iov.theme.H7
import net.hwyz.iov.theme.ListTitleHeight
import net.hwyz.iov.theme.white1
import net.hwyz.iov.utils.RegexUtils

@Composable
fun ListTitle(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String = "",
    isLoading: Boolean = false,
    onSubtitleClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .placeholder(false)
            .fillMaxWidth()
            .height(ListTitleHeight)
            .background(color = AppTheme.colors.background)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .width(5.dp)
                .height(16.dp)
                .align(Alignment.CenterVertically)
                .background(color = AppTheme.colors.textPrimary)
        )
        MediumTitle(
            title = title,
            modifier = Modifier.align(Alignment.CenterVertically),
            isLoading = isLoading
        )
        Spacer(modifier = Modifier.weight(1f))
        TextContent(
            text = subTitle,
            modifier = Modifier
                .padding(end = 10.dp)
                .clickable {
                    onSubtitleClick.invoke()
                },
            isLoading = isLoading
        )
    }

}