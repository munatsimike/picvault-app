package com.example.picvault.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.picvault.R

@Composable
fun ExtendedFOBButton(
    buttonText: String = "Camera",
    btnImage: Int = R.drawable.camera1,
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        icon = {
            Icon(
                painter = painterResource(btnImage),
                contentDescription = "",
                modifier = Modifier.size(30.dp),
                tint = Color.Unspecified
            )
        },
        text = { Text(text = buttonText, color = Color.White) },
        containerColor = Color.Blue
    )
}