/**
 * 官解思路
 * O(row * col)
 */
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int[][] res = new int[row][col];
        for(int[] sub: res){
            Arrays.fill(sub, -1); // 将初始状态都设为-1，代表未访问过
        }

        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(mat[i][j] == 0){ // 将所有 dist 应该为 0 的项先更新结果，并添加到队列中
                    res[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while(!queue.isEmpty()){ // bfs从所有 dist = 0 的开始遍历
            int[] loc = queue.poll();
            int x = loc[0];
            int y = loc[1];
            for(int[] dir: directions){
                int rowIndex = x + dir[0];
                int colIndex = y + dir[1];
                // 当一个新的点未被访问时，更新其值。因为是bfs，所以第一次访问某个点时，该点一定是距离原点（任意为0的点）最近的一个状态。
                if(rowIndex >= 0 && rowIndex < row && colIndex >= 0 && colIndex < col && res[rowIndex][colIndex] == -1){
                    res[rowIndex][colIndex] = res[x][y] + 1;
                    queue.offer(new int[]{rowIndex, colIndex});
                }
            }
        }

        return res;
    }
}

/**
 * 标准思路，但会超时
 */
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int[][] res = new int[row][col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(mat[i][j] == 0){
                    res[i][j] = 0; // 如果原数组为0，则新距离也是0
                }else{ // 对于每一个非0，bfs找它最近的0
                    int count = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    boolean flag = false;
                    pointA: while(!queue.isEmpty()){
                        int size = queue.size();
                        while(size > 0){ // 按层去找，每层结束之后，count++
                            int[] loc = queue.poll();
                            int x = loc[0];
                            int y = loc[1];

                            if(mat[x][y] == 0){
                                res[i][j] = count;
                                flag = true;
                                break pointA;
                            }

                            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                            for(int[] dir: directions){
                                int rowIndex = x + dir[0];
                                int colIndex = y + dir[1];
                                if(rowIndex >= 0 && rowIndex < row && colIndex >= 0 && colIndex < col){
                                    queue.offer(new int[]{rowIndex, colIndex});
                                }
                            }
                            size--;
                        }
                        count++;
                    }
                }
            }
        }

        return res;
    }
}