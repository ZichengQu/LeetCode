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
 * O(n), S(1) 快慢指针
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) { // 设head到环开始的位置的长度为x1，环开始的位置到相遇的位置为x2，环剩余的部分为x3。则fast = 2 * slow. x1 + x2 + x3 +
                                // x2 = 2 * (x1 + x2) => x1 = x3
                ListNode cycle = head;
                while (cycle != slow) {
                    cycle = cycle.next; // cycle负责跑开始的x1
                    slow = slow.next; // slow负责跑剩下的x3。因为 x1 = x3，所以slow和cycle相遇的位置就是环的入口。
                }
                return cycle;
            }
        }

        return null;
    }
}

/**
 * 和上个快慢指针的思路没区别
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode p = head;
        ListNode q = head;
        while (q != null && q.next != null) {
            p = p.next;
            q = q.next.next;
            if (p == q) { // 相等表示判断出一定有环了
                break;
            }

        }
        if (q == null || q.next == null) { // 若q和q.next都不为空，则一定是通过break跳出来的，则一定有环。否则，一定无环。
            return null; // 无环
        }

        p = head;
        while (p != q) { // 找环入口
            p = p.next;
            q = q.next;
        }
        return p;
    }
}