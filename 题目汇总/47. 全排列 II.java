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

// class Solution {
//     boolean[] vis;

//     public List<List<Integer>> permuteUnique(int[] nums) {
//         List<List<Integer>> ans = new ArrayList<List<Integer>>();
//         List<Integer> perm = new ArrayList<Integer>();
//         vis = new boolean[nums.length];
//         Arrays.sort(nums);
//         backtrack(nums, ans, 0, perm);
//         return ans;
//     }

//     public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
//         if (idx == nums.length) {
//             ans.add(new ArrayList<Integer>(perm));
//             return;
//         }
//         for (int i = 0; i < nums.length; ++i) {
//             if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
//                 continue;
//             }
//             perm.add(nums[i]);
//             vis[i] = true;
//             backtrack(nums, ans, idx + 1, perm);
//             vis[i] = false;
//             perm.remove(idx);
//         }
//     }
// }