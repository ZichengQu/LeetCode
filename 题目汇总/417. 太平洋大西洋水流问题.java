/**
 * 评论里的思路，感觉不错
 * O(n * m * n + m * m * n)，遍历第一行和最后一行中所有的列 O(n)，遍历第一列和最后一列中所有的行 O(m)，其中的每个结点 dfs 需要 O(m * n)
 * 思路：
 * 这道题是要寻找一个坐标既能够到达太平洋也能到达大西洋，
 * 但是这个过程一般不是一次深度搜索就能够完成的，所以我们从各边界开始逆流进行搜索。
 * 然后用两个二维数组进行记录，相当于进行了 44 次深度搜索
 */
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new LinkedList<>();

        int m = heights.length;
        int n = heights[0].length;

        int[][] arrivalPacific = new int[m][n]; // 太平洋的节点记录矩阵
        int[][] arrivalAtlantic = new int[m][n]; // 大西洋的节点记录矩阵

        // 1. 从上下边界开始两大洋的回流搜索，变动的是列. O(n * m * n)
        for(int j = 0; j < n; j++){ // 第一行和最后一行
            dfs(heights, arrivalPacific, 0, j); // 按逆流思考，将从靠近 Pacific (0, j) 出发的，逆流能抵达的位置，标志为1。则顺流时，该位置就一定能抵达该 (0, j)。
            dfs(heights, arrivalAtlantic, m - 1, j); // 同理
        }

        // 2. 从左右边界开始两大洋的回流搜索，变动的是行. O(m * m * n)
        for(int i = 0; i < m; i++){ // 第一列和最后一列
            dfs(heights, arrivalPacific, i, 0);
            dfs(heights, arrivalAtlantic, i, n - 1);
        }

        // 3. 输出都为1的坐标
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // 这个if语句一开始写错了，写成了 arrivalPacific[i][j] == arrivalAtlantic[i][j]，造成了结果是要么都可抵达，要么都不可抵达的集合。比正确的结果集大了。
                if(arrivalPacific[i][j] == 1 && arrivalAtlantic[i][j] == 1){ // 都等于1，意味着该结点抵达两个洋
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private void dfs(int[][] heights, int[][] matrix, int i, int j){
        int m = heights.length;
        int n = heights[0].length;

        matrix[i][j] = 1; // 代表可逆流而上 (抵达). // 标记可以从海洋流回经过的节点

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右
        for(int[] direction: directions){
            int x = i + direction[0];
            int y = j + direction[1];
            if(x >= 0 && x < m && y >= 0 && y < n){ // 坐标合法
                if(heights[x][y] >= heights[i][j] && matrix[x][y] != 1){ // 旧坐标高度不大于新坐标高度 (逆流)，并且未抵达过该坐标
                    dfs(heights, matrix, x, y);
                }
            }
        }
    }

}

 
/**
 * DFS
 * 自己的思路，但效率低下。优化方式，记忆路径 (未写代码)
 * 对每一个结点，用 DFS 找所有路径，判断该结点是否可以抵达两个洋
 * O(m * n * m * n)，每个结点遍历一次是 m * n, 同时每个结点DFS还有将所有结点再遍历一次
 */
class Solution {
    boolean isPacific = false; // 从某一结点出发，是否能抵达 Pacific
    boolean isAtlantic =false;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> res = new LinkedList<>();
        
        int m = heights.length;
        int n = heights[0].length;

        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                isPacific = false; // 初始设置当前结点抵达不了
                isAtlantic = false;

                dfs(heights, i, j, visited); // 找所有路径

                if(isPacific && isAtlantic){ // 如果都能抵达
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private void dfs(int[][] heights, int i, int j, boolean[][] visited){
        int m = heights.length;
        int n = heights[0].length;

        // visited[i][j] = true; // 标志该位置被访问过了. // 如果这里改变状态，那return的位置也要回溯

        isNearPacific(i, j); // 判断是否能抵达 Pacific
        isNearAtlantic(m, n, i, j);

        if(isPacific && isAtlantic){ // 如果能抵达 Pacific 和 Atlantic
            // visited[i][j] = false; // 这里也要回溯
            return;
        }

        visited[i][j] = true; // 标志该位置被访问过了。 // 可在return之后改变状态

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 上下左右

        for(int[] direction: directions){
            int x = i + direction[0]; // 新的坐标
            int y = j + direction[1];
            if(x >= 0 && x < m && y >= 0 && y < n){ // 合法，未越界
                if(heights[x][y] <= heights[i][j] && visited[x][y] == false){ // 新位置不能比老位置高，并且该结点未访问过
                    dfs(heights, x, y, visited);
                }
            }
        }

        visited[i][j] = false; // 回溯

    }

    private void isNearPacific(int x, int y){ // 是否抵达 Pacific
        if(x == 0 || y == 0){
            isPacific = true;
        }
    }

    private void isNearAtlantic(int m, int n, int x, int y){
        if(x == m - 1 || y == n - 1){
            isAtlantic = true;
        }
    }
}