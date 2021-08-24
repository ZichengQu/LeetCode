/**
 * 未使用官解答案，使用的是评论里的方式，这里是按自己习惯写的。原版代码在最下面。
 * 1. 先 dfs 将找到的第一座桥的值全部赋值为2，并将第一座桥旁边的 0 全部插入队列中
 * 2. 再 while 循环判断队列是否为空，循环体里会判断是否发现了第二座桥；
 */
class Solution {
    public int shortestBridge(int[][] grid) {
        int row = grid.length; // 行数和列数
        int col = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();

        breakPoint: 
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == 1){
                    dfs(grid, i, j, queue); // 1. 先 dfs，将第一座岛上所有值都该为 2
                    break breakPoint;
                }
            }
        }

        // 2. bfs 寻找下一座岛屿，遍历时将所有 0 值赋值为 2
        int count = 0; // 需要变换的次数

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右
        
        while(!queue.isEmpty()){
            count++;
            int size = queue.size(); // 一开始是遍历所有的 0，然后从 0 逐步向外扩展
            while(size-- > 0){
                int[] loc = queue.poll();
                int i = loc[0]; // 横纵坐标
                int j = loc[1];
                for(int[] dir: directions){
                    int rowIndex = i + dir[0]; // 新的横纵坐标
                    int colIndex = j + dir[1];
                    if(rowIndex >= 0 && rowIndex < row && colIndex >= 0 && colIndex < col){ // 如果未越界
                        if(grid[rowIndex][colIndex] == 1){ // 如果发现了新岛屿 (第二座岛屿)，则直接返回
                            return count;
                        }
                        if(grid[rowIndex][colIndex] == 0){ // 如果发现的还是水 (0)，
                            grid[rowIndex][colIndex] = 2; // 则继续将其变为第一岛屿的范围
                            queue.offer(new int[]{rowIndex, colIndex}); // BFS 一层一层向外扩展，直至碰到第二座岛屿
                        }
                    }
                }
            }
        }
        return -1;
    }

    private void dfs(int[][] grid, int i, int j, Queue<int[]> queue){
        int row = grid.length;
        int col = grid[0].length;

        grid[i][j] = 2; // 标记为访问过

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for(int[] dir: directions){
            int rowIndex = i + dir[0];
            int colIndex = j + dir[1];
            if(rowIndex >= 0 && rowIndex < row && colIndex >= 0 && colIndex < col){
                if(grid[rowIndex][colIndex] == 1){ // 为 1 的值就改变为 2 并且继续遍历四个方向
                    dfs(grid, rowIndex, colIndex, queue);
                }
                if(grid[rowIndex][colIndex] == 0){ // 插入所有为 0 的值的坐标到队列中
                    queue.offer(new int[]{rowIndex, colIndex});
                }
                // 为 2 的值不考虑
            }
           
        }
    }
}

/**
 * 原版代码
 */
 class Solution {
    private int[] direction = new int[]{-1,0,1,0,-1};
    private int res = 0;
    public int shortestBridge(int[][] A) {
        // 1. 先 dfs 将找到的第一座桥的值全部赋值为2，并将第一座桥旁边的 0 全部插入队列中
        // 2. 再 while 循环判断队列是否为空，循环体里会判断是否发现了第二座桥；
        Queue<int[]> queue = new LinkedList<>();
        // 先 dfs，将第一座岛上所有值都该为 2
        boolean dfsFlag = false; 
        for (int i = 0; i < A.length; i++) {
            if (dfsFlag) {
                break;
            }
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] == 1) {
                    dfs(A, queue, i, j, A.length, A[0].length);
                    dfsFlag = true;
                    break;
                }
            }
        }

        // bfs 寻找下一座岛屿，遍历时将所有 0 值赋值为 2
        while (!queue.isEmpty()) {
            res++;
            int queueSize = queue.size();
            while (queueSize-- > 0) {
                int[] root = queue.poll();
                for (int i = 0; i < direction.length-1; i++) {
                    int x1 = root[0] + direction[i];
                    int y1 = root[1] + direction[i+1];
                    if (x1 >= 0 && x1 < A.length && y1 >= 0 && y1 < A[0].length) {
                        if (A[x1][y1] == 1) {
                            return res;
                        } else if (A[x1][y1] == 2) {
                            continue;
                        }
                        A[x1][y1] = 2;
                        queue.add(new int[]{x1,y1});
                    }
                }
            } 
        }
        return res;
    }

    private void dfs(int[][] A, Queue<int[]> queue, int x, int y, int n, int m) {
        // 插入所有为 0 的值的坐标到队列中
        // 为 1 的值就改变为 2 并且继续遍历四个方向
        // 为 2 的值就直接退出
        if (x < 0 || x == n || y < 0 || y == m || A[x][y] == 2) {
            return;
        }
        if (A[x][y] == 0) {
            queue.add(new int[]{x,y});
            return;
        }
        A[x][y] = 2;
        for (int i = 0; i < direction.length-1; i++) {
            int x1 = x + direction[i];
            int y1 = y + direction[i+1];
            dfs(A, queue, x1, y1, n, m);
        }
    }
}