class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        // 创建哑结点，用于将在 head 前插入结点转换为在哑结点后插入，统一处理，更方便
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        ListNode lastSorted = head; // 记录已排序完成的结点末尾
        ListNode curNode = lastSorted.next; // 当前需要新插入的结点
        
        while(curNode != null){
            if(lastSorted.val <= curNode.val){
                // 新插入的值正好是最大值，直接插入链表末尾
                lastSorted = lastSorted.next;
                // curNode = curNode.next; // 在if和else中，这句话是一样的，则放到if-else后面
            }else{
                ListNode pre = dummyHead; // 从头开始寻找插入位置
                while(pre.next.val <= curNode.val){
                    pre = pre.next;
                }

                /**
                 * 将新结点插入链表
                 * 虽然目前找到了curNode要插入的位置。
                 * 同时还需要为下一步更新lastSorted和curNode。不能只更新curNode。
                 * 否则curNode会移到lastSorted前面，并且lastSorted因为未及时更新，仍会指向curNode。因此产生环。
                 */
                // ListNode temp = curNode.next; 
                lastSorted.next = curNode.next;
                curNode.next = pre.next;
                pre.next = curNode;
                // // curNode = temp; // 只更新curNode，不更新lastSorted，会产生环。
                // curNode = lastSorted.next; // 在if和else中，这句话是一样的，则放到if-else后面
            }
            curNode = lastSorted.next; // 更新新结点
        }
        return dummyHead.next;
    }
}