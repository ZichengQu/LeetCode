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
// class Solution {
//     public boolean isSameTree(TreeNode p, TreeNode q) {
//         /*
//          * 都为空，return true
//          * 一个为空，一个不为空，return false
//          * 都不为空，但值不相等，return false
//          * 都不为空，同时当前结点的值相等，此时需进一步判断。
//          */
//         if(p == null && q == null){
//             return true;
//         }else if((p == null || q == null) || (p.val != q.val)){ // p == null && q != null) || (p != null && q == null
//             return false;
//         }
//         // else if(p.val != q.val){ // 可以和上面的部分合并
//         //     return false;
//         // }
//         boolean left = isSameTree(p.left, q.left);
//         boolean right = isSameTree(p.right, q.right);
//         return left && right;
//     }
// }


class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        /*
         * 都为空，return true
         * 一个为空，一个不为空，return false
         * 都不为空，但值不相等，return false
         * 都不为空，同时当前结点的值相等，此时需进一步判断。
         */
        if(p == null || q == null){
            return p == q;
        }

        Queue<TreeNode> treeQueue = new LinkedList<>();
        treeQueue.offer(p);
        treeQueue.offer(q);

        while(!treeQueue.isEmpty()){
            TreeNode tempP = treeQueue.poll();
            TreeNode tempQ = treeQueue.poll();
            if(tempP.val != tempQ.val){
                return false;
            }

            if(tempP.left == null ^ tempQ.left == null){ // 位运算, 相同位则返回0，不同位则返回1。若返回1，则肯定一个为null，一个不为null
                return false;
            }
            if(tempP.right == null ^ tempQ.right == null){
                return false;
            }

            if(tempP.left != null && tempQ.left != null){ // 运行到这里，tempP和tempQ的左右子节点要么都为空，要么都非空
                treeQueue.offer(tempP.left);
                treeQueue.offer(tempQ.left);
            }
            if(tempP.right != null && tempQ.right != null){
                treeQueue.offer(tempP.right);
                treeQueue.offer(tempQ.right);
            }

        }
        return true;
    }
}
