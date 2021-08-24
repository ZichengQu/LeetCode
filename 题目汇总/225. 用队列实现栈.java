/**
 * 单队列实现方式
 */
class MyStack {
    Queue<Integer> queue;
    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
        for(int i = 0; i < queue.size() - 1; i++){
            queue.offer(queue.poll());
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * 双队列实现方式
 */
class MyStack {
    Queue<Integer> myStack1;
    Queue<Integer> myStack2;
    /** Initialize your data structure here. */
    public MyStack() {
        myStack1 = new LinkedList<>();
        myStack2 = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        myStack1.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        top();
        int res = myStack1.poll();
        Queue<Integer> temp = myStack1;
        myStack1 = myStack2;
        myStack2 = temp;
        return res;
    }
    
    /** Get the top element. */
    public int top() {
        while(myStack1.size() > 1){
            myStack2.offer(myStack1.poll());
        }
        return myStack1.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return myStack1.isEmpty();
    }
}