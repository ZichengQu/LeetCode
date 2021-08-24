class Solution {
    public void rotate(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len / 2; ++i) { // 上下翻转
            for (int j = 0; j < len; ++j) {
                matrix[i][j] ^= matrix[len - 1 - i][j];
                matrix[len - 1 - i][j] ^= matrix[i][j];
                matrix[i][j] ^= matrix[len - 1 - i][j];
            }
        }
        for (int i = 0; i < len; ++i) { // 对角线翻转
            for (int j = 0; j < i; ++j) {
                matrix[i][j] ^= matrix[j][i];
                matrix[j][i] ^= matrix[i][j];
                matrix[i][j] ^= matrix[j][i];
            }
        }
    }
}
// 00  02
// 01  12
// 02  22

// 10  01
// 11  11
// 12  21

// ij  -> j(len - 1 - i)
// 11  12
// 11  12