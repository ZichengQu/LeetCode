/**
 * 通用思路 (若用来判断最大堆或最小堆问题，仍然同理可求)
 * O(n), S(n)
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long lower, long upper){
        if(root == null){
            return true;
        }
        if(root.val <= lower || root.val >= upper){
            return false;
        }

        return isValidBST(root.left, lower, root.val) && isValidBST(root.right, root.val, upper);
    }
}

/**
 * 迭代，中序遍历
 * O(n), S(n)
 */
class Solution {
    public boolean isValidBST(TreeNode root) {

        Deque<TreeNode> stack = new LinkedList<>();

        long pre = Long.MIN_VALUE;

        while(root != null || !stack.isEmpty()){
            if(root != null){
                stack.offerLast(root);
                root = root.left;
            }else{
                root = stack.pollLast();
                
                // 此题的重点位置，其余位置均为标准中序迭代流程
                int cur = root.val;
                if(cur <= pre){
                    return false;
                }
                pre = cur;

                root = root.right;
            }
        }

        return true;
    }
}

/**
 * DFS中序遍历
 * O(n), S(n)
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        inorderDFS(root, list);

        int size = list.size();
        for(int i = 0; i < size - 1; i++){
            if(list.get(i) >= list.get(i + 1)){
                return false;
            }
        }

        return true;
    }

    private void inorderDFS(TreeNode root, List<Integer> list){
        if(root == null){
            return;
        }
        inorderDFS(root.left, list);
        list.add(root.val);
        inorderDFS(root.right, list);
    }
}

/**
 * 典型的错误思路，只考虑父结点，未考虑再上层
 * 比如 [5,4,6,null,null,3,7]
 * 判断 6 的左儿子 3 时，仍满足下面代码
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return true;
        }

        if(root.left != null && root.val <= root.left.val){
            return false;
        }
        if(root.right != null && root.val >= root.right.val){
            return false;
        }

        boolean flag1 = true;
        boolean flag2 = true;
        
        if(root.left != null){
            flag1 = isValidBST(root.left);
        }
        if(root.right != null){
            flag2 = isValidBST(root.right);
        }

        return flag1 && flag2;
    }
}