/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
/**
 * O(n), S(n)
 * 散列表
 */
// public class Solution {
//     public ListNode detectCycle(ListNode head) {
//         if(head == null || head.next == null){
//             return null;
//         }
//         Set<ListNode> nodeSet = new HashSet<>();
//         while(head != null){
//             boolean flag = nodeSet.add(head);
//             if(!flag){
//                 return head;
//             }
//             head = head.next;
//         }
//         return null;
//     }
// }

/**
 * 官解思路很好：https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/huan-xing-lian-biao-ii-by-leetcode-solution/
 * O(n), S(1)
 * 快慢指针
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null){
            if(fast.next == null){
                return null;
            }
            ListNode preFast = fast;
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                ListNode pre = head;
                while(pre != slow){
                    pre = pre.next;
                    slow = slow.next;
                }
                return pre;
            }
        }
        return null;
    }
}






