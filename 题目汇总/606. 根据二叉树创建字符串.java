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
//     public String tree2str(TreeNode root) {
//         if(root == null){
//             return "";
//         }else if(root.left == null && root.right == null){
//             return "" + root.val;
//         }else if(root.left == null){
//             return "" + root.val + "()" + "(" + tree2str(root.right) + ")";
//         }else if(root.right == null){
//             return "" + root.val + "(" + tree2str(root.left) + ")";
//         }else{ // root.left == null的条件可以和这个合并
//             return "" + root.val + "(" + tree2str(root.left) + ")" + "(" + tree2str(root.right) + ")";
//         }
//     }
// }

/**
 * 将先序非递归遍历，改成题目要求的模式。  
 */
public class Solution {
    public String tree2str(TreeNode root) {
        if(root == null){ //如果为空树则返回
            return "";
        } 
        
        Stack<TreeNode> treeStack = new Stack<>();
        treeStack.push(root);

        Set<TreeNode> visited = new HashSet<>();

        StringBuilder result = new StringBuilder("");

        while(!treeStack.isEmpty()){
            // TreeNode tempNode = treeStack.pop(); 

            /*
             * 这几行可以将普通先序改成题目要求的模式,
             * 一开始不是直接pop，
             * 当再次遇到时才pop，为了给其增加 )
             */
            TreeNode tempNode = treeStack.peek(); 
            if(visited.contains(tempNode)){
                result.append(")");
                treeStack.pop(); 
            }else{                     

                if(tempNode != null){
                    visited.add(tempNode);
                    result.append("(" + tempNode.val); //访问根节点

                    if (tempNode.left == null && tempNode.right != null)
                        result.append("()");
                    if (tempNode.right != null)
                        treeStack.push(tempNode.right);
                    if (tempNode.left != null)
                        treeStack.push(tempNode.left);
                    
                    // treeStack.push(tempNode.right); //入栈右孩子
                    // treeStack.push(tempNode.left);//入栈左孩子
                }

            }
        }
        return result.substring(1, result.length() - 1);
    }
}

// /**
//     普通的先序遍历
//  */
// class Solution {
//     public String tree2str(TreeNode root) {
//         if(root == null){
//             return "";
//         }
//         String result = "";
        
//         Stack<TreeNode> stack = new Stack<>();
//         while(root != null || !stack.empty()){
//             while(root != null){
//                 result += root.val;

//                 stack.push(root);
//                 root = root.left;
//             }

//             if(!stack.empty()){
//                 root = stack.pop();
//                 root = root.right;
//             }
//         }
//         return result;
//     }
// }

// /**
//     普通的先序遍历
//  */
// public class Solution {
//     public String tree2str(TreeNode root) {
//         Stack<TreeNode> treeStack = new Stack<>();
//         if(root==null){ //如果为空树则返回
//             return "";
//         } 
//         StringBuilder result = new StringBuilder("");
//         treeStack.push(root);
//         while(!treeStack.isEmpty()){
//             TreeNode tempNode=treeStack.pop(); 
//             if(tempNode!=null){
//                 result.append(tempNode.val);//访问根节点
//                 treeStack.push(tempNode.right); //入栈右孩子
//                 treeStack.push(tempNode.left);//入栈左孩子
//             }
//         }
//         return result.toString();
//     }
// }