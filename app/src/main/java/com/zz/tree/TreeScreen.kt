package com.zz.tree

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zz.tree.ui.theme.ComposeTreeTheme

/**
 * Copyright © 2026 zhun All rights reserved.
 * Created by ZhaoZhun on 2026/2/6 17:24.
 * @author: ZhaoZhun 1820022519@qq.com
 * @version: V1.0
 */

@Composable
fun TreeScreen(modifier: Modifier) {
    val nodes = remember {
        listOf(
            TreeNode("1", null, "Android"),
            TreeNode("6", null, "iOS"),
            TreeNode("2", "1", "Jetpack Compose"),
            TreeNode("3", "1", "View System"),
            TreeNode("4", "2", "LazyColumn"),
            TreeNode("5", "2", "Navigation"),
            TreeNode("7", "6", "SwiftUI"),
            TreeNode("8", "6", "UIKit"),
            TreeNode("9", "7", "ListView"),
            TreeNode("10", "7", "TableView"),
            TreeNode("11", "8", "CollectionView"),
            TreeNode("12", "8", "TableView"),
            TreeNode("13", "9", "ListView"),
            TreeNode("14", "10", "TableView"),
            TreeNode("15", "11", "CollectionView"),
            TreeNode("16", "12", "TableView"),
            TreeNode("17", "13", "ListView"),
            TreeNode("18", "14", "TableView"),
            TreeNode("19", "15", "CollectionView"),
            TreeNode("20", "16", "TableView"),
            TreeNode("21", "17", "ListView"),
            TreeNode("22", "18", "TableView"),
            TreeNode("23", "19", "CollectionView"),
            TreeNode("24", "20", "TableView"),
            TreeNode("25", "21", "ListView"),
            TreeNode("26", "22", "TableView"),
            TreeNode("27", "23", "CollectionView"),
            TreeNode("28", "24", "TableView"),
            TreeNode("29", null, "Harmony"),
            TreeNode("30", "29", "ListView"),
            TreeNode("31", "29", "TableView"),
            TreeNode("32", "30", "ListView"),
            TreeNode("33", "31", "TableView"),
            TreeNode("34", "32", "ListView"),
            TreeNode("35", "33", "TableView"),
        )
    }
    val treeState = remember { TreeState(nodes) }
    var currentId by remember { mutableStateOf<String?>(null) }
    TreeLazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        state = treeState,
        currentId = currentId
    ) { node, level, expanded, isCurrent, toggle ->

        val hasChildren = remember(node.id) {
            nodes.any { it.parentId == node.id }
        }

        DefaultTreeItem(
            title = node.data,
            level = level,
            isExpanded = expanded,
            isCurrent = isCurrent,
            hasChildren = hasChildren,
            onToggle = toggle,
            onClick = { currentId = node.id },
        )
    }
}

@Preview(showBackground = true,uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
private fun TreeScreenPreview() {
    ComposeTreeTheme {
        TreeScreen(Modifier)
    }
}
