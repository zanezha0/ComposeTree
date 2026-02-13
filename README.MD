# Compose 树形列表

##  支持上万的数据的树形列表

### 截图
    ![截图1](https://github.com/zanezha0/ComposeTree/blob/master/img/Screenshot_20260212_145310.png?raw=true)

    ![截图2](https://github.com/zanezha0/ComposeTree/blob/master/img/Screenshot_20260212_145526.png?raw=true)

    ![截图3](https://raw.githubusercontent.com/zanezha0/ComposeTree/master/img/Screenshot_20260212_145526.png)
    ![截图4](https://raw.githubusercontent.com/zanezha0/ComposeTree/master/img/Screenshot_20260212_145310.png)


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




