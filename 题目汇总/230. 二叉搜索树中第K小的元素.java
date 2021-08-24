/**
  * DFS，中序遍历
  */
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, k, list); // 中序遍历二叉搜索树，结果正好是从小到大，取第k个数，则为该题答案
        return list.get(k - 1);
    }

    private void inOrder(TreeNode root, int k, List<Integer> list){
        if(root == null){
            return;
        }
        inOrder(root.left, k, list);
        list.add(root.val);
        if(k <= list.size()){ // 如果已经有了k个了，则无需继续
            return;
        }
        inOrder(root.right, k, list);
    }
}

/**
 * 迭代，中序遍历二叉搜索树
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int count = 0; // 计数当前是第几个最小值
        Deque<TreeNode> stack = new LinkedList<>();
        while(root != null || !stack.isEmpty()){
            if(root != null){
                stack.offerLast(root);
                root = root.left;
            }else{
                root = stack.pollLast();
                if(k == count + 1){ // 因为k是从1开始，本程序的count是从0开始
                    return root.val;
                }
                count++;
                root = root.right;
            }
        }
        return -1;
    }
}