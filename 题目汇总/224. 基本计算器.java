/**
 * O(n), S(n)
 * 官解链接：https://leetcode-cn.com/problems/basic-calculator/solution/ji-ben-ji-suan-qi-by-leetcode-solution-jvir/
 */
class Solution {
    public int calculate(String s) {
        Deque<Integer> stack = new LinkedList<>();
        stack.offerLast(1); // 括号外的符号，默认为正

        int sign = 1; // true 是正号，false 是负号
        int res = 0;

        int len = s.length();
        int i = 0;
        while(i < len){
            char ch = s.charAt(i);
            if(ch == ' '){
                i++;
            }else if(ch == '+'){
                sign = stack.peekLast(); // 如果是加号，则和括号外符号相同
                i++;
            }else if(ch == '-'){
                sign = -1 * stack.peekLast(); // 如果是建好，则和括号外符号相反
                i++;
            }else if(ch == '('){
                stack.offerLast(sign); // 把最近的符号作为该左括号的符号
                i++;
            }else if(ch == ')'){
                stack.pollLast(); // 弹出一个左括号
                i++;
            }else{
                int num = 0;
                while(i < len && s.charAt(i) <= '9' && s.charAt(i) >= '0'){
                    num = 10 * num + (s.charAt(i) - '0');
                    i++;
                }
                res = res + sign * num;
            }
        }

        return res;
    }
}