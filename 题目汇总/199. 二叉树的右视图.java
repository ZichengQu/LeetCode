// 官解视频讲的很好，不用看官解Code

/**
 * BFS层序遍历
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int size = queue.size(); // 记录当前队列的长度，表示这一层有多少个结点
            for(int i = 0; i < size; i++){ // 在该长度范围内，将元素出队，最后一个出队的，则是该层最右侧的结点
                TreeNode temp = queue.poll();
                if(temp.left != null){
                    queue.offer(temp.left);
                }
                if(temp.right != null){
                    queue.offer(temp.right);
                }
                if(i == size - 1){
                    res.add(temp.val); // 将最后一个出队的(即最右侧结点)添加到结果中
                }
            }
        }
        return res;
    }
}

/**
 * DFS
 * 先序遍历是 根左右，因此一直是有左结点，就优先访问左结点
 * 同理，若将先序遍历更改成 跟右左，则会一直优先访问右结点
 * 将每层第一个访问的结点 (即右节点)，添加到结果中
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        dfs(root, 0, res);
        return res;
    }
    
    private void dfs(TreeNode root, int depth, List<Integer> res){
        if(root == null){
            return;
        }else if(depth == res.size()){ // 假设根结点为第0层，则层数等于size的时候，意味着这是新的一层，将当前访问的结点加入到结果中。
            res.add(root.val);
        }
        dfs(root.right, depth + 1, res); // 根 右 左
        dfs(root.left, depth + 1, res);

    }

}