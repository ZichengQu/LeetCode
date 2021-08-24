/**
 * 广度优先搜索
 * O(M * N)，S(min(M,N)), 其中 M 和 N 分别为行数和列数。
 */
class Solution {
    public int numIslands(char[][] grid) {
        // 初始化行数和列数
        int row = grid.length;
        int col = grid[0].length;

        int res = 0; // 岛屿的数量

        for(int i = 0; i < row; i++){ // 遍历行
            for(int j = 0; j < col; j++){ // 遍历列
                if(grid[i][j] == '1'){ 
                    res++; // 遍历每一个元素，若为1，岛屿数量计加1. 并将所有与该岛屿相连的其它 '1'也都置为0. 因为它们整体是一个岛屿。
                    grid[i][j] = '0'; // 为了下次不再次遍历这个岛屿，将其置为0

                    Queue<int[]> queue = new LinkedList<>(); // 存下标 (curRow, curCol)

                    queue.offer(new int[]{i, j});

                    while(!queue.isEmpty()){ // 开始广度优先搜索
                        int[] cur = queue.poll(); // 从队列中取出一个元素
                        int curRow = cur[0]; // 当前元素的current row
                        int curCol = cur[1]; // 当前元素的current column
                        if(curRow - 1 >= 0 && grid[curRow - 1][curCol] == '1'){ // 上
                            grid[curRow - 1][curCol] = '0'; // 一定要第一时间就将其标志为访问过，否则因为是BFS，此时发现该结点没访问过，入队，后面还有可能发现该结点是未访问过的状态，会再次入队。虽然不会造成死循环，但可能会重复很多很多次。
                            queue.offer(new int[]{curRow - 1, curCol}); // 若为1，则置为0，并入队
                        }
                        if(curRow + 1 < row && grid[curRow + 1][curCol] == '1'){ // 下
                            grid[curRow + 1][curCol] = '0';
                            queue.offer(new int[]{curRow + 1, curCol});
                        }
                        if(curCol - 1 >= 0 && grid[curRow][curCol - 1] == '1'){ // 左
                            grid[curRow][curCol - 1] = '0';
                            queue.offer(new int[]{curRow, curCol - 1});
                        }
                        if(curCol + 1 < col && grid[curRow][curCol + 1] == '1'){ // 右
                            grid[curRow][curCol + 1] = '0';
                            queue.offer(new int[]{curRow, curCol + 1});
                        }
                    }
                }
            }
        }

        return res;
    }

}

/**
 * 深度优先搜索
 * O(M * N)，S(M * N), 其中 M 和 N 分别为行数和列数。
 */
class Solution {
    public int numIslands(char[][] grid) {
        // 初始化行数和列数
        int row = grid.length;
        int col = grid[0].length;

        int res = 0; // 岛屿的数量

        for(int i = 0; i < row; i++){ // 遍历行
            for(int j = 0; j < col; j++){ // 遍历列
                if(grid[i][j] == '1'){
                    res++; // 遍历每一个元素，若为1，岛屿数量计加1. 并将所有与该岛屿相连的其它 '1'也都置为0. 因为它们整体是一个岛屿。
                    dfs(grid, i, j); // 置为0，是在dfs中依次置0的。
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int curRow, int curCol){
        // 初始化行数和列数
        int row = grid.length;
        int col = grid[0].length;

        if(curRow < 0 || curRow >= row || curCol < 0 || curCol >= col || grid[curRow][curCol] != '1'){
            return; // 返回条件，若下标非法，或该位置不为'1'，则返回
        }
        // 若为'1', 则将该位置标为0，并继续对其上下左右进行dfs。
        grid[curRow][curCol] = '0';

        dfs(grid, curRow - 1, curCol); // 上
        dfs(grid, curRow + 1, curCol); // 下
        dfs(grid, curRow, curCol - 1); // 左
        dfs(grid, curRow, curCol + 1); // 右
    }

}