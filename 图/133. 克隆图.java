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
 * DFS
 */
// class Solution {
//     public Node cloneGraph(Node node) {
//         Map<Node, Node> visited = new HashMap<>();
//         Node newNode = deepCopy(node, visited); // 深拷贝
//         return newNode;
//     }
//     private Node deepCopy(Node node, Map<Node, Node> visited){
//         if(node == null){
//             return null;
//         }else if(visited.containsKey(node)){
//             return visited.get(node);
//         }

//         Node newNode = new Node(node.val, new ArrayList<Node>());

//         visited.put(node, newNode);

//         for(Node neighbour: node.neighbors){
//             newNode.neighbors.add(deepCopy(neighbour, visited));
//         }

//         return newNode;
//     }
// }

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

        Map<Node, Node> visited = new HashMap<>();
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.offer(node);
        visited.put(node, new Node(node.val, new ArrayList<Node>()));

        while(!nodeQueue.isEmpty()){
            Node tempNode = nodeQueue.poll();
            for(Node neighbour: tempNode.neighbors){
                if(visited.containsKey(neighbour)){
                    visited.get(tempNode).neighbors.add(visited.get(neighbour));
                }else{
                    visited.put(neighbour, new Node(neighbour.val, new ArrayList<Node>()));
                    nodeQueue.add(neighbour);
                    visited.get(tempNode).neighbors.add(visited.get(neighbour));
                }
            }
        }
        return visited.get(node);
    }
}