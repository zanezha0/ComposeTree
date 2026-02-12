package com.zz.tree

/**
 * Copyright © 2026 zhun All rights reserved.
 * Created by ZhaoZhun on 2026/2/6 17:27.
 * @author: ZhaoZhun 1820022519@qq.com
 * @version: V1.0
 */


/**
 * 原始树节点
 * @param id 节点唯一ID
 * @param parentId 父节点ID，root 为 null
 * @param data 节点携带的数据（泛型，方便复用）
 * @param isExpanded 是否展开（初始化用）
 */
data class TreeNode<T>(
    val id: String,
    val parentId: String?,
    val data: T,
    val isExpanded: Boolean = false
)

/**
 * 扁平化后的节点（给 LazyColumn 使用）
 * @param node 原始节点
 * @param level 当前层级（用于缩进）
 */
data class FlatTreeNode<T>(
    val node: TreeNode<T>,
    val level: Int
)