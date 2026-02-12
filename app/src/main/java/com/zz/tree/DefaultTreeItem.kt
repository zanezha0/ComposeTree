package com.zz.tree

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zz.tree.ext.onClick
import com.zz.tree.ext.onClickWithoutRipple
import com.zz.tree.ui.theme.ComposeTreeTheme

/**
 * Copyright © 2026 zhun All rights reserved.
 * Created by ZhaoZhun on 2026/2/6 17:29.
 * @author: ZhaoZhun 1820022519@qq.com
 * @version: V1.0
 */
@Composable
fun DefaultTreeItem(
    title: String,
    level: Int,
    isExpanded: Boolean,
    isCurrent: Boolean,
    hasChildren: Boolean,
    onToggle: () -> Unit,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
//            .background(MaterialTheme.colorScheme.background)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onClick() },
                    onLongPress = { onToggle() }
                )
            }, contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (hasChildren) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .size(23.dp)
                        .rotate(
                            animateFloatAsState(
                                targetValue = if (isExpanded) 90f else 0f,
                                label = ""
                            ).value
                        )
                        .onClickWithoutRipple {
                            onToggle()
                        }
                )
            } else {
                Box(modifier = Modifier.size(9.dp))
            }
            Text(
                text = title, color = MaterialTheme.colorScheme.onBackground, modifier = Modifier.padding(vertical = 11.dp),
                fontSize = if (isCurrent) 18.sp else 16.sp,
                fontWeight = if (isCurrent) FontWeight.W700 else FontWeight.W500

            )
        }
        if (isCurrent) {
            Image(
                painter = painterResource(R.drawable.vector_topic_under_way),
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.TopEnd),
                contentDescription = null,
            )
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
private fun TreeScreenPreview() {
    ComposeTreeTheme {
        DefaultTreeItem(
            title = "DefaultTreeItem",
            level = 1,
            isExpanded = true,
            isCurrent = true,
            hasChildren = true,
            onToggle = {},
            onClick = {}
        )
    }
}