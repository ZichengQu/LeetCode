class Solution {
    public int getDistance(String[] maze) {
        char visited = 'V'; // 表示访问过了

        int distance = 0; // 结果

        int row = maze.length; // 行
        int col = maze[0].length(); // 列

        char[][] mazeChar = new char[row][col];
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                mazeChar[i][j] = maze[i].charAt(j);
                if(mazeChar[i][j] == 'S'){
                    queue.offer(new int[]{i, j}); // 将入口 S 入队
                    mazeChar[i][j] = visited; // 将其标志为访问过
                }
            }
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右

        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                int[] loc = queue.poll();
                int x = loc[0];
                int y = loc[1];
                
                for(int[] dir: directions){ // 上下左右
                    int rowIndex = x + dir[0];
                    int colIndex = y + dir[1];
                    if(rowIndex >= 0 && rowIndex < row && colIndex >= 0 && colIndex < col && (mazeChar[rowIndex][colIndex] == '.' || mazeChar[rowIndex][colIndex] == 'E')){
                        queue.offer(new int[]{rowIndex, colIndex});
                        if(mazeChar[rowIndex][colIndex] == 'E'){
                            return distance + 1;
                        }
                        mazeChar[rowIndex][colIndex] = visited; // 入队就代表访问过
                    }
                }
                size--;
            }
            distance++;

        }

        return -1;
    }
}