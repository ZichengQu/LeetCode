/**
 * O(2^n): 选择 或 不选择，一个n个，共2^n种组合
 * S(target): 其实是最多需要递归的层数，这里没有将答案List计算在内。
 */
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();

        dfsBackTrack(candidates, target, list, new ArrayList<>(), 0); // dfs搜索每一种可能的组合

        return list;
    }

    private void dfsBackTrack(int[] candidates, int target, List<List<Integer>> list, List<Integer> temp, int index){
        if(target == 0){ // 如果 target 正好满足了
            list.add(new ArrayList<>(temp));
            return; // 一定要记得返回，否则会有重复解
        }else if(target < 0 || index >= candidates.length){ // 如果当前剩余 target 小于 0 或深度 (下标) 越界，则返回
            return;
        }

        // 选择、不选择，看起来抽象，但其实和树的递归遍历类似，选择左子树，选择右子树，最终会遍历所有结点。这道题是选择，不选择，最终会遍历所有组合。
        dfsBackTrack(candidates, target, list, temp, index + 1); // 不选择，直接跳过当前值，index 需要加1
        
        temp.add(candidates[index]); // 选择，因此将当前值加到路径中
        dfsBackTrack(candidates, target - candidates[index], list, temp, index); // 选择，因为是可重复的，因此 index 不改变
        // 可以不使用下行，因为这次选择，下次递归里执行不选择，就等效于下行的作用了。否则需要用 Set<List<Integer>> list 去重
        // dfsBackTrack(candidates, target - candidates[index], list, temp, index + 1); // 选择，下次不选择该index了
        temp.remove(temp.size() - 1); // 回溯
    }
}