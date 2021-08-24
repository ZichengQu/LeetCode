/**
 * 滑动窗口
 * O(n)
 */
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        int lenS = s.length();
        int lenP = p.length();

        if(lenS < lenP){
            return res;
        }

        int[] target = new int[26]; // 因为只有26个小写字母，所以可以建数组，否则可以建map，其key是字符，value是次数，因为p中可能有重复字符
        int[] slideWindow = new int[26]; // 滑动窗口
        
        for(int i = 0; i < lenP; i++){ // 将p全部放入数组中，同时把对应部分的s也放入
            target[p.charAt(i) - 'a']++;
            slideWindow[s.charAt(i) - 'a']++;
        }
        
        if(isSame(target, slideWindow)){ // Arrays.equals(target, slideWindow)
            res.add(0); // 如果相等，则下标0满足题意
        }

        for(int i = lenP; i < lenS; i++){ // 因为之前的元素已经添加过了
            slideWindow[s.charAt(i) - 'a']++; // 因此滑动窗口向后移动一位
            slideWindow[s.charAt(i - lenP) - 'a']--; // 同时删除滑动窗口最前面失效的那个
            if(isSame(target, slideWindow)){
                res.add(i - lenP + 1); // 如果满足，则添加滑动窗口的第一个下标
            }
        }
        
        return res;
    }

    private boolean isSame(int[] target, int[] slideWindow){
        int len = target.length;
        for(int i = 0; i < len; i++){
            if(target[i] != slideWindow[i]){
                return false;
            }
        }
        return true;
    }
}