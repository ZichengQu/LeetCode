/**
 * 主要考虑list的下标在map中的更新问题。
 * 重要思想：O(1)时间删除数组中的数，可以将最后一个元素放到被删除的位置，并删除最后一个元素。有些题需要考虑特殊情况，被删除的元素是否正好是最后一个元素。
 */
class RandomizedSet {
    Map<Integer, Integer> hashMap; // value, index in arrayList
    List<Integer> arrayList; // 主要为了通过下标实现随机O(1)访问元素
    /** Initialize your data structure here. */
    public RandomizedSet() {
        hashMap = new HashMap<>();
        arrayList = new ArrayList<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        boolean isContain = hashMap.containsKey(val);
        if(!isContain){ // 如果不存在，则加入
            arrayList.add(val);
            hashMap.put(val, arrayList.size() - 1);
        }
        return !isContain;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        boolean isContain = hashMap.containsKey(val);
        if(isContain){ // 如果存在，则删除
            int index = hashMap.get(val); // 获取当前val所对应的在arrayList中的下标
            int lastIndex = arrayList.size() - 1; // 获取arrayList的最后一个下标
            int lastNum = arrayList.get(lastIndex); // 获取arrayList的最后一个元素

            // 第一种写法思路
            // if(index == lastIndex){
            //     // arrayList.set(index, lastNum);
            //     arrayList.remove(lastIndex);
            // }else{
            //     arrayList.set(index, lastNum);
            //     arrayList.remove(lastIndex);
            //     hashMap.put(arrayList.get(index), index);
            // }
            // hashMap.remove(val);

            // 第二种写法思路
            // arrayList.set(index, lastNum); // 为了实现从arrayList可以O(1)的删除元素，用最后一个元素替换下标为index的元素
            // arrayList.remove(lastIndex); // 并删除最后一个元素
            // if(index != lastIndex){ // 如果删除的正好是最后一个元素，则不需要更新其在Map中的下标。如果不是最后一个，则需要。
            //     hashMap.put(arrayList.get(index), index); // 因为arrayList的最后一个元素的位置改变了，需要重新将HashMap中的最后一个元素的索引更新
            // }
            // hashMap.remove(val);

            // 第三种写法思路
            arrayList.set(index, lastNum); // 将最后一个元素赋值到待删除元素的位置
            hashMap.put(arrayList.get(index), index); // 更新最后一个元素的下标为被删除元素的下标
            arrayList.remove(lastIndex); // 删除最后一个元素
            hashMap.remove(val); // 删除待删除元素
        }
        return isContain;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        Random random = new Random();
        int index = random.nextInt(arrayList.size()); // [0, size)
        return arrayList.get(index);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */