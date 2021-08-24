package 树的遍历;
// 144. 二叉树的前序遍历

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
 * 前序，递归
 */
// class Solution {
//     public List<Integer> preorderTraversal(TreeNode root) {
//         List<Integer> preorderList = new ArrayList<>();
//         preorderTree(preorderList, root);
//         return preorderList;
//     }

//     private void preorderTree(List<Integer> list, TreeNode root){
//         if(root == null){
//             return;
//         }else{
//             list.add(root.val);
//             preorderTree(list, root.left);
//             preorderTree(list, root.right);
//         }
//     }
// }

/**
 * 前序，迭代
 */
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorderList = new ArrayList<>();

        if(root == null){
            return preorderList;
        }
        
        Stack<TreeNode> treeStack = new Stack<>();
        treeStack.push(root);

        while(!treeStack.isEmpty()){
            TreeNode tempNode = treeStack.pop();
            preorderList.add(tempNode.val);
            if(tempNode.right != null){
                treeStack.push(tempNode.right);
            }
            if(tempNode.left != null){
                treeStack.push(tempNode.left);
            }
        }
        return preorderList; // 迭代的push过程是先根，再right，再left，因此迭代的遍历过程为“根左右”。因为先push其right，再push其left，当pop时，就会先pop left，再pop right，形成了“根左右”
    }
}