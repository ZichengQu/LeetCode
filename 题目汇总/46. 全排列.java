class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if(nums == null || nums.length == 0){
            return res;
        }
        permuteDFS(nums, res, new boolean[nums.length], 0, new LinkedList<Integer>());
        return res;
    }

    private void permuteDFS(int[] nums, List<List<Integer>> res, boolean[] used, int depth, LinkedList<Integer> path){
        if(depth == nums.length){
            // res.add((LinkedList<Integer>)path.clone()); // 因为向res中添加的path是引用类型，该path一直会变，因此虽然添加的时候是对的，但是之后会被改变。
            res.add(new LinkedList(path));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(used[i]){
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            permuteDFS(nums, res, used, depth + 1, path);
            path.pollLast();
            used[i] = false;
        }
    }
}