class BSTIterator {
    List<Integer> list;

    public BSTIterator(TreeNode root) {
        list = new LinkedList<>();
        // inOrderRecursion(root, list); // 递归中序遍历
        inOrderIteration(root, list); // 迭代中序遍历
    }
    
    public int next() {
        // int res = list.get(0);
        // list.remove(0);
        // return res;
        return list.remove(0);
    }
    
    public boolean hasNext() {
        return !list.isEmpty();
    }

    private void inOrderRecursion(TreeNode root, List<Integer> list){ // DFS写法，中序递归遍历
        if(root == null){
            return;
        }
        inOrderRecursion(root.left, list);
        list.add(root.val);
        inOrderRecursion(root.right, list);
    }

    private void inOrderIteration(TreeNode root, List<Integer> list){ // BFS写法，中序迭代遍历
        Deque<TreeNode> stack = new LinkedList<>();

        while(root != null || !stack.isEmpty()){
            if(root != null){
                stack.offerLast(root);
                root = root.left;
            }else{
                root = stack.pollLast();
                list.add(root.val);
                root = root.right;
            }
        }
    }
}