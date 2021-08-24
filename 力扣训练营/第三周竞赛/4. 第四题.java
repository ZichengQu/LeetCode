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
    public int splitTree(TreeNode root) {
        // Map<TreeNode, int[]> map = new HashMap<>(); // node, [左子树之和，右子树之和]，错误思路
        Map<TreeNode, Integer> map = new HashMap<>(); // node, 所有子孙结点的val之和，正确思路
        dfs(root.left, map);
        dfs(root.right, map);
        
        // int leftSum = root.left == null? 0: map.get(root.left)[0] + map.get(root.left)[1] + root.left.val;
        // int rightSum = root.right == null? 0: map.get(root.right)[0] + map.get(root.right)[1] + root.right.val;
        int leftSum = root.left == null? 0: map.get(root.left) + root.left.val;
        int rightSum = root.right == null? 0: map.get(root.right) + root.right.val;

        // map.put(root, new int[]{leftSum, rightSum});  // node, [左子树之和，右子树之和]
        map.put(root, leftSum + rightSum); // // node, 所有子孙结点的val之和

        int minDifference = Integer.MAX_VALUE;
        int total = map.get(root) + root.val; // 整棵树的val之和
        for(TreeNode node: map.keySet()){
            // 两个子树，一个是node及其子孙结点，另一个是所有结点-该node及其子孙结点。对两个子树的val之和的差取绝对值。取绝对值最小的那一个。
            minDifference = Math.min(minDifference, Math.abs((total - (map.get(node) + node.val)) - (map.get(node) + node.val)));
        }
        return minDifference;
    }

    private void dfs(TreeNode root, Map<TreeNode, Integer> map){
        if(root == null){
            return;
        }

        dfs(root.left, map);
        dfs(root.right, map);

        // int leftSum = root.left == null? 0: map.get(root.left)[0] + map.get(root.left)[1] + root.left.val;
        // int rightSum = root.right == null? 0: map.get(root.right)[0] + map.get(root.right)[1] + root.right.val;
        int leftSum = root.left == null? 0: map.get(root.left) + root.left.val;
        int rightSum = root.right == null? 0: map.get(root.right) + root.right.val;
        
        // map.put(root, new int[]{leftSum, rightSum});
        map.put(root, leftSum + rightSum);
    }
}