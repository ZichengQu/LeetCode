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
  * DFS 递归
  * O(n), S(height)
  */
  class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
        // 上面和下面同理
        // int left = maxDepth(root.left) + 1;
        // int right = maxDepth(root.right) + 1;
        // return Math.max(left, right);
    }
}

/**
  * BFS 迭代
  * O(n), S(1)
  */
class Solution {
    public int maxDepth(TreeNode root) {
        int depth = 0;

        if(root == null){
            return depth;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);

        while(!nodeQueue.isEmpty()){
            int size = nodeQueue.size();
            while(size > 0){
                TreeNode temp = nodeQueue.poll();
                if(temp.left != null){
                    nodeQueue.offer(temp.left);
                }
                if(temp.right != null){
                    nodeQueue.offer(temp.right);
                }
                size--;
            }
            depth++;
        }
        return depth;
    }
}