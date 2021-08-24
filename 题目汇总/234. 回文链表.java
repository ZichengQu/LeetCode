/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     ListNode val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(ListNode val) { this.val = val; }
 *     ListNode(ListNode val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

 /**
  * O(n), S(n)
  */
// class Solution {
//     public boolean isPalindrome(ListNode head) {
//         List<ListNodeeger> arrayList = new ArrayList<>();
//         while(head != null){
//             arrayList.add(head.val);
//             head = head.next;
//         }
//         ListNode size = arrayList.size();
//         for(ListNode i = 0; i < size / 2; i++){
//             if(arrayList.get(i) != arrayList.get(size - 1 - i)){
//                 return false;
//             }
//         }
//         return true;
//     }
// }

 /**
  * O(n), S(1)
  */
  class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null){
            return true;
        }
        // 找到前半部分的最后一个结点
        ListNode firstHalfEnd = findFirstHalfEnd(head);

        // 反转链表，并返回新的头结点(原链表的尾结点)。利用"前半个部分的最后一个结点的next作为后半部分的开头"
        ListNode secondHalfStart = reverseSecondHalf(firstHalfEnd.next); 

        // 判断是否回文
        boolean flag = isPalindrome(head, secondHalfStart); 

        // 利用后半部分的"新的头节点(原链表的尾结点)"，再次反转该链表，并返回原后半部分的头节点，作为前半部分的尾结点的next.
        // 其实可以不还原，但是函数封装原则尽量不改变传入参数的值和结构(在不要求的情况下)
        firstHalfEnd.next = reverseSecondHalf(secondHalfStart); 
        return flag;
    }

    private ListNode findFirstHalfEnd(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode reverseSecondHalf(ListNode head){
        ListNode prev = null;
        ListNode curr = head;
        while(curr != null){
            ListNode tempNext = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tempNext;
        }
        return prev;
    }

    private boolean isPalindrome(ListNode head, ListNode tail){
        ListNode tempHead = head; // 设置新变量，防止改变原有指针指向，影响函数体外的结构
        ListNode tempTail = tail;
        while(tempTail != null){
            if(tempHead.val != tempTail.val){
                return false;
            }
            tempHead = tempHead.next;
            tempTail = tempTail.next;
        }
        return true;
    }
}