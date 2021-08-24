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
    int pre;
    int minDifference;

    public int getMinimumDifference(TreeNode root) {
        pre = -1;
        minDifference = Integer.MAX_VALUE;

        // List<Integer> list = new ArrayList<>(); // 使用 list

        // inorderDFS(root, list);

        // for(int i = 1; i < list.size(); i++){
        //     minDifference = Math.min(minDifference, Math.abs(list.get(i) - list.get(i - 1)));
        // }

        inorderDFS_v2(root);

        return minDifference;
    }

    // private void inorderDFS_v1(TreeNode root, List<Integer> list){ // 使用 list
    //     if(root == null){
    //         return;
    //     }

    //     inorderDFS_v1(root.left, list);

    //     list.add(root.val);

    //     inorderDFS_v1(root.right, list);
    // }

    private void inorderDFS_v2(TreeNode root){ // 在中序内部求最小差
        if(root == null){
            return;
        }

        inorderDFS_v2(root.left);

        if(pre == -1){
            pre = root.val;
        }else{
            minDifference = Math.min(minDifference, Math.abs(root.val - pre));
            pre = root.val;
        }

        inorderDFS_v2(root.right);
    }
}