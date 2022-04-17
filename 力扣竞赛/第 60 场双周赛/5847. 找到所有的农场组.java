// https://leetcode-cn.com/problems/find-all-groups-of-farmland/

/**
 * DFS方式
 */
class Solution {

	public int[][] findFarmland(int[][] land) {
		ArrayList<int[]> list = new ArrayList<>();
		for (int i = 0; i < land.length; i++) {
			for (int j = 0; j < land[0].length; j++) {
				if (land[i][j] == 1) {
					list.add(findFarmland(i, j, new int[] { i, j, 0, 0 }, land));
				}
			}
		}
		return list.toArray(new int[0][]);
        // return list.toArray(new int[list.size()][4]);
	}

	private int[] findFarmland(int i, int j, int[] result, int[][] land) {
		if (i < land.length && j < land[0].length && land[i][j] == 1) {
			land[i][j] = 0;
			result[2] = Math.max(result[2], i);
			result[3] = Math.max(result[3], j);
			findFarmland(i, j + 1, result, land);
			findFarmland(i + 1, j, result, land);
		}
		return result;
	}
}

/**
 * BFS方式
 */
class Solution {
    public int[][] findFarmland(int[][] land) {
        int row = land.length;
        int col = land[0].length;
        
        List<int[]> list = new ArrayList<>();
        // int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右
        int[][] directions = {{1, 0}, {0, 1}}; // 下右。因为用不到上左
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(land[i][j] == 1){
                    int minX = i;
                    int minY = j;
                    int maxX = i;
                    int maxY = j;
                    
                    land[i][j] = 2;
                    Queue<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[]{i, j});
                    while(!queue.isEmpty()){
                        int[] loc = queue.poll();
                        for(int[] direction: directions){
                            int x = loc[0] + direction[0];
                            int y = loc[1] + direction[1];
                            if(x >= 0 && x < row && y >= 0 && y < col && land[x][y] == 1){
                                land[x][y] = 2;
                                queue.offer(new int[]{x, y});
                                maxX = Math.max(maxX, x);
                                maxY = Math.max(maxY, y);
                            }
                        }
                    }
                    list.add(new int[]{minX, minY, maxX, maxY});
                }
            }
        }
        
        // int[][] res = new int[list.size()][4];
        // for(int i = 0; i < list.size(); i++){
        //     int[] temp = list.get(i);
        //     for(int j = 0; j < temp.length; j++){
        //         res[i][j] = temp[j];
        //     }
        // }
        // return res;

        return list.toArray(new int[0][]);

        // return list.toArray(new int[list.size()][4]);

    }
}