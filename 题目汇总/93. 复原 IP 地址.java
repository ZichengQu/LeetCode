/**
 * 官解思路
 * https://leetcode-cn.com/problems/restore-ip-addresses/solution/fu-yuan-ipdi-zhi-by-leetcode-solution/
 * O(∣s∣ * 3^SEG_COUNT), S(SEG_COUNT)，详见官解链接
 */
class Solution {
    private int IP_v4; // 如果是IPv6的话，直接将4改为6即可。
    
    public List<String> restoreIpAddresses(String s) {
        IP_v4 = 4; // 如果是IPv6的话，直接将4改为6即可。
        List<String> res = new ArrayList<>();
        dfsBackTrack(s, res, 0, 0, new int[IP_v4]);
        return res;
    }

    private void dfsBackTrack(String s, List<String> res, int count, int start, int[] ip){
        int sLen = s.length();

        // 如果找到了 4 段 IP 地址
        if(count == IP_v4){
            if(start == sLen){ // 并且遍历完了字符串，那么就是一种答案
                StringBuilder sBuilder = new StringBuilder();
                for(int i = 0; i < IP_v4; i++){
                    int subIP = ip[i];
                    sBuilder.append(subIP);
                    if(i != IP_v4 - 1){
                        sBuilder.append(".");
                    }
                }
                res.add(sBuilder.toString());
            }else{ // 如果没找到，就直接提前回溯
                // return;
            }
            return; // 不管遍没遍历完，只要已经有了 4 段 IP 地址，都需要停止继续dfs了。
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if(start == sLen){
            return;
        }

        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if(s.charAt(start) == '0'){
            ip[count] = 0;
            dfsBackTrack(s, res, count + 1, start + 1, ip);
        }

        // 一般情况，枚举每一种可能性并递归
        int addr = 0;
        for(int end = start; end < sLen; end++){
            addr = addr * 10 + (s.charAt(end) - '0');
            if(addr > 0 && addr <= 255){ // 这里一定是大于0，因为等于0的话，已经在前面判断过了
                ip[count] = addr;
                dfsBackTrack(s, res, count + 1, end + 1, ip);
            }else{
                break;
            }
        }
    }
}