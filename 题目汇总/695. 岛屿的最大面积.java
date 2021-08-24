/**
 * DFS，BFS
 * 每个网格最多访问一次，因此 O(#row * #col), S(#row * #col)。
 */
class Solution {
    int land;
    int water;
    int visited;
    int res;

    public int maxAreaOfIsland(int[][] grid) {
        land = 1;
        water = 0;
        visited = 2;
        res = 0;

        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return res;
        }
        int row = grid.length; // 总行数和列数
        int col = grid[0].length;

        for(int i = 0; i < row; i++){ // 每一行每一列，因为按1作为dfs或bfs的图，但可能该图是不连通的，因此要将每一行每一列都遍历一遍
            for(int j = 0; j < col; j++){
                if(grid[i][j] == land){
                    // int temp = dfs(grid, new int[]{i, j}); // dfs写法

                    // bfs写法
                    int temp = 0; // 计数器
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j}); // 将初始陆地入队
                    while(!queue.isEmpty()){
                        int[] loc = queue.poll();
                        int x = loc[0]; // 获取该陆地的横纵坐标
                        int y = loc[1];
                        if(grid[x][y] != land){ // 和 "grid[rowIndex][colIndex] = visited" 相互代替
                            continue;
                        }
                        grid[x][y] = visited; // 将其标志为访问
                        temp++; // 计数器加1
                        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右
                        for(int[] dir: directions){
                            int rowIndex = x + dir[0]; // 上下左右四种情况的横纵坐标
                            int colIndex = y + dir[1];
                            if(rowIndex >= 0 && rowIndex < row && colIndex >= 0 && colIndex < col && grid[rowIndex][colIndex] == land){
                                // grid[rowIndex][colIndex] = visited;
                                queue.offer(new int[]{rowIndex, colIndex});
                            }
                        }
                    }
                    res = Math.max(res, temp);
                }
            }
        }

        return res;
    }

    private int dfs(int[][] grid, int[] loc){
        int x = loc[0]; // 当前的行纵坐标
        int y = loc[1];

        int count = 1; // 能进来，就至少有 1 个岛屿

        // if(grid[x][y] != land){ // 后面判断了，因此这里不需要了
        //     return 0;
        // }

        grid[x][y] = visited;

        int row = grid.length; // 总行数和列数
        int col = grid[0].length;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右

        for(int[] dir: directions){
            int rowIndex = x + dir[0]; // 上下左右四种情况的横纵坐标
            int colIndex = y + dir[1];
            if(rowIndex >= 0 && rowIndex < row && colIndex >= 0 && colIndex < col && grid[rowIndex][colIndex] == land){
                count += dfs(grid, new int[]{rowIndex, colIndex});
            }
        }
        return count;
    }
}