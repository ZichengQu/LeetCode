/**
 * 自己的回溯的思路
 * O(n), S(n)
 */
class Solution {
    List<String> res;

    public List<String> binaryTreePaths(TreeNode root) {
        res = new LinkedList<>();

        dfsBackTrack(root, new StringBuilder());

        return res;
    }

    private void dfsBackTrack(TreeNode root, StringBuilder path){
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null){ // 如果是叶子结点
            res.add(path.toString() + root.val);
            // path.append(root.val);
            // res.add(path.toString()); // 则把从根到该叶子结点的路径加入，需要去掉最开始的"->"
            
            // int lastIndex = path.lastIndexOf("" + root.val); // 然后删除该叶子结点在路径中的记录
            // path.delete(lastIndex, path.length());
        }else{
            // 回溯 (方式1)
            int curSize = path.length();

            path.append(root.val + "->"); // 如果不是叶子结点，则加入路径
            // DFS遍历该树
            dfsBackTrack(root.left, path);
            dfsBackTrack(root.right, path);
            
            // 回溯 (方式1)
            // path.delete(curSize, path.length()); // O(n)
            path.setLength(curSize); // O(1)

            // 回溯 (方式2)，通过该非叶子结点 (能进入到这里的都是非叶子结点) 的所有路径都DFS完了，则从路径中删除该非叶子结点
            // if(root.left != null || root.right != null){ // 其实不加这个if判断，直接用里面的内容也可以。主要是为了逻辑理解方便
            //     int lastIndex = path.lastIndexOf("->" + root.val);
            //     path.delete(lastIndex, path.length());
            // }
        }
        
    }
}

/**
 * 官解DFS思路
 * O(n^2), S(n^2)
 * 复杂度详见官解
 * https://leetcode-cn.com/problems/binary-tree-paths/solution/er-cha-shu-de-suo-you-lu-jing-by-leetcode-solution/
 */
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfsPaths(root, res, "");

        return res;
    }

    private void dfsPaths(TreeNode root, List<String> res, String path){
        if(root != null){
            StringBuilder pathBuilder = new StringBuilder(path);
            if(root.left == null && root.right == null){ // 当前节点是叶子节点
                pathBuilder.append(root.val);
                res.add(pathBuilder.toString()); // 把路径加入到答案中
            }else{
                pathBuilder.append(root.val + "->"); // 当前节点不是叶子节点，继续递归遍历
                dfsPaths(root.left, res, pathBuilder.toString());
                dfsPaths(root.right, res, pathBuilder.toString());
            }
        }
    }

}

/**
 * BFS官解思路
 * O(n^2), S(n^2)
 * 复杂度详见官解
 * https://leetcode-cn.com/problems/binary-tree-paths/solution/er-cha-shu-de-suo-you-lu-jing-by-leetcode-solution/
 */
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();

        if(root == null){
            return res;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Map<TreeNode, String> nodeToPath = new HashMap<>(); // node, path

        nodeQueue.offer(root);
        nodeToPath.put(root, String.valueOf(root.val)); // 这里和官解稍有不同，官解用了另一个队列，同offer，同poll，保证顺序一致

        while(!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.poll();
            String path = nodeToPath.get(node);
            if(node.left == null && node.right == null){
                res.add(path);
            }else{
                if(node.left != null){
                    nodeQueue.offer(node.left);
                    nodeToPath.put(node.left, path + "->" + node.left.val);
                }
                if(node.right != null){
                    nodeQueue.offer(node.right);
                    nodeToPath.put(node.right, path + "->" + node.right.val);
                }
            }
        }

        return res;
    }
}