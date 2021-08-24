/**
  * 快慢指针，相当于扫描一遍。
  * 需要找到倒数第 n 个节点。
  * 因此我们可以使用两个指针 fast 和 slow 同时对链表进行遍历，
  * 并且 fast 比 slow 超前 n 个节点，并停留在比 slow 快 n + 1的结点的位置。
  * 当 fast 遍历到链表的末尾时，slow 就恰好处于倒数第 n 个节点的前一个结点。
  */
  class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        for(int i = 0; i < n + 1; i++){ // 因为fast走到末尾，为null的时候，slow应停在倒数第n-1个，因此fast应提前先走n+1次。比如 1 -> 2 -> 3 -> 4 -> 5 -> null。假设n为2，若fast为null时，slow应为3 (待删除结点的前一个结点)，则fast应先走n+1次
            fast = fast.next;
        }

        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }
}

/**
 * 两趟扫描 (自己的没有官解好)
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = head;
        int size = 0;
        while(temp != null){ // 得到该list长度
            size++;
            temp = temp.next;
        }
        if(size <= 1){ // 如果小于1，那删除这个结点之后，一定为空。
            return null;
        }

        int index = size - n; // 被删除结点的位置，从0开始
        if(index == 0){ // 如果删除的是第一个结点
            head = head.next;
            return head;
        }

        temp = head; // 如果删除的是其他结点
        for(int i = 0; i < index - 1; i++){ // index - 1 是为了找到被删除结点的前一个结点
            temp = temp.next;
        }
        temp.next = temp.next.next; // 删除待删除结点

        return head;
    }
}

/**
 * 两趟扫面的官解(按自己习惯稍有改编)
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head); // 伪头节点
        int size = getSize(head); // 获取list长度
        ListNode pre = dummy;
        for(int i = 1; i < size - n + 1; i++){ // 找到待删除结点的前一个结点
            pre = pre.next;
        }
        pre.next = pre.next.next; // 删除待删除结点

        return dummy.next; // 返回真正的头节点
    }

    private int getSize(ListNode head){
        int size = 0;
        while(head != null){
            size++;
            head = head.next;
        }
        return size;
    }
}

/**
 * 使用栈
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Deque<ListNode> stack = new LinkedList<>();
        ListNode dummy = new ListNode(0, head);
        ListNode temp = dummy;
        while(temp != null){ // 将node全部压栈
            stack.offerLast(temp);
            temp = temp.next;
        }

        ListNode pre = dummy;
        int count = 0;
        while(count <= n){ // 弹栈，一直弹到待删除结点的前一个结点
            pre = stack.pollLast();
            // System.out.println(pre.val);
            count++;
        }
        // System.out.println(pre.val);
        pre.next = pre.next.next;

        return dummy.next;
    }
}