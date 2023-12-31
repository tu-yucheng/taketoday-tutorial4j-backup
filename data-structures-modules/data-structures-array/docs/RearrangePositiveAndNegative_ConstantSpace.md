## 1. 问题描述

给定一个包含正数和负数数组，对其进行排列，使所有负数出现在数组中所有正数之前，不能使用哈希表、数组等任何其他数据结构。应保持原始数组种元素的出现顺序。

示例：

```
输入: [12, 11, -13, -5, 6, -7, 5, -3, -6]
输出: [-13, -5, -7, -3, -6, 12, 11, 6, 5]
```

一个简单的解决方案是使用另一个数组。我们将原始数组的所有元素到新数组。然后遍历新数组，并将所有负元素和正元素逐一回原始数组。
这种方法的问题是它使用辅助数组，我们不允许使用任何数据结构来解决这个问题。一种不使用任何数据结构的方法是使用快速排序的分区过程。
其思想是将0视为pivot，并围绕它划分数组。这种方法的问题在于它改变了元素的相对顺序。

现在，让我们讨论几个不使用任何其他数据结构并且还保留元素相对顺序的方法。

## 2. 修改快速排序的分区过程

只要相对顺序发生改变，我们就可以颠倒正数的顺序。如果左子数组中最后一个负数和当前负元素之间有多个正元素，则会发生这种情况。