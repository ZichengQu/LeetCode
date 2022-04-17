/**
 * O(len1 + len2), S(len1 + len2)
 * 
 */
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\."); // 转义，将字符串用 . 分隔开
        String[] v2 = version2.split("\\.");

        int index1 = 0; // 双指针
        int index2 = 0;

        while(index1 < v1.length || index2 < v2.length){
            int val1 = 0; // 默认 val 为 0
            int val2 = 0;

            if(index1 < v1.length){ // 当下标不越界时，对 val 赋值，否则默认 val 为 0
                val1 = Integer.parseInt(v1[index1]);
            }

            if(index2 < v2.length){
                val2 = Integer.parseInt(v2[index2]);
            }

            if(val1 < val2){
                return -1;
            }else if(val1 > val2){
                return 1;
            }

            index1++; // 对比下一组
            index2++;
        }

        return 0;
    }
}

/**
 * O(len1 + len2), S(1)
 */
class Solution {
    public int compareVersion(String version1, String version2) {
        int len1 = version1.length();
        int len2 = version2.length();

        int index1 = 0; // 双指针
        int index2 = 0;

        while(index1 < len1 || index2 < len2){
            int val1 = 0; // 默认 val 为 0
            int val2 = 0;

            while(index1 < len1 && version1.charAt(index1) != '.'){ // 按每个点进行分割。如果下标越界，则默认 val 为 0
                val1 = 10 * val1 + Integer.parseInt("" + version1.charAt(index1)); // 将每两个点之间的范围转换成整形
                index1++;
            }

            while(index2 < len2 && version2.charAt(index2) != '.'){
                val2 = 10 * val2 + Integer.parseInt("" + version2.charAt(index2));
                index2++;
            }

            if(val1 < val2){
                return -1;
            }else if(val1 > val2){
                return 1;
            }

            index1++; // 内层的 while 循环遇到 "." 会退出，这里自加是为了越过 "."，继续下面的判断
            index2++;
        }

        return 0;
    }
}