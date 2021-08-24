/**
 * O(n)
 */
class Solution {
    int res;
    int rootVal;
    public int findSecondMinimumValue(TreeNode root) {
        res = -1;
        rootVal = root.val;
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root){
        if(root == null){
            return;
        }
        if(res != -1 && root.val > res){ // 提前终止，如果res不为默认值，并且当前结点已经比res大了，则当前结点的子孙节点已经没有遍历的意义了
            return;
        }
        
        if(res == -1 && root.val != rootVal){ // 如果是res是初始值，并且有一个值和根结点不相等，则更新res值
            res = root.val;
        }else if(res != -1 && root.val > rootVal && root.val < res){ // 如果当前结点的值在rootVal和res之间，则更新
            res = root.val;
        }

        dfs(root.left);
        dfs(root.right);
    }
}

/**
 * O(n * log(n))
 */
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 堆

        dfs(root, pq);

        if(pq.size() == 0){
            return -1;
        }
        int firMin = pq.poll(); // 取最小的
        int secMin = -1; // 取第二小的
        while(!pq.isEmpty()){
            int temp = pq.poll();
            if(temp != firMin){
                secMin = temp;
                break;
            }
        }

        return secMin;
    }

    private void dfs(TreeNode root, PriorityQueue<Integer> pq){
        if(root == null){
            return;
        }
        pq.offer(root.val);
        dfs(root.left, pq);
        dfs(root.right, pq);
    }
}