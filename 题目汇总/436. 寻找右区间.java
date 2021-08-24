/**
 * 使用TreeMap
 * O(n * log(n))
 */
class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int len = intervals.length;
        int[] res = new int[len]; // 返回值

        if(len == 1){
            res[0] = -1;
            return res; // 边界条件
        }

        TreeMap<Integer, Integer> treeMap = new TreeMap<>(); // intervals[i][0], 和其下标 i
        for(int i = 0; i < len; i++){
            treeMap.put(intervals[i][0], i);
        }

        for(int i = 0; i < len; i++){
            int index = -1;
            if(treeMap.containsKey(intervals[i][1])){ // 如果正好有相等的
                index = treeMap.get(intervals[i][1]);
            }else if(treeMap.higherEntry(intervals[i][1]) != null){ // 如果有比当前 intervals[i][1] 大的，则返回所有大的中，最小的一项的下标
                index = treeMap.higherEntry(intervals[i][1]).getValue(); // getValue为了获取其下标
            }
            res[i] = index;
        }

        return res;
    }
}


/**
 * 使用Map、排序、二分查找
 * O(n * log(n))
 */
class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int len = intervals.length;
        int[] res = new int[len]; // 返回值

        if(len == 1){
            res[0] = -1;
            return res; // 边界条件
        }

        int[] startElements = new int[len]; // 存储每个子数组的第0个元素
        Map<Integer, Integer> indexMap = new HashMap<>(); // startElement, index。记录每个子数组的在intervals这个二维数组中的下标
        for(int i = 0; i < len; i++){
            startElements[i] = intervals[i][0];
            indexMap.put(startElements[i], i);
        }

        Arrays.sort(startElements); //升序

        for(int i = 0; i < len; i++){
            int endElement = intervals[i][1]; // 每个下标为1的元素
            int index = binarySearch(endElement, startElements, indexMap); // 二分查找，找到大于end的最小的start的下标
            res[i] = index;
        }

        return res;
    }

    private int binarySearch(int endElement, int[] startElements, Map<Integer, Integer> indexMap){
        int left = 0;
        int right = startElements.length - 1;

        boolean flag = false; // 先假设没值比end大
        while(left <= right){
            int mid = (left + right) / 2;
            if(startElements[mid] == endElement){
                return indexMap.get(startElements[mid]);
            }else if(startElements[mid] < endElement){
                left = mid + 1;
            }else{
                right = mid - 1;
                flag = true; // 发现有值比end大了，则改为true
            }
        }
        if(!flag){ // 如果没有值比end大
            return -1;
        }
        return indexMap.get(startElements[right+1]); // 如果有值比end大，则返回最小的比end大的那个
    }
}