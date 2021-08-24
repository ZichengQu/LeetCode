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
    int count = 0;
    public int countInconsistentNode(TreeNode root1, TreeNode root2) {
        count = 0;
        dfs(root1, root2);
        return count;
    }

    private void dfs(TreeNode root1, TreeNode root2){
        if(root1 == null && root2 == null){ // 都为空
            return;
        }
        if(root1 == null || root2 == null){ // 一个为空，一个不为空时，不同情况等于该不为空的结点，及其子孙结点
            if(root1 == null){
                count += countNode(root2);
            }else{
                count += countNode(root1);
            }
            return;
        }
        if(root1.val != root2.val){ // 都不为空，但值不相等时，是两个"不同"
            count += 2;
        }
        dfs(root1.left, root2.left);
        dfs(root1.right, root2.right);
    }

    private int countNode(TreeNode node){ // 返回当前结点所在的子树的结点数量
        if(node == null){
            return 0;
        }
        int leftCount = countNode(node.left);
        int rightCount = countNode(node.right);
        return leftCount + rightCount + 1;
    }
}