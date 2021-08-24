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
  * 官解答案：https://leetcode-cn.com/problems/invert-binary-tree/solution/fan-zhuan-er-cha-shu-by-leetcode-solution/
  * 递归 (自底向上)
  */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}

 /**
  * 递归 (自顶向下)
  */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        // 将该结点的左右儿子互换，不管是否为null
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // 递归左右子树
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}

/**
 * 层序遍历 (迭代)
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.offer(root);
        while(!nodes.isEmpty()){
            TreeNode temp = nodes.poll(); // 出队
            if(temp == null){ // 如果某一结点为空，则不考虑
                continue;
            }
            // 将该结点的左右儿子互换，不管是否为null
            TreeNode left = temp.left;
            temp.left = temp.right;
            temp.right = left;
            // 不管是否为null，都将其左右儿子入队
            nodes.offer(temp.left);
            nodes.offer(temp.right);
        }
        return root;
    }
}