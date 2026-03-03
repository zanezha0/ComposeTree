# Compose 树形列表

##  支持上万的数据的树形列表

### 截图
    ![截图1](https://github.com/zanezha0/ComposeTree/blob/master/img/Screenshot_20260212_145310.png?raw=true)

    ![截图2](https://github.com/zanezha0/ComposeTree/blob/master/img/Screenshot_20260212_145526.png?raw=true)

    ![截图3](https://raw.githubusercontent.com/zanezha0/ComposeTree/master/img/Screenshot_20260212_145526.png)
    ![截图4](https://raw.githubusercontent.com/zanezha0/ComposeTree/master/img/Screenshot_20260212_145310.png)
    ![截图5](https://p6-xtjj-sign.byteimg.com/tos-cn-i-73owjymdk6/1aa3a41249004c7d8b9a345f2198fa97~tplv-73owjymdk6-jj-mark-v1:0:0:0:0:5o6Y6YeR5oqA5pyv56S-5Yy6IEAgWmhhMFpodW4=:q75.awebp?rk3s=f64ab15b&x-expires=1772801510&x-signature=rj1%2F5kXVWGEfopbCeIhs1j4TYHA%3D)


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




