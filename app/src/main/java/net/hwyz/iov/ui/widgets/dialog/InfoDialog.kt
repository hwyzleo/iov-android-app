package net.hwyz.iov.ui.widgets.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import net.hwyz.iov.ui.widgets.MediumTitle
import net.hwyz.iov.ui.widgets.TextContent

@Composable
fun InfoDialog(
    title: String = "关于我",
    vararg content: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(dismissOnClickOutside = true),
        title = {
            MediumTitle(title = title)
        },
        text = {
            Column(
                Modifier.defaultMinSize(minWidth = 300.dp)
            ) {
                content.forEach {
                    TextContent(
                        text = it,
                        modifier = Modifier.padding(bottom = 10.dp),
                        canCopy = true
                    )
                }
            }
        },
        confirmButton = {
            TextContent(
                text = "关闭",
                modifier = Modifier
                    .padding(end = 18.dp, bottom = 18.dp)
                    .clickable { onDismiss.invoke() }
            )
        }
    )
}