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
  * 时间复杂度：O(n)，其中 n 是数组的长度。每个数字只访问一次。
  * 空间复杂度：O(log n)，其中 n 是数组的长度。空间复杂度不考虑返回值，因此空间复杂度主要取决于递归栈的深度，递归栈的深度是 O(log n)，因为是平衡二叉搜索树
  */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return makeBST(nums, 0, nums.length - 1);
    }

    private TreeNode makeBST(int[] nums, int left, int right){
        if(left > right){
            return null;
        }
        int mid = (left + right) / 2; // 选择中间点作为此BST的根节点
        // int mid = (left + right + 1) / 2; // 和力扣里给的输出形式是一致的，但这两种思路都是对的。

        TreeNode root = new TreeNode(nums[mid]);

        root.left = makeBST(nums, left , mid - 1); // 取左面的一半，作为root的左儿子
        root.right = makeBST(nums, mid + 1, right); // 同理

        return root;
    }
}