/**
 * BFS和DFS的时间复杂度都是 O(grid的行数 * 列数)，因为每个坐标只遍历一次
 */
class Solution {
    int land = 0;
    int water = 1; // 没用到
    int visited = 2;

    boolean marginFlag = false;

    public int closedIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int count = 0;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                marginFlag = false;
                if(grid[i][j] == land){ // 如果此时是没被访问过的陆地
                    // dfs(grid, i, j); // dfs思路

                    // bfs思路
                    Queue<int[]> location = new LinkedList<>();
                    location.offer(new int[]{i, j}); // 将该陆地的坐标入队
                    grid[i][j] = visited; // 将该陆地坐标设为已访问过

                    while(!location.isEmpty()){
                        int[] loc = location.poll(); // 将该陆地的坐标出队
                        int x = loc[0];
                        int y = loc[1];

                        if(!marginFlag){ // 如果之前不是边缘，则判断目前是否是边缘。如果已经处于边缘，则无需再次判断
                            isMargin(grid, x, y);
                        }

                        for(int[] direction: directions){ // 上下左右
                            int newX = x + direction[0];
                            int newY = y + direction[1];

                            if(newX >= 0 && newX < row && newY >= 0 && newY < col && grid[newX][newY] == land){ // 如果坐标没越界，并且是land
                                location.offer(new int[]{newX, newY});
                                grid[newX][newY] = visited; // 将该陆地坐标设为已访问过
                            }
                        }
                    }

                    if(!marginFlag){ // 如果该陆地没有处于边缘，相当于其四周全部被水域包围
                        count++; // 则计数器加一
                        // System.out.println(i + ", " + j); // 封闭岛屿的坐标
                    }
                }
            }
        }

        return count;
    }

    private void dfs(int[][] grid, int i, int j){
        if(!marginFlag){ // 如果之前不是边缘，则判断目前是否是边缘。如果已经处于边缘，则无需再次判断
            isMargin(grid, i, j);
        }

        grid[i][j] = visited; // 将该陆地坐标设为已访问过

        int row = grid.length;
        int col = grid[0].length;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for(int[] direction: directions){ // 上下左右
            int x = i + direction[0];
            int y = j + direction[1];
            if(x >= 0 && x < row && y >= 0 && y < col){ // 如果坐标没越界
                if(grid[x][y] == land){ // 如果是land
                    dfs(grid, x, y);
                }
            }
        }
    }

    private void isMargin(int[][] grid, int i, int j){
        int row = grid.length;
        int col = grid[0].length;

        if(i == 0 || i == row - 1 || j == 0 || j == col - 1){
            marginFlag = true;
        }

    }
}