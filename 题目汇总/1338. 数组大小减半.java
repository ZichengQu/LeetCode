class Solution {
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> hashMap = new HashMap<>(); // num, occurrence

        for(int num: arr){
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1); // 对每个num，记录其出现次数
        }

        List<Integer> occurrenceList = new ArrayList<>(hashMap.values());
        Collections.sort(occurrenceList); // 对出现次数进行排序

        int len = arr.length;
        // if(len % 2 == 0){ // 改成 if(totalSize * 2 >= halfLen) 可以代替验证其奇偶性
        //     len = len / 2;
        // }else{
        //     len = (len + 1) / 2;
        // }
        
        int count = 0; // 结果
        int totalSize = 0; // 累计的size

        int size = occurrenceList.size();
        for(int i = size - 1; i >= 0; i--){ // 从后向前遍历该list
            totalSize += occurrenceList.get(i);
            count++;
            if(totalSize * 2 >= len){ // totalSize * 2 用来代替验证len的奇偶性
                break;
            }
        }
        return count;
    }
}