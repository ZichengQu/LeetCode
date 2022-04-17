class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new LinkedList<>();

        Arrays.sort(nums);

        dfs(nums, list, new LinkedList<>(), new boolean[nums.length], 0);

        return list;
    }

    private void dfs(int[] nums, List<List<Integer>> list, List<Integer> path, boolean[] used, int depth){
        if(depth == nums.length){
            list.add(new LinkedList<>(path));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(used[i] || (i > 0 && nums[i] == nums[i - 1] && used[i - 1])){ // 后半部分是去重
                continue;
            }
            path.add(nums[i]);
            used[i] = true;

            dfs(nums, list, path, used, depth + 1);

            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        if(nums == null || nums.length == 0){
            return new LinkedList<List<Integer>>(res);
        }
        permuteDFS(nums, res, new boolean[nums.length], 0, new LinkedList<Integer>());
        return new LinkedList<List<Integer>>(res);
    }

    private void permuteDFS(int[] nums, Set<List<Integer>> res, boolean[] used, int depth, LinkedList<Integer> path){
        if(depth == nums.length){
            // res.add((LinkedList<Integer>)path.clone()); // 因为向res中添加的path是引用类型，该path一直会变，因此虽然添加的时候是对的，但是之后会被改变。
            res.add(new LinkedList(path));
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