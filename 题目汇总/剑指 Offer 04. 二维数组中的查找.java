/**
 * 核心思想：O(n + m)
 * 从右上开始搜索，
 * 如果该数比target大，则这个数的下面一整列都比target大。因此一直压缩列。
 * 如果该数比target小，则行数加1，在上面列的压缩限制下，继续从右上，往左下排查
 * 
 * 或者将核心思想改成从左下到右上，一样可以。
 * 
 * 其它思路：
 * 对每行用二分查找 O(n * log(m))
 * 2重循环的暴力解法等 O(n * m)
 */
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int rows = matrix.length;
        if(rows == 0){
            return false;
        }
        int cols = matrix[0].length;

        int curRow = 0;
        int curCol = cols - 1; // 取右上的数
        while(curRow < rows && curCol >= 0){
            int num = matrix[curRow][curCol];
            if(num == target){
                return true;
            }else if(num > target){ // 往左缩小范围
                curCol--;
            }else{ // 往下缩小范围。因此相当于从右上，一直往左下缩小范围。
                curRow++;
            }
        }
        return false;
        
    }
}