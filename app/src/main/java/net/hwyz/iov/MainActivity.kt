package net.hwyz.iov

import android.Manifest
import android.R
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.ExperimentalComposeUiApi
import dagger.hilt.android.AndroidEntryPoint
import net.hwyz.iov.ui.page.common.HomeEntry


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )

        setContent {
            HomeEntry()
        }

    }

}