/**
 * 排序
 */
class Solution {
    public int[] smallestK(int[] arr, int k) {
        Arrays.sort(arr); // 基于排序
        int[] res = new int[k];
        for(int i = 0; i < k; i++){
            res[i] = arr[i];
        }

        return res;
    }
}

/**
 * 最大堆
 */
class Solution {
    public int[] smallestK(int[] arr, int k) {
        int[] res = new int[k];

        if(k == 0){
            return res;
        }
        // 创建最大堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer num1, Integer num2){
                return -(num1.compareTo(num2));
            }
        });
        // O(k * logk)
        for(int i = 0; i < k; i++){
            pq.offer(arr[i]);
        }
        // O(n * logk)
        for(int i = k; i < arr.length; i++){
            if(arr[i] < pq.peek()){
                pq.offer(arr[i]);
                pq.poll();
            }
        }
        // O(k * logk)
        int idx = 0;
        while(!pq.isEmpty()){
            res[idx++] = pq.poll();
        }

        return res;
    }
}

/**
 * 快排
 */
class Solution {
    Random rand;
    public int[] smallestK(int[] arr, int k) {
        rand = new Random();
        quickSort(arr, 0, arr.length - 1, k); // 如果主元的位置是 k，那主元及主元之前就有 k 个最小的元素了

        int[] res = new int[k];

        for(int i = 0; i < k; i++){
            res[i] = arr[i];
        }

        return res;
    }

    private void quickSort(int[] arr, int left, int right, int targetPos){
        if(left >= right){
            return;
        }
        int pivot = rand.nextInt(right - left) + left;
        swap(arr, left, pivot);
        
        pivot = left;
        int l = left + 1;
        int r = right;

        while(l < r){
            while(l < r && arr[l] <= arr[pivot]){
                l++;
            }
            while(l < r && arr[r] >= arr[pivot]){
                r--;
            }
            swap(arr, l, r);
        }

        if(arr[pivot] <= arr[l]){
            swap(arr, l - 1, pivot);
            pivot = l - 1;
        }else{
            swap(arr, l, pivot);
            pivot = l;
        }
        if(pivot == targetPos){
            return;
        }
        quickSort(arr, left, pivot - 1, targetPos);
        quickSort(arr, pivot + 1, right, targetPos);
    }

    private void swap(int[] arr, int index1, int index2){
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}