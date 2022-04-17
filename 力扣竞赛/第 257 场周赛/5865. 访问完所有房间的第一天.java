// https://leetcode-cn.com/problems/first-day-where-you-have-been-in-all-the-rooms/

// 前缀和优化 DP
// https://leetcode-cn.com/problems/first-day-where-you-have-been-in-all-the-rooms/solution/qian-zhui-he-you-hua-dp-by-endlesscheng-j10b/
class Solution {

	public int firstDayBeenInAllRooms(int[] nextVisit) {
		int[] dp = new int[nextVisit.length];
        int[] sum = new int[nextVisit.length];
		for (int i = 1; i < nextVisit.length; i++) {
			dp[i] = (sum[i - 1] - sum[nextVisit[i - 1]] + 1000000009) % 1000000007;
			sum[i] = (sum[i - 1] + dp[i]) % 1000000007;
		}
		return sum[nextVisit.length - 1];
	}
}

// https://leetcode-cn.com/problems/first-day-where-you-have-been-in-all-the-rooms/solution/java-dp-by-jmss_xgp-uvpk/
public int firstDayBeenInAllRooms(int[] nextVisit) {
    long[] dp = new long[nextVisit.length+1];
    int mod = 1000000007;
    for (int i = 0; i < nextVisit.length; i++) {

        if (nextVisit[i] == i) {
            dp[i+1] = (dp[i] + 2+mod) % mod;
        } else {
            dp[i+1] = (2 * dp[i] - dp[nextVisit[i]] + 2 + mod) % mod;
        }
    }
    return (int)dp[dp.length-2];
}

/**
 * 自己的超时思路
 */
class Solution {
    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int len = nextVisit.length;
        
        int day = 0;
        int room = 0;
        
        Map<Integer, Integer> hashMap = new HashMap<>();
        
        while(hashMap.size() != len){
            hashMap.put(room, hashMap.getOrDefault(room, 0) + 1);
            // System.out.println(day + ", " + room); // 第几天访问第几个房间
            if(hashMap.get(room) % 2 != 0){
                room = nextVisit[room];
            }else{
                room = (room + 1) % len;
            }
            day = (day + 1) % (int)(1e9 + 7);
           
        }
        
        return --day;

    }
}