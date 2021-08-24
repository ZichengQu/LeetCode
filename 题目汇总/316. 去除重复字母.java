// 官方题解的视频
// https://leetcode-cn.com/problems/remove-duplicate-letters/solution/qu-chu-zhong-fu-zi-mu-by-leetcode-soluti-vuso/

/**
 * 单调栈 + 贪心
 * 其实也可以将栈直接换成StringBuilder，一直操作最后一个元素
 */
class Solution {
    public String removeDuplicateLetters(String s) {
        if(s == null || s.length() == 0){
            return null;
        }

        int[] lastIndex = new int[26]; // 每个字符最后一次出现的位置
        boolean[] inStack = new boolean[26]; // 每个字符是否在栈中，可以用HashSet代替

        for(int i = 0; i < s.length(); i++){
            lastIndex[s.charAt(i) - 'a'] = i; // 减去偏移量，使其对应数组下标
        }

        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(inStack[ch - 'a']){
                continue;
            }
            while(!stack.isEmpty() && stack.peek() > ch && lastIndex[stack.peek() - 'a'] > i){ // 当栈顶字符大于当前字符，并且栈顶字符并不是最后一次出现时，则说明该字典序可以继续减小
                inStack[stack.pop() - 'a'] = false; // pop()弹出并返回栈顶的字符，减去偏移量，将该字符标记为不在栈中
            }
            stack.push(ch);
            inStack[ch - 'a'] = true;
        }

        StringBuilder stringBuilder = new StringBuilder();
        while(!stack.isEmpty()){
            stringBuilder.append(stack.pop());
        }

        return stringBuilder.reverse().toString();
    }
}

/**
 * 单调栈 + 贪心
 * 直接用StringBuilder代替栈，一直操作最后一个字符元素
 */
class Solution {
    public String removeDuplicateLetters(String s) {
        if(s == null || s.length() == 0){
            return null;
        }

        int[] lastIndex = new int[26]; // 每个字符最后一次出现的位置
        boolean[] inStack = new boolean[26]; // 每个字符是否在栈中

        for(int i = 0; i < s.length(); i++){
            lastIndex[s.charAt(i) - 'a'] = i; // 减去偏移量，使其对应数组下标
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(inStack[ch - 'a']){
                continue;
            }
            int length = stringBuilder.length();
            while(length > 0 && stringBuilder.charAt(length - 1) > ch && lastIndex[stringBuilder.charAt(length - 1) - 'a'] > i){ // 当栈顶字符大于当前字符，并且栈顶字符并不是最后一次出现时，则说明该字典序可以继续减小
                inStack[stringBuilder.charAt(length - 1) - 'a'] = false; // pop()弹出并返回栈顶的字符，减去偏移量，将该字符标记为不在栈中
                stringBuilder.deleteCharAt(length - 1);
                length = stringBuilder.length();
            }
            stringBuilder.append(ch);
            inStack[ch - 'a'] = true;
        }

        return stringBuilder.toString();
    }
}