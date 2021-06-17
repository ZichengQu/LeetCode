/**
 * 纵向比较
 * 比较所有str的第0位，
 * 然后第1位，..., 
 * 直到最短的str结束
 */
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null){
            return null;
        }
        
        int minLength = Integer.MAX_VALUE;
        for(int i = 0; i < strs.length; i++){ // 获取该字符串数组中的最短的字符串的长度
            minLength = Math.min(minLength, strs[i].length());
        }

        int index = 0;
        while(index < minLength){
            for(int i = 1; i < strs.length; i++){ // 如果当前都一样，
                if(strs[i - 1].charAt(index) == strs[i].charAt(index)){ // 第0位和第1位比较，第1位和第2位比较，....，第n-1位和第n位比较
                    continue;
                }else{
                    return strs[0].substring(0, index); // 如果有某两位的字符不相等了，则直接返回之前判断相等的部分
                }
            }
            index++; // index位判断都相等之后，再次判断其下一位是否都相等
        }
        return strs[0].substring(0, index);
    }
}

/**
 * 横向比较
 * 得到第0个str和第1个str的共同的prefix，
 * 然后用这个prefix再和第2个str去获得新的prefix，...， 
 * 直到prefix长度为0，或者遍历完strs数组中所有的str
 */
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null){
            return null;
        }
        String prefix = strs[0];
        for(int i = 1; i < strs.length; i++){
            prefix = longestCommonPrefix(prefix, strs[i]); // 方法的重载，获取两个str之间最大的common prefix
            if(prefix.length() == 0){
                return "";
            }
        }
        return prefix;
    }

    private String longestCommonPrefix(String prefix, String test){
        int minLength = Math.min(prefix.length(), test.length());
        prefix = prefix.substring(0, minLength);

        for(int i = 0; i < minLength; i++){
            if(prefix.charAt(i) != test.charAt(i)){
                return prefix.substring(0, i);
            }
        }
        return prefix;
    }
}