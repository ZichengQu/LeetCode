/**
 * O(n * log(n))
 */
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] interval1, int[] interval2){
                return interval1[0] - interval2[0]; // 按照每个interval的第一个数字进行升序排列
            }
        });

        // while(!pq.isEmpty()){ // 一定要这样检查pq是否正确，若直接打印，其结果不对，因为堆是按数组实现的，其顺序与位置不一致
        //     int[] temp = pq.poll();
        //     System.out.println(Arrays.toString(temp));
        // }

        List<int[]> merged = new ArrayList<>(); // result

        int len = intervals.length;
        for(int i = 0; i < len; i++){
            int left = intervals[i][0]; // 当前interval的左边界
            int right = intervals[i][1]; // 右

            if(merged.size() == 0 || merged.get(merged.size() - 1)[1] < left){ // list为空时，或list最后一个interval的右边界小于当前interval的左边界，意味着不重合
                merged.add(new int[]{left, right});
            }else{ // 若重合，则更新右边界。因为左边界是升序排列的，所以不用考虑
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], right);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}