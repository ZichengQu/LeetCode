// 0   0000
// 1   0001
// 2   0010
// 3   0011
// 4   0100
// 5   0101
// 6   0110
// 7   0111
// 8   1000
// 9   1001
// 10  1010
// 11  1011
// 12  1100
// 13  1101
// 14  1110
// 15  1111

/**
 * 错误思路，比如12，按这个错误算法，偶数的bit数应与前一个数一致，但比如12却不一致.
 * 数字 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6
 * 错误[0,1,1,2,1,2,2,3,1,2,2,3,3,4,4,5,1]
 * 正确[0,1,1,2,1,2,2,3,1,2,2,3,2,3,3,4,1]
 */
// class Solution {
//     public int[] countBits(int n) {
//         int[] res = new int[n + 1];
//         res[0] = 0;
//         for(int i = 1; i <= n; i++){
//             if((i & (i - 1)) == 0){// 如果i是2的次幂，则归一
//                 res[i] = 1;
//             }else if(i % 2 == 0){ // 如果i是偶数，则不变
//                 res[i] = res[i - 1];
//             }else{ // 如果i是奇数，则加一
//                 res[i] = res[i - 1] + 1;
//             }
//         }
//         return res;
//     }
// }

/**
 * 动态规划方法2、3、4
 * O(n), S(1)
 * 注：方法一是单纯的求bit数，不是动态规划方法，其时间复杂度为O(n * log(n))，不推荐在这道题里使用，因此将方法一放置到了最后。
 */
class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        res[0] = 0;
        
        for(int i = 1; i <= n; i++){
            /*
             * 方法二：动态规划——最高有效位
             */
            int highBit = 0;
            if((i & (i - 1)) == 0){
                highBit = i;
            }
            res[i] = res[i - highBit] + 1;

            /**
             * 方法三：动态规划——最低有效位
             * i >> 1 右移一位，如果 i 的二进制表示中最后一位是1，则 i >> 1 比 i 少1；如果是0，则 i >> 1 与 i 的bit数相等。
             * 思路1：用位与运算，判断最后一位是否是1
             * 思路2：因为2进制表示中，只有2^0是1，其余位均为偶数。因此若最后一位为1，则i必为奇数，否则为偶数。
             */
            res[i] = res[i >> 1] + (i & 1); // // 等价于res[i] == res[(int)Math.floor(i / 2)] + i % 2;

            /**
             * 方法四：动态规划——最低设置位
             * i & (i - 1) 比 i 少了个1，在res中(i & (i - 1))已经求过了，因此利用res[i & (i - 1)]的bit数加1，即可获得res[i]的bit数
             */
            res[i] = res[i & (i - 1)] + 1;
        }
        return res;
    }
}


/**
 * 方法一
 * 下面三种算法均为 461.汉明距离
 * 其时间复杂度均为O(n * log(n))
 */
class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for(int i = 0; i <= n; i++){
            res[i] = Integer.bitCount(i);
        }
        return res;
    }
}

class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for(int i = 0; i <= n; i++){
            int count = 0;
            int temp = i;
            while(temp != 0){
                count += temp & 1;
                temp >>= 1;
            }
            res[i] = count;
        }
        return res;
    }
}

/**
 * 一般用这个，如果是461题中的情况下
 */
class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for(int i = 0; i <= n; i++){
            int count = 0;
            int temp = i;
            while(temp != 0){
                temp &= temp - 1;
                count++;
            }
            res[i] = count;
        }
        return res;
    }
}