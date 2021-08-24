class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack; // 若不用额外空间的话，可以考虑尝试用差值代替这个minStack.
    
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
        minStack.push(Integer.MAX_VALUE);
    }
    
    public void push(int val) {
        stack.push(val);
        minStack.push(Math.min(minStack.peek(), val)); // 如果val比栈顶的最小值小，则将val压栈，否则stack中新增加一个val，如果之后要pop，则最小值不变，因此将较小的那个压栈。
    }
    
    public void pop() {
        int pop = stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */