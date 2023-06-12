package net.hwyz.iov.ui.widgets.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.hwyz.iov.theme.AppTheme

@Composable
fun SecondlyButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    AppButton(
        text = text,
        modifier = modifier,
        textColor = AppTheme.colors.textSecondary,
        onClick = onClick
    )
}