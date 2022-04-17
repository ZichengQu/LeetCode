/**
 * 高频误区：
 * 假如有两次遍历: 
 * 第一次遍历：从前往后遍历确定')'，排除类似'*))*'的情况;
 * 第二次遍历：从后往前遍历确定'('，排除类似'*((*'的情况;
 * 其余的都符合
 */

/**
 * 贪心
 * O(n), S(1)
 */
class Solution {
    public boolean checkValidString(String s) {
        int len = s.length();

        int min = 0; // 左括号个数的最小值
        int max = 0; // 左括号个数的最大值

        for(int i = 0; i < len; i++){
            char ch = s.charAt(i);
            if(ch == '('){ // 遇到左括号，则最小值和最大值都增加
                min++;
                max++;
            }else if(ch == ')'){ // 遇到右括号，则最小值和最大值都减小
                min = Math.max(min - 1, 0); // 最小值最低是 0，不能比 0 再小了
                max--;
                if(max < 0){ // 如果最大值也是小于 0 了，说明 * 也补不齐了
                    return false;
                }
            }else{
                min = Math.max(min - 1, 0); // 遇到 *，左括号个数的最小值减小，最大值增加
                max++;
            }
        }

        return min == 0;
    }
}

/**
 * 栈
 * O(n), S(n)
 */
class Solution {
    public boolean checkValidString(String s) {
        Deque<Integer> left = new ArrayDeque<>(); // 左括号栈
        Deque<Integer> star = new ArrayDeque<>(); // 星号栈

        int len = s.length();

        for(int i = 0; i < len; i++){
            char ch = s.charAt(i);
            if(ch == '('){
                left.push(i);
            }else if(ch == ')'){ // 遇到右括号
                if(!left.isEmpty()){ // 如果左括号有，则弹出一个
                    left.pop();
                }else if(!star.isEmpty()){ // 如果左括号没有，则从星号中弹出一个作为左括号
                    star.pop();
                }else{ // 都不够了，则不满足了
                    return false;
                }
            }else{
                star.push(i);
            }
        }

        while(!left.isEmpty() && !star.isEmpty()){// 为了排除类似'*((*'的情况
            int leftIndex = left.pop();
            int starIndex = star.pop();
            if(leftIndex > starIndex){
                return false;
            }
        }

        return left.isEmpty();
    }
}