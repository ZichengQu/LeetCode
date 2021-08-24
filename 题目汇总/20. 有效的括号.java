class Solution {
    public boolean isValid(String s) {
        if (s != null && s.length() % 2 == 1) { // 官解部分的优化，如果不是成对(偶数长度)出现的，则肯定不匹配.
            return false;
        }

        Stack<Character> quoteStack = new Stack<>();
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put('}', '{');
        pairs.put(']', '[');
        
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(pairs.containsKey(ch)){ // 需要找到一个匹配的左括号
                if (quoteStack.isEmpty() || quoteStack.peek() != pairs.get(ch)) {
                    return false; // 若找不到了，则false
                }
                quoteStack.pop(); // 匹配上了一个括号，则弹出栈中匹配的左括号
            }else{
                quoteStack.push(ch); // 没匹配上，则说明不是右括号，把当前ch(不管是不是左括号)压入栈
            }
        }
        // if(!quoteStack.isEmpty()){ // 官解这里的写法更好
        //     return false;
        // }
        // return true;
        return quoteStack.isEmpty(); // 官解这里的写法更好
    }
}