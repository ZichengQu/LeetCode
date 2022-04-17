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
            minDifference = Math.min(minDifference, Math.abs(root.val - pre)); // 其实不用绝对值也可以，因为是二叉搜索树的中序遍历，一定是非递减的
            pre = root.val;
        }

        inorderDFS_v2(root.right);
    }
}

/**
 * 错误示例
 */
class Solution {
    int min;

    public int getMinimumDifference(TreeNode root) {
        min = Integer.MAX_VALUE;

        inorder(root, null);

        return min;
    }

    private void inorder(TreeNode root, TreeNode last){ // 错误
        if(root == null){
            return;
        }
        
        inorder(root.left, last);

        if(last == null){
            last = root; // last 在递归的过程中会丢失，想用 last 需要定义全局，不能是方法内的局部变量
        }else{
            min = Math.min(min, root.val - last.val);
            last = root;
        }

        inorder(root.right, last);
        
    }
}