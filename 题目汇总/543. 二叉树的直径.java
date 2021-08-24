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
  * DFS
  * O(n), S(Height)
  */
class Solution {
    int max;
    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        max = Math.max(max, left + right); // 和官解不同，不加1，因为本身就是当前结点的左侧+右侧，不需要加1来表示当前结点的深度.
        return Math.max(left, right) + 1;
    }
}