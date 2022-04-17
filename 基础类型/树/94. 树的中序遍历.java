package 树的遍历;
// 94. 二叉树的中序遍历

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

// /**
//  * 中序，递归
//  */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderList = new ArrayList<>();

        inorderTree(inorderList, root);

        return inorderList;
    }

    private void inorderTree(List<Integer> list, TreeNode root){
        if(root == null){
            return;
        }

        inorderTree(list, root.left);
        list.add(root.val);
        inorderTree(list, root.right);
        
    }
}

/**
 * 中序，迭代
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderList = new ArrayList<>();

        if(root == null){
            return inorderList;
        }

        Stack<TreeNode> treeStack = new Stack<>();
        // treeStack.push(root);

        while(root != null || !treeStack.isEmpty()){
            if(root != null){
                treeStack.push(root);
                root = root.left; //因为是中序“左根右”，所以一直向最左面push
            }else{
                TreeNode tempNode = treeStack.pop(); // pop出最左面的结点
                inorderList.add(tempNode.val);

                root = tempNode.right; // 如果有右节点，则相当于将该tempNode是该右节点的根结点，该根节点的左节点为空。因此，左(空)根右

            }
        }
        
        return inorderList;
    }
}