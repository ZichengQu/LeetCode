/**
 * O(2^n): 选择 或 不选择，一个n个，共2^n种组合
 * S(target): 其实是最多需要递归的层数，这里没有将答案List计算在内。
 */
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();

        dfs(candidates, 0, 0, target, res, new LinkedList<>()); // dfs搜索每一种可能的组合

        return res;
    }

    private void dfs(int[] candidates, int depth, int sum, int target, List<List<Integer>> res, List<Integer> path){
        if(depth == candidates.length){ // 如果当前深度 (下标) 越界，则返回
            return;
        }
        if(target == sum){ // 如果当前sum等于target，则将当前组合加入到result张
            res.add(new LinkedList<>(path));
            return; // 一定要记得返回，否则会有重复解
        }
        // 选择、不选择，看起来抽象，但其实和树的递归遍历类似，选择左子树，选择右子树，最终会遍历所有结点。这道题是选择，不选择，最终会遍历所有组合。
        dfs(candidates, depth + 1, sum, target, res, path); // 不选择，直接跳过当前值，depth需要加1

        if(sum + candidates[depth] <= target){ // 如果不限制此条件，则相当于会一直选择，并且不跳过当前值，造成无线递归
            path.add(candidates[depth]); // 选择，因此将当前值加到路径中
            dfs(candidates, depth, sum + candidates[depth], target, res, path); // 选择，因为是可重复的，因此depth不改变
            path.remove(path.size() - 1); // 回溯
        }
    }
}