/**
 * 其实这道题与力扣934题差不多：https://leetcode-cn.com/problems/shortest-bridge/
 */
class Solution {
    public int getDistance(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();

        breakPoint: for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == 1){
                    dfs(grid, i, j, queue); // DFS: 将第一座岛屿全部赋值为2，并将周围的 0 加入队列中。
                    break breakPoint;
                }
            }
        }
        // BFS: 然后通过周围的 0 一层一层往外找第二个岛屿。
        int distance = 0; // 结果 (变换的数量，将 0 变为 2，假设找到的位置，就和第一座岛屿连接起来了，因此将其赋值为第一座岛屿的范围)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右

        while(!queue.isEmpty()){
            distance++;
            int size = queue.size(); // 一开始是遍历所有的 0，然后从 0 逐步向外扩展
            while(size > 0){
                int[] loc = queue.poll();
                int x = loc[0];
                int y = loc[1];
                for(int[] dir: directions){
                    int rowIndex = x + dir[0];
                    int colIndex = y + dir[1];
                    if(rowIndex >= 0 && rowIndex < row && colIndex >= 0 && colIndex < col){
                        if(grid[rowIndex][colIndex] == 1){
                            return distance + 1; // (变换的数量 + 1 => 最短距离(桥数))
                        }
                        if(grid[rowIndex][colIndex] == 0){
                            queue.offer(new int[]{rowIndex, colIndex});
                            grid[rowIndex][colIndex] = 2; // 则标志为访问过
                        }
                    }
                }
                size--;
            }
        }
        
        return -1;
    }

    private void dfs(int[][] grid, int i, int j, Queue<int[]> queue){
        int row = grid.length; // 总行数和列数
        int col = grid[0].length;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右
        for(int[] dir: directions){
            int rowIndex = i + dir[0]; // 上下左右四种情况的横纵坐标
            int colIndex = j + dir[1];
            if(rowIndex >= 0 && rowIndex < row && colIndex >= 0 && colIndex < col){
                if(grid[rowIndex][colIndex] == 1){ // 如果是1，
                    grid[rowIndex][colIndex] = 2; // 则标志为访问过
                    dfs(grid, rowIndex, colIndex, queue);
                }
                if(grid[rowIndex][colIndex] == 0){ // 如果是0，
                    queue.offer(new int[]{rowIndex, colIndex}); // 则加入队列
                    grid[rowIndex][colIndex] = 2; // 则标志为访问过
                }
            }
        }
    }
}