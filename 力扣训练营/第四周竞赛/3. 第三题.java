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
    public int[][] solve(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>(); // 结果 list(list())，最后需要转成 int[][]

        Queue<TreeNode> queue = new LinkedList<>(); // 队列，用于层序遍历

        queue.offer(root);

        while(!queue.isEmpty()){
            List<Integer> subList = new ArrayList<>();

            int size = queue.size(); // 当前层的结点数
            while(size > 0){
                TreeNode node = queue.poll();
                subList.add(node.val);

                if(node.right != null){
                    queue.offer(node.right);
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
                size--;
            }

            list.add(subList); // 将当前层的所有结点加入
            
        }

        int[][] res = new int[list.size()][]; // 结果数组

        int size = list.size();
        for(int i = 0; i < size; i++){ // 将 list(list()) 中的值，复制到 int[][] 中
            List<Integer> subList = list.get(i);
            int subSize = subList.size();
            res[i] = new int[subSize];
            for(int j = 0; j < subSize; j++){
                res[i][j] = subList.get(j);
            }
        }

        return res;
    }
}