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
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }else{
            int rootCount = 1;
            int leftCount = countNodes(root.left);
            int rightCount = countNodes(root.right);
            return rootCount + leftCount + rightCount;
        }
    }
}

// 官方答案 (bits & k) == 0) 没太懂

// 这个算法很好
// https://leetcode-cn.com/problems/count-complete-tree-nodes/solution/chang-gui-jie-fa-he-ji-bai-100de-javajie-fa-by-xia/