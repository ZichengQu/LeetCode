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
    int count;
    int val;
    public int countNodes(TreeNode root, int val) {
        count = 0;
        this.val = val;
        dfs(root);
        return count;
    }
    private void dfs(TreeNode root){
        if(root == null){
            return;
        }
        if(root.val == val){
            count++;
        }
        dfs(root.left);
        dfs(root.right);
    }   
}