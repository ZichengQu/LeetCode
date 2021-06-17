package 树的遍历;
// 145. 二叉树的后序遍历

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
 * 后序，递归
 */
// class Solution {
//     public List<Integer> postorderTraversal(TreeNode root) {
//         List<Integer> postoderList = new ArrayList<>();

//         postorderTree(postoderList, root);

//         return postoderList;
//     }

//     private void postorderTree(List<Integer> list, TreeNode root){
//         if(root == null){
//             return;
//         }else{
//             postorderTree(list, root.left);
//             postorderTree(list, root.right);
//             list.add(root.val);
//         }
//     }
// }

/**
 * 后序，迭代
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postoderList = new ArrayList<>();

        if(root == null){
            return postoderList;
        }

        Stack<TreeNode> treeStack = new Stack<>();
        treeStack.push(root);

        while(!treeStack.isEmpty()){
            TreeNode tempNode = treeStack.pop();
            postoderList.add(tempNode.val);

            if(tempNode.left != null){
                treeStack.push(tempNode.left);
            }
            if(tempNode.right != null){
                treeStack.push(tempNode.right);
            }
        }
        
        Collections.reverse(postoderList); // 迭代的push过程是先根，再left，再right，因此迭代的遍历过程为“根右左”，因此将其反转，变成了后序的“左右根”
        
        return postoderList;
    }
}