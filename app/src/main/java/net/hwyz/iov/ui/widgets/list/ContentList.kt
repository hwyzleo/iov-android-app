package net.hwyz.iov.ui.widgets.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import net.hwyz.iov.theme.AppTheme
import net.hwyz.iov.theme.H7
import net.hwyz.iov.ui.page.my.MyScreen
import net.hwyz.iov.ui.page.my.MyState
import net.hwyz.iov.ui.widgets.TextContent

@Composable
fun ContentList(
    title: String = "标题",
    msgCount: Int? = null,
    valueText: String = "",
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 10.dp)
            .clickable {
                onClick.invoke()
            }
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
        ) {
            TextContent(text = title, modifier = Modifier.align(Alignment.CenterVertically))
            if (msgCount != null) {
                Text(
                    text = "（$msgCount）",
                    fontSize = H7,
                    color = AppTheme.colors.error,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        if (valueText.isNotEmpty()) {
            TextContent(
                text = valueText,
                modifier = Modifier
                    .padding(end = 5.dp)
                    .align(Alignment.CenterVertically)
            )
        }
        Icon(
            Icons.Default.KeyboardArrowRight,
            null,
            tint = AppTheme.colors.textSecondary,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
    Divider()
}

@Preview
@Composable
fun ContentListPreview() {
    ContentList(valueText = "内容") {

    }
}