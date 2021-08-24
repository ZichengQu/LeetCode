/**
 * 回溯
 * O(n!), S(n)
 * 由于每个皇后必须位于不同列，因此已经放置的皇后所在的列不能放置别的皇后。
 * 第一个皇后有 N 列可以选择，第二个皇后最多有 N−1 列可以选择，第三个皇后最多有 N−2 列可以选择（如果考虑到不能在同一条斜线上，可能的选择数量更少），
 * 因此所有可能的情况不会超过 N! 种，遍历这些情况的时间复杂度是 O(N!)。
 * 为了降低总时间复杂度，每次放置皇后时需要快速判断每个位置是否可以放置皇后，显然，最理想的情况是在 O(1) 的时间内判断该位置所在的列和两条斜线上是否已经有皇后。
 */
class Solution {
    Set<Integer> col = new HashSet<>(); // 列
    Set<Integer> xPlusY = new HashSet<>(); // x + y，右上左下的对角线
    Set<Integer> xMinusY = new HashSet<>(); // x - y，左上右下的对角线

    List<List<String>> res = new LinkedList<>();

    public List<List<String>> solveNQueens(int n) {
        List<String> seq = new LinkedList<>();
        
        backTrack(seq, 0, n); // 0 代表从第 0 行开始

        return res;
    }

    private void backTrack(List<String> seq, int x, int n){
        if(x == n){ // 因为x是从0开始，因此当x等于n时，其实就超出了边界
            // res.add(seq); // 因为seq是引用类型，因此哪怕当前存的是对的，但之后seq会被不断的改变
            res.add(new LinkedList<>(seq)); // 相当于深拷贝
            return;
        }
        for(int y = 0; y < n; y++){ // 遍历当前第x行的第y列
            if(col.contains(y) || xPlusY.contains(x + y) || xMinusY.contains(x - y)){ // 如果冲突
                continue;
            }
            // 如果不冲突
            StringBuilder seqString = new StringBuilder();
            for(int i = 0; i < n; i++){
                if(i == y){
                    seqString.append("Q");
                }else{
                    seqString.append(".");
                }
            }
            seq.add(seqString.toString()); // 为当前第x行和第y列的皇后构造 ".Q.."

            // 添加冲突项
            col.add(y);
            xPlusY.add(x + y);
            xMinusY.add(x - y);
            // 找下一行
            x++;

            backTrack(seq, x, n);

            // 回溯到上一个状态，最好按上面的顺序，这里逆序
            x--; // 这行若不在最先，会发生错误，因为后面用到了更新的x值，x必须是回溯之后的。
            xMinusY.remove(x - y);
            xPlusY.remove(x + y);
            col.remove(y);
            seq.remove(seq.size() - 1);
        }
    }
}