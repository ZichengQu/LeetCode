class Solution {
    int res;
    public int countPlans(int[] nums, int target) {
        res = 0;
        boolean[] used = new boolean[nums.length];

        dfsBackTrack(nums, 0, 0, target, used);

        return res;
    }

    private void dfsBackTrack(int[] nums, int depth, int sum, int target, boolean[] used){
        if(depth == nums.length){ // 当depth越界时
            if(sum == target){ // 若正好相当了，则记录
                res++;
            }
            return;
        }
        if(sum == target){ // 提前枝剪
            res++;
            return;
        }

        dfsBackTrack(nums, depth + 1, sum, target, used); // 不选择

        if(used[depth] == true){ // 只使用一次
            return;
        }
        used[depth] = true;
        dfsBackTrack(nums, depth + 1, sum + nums[depth], target, used); // 选择
        used[depth] = false; // 回溯
    }
}