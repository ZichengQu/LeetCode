/**
 * DFS
 */
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode root){
        if(root == null){
            return 0;
        }
        int res = 0;
        if(root.left != null && root.left.left == null && root.left.right == null){ // 只有 root.left != null 是左子树之和，所有条件是左叶子之和
            res += root.left.val;
        }
        res += dfs(root.left);
        res += dfs(root.right);

        return res;
    }
}

/**
 * BFS
 */
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        int res = 0;

        if(root == null){
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node.left != null && node.left.left == null && node.left.right == null){
                res += node.left.val;
            }
            if(node.left != null){
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }

        return res;
    }
}