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
//     public boolean hasCycle(ListNode head) {
//         if(head == null || head.next == null){
//             return false;
//         }
//         Set<ListNode> nodeSet = new HashSet<>();
//         while(head != null){
//             boolean flag = nodeSet.add(head);
//             if(!flag){
//                 return true;
//             }
//             head = head.next;
//         }
//         return false;
//     }
// }

 /**
  * O(n), S(1)
  * 快慢指针
  */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast){
            if(slow == null || fast == null || fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}

public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next; // 其实fast初始时设为head或head.next都无所谓，next更好一些，但只要fast快，早晚会追上（如果有环）.
        while(fast != null){
            if(fast.next == null){
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }
}