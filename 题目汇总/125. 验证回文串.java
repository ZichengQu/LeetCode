class Solution {
    public boolean isPalindrome(String s) {
        // 过滤
        // s = s.replaceAll("[^A-Za-z0-9]", ""); // 正则会比较慢
        // 前后同时遍历
        boolean res = palindrom(s.toLowerCase());
        return res;
    }

    private boolean palindrom(String s){
        int left = 0;
        int right = s.length() - 1;
        while(left < right){
            while(left < right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }
            while(left < right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}