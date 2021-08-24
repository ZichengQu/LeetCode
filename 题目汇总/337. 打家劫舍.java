/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/**
 * 337. 动态规划，O(n), S(n) 栈空间的使用代价是S(n)
 * 没有第一种方法直观
 */
class Solution {
    public int rob(TreeNode root) {
        int[] rootStatus = dfs(root);
        return Math.max(rootStatus[0], rootStatus[1]); // 0是选择的，1是不选择的
    }

    public int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs(node.left); // 获取其左节点的的最高金额, l[0]选择时的最高金额，l[1]不选择时的
        int[] r = dfs(node.right); // 获取其右节点的的最高金额, r[0]选择时的最高金额，r[1]不选择时的
        int selected = node.val + l[1] + r[1]; // 选择根结点，一定不能选择其左右子结点
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]); // 不选择根结点，根据情况是否选择其左右子结点.
        return new int[]{selected, notSelected};
    }
}

// class Solution {
//     Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>(); // f代表选择
//     Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>(); // g代表不选择

//     public int rob(TreeNode root) {
//         dfs(root);
//         return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
//     }

//     public void dfs(TreeNode node) {
//         if (node == null) {
//             return;
//         }
//         dfs(node.left);
//         dfs(node.right);
//         f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
//         g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
//     }
// }