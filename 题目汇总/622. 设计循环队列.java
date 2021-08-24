/**
 * 没有第二次代码的逻辑好
 */
class MyCircularQueue {
    private int[] circularQueue;
    private int front;
    private int rear;
    private int length;
    private int maxSize;
    public MyCircularQueue(int k) {
        maxSize = k;
        length = 0;
        front = 0;
        rear = 0;
        circularQueue = new int[maxSize];
    }
    
    public boolean enQueue(int value) {
        if(length >= maxSize){
            return false;
        }
        circularQueue[rear++] = value;
        rear %= maxSize;
        length++;
        return true;
    }
    
    public boolean deQueue() {
        if(length <= 0){
            return false;
        }
        front++;
        front %= maxSize;
        length--;
        return true;
    }
    
    public int Front() {
        if(length <= 0){
            return -1;
        }
        return circularQueue[front];
    }
    
    public int Rear() {
        if(length <= 0){
            return -1;
        }
        int rearIndex = (rear - 1 + maxSize) % maxSize;
        return circularQueue[rearIndex];
    }
    
    public boolean isEmpty() {
        return length == 0;
    }
    
    public boolean isFull() {
        return length == maxSize;
    }
}

/**
 * 复习，发现代码逻辑比第一次写的更优
 */
class MyCircularQueue {
    int[] circularQueue;
    int front;
    int rear;
    int curSize;
    int maxSize;

    public MyCircularQueue(int k) {
        maxSize = k;
        circularQueue = new int[maxSize];
        front = 0;
        rear = -1;
        curSize = 0;
    }
    
    public boolean enQueue(int value) {
        if(!isFull()){
            rear += 1;
            rear %= maxSize;
            circularQueue[rear] = value;
            curSize++;
            return true;
        }else{
            return false;
        }
    }
    
    public boolean deQueue() {
        if(!isEmpty()){
            front += 1;
            front %= maxSize;
            curSize--;
            return true;
        }else{
            return false;
        }
    }
    
    public int Front() {
        return isEmpty()? -1: circularQueue[front];
    }
    
    public int Rear() {
        return isEmpty()? -1: circularQueue[rear];
    }
    
    public boolean isEmpty() {
        return curSize == 0;
    }
    
    public boolean isFull() {
        return curSize == maxSize;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */