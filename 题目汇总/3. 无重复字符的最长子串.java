/**
 * O(n)
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int length = 0;
        int index = 0;
        Set<Character> hashSet = new HashSet<>();
        for(int i = 0; i < s.length(); i++){
            if(i > 0){
                hashSet.remove(s.charAt(i - 1)); // 将第i个字符之前的字符删除，因为第i-1个字符和index的字符重复了。
            }
            while(index < s.length() && !hashSet.contains(s.charAt(index))){
                // System.out.println(index + ", " + s.charAt(index) + ", " + length + ", " + hashSet.size());
                hashSet.add(s.charAt(index));
                index++; // 从i到index范围内的字符一定不重复
            }
            length = Math.max(length, hashSet.size()); // 目前这个set里面包含的字符个数就是不重复的数量
        }
        return length;
    }
}

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> hashMap = new HashMap<>(); // 字符，下标

        int res = 0;

        int start = 0; // 新的起始位置

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(hashMap.containsKey(ch)){
                int index = hashMap.get(ch);

                for(int j = start; j <= index; j++){
                    hashMap.remove(s.charAt(j));
                }

                hashMap.put(ch, i);

                start = index + 1;
            }else{
                hashMap.put(ch, i);
            }

            res = Math.max(res, hashMap.size());
        }

        return res;
    }
}