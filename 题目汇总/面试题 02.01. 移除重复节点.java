/**
 * O(n), S(n)
 */
class Solution {
    public ListNode removeDuplicateNodes(ListNode head) {
        if(head == null){
            return null;
        }

        Set<Integer> occurred  = new HashSet<>();
        ListNode dummyHead = head;
        occurred.add(head.val);

        while(head != null && head.next != null){
            if(occurred.contains(head.next.val)){ // 如果出现过
                head.next = head.next.next; // 则删除
            }else{ // 如果没出现过
                occurred.add(head.next.val);
                head = head.next; // 则继续向下遍历
            }
        }
        head.next = null; // 不加也不会报错，但最好加上，这是好习惯，否则在有些题中可能产生环

        return dummyHead;
    }
}

/**
 * O(n^2), S(1)
 */
class Solution {
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode dummyHead = head;

        while(head != null){
            ListNode pre = head;
            while(pre != null && pre.next != null){
                ListNode cur = pre.next;
                if(cur.val == head.val){
                    pre.next = cur.next;
                }else{
                    pre = pre.next;
                }
            }
            head = head.next;
        }

        return dummyHead;
    }
}