/**
 * 两种不同的实现思想
 */

class MyQueue {
    Deque<Integer> stack;
    Deque<Integer> util;
    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new LinkedList<>();
        util = new LinkedList<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        while(stack.size() > 0){
            util.offerLast(stack.pollLast());
        }
        util.offerLast(x);
        while(util.size() > 0){
            stack.offerLast(util.pollLast());
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack.pollLast();
    }
    
    /** Get the front element. */
    public int peek() {
        return stack.peekLast();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }
}


class MyQueue {
    Deque<Integer> stack;
    Deque<Integer> util;
    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new LinkedList<>();
        util = new LinkedList<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        stack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        peek();
        int res = stack.pop();
        while(util.size() > 0){
            stack.push(util.pop());
        }
        return res;
    }
    
    /** Get the front element. */
    public int peek() {
        while(stack.size() > 1){
            util.push(stack.pop());
        }
        return stack.peekLast();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }
}
