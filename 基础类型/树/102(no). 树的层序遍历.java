package 树的遍历;
// 102. 二叉树的层序遍历，通过该题简化为标准版的层序遍历，非此题答案

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
class Solution {
    public List<Integer> levelOrder(TreeNode root) {
        List<Integer> levelList = new ArrayList<>();
        if(root == null){
            return levelList;
        }
        Queue<TreeNode> treeQueue = new LinkedList<>();

        treeQueue.offer(root);

        while(!treeQueue.isEmpty()){
            TreeNode tempNode = treeQueue.poll();
            levelList.add(tempNode.val);
            if(tempNode.left != null){
                treeQueue.offer(tempNode.left);
            }
            if(tempNode.right != null){
                treeQueue.offer(tempNode.right);
            }
        }
        return levelList;
    }
}