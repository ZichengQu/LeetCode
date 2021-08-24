/**
 * 时间复杂度太高 O(n^2)，会超出时间限制
 */
// class Solution {
//     public int maxArea(int[] height) {
//         int max = 0;
//         for(int i = 0; i < height.length - 1; i++){
//             for(int j = i + 1; j < height.length; j++){
//                 int area = (j - i) * Math.min(height[i], height[j]);
//                 max = Math.max(max, area);
//             }
//         }
//         return max;
//     }
// }

/**
 * 左右边界向中间收敛，使用双指针
 * 盛水的容器，假设从两端开始 (即假设宽度最大)，比较两边的高度，谁小，谁是造成当前max值的原因，因此移动这个边，若导致这个area (max) 增大了，则更新max值。
 * O(n), S(1)
 */
class Solution {
    public int maxArea(int[] height) {
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        while(left < right){
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            
            int area = minHeight * width;

            res = Math.max(res, area);
            
            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }
        }
        return res;
    }
}


