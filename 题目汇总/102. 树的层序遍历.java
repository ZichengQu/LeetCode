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
/**
 * 标准层序BFS遍历，非此题解法，此题解法在下面
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

/**
 * 此题解法
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();

        if(root == null){
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            List<Integer> subList = new LinkedList<>();
            int size = queue.size(); // 记录当前层的node个数
            while(size-- > 0){
                TreeNode node = queue.poll();
                subList.add(node.val);
                // 把下一层的node入队
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
            }
            res.add(subList);
        }

        return res;
    }
}