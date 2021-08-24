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

 /**
 * 通用做法: (COMP6717)
 * 时间复杂度：O(n + m)
 * 空间复杂度：O(1)
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        // ListNode temp = res.next; // 下面所有注释的地方会引起错误，使temp指向res.next，但后续又修改了temp的指针指向.
        ListNode temp = res;

        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                // temp = l1; // 在这里并不是赋值。会将原指向res.next的temp，重新修改，指向为l1，导致其与res无任何关系.
                temp.next = l1;
                l1 = l1.next;
            }else{
                // temp = l2;
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        // 下面所有被注释掉的代码，可用倒数第二行代码代替。
        // while(l1 != null){
        //     // temp = l1;
        //     temp.next = l1;
        //     l1 = l1.next;
        //     temp = temp.next;
        // }
        // while(l2 != null){
        //     // temp = l2;
        //     temp.next = l2;
        //     l2 = l2.next;
        //     temp = temp.next;
        // }
        temp.next = l1 != null? l1: l2; // 合并后 l1 和 l2 最多只有一个还未被合并完，直接将链表末尾指向未合并完的链表即可
        return res.next;
    }
}

/**
 * 递归做法 (官解)
 * 时间复杂度：O(n + m)
 * 空间复杂度：O(n + m)
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }
}