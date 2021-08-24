/**
 * 方法一：利用队列实现广度优先搜索
 * O(n), S(n)
 */
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new LinkedList<>();

        if(root == null){
            return res;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            List<Integer> levelNode = new LinkedList<>();
            int levelSize = queue.size();

            while(levelSize-- > 0){
                Node node = queue.poll();
                levelNode.add(node.val);

                for(Node child: node.children){
                    queue.offer(child); // 是否有null？经发现不需要判断null，直接添加即可
                }
            }

            res.add(levelNode);
        }

        return res;
    }
}

/**
 * 方法二：简化的广度优先搜索 (利用List代替Queue)
 * O(n), S(n)
 */
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new LinkedList<>();
        if(root == null){
            return res;
        }

        List<Node> curLayer = new LinkedList<>(); // 当前层的结点
        curLayer.add(root);

        while(!curLayer.isEmpty()){
            List<Integer> curValue = new LinkedList<>(); // 当前层的结点值
            List<Node> nextLayer = new LinkedList<>(); // 下一层即将进入的结点

            for(Node node: curLayer){
                curValue.add(node.val); // 把当前结点值加入
                nextLayer.addAll(node.children); // 把当前结点的children都加入到下一层中
            }
            curLayer = nextLayer; // 去遍历下一层

            res.add(curValue); // 把当前层的结点值加入到结果中
        }

        return res;
    }
}

/**
 * 方法三：递归
 * O(n), S(depth)，depth平均log(n), 最坏是n
 * 思想：
 * 我们可以使用递归来解决这个问题，通常我们不能使用递归进行广度优先搜索。
 * 这是因为广度优先搜索基于队列，而递归运行时使用堆栈，适合深度优先搜索。
 * 但是在本题中，我们可以以不同的顺序添加到最终列表中，只要我们知道节点在哪一层并确保在那一层的列表顺序正确就可以了。
 */
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();

        if(root == null){
            return res;
        }

        dfs(root, 0, res);

        return res;
    }

    private void dfs(Node root, int depth, List<List<Integer>> res){
        if(depth >= res.size()){
            res.add(new LinkedList<>());
        }
        res.get(depth).add(root.val); // 已知结点在哪一层，因此将结点值加入到这一层的结果中

        for(Node node: root.children){
            dfs(node, depth + 1, res);
        }
    }
}