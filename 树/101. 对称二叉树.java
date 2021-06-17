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
 * 递归，O(n), S(n)
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return isSymmetric(root.left, root.right);
    }
    private boolean isSymmetric(TreeNode leftNode, TreeNode rightNode){
        if(leftNode == null && rightNode == null){
            return true;
        }else if(leftNode == null || rightNode == null){
            return false;
        }else if(leftNode.val != rightNode.val){
            return false;
        }
        return isSymmetric(leftNode.left, rightNode.right) && isSymmetric(leftNode.right, rightNode.left);
    }
}

/**
 * 迭代，O(n), S(n)
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return isSymmetric(root.left, root.right);
    }
    private boolean isSymmetric(TreeNode leftNode, TreeNode rightNode){
        
        Queue<TreeNode> treeQueue = new LinkedList<>();
        treeQueue.offer(leftNode);
        treeQueue.offer(rightNode);

        while(!treeQueue.isEmpty()){
            TreeNode tempLeft = treeQueue.poll();
            TreeNode tempRight = treeQueue.poll();
            if(tempLeft == null && tempRight == null){
                continue;
            }else if(tempLeft == null || tempRight == null){
                return false;
            }else if(tempLeft.val != tempRight.val){
                return false;
            }
            treeQueue.offer(tempLeft.left);
            treeQueue.offer(tempRight.right);
            treeQueue.offer(tempLeft.right);
            treeQueue.offer(tempRight.left);
        }
        return true;

    }
}