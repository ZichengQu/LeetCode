class Solution {
    boolean[][] rows;
    boolean[][] cols;
    boolean[][] grid;
    List<int[]> positions;

    boolean flag;

    public void solveSudoku(char[][] board) {
        rows = new boolean[9][9];
        cols = new boolean[9][9];
        grid = new boolean[9][9];
        positions = new ArrayList<>();

        flag = false;

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                char ch = board[i][j];
                if(ch == '.'){
                    positions.add(new int[]{i, j}); // 把带填充的位置记录下来
                }else{
                    int index = ch - '1';
                    rows[i][index] = true; // 原有的数字，将其所在行的该数字设为已使用
                    cols[j][index] = true; // 原有的数字，将其所在列的该数字设为已使用
                    int gridIdx = (i / 3) * 3 + (j / 3); // 块号
                    grid[gridIdx][index] = true; // 原有的数字，将其所在块的该数字设为已使用
                }
            }
        }

        dfs(board, 0);
    }

    private void dfs(char[][] board, int index){
        if(index == positions.size()){
            flag = true; // 找到答案了
            return;
        }

        int[] pos = positions.get(index); // 带填充的位置记录
        int x = pos[0];
        int y = pos[1];

        for(int num = 1; num <= 9; num++){
            if(flag){ // 如果已经找到了，就不继续了，否则在递归中，回溯会还原状态，使已使用的误认为未使用，从而产生错误结果。
                return;
            }
            if(rows[x][num - 1] || cols[y][num - 1] || grid[3 * (x / 3) + y / 3][num - 1]){ // 任意行、列、块的num使用过
                continue; // 则跳过
            }

            board[x][y] = (char)('0' + num);

            rows[x][num - 1] = cols[y][num - 1] = grid[3 * (x / 3) + y / 3][num - 1] = true; // 标志为已使用
            dfs(board, index + 1);
            grid[3 * (x / 3) + y / 3][num - 1] = cols[y][num - 1] = rows[x][num - 1] = false; // 回溯
        }

    }
}