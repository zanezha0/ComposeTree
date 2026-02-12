# Compose 树形结构

### 截图
    ![截图1](https://raw.githubusercontent.com/zanezha0/ComposeTree/blob/master/img/Screenshot_20260212_145310.png)
    ![截图2](https://raw.githubusercontent.com/zanezha0/ComposeTree/blob/master/img/Screenshot_20260212_145526.png)
### Usage
```kotlin
 val treeState = remember { TreeState(nodes) }
var currentId by remember { mutableStateOf<String?>(null) }
TreeLazyColumn(
    modifier = modifier
        .fillMaxSize(),
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
```




