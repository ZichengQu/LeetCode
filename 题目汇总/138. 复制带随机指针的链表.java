/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

// 第一个是自己的解法，与第二个官解的迭代思想相同，但没有官解的封装好
// 第三个是官解的回溯思想，特别好

/**
 * 自己的解法，和官解的迭代是一个思路，但没有官解的结构好
 */
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }

        Map<Node, Node> hashMap = new HashMap<>(); // 旧结点，新结点
        
        Node newHead = new Node(head.val); // 深拷贝
        Node res = new Node(0);
        res.next = newHead;

        hashMap.put(head, newHead); // 利用旧结点，可以找到新结点

        while(head != null){
            Node oriNext = head.next; // next
            if(oriNext != null){
                if(hashMap.containsKey(oriNext)){ // 如果包含旧结点，则说明新结点已经存在
                    newHead.next = hashMap.get(oriNext); // 直接返回
                }else{
                    newHead.next = new Node(oriNext.val); // 否则创建新节点
                    hashMap.put(oriNext, newHead.next); // 并同时放入map中
                }
            }

            Node random = head.random; // random同理
            if(random != null){
                if(hashMap.containsKey(random)){
                    newHead.random = hashMap.get(random);
                }else{
                    newHead.random = new Node(random.val);
                    hashMap.put(random, newHead.random);
                }
            }
            
            head = head.next; // 直接使用head并不会影响该函数外部的head，因为这个函数内部维持一个新的指针，不断的指向next的位置的地址，并没有修改原有结构。比如如果使用了 head.next = ...，则会修改原结构。
            newHead = newHead.next;
        }

        return res.next; //返回深拷贝的头结点
    }
}

/**
 * 官解：迭代
 * O(N)
 */
public class Solution {
    // Visited dictionary to hold old node reference as "key" and new node reference as the "value"
    HashMap<Node, Node> visited = new HashMap<Node, Node>();
  
    public Node getClonedNode(Node node) {
      // If the node exists then
      if (node != null) {
        // Check if the node is in the visited dictionary
        if (this.visited.containsKey(node)) {
          // If its in the visited dictionary then return the new node reference from the dictionary
          return this.visited.get(node);
        } else {
          // Otherwise create a new node, add to the dictionary and return it
          this.visited.put(node, new Node(node.val, null, null));
          return this.visited.get(node);
        }
      }
      return null;
    }
  
    public Node copyRandomList(Node head) {
  
      if (head == null) {
        return null;
      }
  
      Node oldNode = head;
  
      // Creating the new head node.
      Node newNode = new Node(oldNode.val);
      this.visited.put(oldNode, newNode);
  
      // Iterate on the linked list until all nodes are cloned.
      while (oldNode != null) {
        // Get the clones of the nodes referenced by random and next pointers.
        newNode.random = this.getClonedNode(oldNode.random);
        newNode.next = this.getClonedNode(oldNode.next);
  
        // Move one step ahead in the linked list.
        oldNode = oldNode.next;
        newNode = newNode.next;
      }
      return this.visited.get(head);
    }
  }
  

/**
 * 官解：递归，回溯
 * O(n)
 */
class Solution {
    Map<Node, Node> visited = new HashMap<>(); // 旧结点，新结点。判断是否拷贝过
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        if(visited.containsKey(head)){ // 如果包含老结点，则返回对应的新结点
            return visited.get(head);
        }
        Node newHead = new Node(head.val); // 如果不包含老结点，证明这个老结点之前未被深拷贝过，则深拷贝该老结点。
        visited.put(head, newHead); // 放入拷贝过的map里面

        newHead.next = copyRandomList(head.next); // 去拷贝老结点的next
        newHead.random = copyRandomList(head.random); // 同理

        return newHead;
    }
}