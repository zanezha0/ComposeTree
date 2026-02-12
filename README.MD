# Compose 树形结构

### 截图
    <img src="https://github.com/zanezha0/ComposeTree/blob/master/img/Screenshot_20260212_145310.png" width="300">
    <img src="https://github.com/zanezha0/ComposeTree/blob/master/img/Screenshot_20260212_145526.png" width="300">

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




