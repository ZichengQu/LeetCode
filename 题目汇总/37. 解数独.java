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

// 下面是 C 语言的解法
bool dfs(char** board, int boardSize, int* boardColSize, int loc);

void solveSudoku(char** board, int boardSize, int* boardColSize) {
    dfs(board, boardSize, boardColSize, 0);
}

bool isRowRepeat(char** board, int* boardColSize, int row, int col, char value) {
    for (int i = 0; i < boardColSize[row]; i++) {
        if (i == col)
            continue;
        if (board[row][i] == value)
            return true;
    }

    return false;
}

bool isColRepeat(char** board, int boardSize, int row, int col, char value) {
    for (int i = 0; i < boardSize; i++) {
        if (i == row)
            continue;
        if (board[i][col] == value)
            return true;
    }

    return false;
}

bool isUnitRepeat(char** board, int boardSize,
                  int row, int col, char value) {
    int unitSize = sqrt(boardSize);

    int startRow = (row / unitSize) * unitSize;
    int startCol = (col / unitSize) * unitSize;

    for (int i = startRow; i < startRow + unitSize; i++) {
        for (int j = startCol; j < startCol + unitSize; j++) {
            if (i == row && j == col)
                continue;

            if (board[i][j] == value)
                return true;
        }
    }

    return false;
}

bool isValid(char** board, int boardSize, int* boardColSize, int row, int col, char value) {
    if (isRowRepeat(board, boardColSize, row, col, value)) {
        // printf("isRowRepeat board[%d][%d] = [%c]\n", row, col, value);
        return false;
    }
    if (isColRepeat(board, boardSize, row, col, value)) {
        // printf("isColRepeat board[%d][%d] = [%c]\n", row, col, value);
        return false;
    }
    if (isUnitRepeat(board, boardSize, row, col, value)) {
        // printf("isUnitRepeat board[%d][%d] = [%c]\n", row, col, value);
        return false;
    }
    return true;
}

bool dfs(char** board, int boardSize, int* boardColSize, int loc) {
    int colSize = boardColSize[0];

    if (loc == boardSize * colSize)
        return true;

    int row = loc / colSize;
    int col = loc % colSize;

    if (board[row][col] != '.')
        return dfs(board, boardSize, boardColSize, loc + 1);

    for (char num = '1'; num <= '9'; num++) {
        if (!isValid(board, boardSize, boardColSize, row, col, num))
            continue;

        board[row][col] = num;

        if (dfs(board, boardSize, boardColSize, loc + 1))
            return true;

        board[row][col] = '.';
    }

    return false;
}

// C 语言的第二种思路（其实也很类似）
bool dfs(char** board, int boardSize, int* boardColSize);

void solveSudoku(char** board, int boardSize, int* boardColSize) {
    dfs(board, boardSize, boardColSize);
}

bool isRowRepeat(char** board, int* boardColSize, int row, int col, char value) {
    for (int i = 0; i < boardColSize[row]; i++) {
        if (i == col)
            continue;
        if (board[row][i] == value)
            return true;
    }

    return false;
}

bool isColRepeat(char** board, int boardSize, int row, int col, char value) {
    for (int i = 0; i < boardSize; i++) {
        if (i == row)
            continue;
        if (board[i][col] == value)
            return true;
    }

    return false;
}

bool isUnitRepeat(char** board, int row, int col, char value) {
    int x = row % 3;
    x = row - x;
    int y = col % 3;
    y = col - y;

    for (int i = x; i < x + 3; i++) {
        for (int j = y; j < y + 3; j++) {
            if (i == row && j == col)
                continue;
            if (board[i][j] == value)
                return true;
        }
    }

    return false;
}

bool isValid(char** board, int boardSize, int* boardColSize, int row, int col, char value) {
    if (isRowRepeat(board, boardColSize, row, col, value)) {
        // printf("isRowRepeat board[%d][%d] = [%c]\n", row, col, value);
        return false;
    }
    if (isColRepeat(board, boardSize, row, col, value)) {
        // printf("isColRepeat board[%d][%d] = [%c]\n", row, col, value);
        return false;
    }
    if (isUnitRepeat(board, row, col, value)) {
        // printf("isUnitRepeat board[%d][%d] = [%c]\n", row, col, value);
        return false;
    }
    return true;
}

bool dfs(char** board, int boardSize, int* boardColSize) {
    for (int i = 0; i < boardSize; i++) {
        for (int j = 0; j < boardColSize[i]; j++) {
            if (board[i][j] != '.')
                continue;
            for (char k = '1'; k <= '9'; k++) {
                if (!isValid(board, boardSize, boardColSize, i, j, k))
                    continue;
                board[i][j] = k;
                if (dfs(board, boardSize, boardColSize))
                    return true;
                board[i][j] = '.';
            }
            return false;
        }
    }
    return true;
}
