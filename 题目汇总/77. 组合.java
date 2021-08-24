/**
 * 官解思路
 * 时间和空间复杂度：https://leetcode-cn.com/problems/combinations/solution/zu-he-by-leetcode-solution/
 */
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();

        dfsBackTrack(n, k, 0, new ArrayList<>(), res);

        return res;
    }

    private void dfsBackTrack(int n, int k, int depth, List<Integer> path, List<List<Integer>> res){
        // path 长度加上区间 (depth, n] 的长度小于 k，不可能构造出长度为 k 的 path
        if(path.size() + n - depth < k){
            return;
        }
        // 记录合法的答案
        if(k == path.size()){
            res.add(new ArrayList<>(path));
            return;
        }
        // 考虑选择当前位置
        path.add(depth + 1);
        dfsBackTrack(n, k, depth + 1, path, res);
        path.remove(path.size() - 1);
        // 考虑不选择当前位置
        dfsBackTrack(n, k, depth + 1, path, res);
    }
}

/**
 * 自己的思路
 */
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();

        boolean[] used = new boolean[n];
        dfsBackTrack(n, k, 0, used, new ArrayList<>(), res);

        return res;
    }

    private void dfsBackTrack(int n, int k, int depth, boolean[] used, List<Integer> path, List<List<Integer>> res){
        if(path.size() + n - depth < k){
            return;
        }
        if(path.size() == k){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = depth; i < n; i++){
            // if(used[i] == true){ // 可用可不用，不影响结果
            //     continue;
            // }
            // used[i] = true;
            path.add(i + 1);
            dfsBackTrack(n, k, i + 1, used, path, res); // 这里是 i + 1，一定不能是 depth + 1
            path.remove(path.size() - 1);
            // used[i] = false;
        }
    }
}

/**
 * 经典DFS思路（和自己的思路差不多）
 */
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    
    public List<List<Integer>> combine(int n, int k) {
        dfs(1, n, k);
        return res;
    }
    
    private void dfs(int u, int n, int k) {
        if (k == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = u; i <= n - k + 1; i++) {
            path.add(i);
            dfs(i + 1, n, k - 1);
            path.remove(path.size() - 1);
        }
    }
}