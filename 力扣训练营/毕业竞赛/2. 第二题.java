/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode swapTree(TreeNode root) {
        dfs(root);
        return root;
    }

    private void dfs(TreeNode root){
        if(root == null){
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        dfs(root.left);
        dfs(root.right);
    }
}