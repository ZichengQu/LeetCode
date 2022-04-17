/**
 * 遍历一次
 * O(n^2), S(n^2)
 */
class Solution {
    public boolean isValidSudoku(char[][] board) {
        Map<Character, Integer>[] rows = new HashMap[9]; // 9行，每行对应一个map
        Map<Character, Integer>[] cols = new HashMap[9]; // 9列
        Map<Character, Integer>[] blocks = new HashMap[9]; // 9块

        for(int i = 0; i < board.length; i++){
            rows[i] = new HashMap<>();
            cols[i] = new HashMap<>();
            blocks[i] = new HashMap<>();
        }

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                char ch = board[i][j]; // 对于每个字符
                if(ch == '.'){
                    continue; // 跳过
                }
                rows[i].put(ch, rows[i].getOrDefault(ch, 0) + 1); // 将该字符放入对应的行中
                cols[j].put(ch, cols[j].getOrDefault(ch, 0) + 1); // 将该字符放入对应的列中

                int blockIdx = (i / 3) * 3 + (j / 3); // 该位置对应的块号
                blocks[blockIdx].put(ch, blocks[blockIdx].getOrDefault(ch, 0) + 1); // 将该字符放入对应的块中

                if(rows[i].get(ch) > 1 || cols[j].get(ch) > 1 || blocks[blockIdx].get(ch) > 1){
                    return false;
                }

            }
        }

        return true;
    }
}

/**
 * 和上面逻辑几乎完全一致
 */
class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] grid = new boolean[9][9];

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                char ch = board[i][j];
                if(ch == '.'){
                    continue;
                }
                int index = ch - '1';

                if(rows[i][index]){
                    return false;
                }else if(cols[j][index]){
                    return false;
                }else if(grid[3 * (i / 3) + j / 3][index]){
                    return false;
                }

                rows[i][index] = true;
                cols[j][index] = true;
                grid[3 * (i / 3) + j / 3][index] = true;
            }
        }

        return true;
    }
}

/**
 * O(n^2), S(n)
 */
class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean flag = true;

        for(int i = 0; i < board.length; i++){
            flag = isValidRow(board, i);
            if(flag == false){
                return false;
            }
        }
        for(int j = 0; j < board[0].length; j++){
            flag = isValidCol(board, j);
            if(flag == false){
                return false;
            }
        }

        for(int i = 0; i < board.length; i += 3){
            for(int j = 0; j < board[0].length; j += 3){
                flag = isValidBlock(board, i, j);
                if(flag == false){
                    return false;
                }
            }
        }

        return true;
    }
    
    private boolean isValidRow(char[][] board, int i){
        Set<Character> hashSet = new HashSet<>();
        for(int j = 0; j < board[0].length; j++){
            if(board[i][j] == '.'){
                continue;
            }else if(hashSet.add(board[i][j]) == false){
                return false;
            }
        }
        return true;
    }
    
    private boolean isValidCol(char[][] board, int j){
        Set<Character> hashSet = new HashSet<>();
        for(int i = 0; i < board.length; i++){
            if(board[i][j] == '.'){
                continue;
            }else if(hashSet.add(board[i][j]) == false){
                return false;
            }
        }
        return true;
    }
    
    private boolean isValidBlock(char[][] board, int i, int j){
        Set<Character> hashSet = new HashSet<>();
        int minRow = (i / 3) * 3;
        int maxRow = minRow + 2;
        int minCol = (j / 3) * 3;
        int maxCol = minCol + 2;
        for(int row = minRow; row <= maxRow; row++){
            for(int col = minCol; col <= maxCol; col++){
                if(board[row][col] == '.'){
                    continue;
                }else if(hashSet.add(board[row][col]) == false){
                    return false;
                }
            }
        }
        return true;
    }
}