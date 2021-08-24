class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int poisonTime = 0;

        if(timeSeries.length == 0){
            return poisonTime;
        }
        
        for(int i = 1; i < timeSeries.length; i++){
            if(timeSeries[i] - timeSeries[i-1] >= duration){
                poisonTime += duration;
            }else{
                poisonTime += timeSeries[i] - timeSeries[i-1];
            }
        }
        poisonTime += duration; // 假设最后一次是整个duration的中毒时长，则前一次则是timeSeries中两个相邻位的差值或duration(根据具体情况判断)
        return poisonTime;
    }
}

// class Solution {
//     public int findPoisonedDuration(int[] timeSeries, int duration) {
//         int n = timeSeries.length;
//         if (n == 0) return 0;

//         int total = 0;
//         for(int i = 0; i < n - 1; ++i)
//           total += Math.min(timeSeries[i + 1] - timeSeries[i], duration);
//         return total + duration;
//     }
// }

// 作者：LeetCode
// 链接：https://leetcode-cn.com/problems/teemo-attacking/solution/ti-mo-gong-ji-by-leetcode/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。