class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int carry = 0;
        while(l1 != null || l2 != null){
            int val_1 = l1 != null? l1.val: 0;
            int val_2 = l2 != null? l2.val: 0;
            int val = val_1 + val_2 + carry;
            if(val >= 10){
                val -= 10;
                carry = 1;
            }else{
                carry = 0;
            }
            if(head == null){
                head = tail = new ListNode(val);
            }else{
                tail.next = new ListNode(val);
                tail = tail.next;
            }
            l1 = l1 == null? null: l1.next;
            l2 = l2 == null? null: l2.next;
        }
        if(carry == 1){
            tail.next = new ListNode(carry);
        }
        return head;
    }
}