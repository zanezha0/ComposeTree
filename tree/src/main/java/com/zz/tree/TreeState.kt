package com.zz.tree

import androidx.compose.runtime.mutableStateListOf

/**
 * Copyright © 2026 zhun All rights reserved.
 * Created by ZhaoZhun on 2026/2/6 17:27.
 * @author: ZhaoZhun 1820022519@qq.com
 * @version: V1.0
 *
 * 树状态管理器
 * 负责：
 * 1. 保存节点
 * 2. 管理展开状态
 * 3. 生成 LazyColumn 可用的扁平列表
 */
class TreeState<T>(
    nodes: List<TreeNode<T>>
) {

    /** 根据 id 快速查找节点 */
    private val nodeMap = nodes.associateBy { it.id }.toMutableMap()

    /** parentId -> children 列表 */
    private val childrenMap = nodes.groupBy { it.parentId }

    /**
     * 当前展开的节点ID集合
     * mutableStateListOf 才能触发 Compose 刷新
     */
    var expandedIds = mutableStateListOf<String>()
        private set

    /** 展开/收起节点 */
    fun toggle(id: String) {
        if (expandedIds.contains(id)) expandedIds.remove(id)
        else expandedIds.add(id)
    }

    /** 判断节点是否展开 */
    fun isExpanded(id: String) = expandedIds.contains(id)

    /**
     * ⭐核心算法：生成可见节点
     * 把树 -> 扁平列表
     *
     * LazyColumn 只能高效渲染 List
     * 所以必须把树结构拍平成 List
     */
    fun visibleNodes(): List<FlatTreeNode<T>> {
        val result = mutableListOf<FlatTreeNode<T>>()

        /**
         * 递归添加子节点
         * @param parentId 父节点ID
         * @param level 当前层级
         */
        fun addChildren(parentId: String?, level: Int) {
            val children = childrenMap[parentId] ?: return

            children.forEach { node ->
                // 添加当前节点
                result.add(FlatTreeNode(node, level))

                // 如果节点展开 -> 递归添加子节点
                if (isExpanded(node.id)) {
                    addChildren(node.id, level + 1)
                }
            }
        }
        // 从 root 开始递归
        addChildren(null, 0)
        return result
    }
}