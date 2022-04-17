//https://leetcode-cn.com/problems/the-number-of-weak-characters-in-the-game/

/**
 * 此次竞赛第一名的思路
 * O(n * log(n))
 */
class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        // 优先对第一个元素降序排列，当第一个元素相等时，对第二个元素升序排列
        Arrays.sort(properties, (arr1, arr2) -> arr1[0] == arr2[0]? arr1[1] - arr2[1]: arr2[0] - arr1[0]);
        // Arrays.sort(properties, (init[] arr1, int[] arr2) -> {
        //     return arr1[0] == arr2[0]? arr1[1] - arr2[1]: arr2[0] - arr1[0];
        // });

        // for(int[] temp: properties){
        //     System.out.println(Arrays.toString(temp));
        // }

        int cnt = 0;
        int max = 0;
        for(int[] arr: properties){
            cnt += max > arr[1]? 1: 0; // 因为arr[0]是降序的，arr[1]是升序的。所以若之前的max大于当前的arr[1]了，则之前max对应的arr[0]一定大于当前的arr[0]
            max = Math.max(max, arr[1]);
        }
        return cnt;

    }
}

/**
 * 自己的超时思路
 * O(n^2)
 */
class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        // 按第一个元素排序
        Arrays.sort(properties, new Comparator<int[]>(){
           public int compare(int[] arr1, int[] arr2){
               return arr1[0] - arr2[0];
           } 
        });
        
        int cnt = 0;
        
        int len = properties.length;
        
        boolean[] status = new boolean[len];
        
        
        for(int i = len - 1; i >= 0; i--){
            int[] post = properties[i];
            if(status[i]){
                continue;
            }
            for(int j = i - 1; j >= 0; j--){
                if(status[j]){
                    continue;
                }
                int[] pre = properties[j];
                if(post[0] > pre[0] && post[1] > pre[1]){
                    status[j] = true;
                    cnt++;
                }
            }
        }
        
        return cnt;
        
    }
}