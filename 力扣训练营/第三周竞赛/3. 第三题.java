/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    PriorityQueue<Integer> pq;
    public int[] solve(TreeNode root, int[][] operations) {
        // 最大堆
        pq = new PriorityQueue(new Comparator<Integer>(){
            @Override
            public int compare(Integer val1, Integer val2){
                return -(val1.intValue() - val2.intValue());
            }
        });

        dfs(root, pq); // O(n * log(n))

        int len = operations.length;
        int[] ans = new int[len];

        for(int i = 0; i < len; i++){
            int[] operation = operations[i];
            if(operation[0] == 0){
                // 将当前值按顺序加入最大堆里
                pq.offer(operation[1]);
            }else{ // 删除最大元素
                pq.poll();
            }
            ans[i] = pq.peek(); // 获取最大元素
        }

        return ans;
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