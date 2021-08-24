## **Index**
1. **[基础类型](#基础类型)** <br>
    1.1  **[排序](#排序)** <br>
    1.2  **[树](#树)** <br>
    1.3  **[图](#图)** <br>
    1.4  **[回溯](#回溯)** <br>
    1.5  **[滑动窗口](#滑动窗口)** <br>
    1.6  **[分治](#分治)** <br>
    1.7  **[贪心算法](#贪心算法)** <br>
    1.8  **[动态规划](#动态规划)** <br>
    1.9  **[最大流](#最大流)** <br>
2. **[题目汇总](#题目汇总)** <br>


# **基础类型**
**[基础类型 (总入口)](基础类型)** <br>

## 排序
1. <a href="https://leetcode-cn.com/leetbook/detail/sort-algorithms/" target="_blank"><b>LeetCode 9大排序算法总结</b></a> <br>
2. **[排序总结 (待整理)](基础类型/排序/排序总结.txt)** <br>
3. **[冒泡排序](基础类型/排序/1.%20O(n^2)/1.%20冒泡排序.java)** <br>
4. **[选择排序](基础类型/排序/1.%20O(n^2)/2.%20选择排序.java)** <br>
5. **[插入排序](基础类型/排序/1.%20O(n^2)/3.%20插入排序.java)** <br>
6. **[希尔排序](基础类型/排序/2.%20O(n%20*%20log_n)/4.%20希尔排序.java)** <br>
7. **[堆排序](基础类型/排序/2.%20O(n%20*%20log_n)/5.%20堆排序.java)** <br>
8. **[快速排序](基础类型/排序/2.%20O(n%20*%20log_n)/6.%20快速排序.java)** <br>
9. **[归并排序](基础类型/排序/2.%20O(n%20*%20log_n)/7.%20归并排序.java)** <br>
10. **[计数排序 (待整理)](基础类型/排序/3.%20O(n)/8.%20计数排序.java)** <br>
11. **[基数排序 (待整理)](基础类型/排序/3.%20O(n)/9.%20基数排序.java)** <br>
**[56. 合并区间 (利用 Comparator 自定义排序)](题目汇总/56.%20合并区间.java)** <br>
**[239. 滑动窗口最大值 (单调队列、优先队列 (Comparator自定义排序)、TreeMap)](题目汇总/239.%20滑动窗口最大值.java)** <br>
**[373. 查找和最小的K对数字 (堆、优先队列 (Comparator自定义排序))](题目汇总/373.%20查找和最小的K对数字.java)** <br>
**[23. 合并K个升序链表 (归并排序、优先队列 (Comparable自定义排序))](题目汇总/23.%20合并K个升序链表.java)** <br>

## 树
**[144. 树的前序遍历](基础类型/树/144.%20树的前序遍历.java)** <br>
**[94. 树的中序遍历](基础类型/树/94.%20树的中序遍历.java)** <br>
**[145. 树的后序遍历](基础类型/树/145.%20树的后序遍历.java)** <br>
**[102. 树的层序遍历](题目汇总/102.%20树的层序遍历.java)** <br>
**[104. 二叉树的最大深度 (DFS + BFS)](题目汇总/104.%20二叉树的最大深度.java)** <br>
**[105. 从前序与中序遍历序列构造二叉树 (递归、迭代)](题目汇总/105.%20从前序与中序遍历序列构造二叉树.java)** <br>
**[106. 从中序与后序遍历序列构造二叉树 (递归、迭代)](题目汇总/106.%20从中序与后序遍历序列构造二叉树.java)** <br>
**[108. 将有序数组转换为二叉搜索树 (中序遍历二叉搜索树)](题目汇总/108.%20将有序数组转换为二叉搜索树.java)** <br>
**[257. 二叉树的所有路径 (回溯、DFS、BFS)](题目汇总/257.%20二叉树的所有路径.java)** <br>
**[98. 验证二叉搜索树 (一种通用递归，两种中序遍历(递归、迭代))](题目汇总/98.%20验证二叉搜索树.java)** <br>
**[530. 二叉搜索树的最小绝对差 (中序遍历二叉搜索树)](题目汇总/530.%20二叉搜索树的最小绝对差.java)** <br>

## 图
**[133. 图的DFS和BFS](基础类型/图/133.%20克隆图.java)** <br>
**[695. 岛屿的最大面积 (DFS、BFS)](题目汇总/695.%20岛屿的最大面积.java)** <br>
**[1254. 统计封闭岛屿的数目 (DFS、BFS))](题目汇总/1254.%20统计封闭岛屿的数目.java)** <br>
**[785. 判断二分图 (BFS、DFS)](题目汇总/785.%20判断二分图.java)** <br>
**[542. 01 矩阵 (BFS (非常规方式))](题目汇总/542.%2001%20矩阵.java)** <br>
**[934. 最短的桥 (DFS、BFS)](题目汇总/934.%20最短的桥.java)** <br>
**[417. 太平洋大西洋水流问题 (DFS)](题目汇总/417.%20太平洋大西洋水流问题.java)** <br>
**[847. 访问所有节点的最短路径 (用状态进行BFS)](题目汇总/847.%20访问所有节点的最短路径.java)** <br>

## 回溯
**[51. N 皇后 (回溯算法)](题目汇总/51.%20N%20皇后.java)** <br>
**[79. 单词搜索 (DFS、回溯)](题目汇总/79.%20单词搜索.java)** <br>
**[491. 递增子序列 (回溯、枝剪)](题目汇总/491.%20递增子序列.java)** <br>

## 滑动窗口
**[1423. 可获得的最大点数 (滑动窗口、DFS搜索(会超时))](题目汇总/1423.%20可获得的最大点数.java)** <br>
**[239. 滑动窗口最大值 (单调队列、优先队列 (Comparator自定义排序)、TreeMap)](题目汇总/239.%20滑动窗口最大值.java)** <br>
**[480. 滑动窗口中位数 (滑动窗口)](题目汇总/480.%20滑动窗口中位数.java)** <br>

## 分治
**[704. 二分查找](基础类型/查找/704.%20二分查找.java)** <br>
**[540. 有序数组中的单一元素 (二分查找)](题目汇总/540.%20有序数组中的单一元素.java)** <br>
**[大于target的最小的数的下标](力扣训练营/毕业竞赛/3.%20第三题.java)** <br>
**[441. 排列硬币 (小于等于target的最大的数)](题目汇总/441.%20排列硬币.java)** <br>

## 贪心算法
**[452. 用最少数量的箭引爆气球 (贪心算法)](题目汇总/452.%20用最少数量的箭引爆气球.java)** <br>

## 动态规划
**[279. 完全平方数 (动态规划、完全背包)](题目汇总/279.%20完全平方数.java)** <br>
**[322. 零钱兑换 (完全背包、动态规划)](题目汇总/322.%20零钱兑换.java)** <br>
**[518. 零钱兑换 II (动态规划，非完全背包)](题目汇总/518.%20零钱兑换%20II.java)** <br>
**[416. 分割等和子集 (0-1背包问题、动态规划)](题目汇总/416.%20分割等和子集.java)** <br>
**[486. 预测赢家 (动态规划、DFS、博弈论)](题目汇总/486.%20预测赢家.java)** <br>
**[1027. 最长等差数列 (动态规划，二维数组、Map数组)](题目汇总/1027.%20最长等差数列.java)** <br>
**[120. 三角形最小路径和 (动态规划、滚动数组优化空间)](题目汇总/120.%20三角形最小路径和.java)** <br>
**[213. 打家劫舍 (2) (动态规划、滚动数组优化空间) (通项公式)](题目汇总/213.%20打家劫舍.java)** <br>
**[72. 编辑距离 (动态规划)](题目汇总/72.%20编辑距离.java)** <br>

## 最大流

<br>

# **题目汇总**
**[题目汇总 (总入口)](题目汇总)** <br>
    1. **[两数之和 (哈希表)](题目汇总/1.%20两数之和.java)** <br>
    2. **[两数相加 (链表)](题目汇总/2.%20两数相加.java)** <br>
    3. **[无重复字符的最长子串 (滑动窗口)](题目汇总/3.%20无重复字符的最长子串.java)** <br>
    4. **[寻找两个正序数组的中位数 (归并、二分查找)](题目汇总/4.%20寻找两个正序数组的中位数.java)** <br>
    11. **[盛最多水的容器 (双指针)](题目汇总/11.%20盛最多水的容器.java)** <br>
    12. **[整数转罗马数字](题目汇总/12.%20整数转罗马数字.java)** <br>
    14. **[最长公共前缀](题目汇总/14.%20最长公共前缀.java)** <br>
    15. **[三数之和 (双指针)](题目汇总/15.%20三数之和.java)** <br>
    18. **[四数之和](题目汇总/18.%20四数之和.java)** <br>
    19. **[删除链表的倒数第 (快慢指针、栈)](题目汇总/19.%20删除链表的倒数第%20N%20个结点.java)** <br>
    20. **[有效的括号 (栈)](题目汇总/20.%20有效的括号.java)** <br>
    21. **[合并两个有序链表 (链表的指针操作)](题目汇总/21.%20合并两个有序链表.java)** <br>
    23. **[合并K个升序链表 (归并排序、优先队列 (Comparable自定义排序))](题目汇总/23.%20合并K个升序链表.java)** <br>
    26. **[删除有序数组中的重复项 (快慢指针)](题目汇总/26.%20删除有序数组中的重复项.java)** <br>
    31. **[下一个排列 (某一个思想技巧)](题目汇总/31.%20下一个排列.java)** <br>
    33. **[搜索旋转排序数组 (二分查找)](题目汇总/33.%20搜索旋转排序数组.java)** <br>
    34. **[在排序数组中查找元素的第一个和最后一个位置 (二分查找)](题目汇总/34.%20在排序数组中查找元素的第一个和最后一个位置.java)** <br>
    39. **[组合总和 (递归、回溯)](题目汇总/39.%20组合总和.java)** <br>
    41. **[缺失的第一个正数](题目汇总/41.%20缺失的第一个正数.java)** <br>
    42. **[接雨水 (动态规划、单调栈、双指针)](题目汇总/42.%20接雨水.java)** <br>
    45. **[跳跃游戏 II (贪心 or 动态规划)](题目汇总/45.%20跳跃游戏%20II.java)** <br>
    46. **[全排列 (DFS + 回溯)](题目汇总/46.%20全排列.java)** <br>
    47. **[全排列 II (DFS + 回溯)](题目汇总/47.%20全排列%20II.java)** <br>
    48. **[旋转图像 (数学找规律)](题目汇总/48.%20旋转图像.java)** <br>
    51. **[N 皇后 (回溯算法)](题目汇总/51.%20N%20皇后.java)** <br>
    53. **[最大子序和](题目汇总/53.%20最大子序和.java)** <br>
    56. **[合并区间 (利用 Comparator 自定义排序)](题目汇总/56.%20合并区间.java)** <br>
    62. **[不同路径 (动态规划)](题目汇总/62.%20不同路径.java)** <br>
    64. **[最小路径和 (动态规划)](题目汇总/64.%20最小路径和.java)** <br>
    66. **[加一 (数组模拟进位)](题目汇总/66.%20加一.java)** <br>
    69. **[x 的平方根 (二分)](题目汇总/69.%20x%20的平方根.java)** <br>
    70. **[爬楼梯 (最近重复子问题，斐波那契数列)](题目汇总/70.%20爬楼梯.java)** <br>
    72. **[编辑距离 (动态规划)](题目汇总/72.%20编辑距离.java)** <br>
    75. **[颜色分类 (双指针)](题目汇总/75.%20颜色分类.java)** <br>
    76. **[最小覆盖子串 (滑动窗口)](题目汇总/76.%20最小覆盖子串.java)** <br>
    77. **[组合 (回溯)](题目汇总/77.%20组合.java)** <br>
    79. **[单词搜索 (DFS、回溯)](题目汇总/79.%20单词搜索.java)** <br>
    80. **[删除有序数组中的重复项 (双指针、快慢指针)](题目汇总/80.%20删除有序数组中的重复项%20II.java)** <br>
    84. **[柱状图中最大的矩形 (单调栈，哨兵思想)](题目汇总/84.%20柱状图中最大的矩形.java)** <br>
    93. **[复原 IP 地址 (DFS，回溯)](题目汇总/93.%20复原%20IP%20地址.java)** <br>
    94. **[树的中序遍历](题目汇总/94.%20树的中序遍历.java)** <br>
    98. **[验证二叉搜索树 (一种通用递归，两种中序遍历(递归、迭代))](题目汇总/98.%20验证二叉搜索树.java)** <br>
    100. **[树的DFS和BFS (常用递归和层序)](题目汇总/100.%20相同的树.java)** <br>
    101. **[对称二叉树 ](题目汇总/101.%20对称二叉树.java)** <br>
    102. **[树的层序遍历](题目汇总/102.%20树的层序遍历.java)** <br>
    104. **[二叉树的最大深度 (DFS + BFS)](题目汇总/104.%20二叉树的最大深度.java)** <br>
    105. **[从前序与中序遍历序列构造二叉树 (递归、迭代)](题目汇总/105.%20从前序与中序遍历序列构造二叉树.java)** <br>
    106. **[从中序与后序遍历序列构造二叉树 (递归、迭代)](题目汇总/106.%20从中序与后序遍历序列构造二叉树.java)** <br>
    108. **[将有序数组转换为二叉搜索树 (中序遍历二叉搜索树)](题目汇总/108.%20将有序数组转换为二叉搜索树.java)** <br>
    118. **[杨辉三角 (动态规划)](题目汇总/118.%20杨辉三角.java)** <br>
    120. **[三角形最小路径和 (动态规划、滚动数组优化空间)](题目汇总/120.%20三角形最小路径和.java)** <br>
    121. **[买卖股票的最佳时机 (1) (前缀和 || 动态规划)](题目汇总/121.%20买卖股票的最佳时机.java)** <br>
    122. **[xxx买卖股票的最佳时机 (2) (待整理)xxx](题目汇总/122.%20买卖股票的最佳时机%20II.java)** <br>
    123. **[xxx买卖股票的最佳时机 (3) (待整理)xxx](题目汇总/123.%20买卖股票的最佳时机%20III.java)** <br>
    125. **[验证回文串](题目汇总/125.%20验证回文串.java)** <br>
    128. **[最长连续序列](题目汇总/128.%20最长连续序列.java)** <br>
    133. **[图的DFS和BFS](题目汇总/133.%20克隆图.java)** <br>
    134. **[加油站 (贪心算法)](题目汇总/134.%20加油站.java)** <br>
    136. **[只出现一次的数字 (位运算: 异或)](题目汇总/136.%20只出现一次的数字.java)** <br>
    138. **[复制带随机指针的链表 (迭代、回溯)](题目汇总/138.%20复制带随机指针的链表.java)** <br>
    141. **[环形链表 (1)，散列表、双(快慢)指针](题目汇总/141.%20环形链表.java)** <br>
    142. **[环形链表 (2)，散列表、双(快慢)指针](题目汇总/142.%20环形链表%20II.java)** <br>
    144. **[树的前序遍历](题目汇总/144.%20树的前序遍历.java)** <br>
    145. **[树的后序遍历](题目汇总/145.%20树的后序遍历.java)** <br>
    146. **[LRU 缓存机制 (链表操作(重点))](题目汇总/146.%20LRU%20缓存机制.java)** <br>
    147. **[对链表进行插入排序 (链表操作(重点))](题目汇总/147.%20对链表进行插入排序.java)** <br>
    155. **[最小栈 (用类实现一个最小栈)](题目汇总/155.%20最小栈.java)** <br>
    160. **[相交链表 (双指针、哈希)](题目汇总/160.%20相交链表.java)** <br>
    162. **[寻找峰值 (二分查找变形)](题目汇总/162.%20寻找峰值.java)** <br>
    169. **[多数元素(主要元素) (候选者、排序、哈希、随机抽样)](题目汇总/169.%20多数元素.java)** <br>
    173. **[二叉搜索树迭代器 (中序遍历二叉搜索树，递归，迭代)](题目汇总/173.%20二叉搜索树迭代器.java)** <br>
    198. **[打家劫舍 (1) (动态规划) (通项公式)](题目汇总/198.%20打家劫舍.java)** <br>
    199. **[二叉树的右视图 (BFS、DFS)](题目汇总/199.%20二叉树的右视图.java)** <br>
    200. **[岛屿数量 (BFS、DFS、并查集(未整理并查集)) (通项公式)](题目汇总/200.%20岛屿数量.java)** <br>
    206. **[反转链表](题目汇总/206.%20反转链表.java)** <br>
    207. **[课程表 (1) (BFS)](题目汇总/207.%20课程表.java)** <br>
    210. **[课程表 (2) (BFS)](题目汇总/210.%20课程表%20II.java)** <br>
    213. **[打家劫舍 (2) (动态规划、滚动数组优化空间) (通项公式)](题目汇总/213.%20打家劫舍.java)** <br>
    215. **[数组中的第K个最大元素 (各种排序方式都可以)](题目汇总/215.%20数组中的第K个最大元素.java)** <br>
    222. **[完全二叉树的节点个数](题目汇总/222.%20完全二叉树的节点个数.java)** <br>
    224. **[基本计算器](题目汇总/224.%20基本计算器.java)** <br>
    225. **[用队列实现栈](题目汇总/225.%20用队列实现栈.java)** <br>
    226. **[翻转二叉树 (递归、层序)](题目汇总/226.%20翻转二叉树.java)** <br>
    230. **[二叉搜索树中第K小的元素 (中序遍历二叉搜索树，递归，迭代)](题目汇总/230.%20二叉搜索树中第K小的元素.java)** <br>
    231. **[2的幂 (位运算)](题目汇总/231.%202的幂.java)** <br>
    232. **[用栈实现队列](题目汇总/232.%20用栈实现队列.java)** <br>
    234. **[回文链表 (反转链表(206)、双指针)](题目汇总/234.%20回文链表.java)** <br>
    239. **[滑动窗口最大值 (单调队列、优先队列 (Comparator自定义排序)、TreeMap)](题目汇总/239.%20滑动窗口最大值.java)** <br>
    257. **[二叉树的所有路径 (回溯、DFS、BFS)](题目汇总/257.%20二叉树的所有路径.java)** <br>
    260. **[只出现一次的数字 (位运算)](题目汇总/260.%20只出现一次的数字%20III.java)** <br>
    268. **[丢失的数字 (位运算、数学)](题目汇总/268.%20丢失的数字.java)** <br>
    274. **[H 指数](题目汇总/274.%20H%20指数.java)** <br>
    279. **[完全平方数 (动态规划、完全背包)](题目汇总/279.%20完全平方数.java)** <br>
    283. **[移动零 (一维数组的坐标变换)](题目汇总/283.%20移动零.java)** <br>
    297. **[二叉树的序列化与反序列化 (DFS)](题目汇总/297.%20二叉树的序列化与反序列化.java)** <br>
    300. **[最长递增子序列 (动态规划)](题目汇总/300.%20最长递增子序列.java)** <br>
    316. **[去除重复字母 (单调栈、贪心)](题目汇总/316.%20去除重复字母.java)** <br>
    322. **[零钱兑换 (完全背包、动态规划)](题目汇总/322.%20零钱兑换.java)** <br>
    337. **[打家劫舍 (3) (动态规划) (子解 -> 最终解)](题目汇总/337.%20打家劫舍.java)** <br>
    338. **[比特位计数 (动态规划、位运算)](题目汇总/338.%20比特位计数.java)** <br>
    342. **[4的幂 (位运算)](题目汇总/342.%204的幂.java)** <br>
    373. **[查找和最小的K对数字 (堆、优先队列 (Comparator自定义排序))](题目汇总/373.%20查找和最小的K对数字.java)** <br>
    380. **[O(1) 时间插入、删除和获取随机元素 (O(1)时间删除数组)](题目汇总/380.%20O(1)%20时间插入、删除和获取随机元素.java)** <br>
    400. **[第 N 位数字 (数学)](题目汇总/400.%20第%20N%20位数字.java)** <br>
    404. **[左叶子之和 (树的DFS、BFS)](题目汇总/404.%20左叶子之和.java)** <br>
    414. **[第三大的数](题目汇总/414.%20第三大的数.java)** <br>
    416. **[分割等和子集 (0-1背包问题、动态规划)](题目汇总/416.%20分割等和子集.java)** <br>
    417. **[太平洋大西洋水流问题 (DFS)](题目汇总/417.%20太平洋大西洋水流问题.java)** <br>
    429. **[N 叉树的层序遍历 (树的层序遍历)](题目汇总/429.%20N%20叉树的层序遍历.java)** <br>
    436. **[寻找右区间 (特殊二分查找、TreeMap)](题目汇总/436.%20寻找右区间.java)** <br>
    438. **[找到字符串中所有字母异位词 (滑动窗口)](题目汇总/438.%20找到字符串中所有字母异位词.java)** <br>
    441. **[排列硬币 (小于等于target的最大的数)](题目汇总/441.%20排列硬币.java)** <br>
    442. **[数组中重复的数据](题目汇总/442.%20数组中重复的数据.java)** <br>
    448. **[找到所有数组中消失的数字](题目汇总/448.%20找到所有数组中消失的数字.java)** <br>
    452. **[用最少数量的箭引爆气球 (贪心算法)](题目汇总/452.%20用最少数量的箭引爆气球.java)** <br>
    461. **[汉明距离 (位运算、异或、逻辑与)](题目汇总/461.%20汉明距离.java)** <br>
    480. **[滑动窗口中位数 (滑动窗口)](题目汇总/480.%20滑动窗口中位数.java)** <br>
    485. **[最大连续 1 的个数](题目汇总/485.%20最大连续%201%20的个数.java)** <br>
    486. **[预测赢家 (动态规划、DFS、博弈论)](题目汇总/486.%20预测赢家.java)** <br>
    491. **[递增子序列 (回溯、枝剪)](题目汇总/491.%20递增子序列.java)** <br>
    495. **[提莫攻击](题目汇总/495.%20提莫攻击.java)** <br>
    506. **[相对名次 (排序算法)](题目汇总/506.%20相对名次.java)** <br>
    518. **[零钱兑换 II (动态规划，非完全背包)](题目汇总/518.%20零钱兑换%20II.java)** <br>
    525. **[连续数组 (前缀和 + 哈希表)](题目汇总/525.%20连续数组.java)** <br>
    530. **[二叉搜索树的最小绝对差 (中序遍历二叉搜索树)](题目汇总/530.%20二叉搜索树的最小绝对差.java)** <br>
    540. **[有序数组中的单一元素 (二分查找)](题目汇总/540.%20有序数组中的单一元素.java)** <br>
    541. **[反转字符串 II (字符串)](题目汇总/541.%20反转字符串%20II.java)** <br>
    542. **[01 矩阵 (BFS)](题目汇总/542.%2001%20矩阵.java)** <br>
    543. **[二叉树的直径 (DFS)](题目汇总/543.%20二叉树的直径.java)** <br>
    560. **[和为K的子数组 (前缀和 + 哈希表)](题目汇总/560.%20和为K的子数组.java)** <br>
    606. **[根据二叉树创建字符串](题目汇总/606.%20根据二叉树创建字符串.java)** <br>
    617. **[合并二叉树 (DFS、BFS)](题目汇总/617.%20合并二叉树.java)** <br>
    622. **[设计循环队列](题目汇总/622.%20设计循环队列.java)** <br>
    628. **[三个数的最大乘积](题目汇总/628.%20三个数的最大乘积.java)** <br>
    645. **[错误的集合](题目汇总/645.%20错误的集合.java)** <br>
    671. **[二叉树中第二小的节点](题目汇总/671.%20二叉树中第二小的节点.java)** <br>
    695. **[岛屿的最大面积 (DFS、BFS)](题目汇总/695.%20岛屿的最大面积.java)** <br>
    697. **[数组的度](题目汇总/697.%20数组的度.java)** <br>
    704. **[二分查找](题目汇总/704.%20二分查找.java)** <br>
    785. **[判断二分图 (BFS、DFS)](题目汇总/785.%20判断二分图.java)** <br>
    847. **[访问所有节点的最短路径 (用状态进行BFS)](题目汇总/847.%20访问所有节点的最短路径.java)** <br>
    912. **[排序数组 (各种排序)](题目汇总/912.%20排序数组.java)** <br>
    914. **[卡牌分组 (最大公约数)](题目汇总/914.%20卡牌分组.java)** <br>
    930. **[和相同的二元子数组 (哈希、滑动数组)](题目汇总/930.%20和相同的二元子数组.java)** <br>
    934. **[最短的桥 (DFS、BFS)](题目汇总/934.%20最短的桥.java)** <br>
    1027. **[最长等差数列 (动态规划，二维数组、Map数组)](题目汇总/1027.%20最长等差数列.java)** <br>
    1143. **[最长公共子序列 (动态规划(经典题目))](题目汇总/1143.%20最长公共子序列.java)** <br>
    1254. **[统计封闭岛屿的数目 (DFS、BFS))](题目汇总/1254.%20统计封闭岛屿的数目.java)** <br>
    1338. **[数组大小减半 (贪心算法)](题目汇总/1338.%20数组大小减半.java)** <br>
    1423. **[可获得的最大点数 (滑动窗口、DFS搜索)](题目汇总/1423.%20可获得的最大点数.java)** <br>
    剑指 Offer 04. **[二维数组中的查找](题目汇总/剑指%20Offer%2004.%20二维数组中的查找.java)** <br>
    剑指 Offer 05. **[替换空格](题目汇总/剑指%20Offer%2005.%20替换空格.java)** <br>
    剑指 Offer 39. **[数组中出现次数超过一半的数字](题目汇总/剑指%20Offer%2039.%20数组中出现次数超过一半的数字.java)** <br>
    面试题 02.01. **[移除重复节点](题目汇总/面试题%2002.01.%20移除重复节点.java)** <br>