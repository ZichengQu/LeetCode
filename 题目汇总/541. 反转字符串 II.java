/**
 * 官解思路
 */
class Solution {
    public String reverseStr(String s, int k) {
        char[] sChar = s.toCharArray();

        for(int i = 0; i < sChar.length; i += 2 * k){ // 我们直接翻转每个 2k 字符块。
            // 每个块开始于 2k 的倍数，也就是 0, 2k, 4k, 6k, ...。需要注意的一件是：如果没有足够的字符，我们并不需要翻转这个块。
            int left = i;
            int right = Math.min(i + k - 1, sChar.length - 1); // 如果剩余字符少于 k 个，则将剩余字符全部反转。
            reverse(sChar, left, right); // 最后一次，如果剩余字符小于 2k 但大于或等于 k 个，则翻转前k个字符后，利用 i += 2 * k 直接跳出实现，后面的不变。
        }

        return new String(sChar);
    }

    private void reverse(char[] sChar, int left, int right){
        while(left < right){
            char temp = sChar[left];
            sChar[left] = sChar[right];
            sChar[right] = temp;
            left++;
            right--;
        }
    }
}

/**
 * 自己的思路，完全按照题意
 * O(n), S(n)
 * 没官解好，官解是有特殊技巧
 */
class Solution {
    public String reverseStr(String s, int k) {
        char[] sChar = s.toCharArray();

        int len = sChar.length;

        for(int i = 0; i < len; i++){
            if((len - i) >= 2 * k){ // 从字符串开头算起，每 2k 个字符反转前 k 个字符
                reverseRange(sChar, i, i + k - 1);
                i = i + 2 * k - 1; // i + 2 * k 是跳到下一个位置，-1是因为for循环中会有i++
            }else if(len - i < k){ // 如果剩余字符少于 k 个，则将剩余字符全部反转。
                reverseRange(sChar, i, len - 1);
                break;
            }else if((len - i) >= k && (len - i) < (2 * k)){ // 如果剩余字符小于 2k， 但大于或等于 k 个
                reverseRange(sChar, i, i + k - 1); // 反转前 k 个字符，其余字符保持原样。
                break;
            }
        }

        // StringBuilder sBuilder = new StringBuilder();
        // for(char ch: sChar){
        //     sBuilder.append(ch);
        // }

        // return sBuilder.toString();

        return new String(sChar); // 从官解学的

    }

    private void reverseRange(char[] sChar, int left, int right){
        while(left < right){
            char temp = sChar[left];
            sChar[left] = sChar[right];
            sChar[right] = temp;
            left++;
            right--;
        }
    }
}

