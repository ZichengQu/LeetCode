/**
 * 正确思路，按最短桥的思路，从三个国家往外扩展，3个bfs
 * O(row * col)
 */

/**
 * 方法超时
 * O(row^2 * col^2)
 */
class Solution {
    public int miniumDistance(int[][] grid) {
        int minDistance = Integer.MAX_VALUE;

        int row = grid.length;
        int col = grid[0].length;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(grid[i][j] == 0){
                    int distance = 1;
                    int curDistanceA = Integer.MAX_VALUE; // 当前公共领土到三个国家的 max(min(distance))
                    int curDistanceB = Integer.MAX_VALUE; 
                    int curDistanceC = Integer.MAX_VALUE; 

                    boolean[][] visited = new boolean[row][col]; // 判断是否访问过

                    Queue<int[]> queue = new LinkedList<>();

                    queue.offer(new int[]{i, j});

                    visited[i][j] = true;

                    pointA: while(!queue.isEmpty()){
                        int size = queue.size();
                        while(size-- > 0){
                            int[] loc = queue.poll();
                            int x = loc[0];
                            int y = loc[1];
                            for(int[] direction: directions){
                                int new_x = x + direction[0];
                                int new_y = y + direction[1];
                                if(new_x >= 0 && new_x < row && new_y >= 0 && new_y < col){
                                    if(visited[new_x][new_y] == false){
                                        queue.offer(new int[]{new_x, new_y});
                                        visited[new_x][new_y] = true;

                                        if(grid[new_x][new_y] == 1 && curDistanceA == Integer.MAX_VALUE){
                                            curDistanceA = distance;
                                        }else if(grid[new_x][new_y] == 2 && curDistanceB == Integer.MAX_VALUE){
                                            curDistanceB = distance;
                                        }else if(grid[new_x][new_y] == 3 && curDistanceC == Integer.MAX_VALUE){
                                            curDistanceC = distance;
                                        }
                                        if(curDistanceA != Integer.MAX_VALUE && curDistanceB != Integer.MAX_VALUE && curDistanceC != Integer.MAX_VALUE){
                                        break pointA; 
                                        }
                                    }
                                }
                            }

                        }
                        distance++;
                    }
                    
                    distance = Math.max(Math.max(curDistanceA, curDistanceB), curDistanceC); // 最大距离
                    minDistance = Math.min(minDistance, distance); // 最小
                }
            }
        }

        return minDistance;
    }
}