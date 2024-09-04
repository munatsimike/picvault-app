package com.example.picvault.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DisplayScreenHeader(
    headerData: HeaderData,
    isTitleVisible: Boolean,
    onBtnClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color(255,215,0))
            .padding(10.dp)
            .animateContentSize(animationSpec = tween(durationMillis = 500) )
    ) {
        BackButtonAndUsername(headerData) {onBtnClick()}
        HideAndShowPageTitleSection(isTitleVisible = isTitleVisible, headerData)
    }
}

@Composable
fun HideAndShowPageTitleSection(isTitleVisible: Boolean, headerData: HeaderData) {
    val slideDuration = 300
    AnimatedVisibility(
        visible = isTitleVisible,
        enter = fadeIn(animationSpec = tween(durationMillis = slideDuration)) +
                slideInVertically(
                    initialOffsetY = { -it }, // Slide from top
                    animationSpec = tween(durationMillis = slideDuration)
                ),
        exit = fadeOut(animationSpec = tween(durationMillis = slideDuration)) +
                slideOutVertically(
                    targetOffsetY = { -it }, // Slide to top
                    animationSpec = tween(durationMillis = slideDuration)
                )
    ) {
        ProjectTitleAndCurrentScreen(headerData)
    }
}


@Composable
private fun BackButtonAndUsername(
    headerData: HeaderData,
    modifier: Modifier = Modifier,
    onBtnClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onBtnClick() }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = ""
            )
            Text(text = "Back")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "",
                modifier = modifier.size(30.dp)
            )
            Text(text = headerData.username)
        }
    }
}

@Composable
private fun ProjectTitleAndCurrentScreen(headerData: HeaderData, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Outlined.Home,
            contentDescription = "",
            modifier.size(40.dp),
            tint = Color.Blue
        )
        Text(text = headerData.projectTitle)
        Text(text = headerData.currentPage)
    }
}