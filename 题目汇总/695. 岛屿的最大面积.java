/**
 * DFS，BFS
 * 每个网格最多访问一次，因此 O(#row * #col), S(#row * #col)。
 */
class Solution {
    int land;
    int water;
    int visited;
    public int maxAreaOfIsland(int[][] grid) {
        int maxCnt = 0;

        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return maxCnt;
        }

        land = 1;
        water = 0;
        visited = 2;

        int row = grid.length; // 总行数和列数
        int col = grid[0].length;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右
        
        for(int i = 0; i < row; i++){ // 每一行每一列，因为按1作为dfs或bfs的图，但可能该图是不连通的，因此要将每一行每一列都遍历一遍
            for(int j = 0; j < col; j++){
                if(grid[i][j] == land){
                    // dfs写法
                    int cnt = dfs(grid, i, j);

                    // bfs写法
                    int cnt = 1; // 计数器。能到这里就说明该位置是岛屿，因此面积至少为 1.
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[]{i, j}); // 将初始陆地入队
                    grid[i][j] = visited; // 将其标志为访问

                    while(!queue.isEmpty()){
                        int[] loc = queue.poll(); // 获取该陆地的横纵坐标
                        for(int[] direction: directions){
                            int x = loc[0] + direction[0]; // 上下左右四种情况的横纵坐标
                            int y = loc[1] + direction[1];
                            if(x >= 0 && x < row && y >= 0 && y < col){
                                if(grid[x][y] == land){
                                    queue.offer(new int[]{x, y});
                                    grid[x][y] = visited; // 将其标志为访问
                                    cnt++; // 发现一个新的陆地
                                }
                            }
                        }
                    }
                    
                    maxCnt = Math.max(maxCnt, cnt);
                }
            }
        }


        return maxCnt;
    }

    private int dfs(int[][] grid, int i, int j){ // i 和 j  是当前"岛屿"的行纵坐标
        int cnt = 1; // 是岛屿就至少为 1

        grid[i][j] = visited; // 标志为访问过

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右

        for(int[] direction: directions){
            int x = i + direction[0]; // 新位置坐标
            int y = j + direction[1];
            if(x >= 0 && x < grid.length && y >= 0 && y < grid[0].length){
                if(grid[x][y] == land){ // 如果新位置是岛屿
                    cnt += dfs(grid, x, y);
                }
            }
        }

        return cnt;
    }
}