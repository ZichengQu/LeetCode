/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
// class Solution {
//     public ListNode reverseList(ListNode head) {
//         // ListNode prev = head; // 会报错，因为
//         // ListNode curr = head.next;
//         ListNode prev = null;
//         ListNode curr = head;
//         while(curr != null){
//             ListNode next = curr.next;
//             curr.next = prev; // prev初始不是null时，这里会产生环
//             prev = curr;
//             curr = next;
//         }
//         return prev;
//     }
// }

// 两种方法讲解：
// https://leetcode-cn.com/problems/reverse-linked-list/solution/shi-pin-jiang-jie-die-dai-he-di-gui-hen-hswxy/

/**
 * 迭代方法一
 * O(n), S(1)
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        // ListNode prev = head; // 会报错，因为
        // ListNode curr = head.next;
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev; // prev初始不是null时，这里会产生环
            prev = curr;
            curr = next;
        }
        return prev;
    }
}

/**
 * 迭代方法二
 * O(n), S(n)
 */
public class Solution {
    public ListNode ReverseList(ListNode head) {
        Deque<ListNode> stack = new LinkedList<>();
        
        while(head != null){
            stack.offerLast(head);
            head = head.next;
        }
        
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead;
        
        while(!stack.isEmpty()){
            ListNode node = stack.pollLast();
            temp.next = node;
            temp = node;
        }
        temp.next = null; // 否则会因为翻转后的最后一个 (正数第一个) node而产生有环
        
        return dummyHead.next;
    }
}

/**
 * 递归
 * O(n), S(n)
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head; // 0个或1个结点时，不需要反转
        }
        ListNode lastNode = reverseList(head.next); // 根据递归终止条件，只会return 最后一个结点，并且这个结点，会在递归中一直传递.
        head.next.next = head; // 当前结点的下一个结点指向当前结点
        head.next = null; // 将当前结点的下一个设为null
        return lastNode;
    }
}

