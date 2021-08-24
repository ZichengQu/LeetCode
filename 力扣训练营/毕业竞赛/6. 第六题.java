class Solution {
    public boolean checkBracketSequence(String origin) {
        int left = 0; // 左括号的剩余个数

        int len = origin.length();

        for(int i = 0; i < len; i++){
            if(origin.charAt(i) == '('){ // 如果是左括号
                left++;
            }else if(origin.charAt(i) == ')'){ // 如果是右括号
                left--; // 需要使用一个左括号进行匹配
            }else{
                int index = i; // 当前位置
                int num = Integer.parseInt("" + origin.charAt(i)); // 将字符数字转换成整型
                while((i + 1) < len && origin.charAt(i + 1) >= '0' && origin.charAt(i + 1) <= '9'){ // 如果下一个字符还是数字
                    num = num * 10 + Integer.parseInt("" + origin.charAt(i + 1));
                    i++;
                }
                if(origin.charAt(index - 1) == '('){ // 如果上一个字符是左括号
                    left = left - 1 + num; // 则加上当前的个数(num)，并减去之前在if中误认为的左括号(数字前面的)，因为数字前面的左括号是标识符，不算个数
                }else{
                    left = left + 1 - num; // 同理，之前else if中的右括号多算了一个，把多减去的补回来，并减去真实的右括号的数量(num)
                }
            }

            if(left < 0){ // 如果左括号不够了
                return false;
            }
        }

        if(left != 0){ // 如果最终右括号比左括号多了
            return false;
        }

        return true;
    }
}