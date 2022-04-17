/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

/**
 * DFS 思路 1
 */
class Solution {
    public Node cloneGraph(Node node) {
        Map<Node, Node> visited = new HashMap<>();
        Node newNode = deepCopy(node, visited); // 深拷贝
        return newNode;
    }
    private Node deepCopy(Node node, Map<Node, Node> visited){
        if(node == null){
            return null;
        }else if(visited.containsKey(node)){
            return visited.get(node);
        }

        Node newNode = new Node(node.val);

        visited.put(node, newNode);

        for(Node neighbour: node.neighbors){
            newNode.neighbors.add(deepCopy(neighbour, visited));
        }

        return newNode;
    }
}

/**
 * DFS 思路 2
 */
class Solution {
    public Node cloneGraph(Node node) {
        return deepCopy(node, new HashMap<Node, Node>());
    }

    private Node deepCopy(Node node, Map<Node, Node> hashMap){
        if(node == null){
            return null;
        }

        Node head = new Node(node.val);
        hashMap.put(node, head);

        for(Node temp: node.neighbors){
            if(hashMap.containsKey(temp)){
                head.neighbors.add(hashMap.get(temp));
            }else{
                head.neighbors.add(deepCopy(temp, hashMap));

            }
        }

        return head;
    }
}

//         A
//     /       \
// B               D
//     \
//         C

/**
 * BFS
 */
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null){
            return null;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);

        Node head = new Node(node.val);
        Map<Node, Node> hashMap = new HashMap<>(); // ori, cur，方便通过原结点，找到对应的现在新的结点
        hashMap.put(node, head);

        while(!queue.isEmpty()){
            Node oriNode = queue.poll(); // 原结点
            Node curNode = hashMap.get(oriNode); // 现在新的结点
            for(Node temp: oriNode.neighbors){
                if(hashMap.containsKey(temp)){ // 如果之前扩展过该原结点，其实是判断是否 visited 的意思
                    curNode.neighbors.add(hashMap.get(temp));
                }else{ // 如果之前没扩展过该原结点
                    Node newNode = new Node(temp.val);
                    hashMap.put(temp, newNode);
                    queue.offer(temp);
                    curNode.neighbors.add(hashMap.get(temp)); // 为了方便理解，因此没有将共同部分提取出来
                }
            }
        }

        return hashMap.get(node);
    }
}