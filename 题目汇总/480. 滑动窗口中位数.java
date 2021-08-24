/**
 * O(n * log(n)), S(n)
 * 官解思路很好
 * 官解链接：https://leetcode-cn.com/problems/sliding-window-median/solution/hua-dong-chuang-kou-zhong-wei-shu-by-lee-7ai6/
 */
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        DualHeap dHeap = new DualHeap(k);
        double[] res = new double[nums.length - k + 1];

        for(int i = 0; i < k; i++){
            dHeap.insert(nums[i]);
        }
        res[0] = dHeap.getMedian();

        for(int i = k; i < nums.length; i++){
            dHeap.insert(nums[i]);
            dHeap.erase(nums[i - k]);
            res[i - k + 1] = dHeap.getMedian();
        }

        return res;
    }
}

class DualHeap {
    private PriorityQueue<Integer> small; // 大顶堆，维护较小的一半元素
    private PriorityQueue<Integer> large; // 小顶堆，维护较大的一半元素
    private Map<Integer, Integer> delayed; // 哈希表，记录「延迟删除」的元素，key 为元素，value 为需要删除的次数
    private int k; // 滑动窗口的个数
    private int smallSize; // small 和 large 当前包含的元素个数，需要扣除被「延迟删除」的元素
    private int largeSize;

    public DualHeap(int k){
        this.k = k;
        smallSize = 0;
        largeSize = 0;
        // 从大到小
        small = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer num1, Integer num2){
                return num2.compareTo(num1);
            }
        });
        // 从小到大，其实不需要实现Comparator接口，直接使用默认的就是从小到大的
        large = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer num1, Integer num2){
                return num1.compareTo(num2);
            }
        });
        delayed = new HashMap<>();
    }

    public void insert(int num){
        if(small.isEmpty() || num <= small.peek()){
            small.offer(num);
            smallSize++;
        }else{
            large.offer(num);
            largeSize++;
        }
        makeBalance();
    }

    public void erase(int num){
        delayed.put(num, delayed.getOrDefault(num, 0) + 1);
        if(num <= small.peek()){
            smallSize--;
            if(num == small.peek()){
                prune(small);
            }
        }else{
            largeSize--;
            if(num == large.peek()){
                prune(large);
            }
        }
        makeBalance();
    }

    public double getMedian(){
        return (k & 1) == 1? small.peek(): ((double)small.peek() + large.peek()) / 2;
    }

    // 不断地弹出 heap 的堆顶元素，并且更新哈希表
    private void prune(PriorityQueue<Integer> pq){
        while(!pq.isEmpty()){
            int num = pq.peek();
            if(delayed.containsKey(num)){
                pq.poll();
                delayed.put(num, delayed.get(num) - 1);
                if(delayed.get(num) == 0){
                    delayed.remove(num);
                }
            }else{
                break;
            }
        }
    }

    // 调整 small 和 large 中的元素个数，使得二者的元素个数满足要求
    private void makeBalance(){
        if(smallSize > largeSize + 1){ // small 比 large 元素多 2 个
            large.offer(small.poll());
            smallSize--;
            largeSize++;
            prune(small); // small 堆顶元素被移除，需要进行 prune。删除不需要考虑其size，因为size已经单独考虑了。因此删除后，不影响small或large的smallSize和largeSize，因此一定是平衡的
        }else if(smallSize < largeSize){ // large 比 small 元素多 1 个
            small.offer(large.poll());
            smallSize++;
            largeSize--;
            prune(large); // large 堆顶元素被移除，需要进行 prune
        }
    }
}