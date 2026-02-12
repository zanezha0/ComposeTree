package com.zz.tree

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Copyright © 2026 zhun All rights reserved.
 * Created by ZhaoZhun on 2026/2/6 17:28.
 * @author: ZhaoZhun 1820022519@qq.com
 * @version: V1.0
 *
 * 通用树形 LazyColumn
 * @param state 树状态
 * @param currentId 当前选中节点
 * @param indentWidth 每级缩进宽度
 * @param itemContent 每个节点的UI（完全自定义）
 */
@Composable
fun <T> TreeLazyColumn(
    modifier: Modifier = Modifier,
    state: TreeState<T>,
    currentId: String? = null,
    indentWidth: Dp = 16.dp,
    itemContent: @Composable (
        node: TreeNode<T>,
        level: Int,
        isExpanded: Boolean,
        isCurrent: Boolean,
        toggle: () -> Unit
    ) -> Unit
) {
    /**
     * 派生状态
     * expandedIds 改变时自动重新计算
     */
    val visibleNodes by remember { derivedStateOf { state.visibleNodes() } }

    LazyColumn(modifier) {
        items(
            items = visibleNodes,
            key = { it.node.id } // key 防止列表错乱
        ) { flatNode ->

            val node = flatNode.node
            val level = flatNode.level
            val isExpanded = state.isExpanded(node.id)
            val isCurrent = currentId == node.id

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    // 根据层级缩进
                    .padding(start = indentWidth * level)
            ) {
                itemContent(
                    node,
                    level,
                    isExpanded,
                    isCurrent
                ) {
                    // 点击箭头时切换展开
                    state.toggle(node.id)
                }
            }
        }
    }
}