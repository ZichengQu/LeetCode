/**
 * 自己的做法，使用了LinkedHashSet，但面试中最好不要用
 */
class LRUCache {
    int capacity;
    Map<Integer, Integer> lru; // key, value
    Set<Integer> popular; // key, 最近使用的次数

    public LRUCache(int capacity) {
        this.capacity = capacity;
        lru = new HashMap<>();
        popular = new LinkedHashSet<>();
    }
    
    public int get(int key) {
        int value = lru.get(key) != null? lru.get(key): -1;
        if(value != -1){
            popular.remove(key);
            popular.add(key);
        }
        return value;
    }
    
    public void put(int key, int value) {
        if(popular.size() < capacity){
            if(!lru.containsKey(key)){
                popular.add(key);
            }else{
                popular.remove(key);
                popular.add(key);
            }
        }else{
            if(!lru.containsKey(key)){
                int leastRecentUsed = popular.iterator().next();
                popular.remove(leastRecentUsed);
                lru.remove(leastRecentUsed);
                popular.add(key);
            }else{
                popular.remove(key);
                popular.add(key);
            }
        }
        lru.put(key, value);
    }
}

/**
 * 官解 (面试中不推荐)，直接使用 LinkeHashMap 代替LinkeHashSet。
 * https://leetcode-cn.com/problems/lru-cache/solution/lruhuan-cun-ji-zhi-by-leetcode-solution/
 */
class LRUCache extends LinkedHashMap<Integer, Integer>{
    private int capacity;
    
    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity; 
    }
}

/**
 * 官解 (面试中推荐)，手写代码，代替LinkedHashSet和LinkedHashMap。常考题，一定要会
 * https://leetcode-cn.com/problems/lru-cache/solution/lruhuan-cun-ji-zhi-by-leetcode-solution/
 */
public class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}