/**
 * 自己的思路
 * O(n * log(n))
 */
class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();

        Arrays.sort(intervals, (int[] arr1, int[] arr2)->{
            return arr1[0] == arr2[0]? arr1[1] - arr2[1]: arr1[0] - arr2[0]; // 先按start升序，若start相等则按end升序
        });

        for(int[] interval: intervals){
            if(list.isEmpty()){ // 如果当前为空，则直接填入
                list.add(interval);
            }else{
                int[] arr = list.get(list.size() - 1);
                int preStart = arr[0]; // 前一个的开始
                int preEnd = arr[1]; // 前一个的结束
                int curStart = interval[0]; // 当前的开始
                int curEnd = interval[1]; // 当前的结束

                if(preStart <= curStart && preEnd >= curEnd){ // 当前完全被前一个包括 (preStart, curStart, curEnd, preEnd)
                    continue;
                }else if(curStart <= preEnd && preEnd <= curEnd){ // 当前和前一个的范围有重合 (preStart, curStart, preEnd, curEnd)
                    list.set(list.size() - 1, new int[]{preStart, curEnd});
                }else{ // 当前完全在前一个的后面 (preStart, preEnd, curStart, curEnd)
                    list.add(interval);
                }
            }
        }

        int[][] res = list.toArray(new int[list.size()][2]);

        return res;
    }
}

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