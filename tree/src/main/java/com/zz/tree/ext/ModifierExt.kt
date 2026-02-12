package com.zz.tree.ext

import android.graphics.Paint
import android.graphics.Typeface
import android.util.TypedValue
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Copyright © 2024 zhun All rights reserved.
 * Created by Zha0Zhun on 2024/6/13 22:30.
 * @author: Zha0Zhun 1820022519@qq.com
 * @version: V1.0
 */

/**
 * 点击无水波纹
 */
fun Modifier.clickableWithoutRipple(enabled: Boolean = true, onClick: () -> Unit) = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        enabled
    ) {
        onClick()
    }
}

/**
 * 点击无水波纹
 */
fun Modifier.onClickUnboundedRipple(debounceTime: Long = 500L, enabled: Boolean = true, onClick: () -> Unit) = composed {
    var debounceJob by remember { mutableStateOf<Job?>(null) }
    val coroutineScope = rememberCoroutineScope()
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        enabled = enabled,
        indication = null
    ) {
        if (debounceJob == null || debounceJob?.isCompleted == false) {
            debounceJob = coroutineScope.launch {
                onClick()
                delay(debounceTime)
                debounceJob = null
            }
        }
    }
}

/**
 *
 * @receiver Modifier
 * @param debounceTime Long
 * @param enabled Boolean
 * @param onClick Function0<Unit>
 * @return Modifier
 */
fun Modifier.onClickWithoutRipple(enabled: Boolean = true, debounceTime: Long = 500L, onClick: () -> Unit) = composed {
    clickableWithoutRipple(enabled, debounceTime, onClick)
}

fun Modifier.clickableWithoutRipple(enabled: Boolean = true, debounceTime: Long = 500L, onClick: () -> Unit) = composed {
    var debounceJob by remember { mutableStateOf<Job?>(null) }
    val coroutineScope = rememberCoroutineScope()
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        enabled
    ) {
        if (debounceJob == null || debounceJob?.isCompleted == true) {
            debounceJob = coroutineScope.launch {
                onClick()
                delay(debounceTime)
                debounceJob = null
            }
        }
    }
}

/**
 * @receiver Modifier
 * @param debounceTime Long
 * @param enabled Boolean
 * @param onClick Function0<Unit>
 * @return Modifier
 */
fun Modifier.onClick(debounceTime: Long = 500L, enabled: Boolean = true, onClick: () -> Unit) = composed {
    var debounceJob by remember { mutableStateOf<Job?>(null) }
    val coroutineScope = rememberCoroutineScope()
    this.clickable(enabled = enabled) {
        if (debounceJob == null || debounceJob?.isCompleted == true) {
            debounceJob = coroutineScope.launch {
                onClick()
                delay(debounceTime)
                debounceJob = null
            }
        }
    }
}

// 扩展方法，用于添加长按功能
fun Modifier.onLongPress(onLongPress: () -> Unit): Modifier = pointerInput(Unit) {
    this.detectTapGestures(
        onLongPress = {
            onLongPress()
        }
    )
}

// 扩展方法：处理多次点击
fun Modifier.onMultiClick(
    clickTimes: Int,     // 需要点击的次数
    onMultiClick: () -> Unit,
): Modifier = composed {
    var clickCount by remember { mutableStateOf(0) }
    var debounceJob by remember { mutableStateOf<Job?>(null) }
    val coroutineScope = rememberCoroutineScope()
    this.pointerInput(Unit) {
        detectTapGestures(
            onTap = {
                debounceJob?.cancel()  // 取消上一个点击任务
                clickCount++
                debounceJob = coroutineScope.launch {
                    delay(300L) // 延迟，用于防止误触和多次点击之间的时间窗口
                    if (clickCount >= clickTimes) {
                        onMultiClick() // 达到点击次数时调用回调
                    }
                    clickCount = 0 // 重置计数器
                }
            }
        )
    }
}






