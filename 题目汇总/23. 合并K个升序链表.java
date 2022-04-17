/**
 * 自己的思路
 * 和下面堆排的官解思路类似，但远远没有其好。
 * 下面的官解的利用分治处理归并的思路也非常好。
 * O(k^2 * n)
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        List<ListNode> dummyHeads = new ArrayList<>(); // 把 lists 中所有的非空 head 放进来；其实类似于 List<int[]> 中每个 int[] 的 未排序部分的下标index

        for(int i = 0; i < lists.length; i++){
            if(lists[i] != null){
                dummyHeads.add(lists[i]);
            }
        }

        ListNode dummyHead = new ListNode(); // 伪头结点
        ListNode temp = dummyHead;

        while(dummyHeads.size() > 0){ // 只要还有链表非空，则继续。若所有链表都空了，则意味着排序完成了。
            int minValue = Integer.MAX_VALUE;
            int argMin = 0;

            for(int i = 0; i < dummyHeads.size(); i++){ // 取当前最小的值,和其下标
                ListNode tempHead = dummyHeads.get(i);
                if(tempHead.val < minValue){
                    minValue = tempHead.val;
                    argMin = i;
                }
            }

            temp.next = dummyHeads.get(argMin); // 将最小的赋给结果链表
            temp = temp.next;

            dummyHeads.set(argMin, dummyHeads.get(argMin).next); // 把该最小的结点，用其所在链表的下一个结点进行替换
            if(dummyHeads.get(argMin) == null){ // 如果发现该最小的元素后面是null，则意味着这个链表空了，则删除
                dummyHeads.remove(argMin);
            }
        }
        return dummyHead.next;
    }
}

/**
 * 归排思想
 * O(kn * log(k))
 * 官解链接：https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int left, int right){
        if(left > right){ // mergeKLists(lists, mid + 1, right) 的 mid + 1 可能导致其大于 right
            return null;
        }
        if(left == right){
            return lists[left];
        }

        int mid = (left + right) / 2;

        ListNode leftHead = mergeKLists(lists, left, mid);
        ListNode rightHead = mergeKLists(lists, mid + 1, right);

        return merge(leftHead, rightHead);
    }

    private ListNode merge(ListNode leftHead, ListNode rightHead){
        if(leftHead == null || rightHead == null){
            return leftHead == null? rightHead: leftHead;
        }

        ListNode dummyHead = new ListNode();
        ListNode temp = dummyHead;

        while(leftHead != null && rightHead != null){
            if(leftHead.val < rightHead.val){
                temp.next = leftHead;
                // temp = temp.next;
                leftHead = leftHead.next;
            }else{
                temp.next = rightHead;
                // temp = temp.next;
                rightHead = rightHead.next;
            }
            temp = temp.next;
        }

        temp.next = leftHead == null? rightHead: leftHead;
        // if(leftHead != null){
        //     temp.next = leftHead;
        // }
        // if(rightHead != null){
        //     temp.next = rightHead;
        // }

        return dummyHead.next;
    }
}

/**
 * 堆排思想
 * O(kn * log(k))，S(k)
 * 官解链接：https://leetcode-cn.com/problems/merge-k-sorted-lists/solution/he-bing-kge-pai-xu-lian-biao-by-leetcode-solutio-2/
 */
class Solution {
    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }

    PriorityQueue<Status> queue = new PriorityQueue<Status>();

    public ListNode mergeKLists(ListNode[] lists) {
        for (ListNode node: lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }
}


/**
 * 自己的堆排思想
 * O(kn * log(k))，S(kn)
 * 这个思想，不使用上面的内部类，也可以将空间复杂度优化成 S(k)
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((ListNode n1, ListNode n2) -> {return n1.val - n2.val;});

        for(int i = 0; i < lists.length; i++){
            ListNode head = lists[i];
            while(head != null){
                pq.offer(head); // 将各 Node 按其 val 大小，从小到大，放入最小堆中
                head = head.next;
            }
        }

        // while(!pq.isEmpty()){
        //     System.out.println(pq.poll().val);
        // }

        ListNode dummyHead = new ListNode();
        ListNode head = dummyHead;

        while(!pq.isEmpty()){
            head.next = pq.poll(); // 一直从最小堆中取出最小的 Node
            head = head.next;
        }

        head.next = null;

        return dummyHead.next;
    }
}