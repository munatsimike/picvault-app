package com.example.picvault.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.picvault.R

@Composable
fun ExtendedFOBButton(
    buttonText: String = "Camera",
    btnImage: ImageVector = ImageVector.vectorResource(R.drawable.photo_camera_24px),
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        icon = {
            Icon(
                imageVector = btnImage,
                contentDescription = "",
                modifier = Modifier.size(26.dp),
                tint = Color.Unspecified
            )
        },
        text = { Text(text = buttonText, color = Color.White) },
        containerColor = Color.Blue
    )
}