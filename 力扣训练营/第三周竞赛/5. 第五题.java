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
    Deque<TreeNode> deque1; // 双端队列
    Deque<TreeNode> deque2; 
    public int getDistance(TreeNode root, int num1, int num2) {
        deque1 = new LinkedList<>();
        deque2 = new LinkedList<>();

        dfs(root, num1, deque1); // 使双端队列存储从根结点到目标结点的路径上的所有结点
        dfs(root, num2, deque2);

        TreeNode root1 = deque1.pollFirst(); // 最开始存的是根节点，因此root1和root2一定相等
        TreeNode root2 = deque2.pollFirst();

        // 找到num1和num2的最近的公共祖先结点
        while(deque1.size() > 0 && deque2.size() > 0 && deque1.peekFirst() == deque2.peekFirst()){
            root1 = deque1.pollFirst();
            root2 = deque2.pollFirst();
        }

        return deque1.size() + deque2.size(); // 最近公共结点 + 某一侧结点总数 - 1 => 边数 == deque.size
    }

    private void dfs(TreeNode root, int num, Deque<TreeNode> deque){
        if(root == null){
            return;
        }
        if(deque.peekLast() != null && deque.peekLast().val == num){
            return; // 提前终止，不需要DFS遍历全部结点
        }
        deque.offerLast(root);
        dfs(root.left, num, deque);
        dfs(root.right, num, deque);
        if(deque.peekLast() != null && deque.peekLast().val != num){ // 如果双端队列的最后一个结点不是目标结点
            deque.pollLast(); // 则回溯
        }
    }
}