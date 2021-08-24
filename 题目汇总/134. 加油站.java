/**
 * 自己的思路，利用53题，最大子序列和的思想。利用"子序列和"最大的那一段，，如果最大的和，减去其它段，结果不小于0，则有满足题意的起始位置。否则一定没有，因为连最大的都不满足了。
 * 贪心思想
 * O(n)
 */
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0; // 默认起始位置
        int len = gas.length;
        int gasLeft = 0; // 汽油剩余量
        for(int i = 0; i < len; i++){ // 利用53题，最大子序列和的思想，找到最大子序列和的起始位置。
            gasLeft = gasLeft + gas[i] - cost[i]; 
            if(gasLeft < 0){ // 如果gasLest小于0了
                start = i + 1; // 那从上一个start开始，到目前的i，一定无法到达。那么新起点就是这个i的下一个点
                gasLeft = 0; // 重置汽油剩余量
            }
        }
        if(start >= len){ // 如果start越界了，代表一直没找到，则返回-1
            return -1;
        }
        for(int i = 0; i < start; i++){ // 从头开始，到start为止
            gasLeft = gasLeft + gas[i] - cost[i]; // 判断绕环路行驶一周的汽油剩余量
            if(gasLeft < 0){
                return -1;
            }
        }
        return start;
    }
}

/**
 * 官解思路
 * O(n)
 * 思想：
 * 如果从 x 出发，无法到达y，则从x和y之间的任何一个加油站出发，都无法到达加油站 y 的下一个加油站。
 * 我们首先检查第 0 个加油站，并试图判断能否环绕一周；如果不能，就从第一个无法到达的加油站开始继续检查。
 */
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        while (i < n) { // 起点为i
            int sumOfGas = 0, sumOfCost = 0;
            int cnt = 0; // 计步器
            while (cnt < n) {
                int j = (i + cnt) % n; // 当前位置为加油站j
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                if (sumOfCost > sumOfGas) {
                    break;
                }
                cnt++; // 汽油剩余量够，则继续往 j+1 加油站走
            }
            if (cnt == n) { // 如果是走完了n个加油站，汽油还够，则返回此时起点i
                return i;
            } else { // 如果不够，则将不够的加油站的下一个加油站作为新的起点，继续查找
                i = i + cnt + 1;
            }
        }
        return -1;
    }
}

/**
 * 暴力解 (自己的思路)
 * O(n^2)
 */
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        // System.out.println(len);
        for(int i = 0; i < len; i++){
            if(gas[i] < cost[i]){ // 如果起始位下标位i时，油量不足以支撑到下一站，则肯定不能作为起点
                continue;
            }
            int left = 0; // 剩余油量
            for(int j = i; j < len; j++){ // 从起始位置往后遍历
                left = left + gas[j] - cost[j];
                if(left < 0){
                    break;
                }
            }
            if(left < 0){ // 如果是因为break出来的，则left < 0, 则当前情况不满足，继续搜索下一个i是否满足
                continue;
            }

            for(int j = 0; j < i; j++){ // 再从头遍历到起始位置的前一个
                left = left + gas[j] - cost[j];
                if(left < 0){
                    break;
                }
            }
            if(left < 0){
                continue;
            }

            if(left >= 0){ /// 符合题意
                return i;
            }
        }
        return -1;
    }
}