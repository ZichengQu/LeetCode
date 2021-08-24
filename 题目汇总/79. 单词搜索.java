/**
 * 按官解思路改写
 * 时间复杂度请参考官解说明：https://leetcode-cn.com/problems/word-search/solution/dan-ci-sou-suo-by-leetcode-solution/
 */
class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length; // 行数
        int n = board[0].length; // 列数
        for(int i = 0; i < m; i++){ // 每行每列作为dfs的开始
            for(int j = 0; j < n; j++){
                boolean flag = dfsBackTrack(board, word, new boolean[m][n], 0, new int[]{i, j});
                if(flag){ // 如果已经找到了，则直接返回
                    return true;
                }
            }
        }
        return false; // 遍历完了所有情况，还没找到
    }

    private boolean dfsBackTrack(char[][] board, String word, boolean[][] used, int depth, int[] loc){
        int x = loc[0]; // 当前横纵坐标
        int y = loc[1];

        if(word.charAt(depth) != board[x][y]){ // 如果此时深度的位置与当前board中的字符不等
            return false;
        }

        // if(used[x][y] == true){ // 一定要在判断 "word.length() == depth + 1" 之前，否则可能导致重复元素引起的误判。后面判断中找的是 "used[rowIndex][colIndex] == false"，因此若在后面加额外判断，则这个判断一定不起作用，因为能过来的都是未访问过的
        //     return false;
        // }

        if(word.length() == depth + 1){ // 如果已经达到最大深度了 (能运行到这里说明前面的字符一直相等，则可理解为一直相等，并且达到word的长度，则肯定是找到了)
            return true;
        }
        
        used[x][y] = true; // 标志为访问了

        int m = board.length; // 行数
        int n = board[0].length; // 列数

        // boolean result = false;
        int[][] directions = {{-1, 0}, {1, 0}, { 0, -1}, {0, 1}}; // 上下左右
        for(int[] dir: directions){ // 遍历上下左右四种情况
            int rowIndex = x + dir[0]; // 新的行 (上下左右中的一种)
            int colIndex = y + dir[1]; // 新的列 (上下左右中的一种)
            if(rowIndex >= 0 && rowIndex < m && colIndex >= 0 && colIndex < n && used[rowIndex][colIndex] == false){
                boolean flag = dfsBackTrack(board, word, used, depth + 1, new int[]{rowIndex, colIndex});
                if(flag){
                    // result = true;
                    // break;
                    return true;
                }
            }
        }
        used[x][y] = false; // 回溯
        // return result;
        return false;
    }
}

/**
 * 自己的思路，没有官解枝剪的好
 */
class Solution {
    boolean flag;
    public boolean exist(char[][] board, String word) {
        flag = false;
        int m = board.length; // 行数
        int n = board[0].length; // 列数
        for(int i = 0; i < m; i++){ // 每行每列作为dfs的开始
            for(int j = 0; j < n; j++){
                dfsBackTrack(board, word, new boolean[board.length][board[0].length], 0, new StringBuilder(), new int[]{i, j});
            }
            if(flag == true){
                break;
            }
        }
        return flag;
    }

    private void dfsBackTrack(char[][] board, String word, boolean[][] used, int depth, StringBuilder path, int[] loc){
        if(flag == true){
            return;
        }
        if(word.length() == depth){
            if(word.equals(path.toString())){
                flag = true;
            }
            return;
        }

        int m = board.length;
        int n = board[0].length;

        int x = loc[0];
        int y = loc[1];

        if(used[x][y] == true){ // 如果已经访问过，则跳过
            return;
        }

        used[x][y] = true; // 如果没有访问过，则将其标志为访问过
        path.append(board[x][y]); // 追加字符

        if(word.equals(path.toString())){ // 必须加，否则[[a]]这种case会失败
            flag = true;
            return;
        }

        if(x - 1 >= 0){ // dfs往上
            dfsBackTrack(board, word, used, depth + 1, path, new int[]{x - 1, y});
        }

        if(x + 1 < m){ // dfs往下
            dfsBackTrack(board, word, used, depth + 1, path, new int[]{x + 1, y});
        }

        if(y - 1 >= 0){ // dfs往左
            dfsBackTrack(board, word, used, depth + 1, path, new int[]{x, y - 1});
        }

        if(y + 1 < n){ // dfs往右
            dfsBackTrack(board, word, used, depth + 1, path, new int[]{x, y + 1});
        }

        path.deleteCharAt(path.length() - 1); // 回溯
        used[x][y] = false; // 回溯
    }
}