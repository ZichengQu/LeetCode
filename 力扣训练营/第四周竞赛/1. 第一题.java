class Solution {
    public int countIslands(int[][] grid) {
        // 初始化行数和列数
        int row = grid.length;
        int col = grid[0].length;

        int res = 0; // 岛屿的数量

        for(int i = 0; i < row; i++){ // 遍历行
            for(int j = 0; j < col; j++){ // 遍历列
                if(grid[i][j] == 1){
                    res++; // 遍历每一个元素，若为1，岛屿数量计加1. 并将所有与该岛屿相连的其它 1 也都置为0. 因为它们整体是一个岛屿。
                    dfs(grid, i, j); // 置为0，是在dfs中依次置0的。
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid, int curRow, int curCol){
        // 初始化行数和列数
        int row = grid.length;
        int col = grid[0].length;

        if(curRow < 0 || curRow >= row || curCol < 0 || curCol >= col || grid[curRow][curCol] != 1){
            return; // 返回条件，若下标非法，或该位置不为 1，则返回
        }
        // 若为 1, 则将该位置标为0，并继续对其上下左右进行dfs。
        grid[curRow][curCol] = 0;

        dfs(grid, curRow - 1, curCol); // 上
        dfs(grid, curRow + 1, curCol); // 下
        dfs(grid, curRow, curCol - 1); // 左
        dfs(grid, curRow, curCol + 1); // 右

        dfs(grid, curRow - 1, curCol - 1); // 左上
        dfs(grid, curRow - 1, curCol + 1); // 右上
        dfs(grid, curRow + 1, curCol - 1); // 左下
        dfs(grid, curRow + 1, curCol + 1); // 右下
    }
}