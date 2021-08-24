/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

 /**
 * 官解证明很清晰，O(n), S(1)
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/xiang-jiao-lian-biao-by-leetcode-solutio-a8jn/
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while(pA != pB){
            pA = pA == null? headB: pA.next;
            pB = pB == null? headA: pB.next;
        }
        return pA;
    }
}

 /**
  * 用哈希判断是否重复过
  * O(n + m), S(n)
  */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> intersect = new HashSet<>();
        ListNode temp = headA; // 官解部分，为了不改变传入的headA与headB的指针指向 (避免影响函数外的部分与逻辑)。
        while(temp != null){
            intersect.add(temp);
            temp = temp.next;
        }
        temp = headB; // 官解部分，为了不改变传入的headA与headB的指针指向 (避免影响函数外的部分与逻辑)。
        while(temp != null){
            if(intersect.contains(temp)){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
}

/**
 * 和方法一思路一致
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode dummyHeadA = headA;
        ListNode dummyHeadB = headB;
        while(dummyHeadA != null && dummyHeadB != null){
            if(dummyHeadA == dummyHeadB){
                return dummyHeadA;
            }
            dummyHeadA = dummyHeadA.next;
            dummyHeadB = dummyHeadB.next;
        }
        if(dummyHeadA == null){
            dummyHeadA = headB;
            while(dummyHeadB != null){
                dummyHeadA = dummyHeadA.next;
                dummyHeadB = dummyHeadB.next;
            }
            dummyHeadB = headA;
        }else if(dummyHeadB == null){
            dummyHeadB = headA;
            while(dummyHeadA != null){
                dummyHeadA = dummyHeadA.next;
                dummyHeadB = dummyHeadB.next;
            }
            dummyHeadA = headB;
        }

        while(dummyHeadA != null && dummyHeadB != null){
            if(dummyHeadA == dummyHeadB){
                return dummyHeadA;
            }
            dummyHeadA = dummyHeadA.next;
            dummyHeadB = dummyHeadB.next;
        }

        return null;
    }
}


