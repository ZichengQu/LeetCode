/**
 * 评论中的思路
 * https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/solution/javada-ding-dui-xiao-ding-dui-jie-fa-yi-dong-by-vi/
 */
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new LinkedList<>();

        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){ // 两数之和的大顶堆
            public int compare(int[] sum1, int[] sum2){
                return -(sum1[0] + sum1[1] - sum2[0] - sum2[1]);
            }
        });

        // 遍历所有可能的集合
        for(int i = 0; i < nums1.length; i++){
            for(int j = 0; j < nums2.length; j++){
                // 剪枝，如果当前的两个数之和超过了堆顶元素，由于数组已经排序，后面的元素只会更大，因此无需继续遍历
                if(pq.size() == k && nums1[i] + nums2[j] >= pq.peek()[0] + pq.peek()[1]){
                    break;
                }
                // 若比堆顶小 (堆达到容积 k 时)，则弹出堆顶元素，把当前数对加入
                if(pq.size() == k){
                    pq.poll();
                }
                pq.offer(new int[]{nums1[i], nums2[j]});
            }
        }

        // 最后将元素从最大堆中弹出，倒序插入数组即可
        for(int i = 0; i < k && pq.size() > 0; i++){
            int[] pair = pq.poll();
            res.add(0, Arrays.asList(pair[0], pair[1])); // 因为pq是大顶堆，所以第一个被poll的是最大的，因此后来每个新poll的，都应该放到当前res里的第一位
            
        }

        return res;
    }
}

/**
 * 与上一个解法的思想没有任何区别，只是将 Comparator 的排序内容替换了 (为了练习)
 */
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new LinkedList<>();

        Queue<List<Integer>> pq = new PriorityQueue<>(new Comparator<List<Integer>>(){ // 两数之和的大顶堆
            public int compare(List<Integer> sum1, List<Integer> sum2){
                return -(sum1.get(0) + sum1.get(1) - sum2.get(0) - sum2.get(1));
            }
        });

        // 遍历所有可能的集合
        for(int i = 0; i < nums1.length; i++){
            for(int j = 0; j < nums2.length; j++){
                // 剪枝，如果当前的两个数之和超过了堆顶元素，由于数组已经排序，后面的元素只会更大，因此无需继续遍历
                List<Integer> pair = pq.peek();
                if(pq.size() == k && nums1[i] + nums2[j] >= pair.get(0) + pair.get(1)){
                    break;
                }
                // 若比堆顶小 (堆达到容积 k 时)，则弹出堆顶元素，把当前数对加入
                if(pq.size() == k){
                    pq.poll();
                }
                List<Integer> newPair = new ArrayList<>();
                newPair.add(nums1[i]);
                newPair.add(nums2[j]);
                pq.offer(newPair);
            }
        }

        // 最后将元素从最大堆中弹出，倒序插入数组即可
        for(int i = 0; i < k && pq.size() > 0; i++){
            List<Integer> pair = pq.poll();
            res.add(0, Arrays.asList(pair.get(0), pair.get(1)));
        }

        return res;
    }
}
