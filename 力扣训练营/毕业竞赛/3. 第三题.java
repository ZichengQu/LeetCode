class Solution {
    public int cardGame(int[] cardA, int[] cardB) {
        Arrays.sort(cardA);
        Arrays.sort(cardB); // 李的牌，排不排序无所谓

        boolean[] used = new boolean[cardA.length]; // 未使用：false，已使用：true

        int winCount = 0;

        for(int i = 0; i < cardB.length; i++){
            int target = cardB[i]; // 李的出牌
            int index = binarySearchLarger(cardA, target, used);
            if(target < cardA[index]){
                winCount++;
            }
        }

        return winCount;
    }

    // 如果是找 min(num > target), 则使用left。如果是找 max(num < target)，则将下面的换成 if( cardA[mid] >= target, right = mid - 1, 并使用 right
    private int binarySearchLarger(int[] cardA, int target, boolean[] used){ // 找 argmin(cardA[index] > target)，若不存在，则返回最小可利用的元素
        int left = 0;
        int right = cardA.length - 1;

        while(left <= right){ // 找 argmin(cardA[index] > target)
            int mid = (left + right) / 2;
            if(cardA[mid] <= target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        while(left < cardA.length && used[left] == true){ // 若被使用过，则向右寻找
            left++;
        }

        if(left == cardA.length){ // 若越界了，说明没有比 target 大的(未被使用过的)元素了
            left = 0; // 因为没有比 target 大的元素了，那就拿一个最小的元素和 target 去对比吧 (田忌赛马)
            while(left < cardA.length && used[left] == true){ 
                left++;
            }
        }

        used[left] = true; // 标志为使用过

        return left;
    }
}