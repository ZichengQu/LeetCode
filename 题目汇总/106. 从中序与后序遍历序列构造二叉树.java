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
  * 递归方式
  * O(n) : n个结点
  * S(n) : HashMap, 以空间换时间
  * 官解视频非常好：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/cong-qian-xu-yu-zhong-xu-bian-li-xu-lie-gou-zao-9/
  */
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int inLen = inorder.length;
        int postLen = postorder.length;

        Map<Integer, Integer> inorderMap = new HashMap<>(); // num, index
        for(int i = 0; i < inLen; i++){
            inorderMap.put(inorder[i], i);
        }

        return buildTree(postorder, 0, postLen - 1, inorderMap, 0, inLen - 1);
    }

    private TreeNode buildTree(int[] postorder, int postLeft, int postRight, Map<Integer, Integer> inorderMap, int inLeft, int inRight){
        if(postLeft > postRight || inLeft > inRight){
            return null;
        }

        int rootVal = postorder[postRight];
        TreeNode root = new TreeNode(rootVal);

        int pIndex = inorderMap.get(rootVal);

        // ? - postLeft = pIndex - 1 - inLeft
        // ? = pIndex - 1 - inLeft + postLeft
        // root.left = buildTree(postorder, postLeft, ?, inorderMap, inLeft, pIndex - 1);
        // root.right = buildTree(postorder, ? + 1, postRight - 1, inorderMap, pIndex + 1, inRight);
        root.left = buildTree(postorder, postLeft, pIndex - 1 - inLeft + postLeft, inorderMap, inLeft, pIndex - 1);
        root.right = buildTree(postorder, pIndex - 1 - inLeft + postLeft + 1, postRight - 1, inorderMap, pIndex + 1, inRight);
        
        return root;
    }
}

/**
 * 迭代方式
 * 思路很好，详见官解说明，否则只看代码，理解较难
 * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solution/cong-zhong-xu-yu-hou-xu-bian-li-xu-lie-gou-zao-14/
 */