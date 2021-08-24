/**
 * 官解视频里的思路，运行效率很好
 */
class Solution {
    public String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        if (sLen < tLen) {
            return "";
        }

        // 维护两个数组，记录已有字符串指定字符的出现次数，和目标字符串指定字符的出现次数
        int[] need = new int[128]; // ASCII表总长128
        int[] have = new int[128];

        // 将目标字符串指定字符的出现次数记录
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }

        int start = 0; // 最小覆盖子串在原字符串中的起始位置
        int minLen = s.length() + 1; // 最小长度(初始值为一定不可达到的长度)

        int count = 0; // 已有字符串中目标字符串指定字符的出现总频次

        int left = 0; // 滑动窗口左边界
        int right = 0; // 滑动窗口右边界

        while (right < sLen) {
            char r = s.charAt(right);

            // 说明该字符不被目标字符串需要，此时有两种情况
            // 1.循环刚开始，那么直接移动右指针即可，不需要做多余判断
            // 2.循环已经开始一段时间，此处又有两种情况
            // 2.1 上一次条件不满足，已有字符串指定字符出现次数不满足目标字符串指定字符出现次数，那么此时
            // 如果该字符还不被目标字符串需要，就不需要进行多余判断，右指针移动即可
            // 2.2 左指针已经移动完毕，那么此时就相当于循环刚开始，同理直接移动右指针
            if (need[r] == 0) {
                right++;
                continue;
            }

            // 当且仅当已有字符串目标字符出现的次数小于目标字符串字符的出现次数时，count才会+1
            // 是为了后续能直接判断已有字符串是否已经包含了目标字符串的所有字符，不需要挨个比对字符出现的次数
            if (have[r] < need[r]) {
                count++;
            }

            have[r]++; // 已有字符串中目标字符出现的次数+1

            // 当且仅当已有字符串已经包含了所有目标字符串的字符，且出现频次一定大于或等于指定频次
            while (count == tLen) {
                if (right - left + 1 < minLen) { // 挡窗口的长度比已有的最短值小时，更改最小值，并记录起始位置
                    minLen = right - left + 1;
                    start = left;
                }

                char l = s.charAt(left);
                if (need[l] == 0) { // 如果左边即将要去掉的字符不被目标字符串需要，那么不需要多余判断，直接可以移动左指针
                    left++;
                    continue;
                }

                // 如果左边即将要去掉的字符被目标字符串需要，且出现的频次正好等于指定频次，那么如果去掉了这个字符，
                // 就不满足覆盖子串的条件，此时要破坏循环条件跳出循环，即控制目标字符串指定字符的出现总频次(count）-1
                if (have[l] == need[l]) {
                    count--;
                }

                have[l]--; // 已有字符串中目标字符出现的次数-1
                left++; // 移动左指针
            }

            right++; // 移动右指针到下一位置，继续判断
        }
        // 如果最小长度还为初始值，说明没有符合条件的子串
        if (minLen == s.length() + 1) {
            return "";
        }
        // 返回的为以记录的起始位置为起点，记录的最短长度为距离的指定字符串中截取的子串
        return s.substring(start, start + minLen);
    }
}

/**
 * 滑动窗口，Map 基本是官解思路，但是对官解按自己习惯做了一些调整
 */
class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> need = new HashMap<>(); // 字符，次数
        Map<Character, Integer> have = new HashMap<>();

        int tLen = t.length(); // 长度
        int sLen = s.length();

        for (int i = 0; i < tLen; i++) { // 将字符串 t 的字符及对应的个数添加到 need 中
            char ch = t.charAt(i);
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }

        int begin = 0; // 如果存在题意的子串，则其开始位置暂时设为 begin
        int minLen = sLen + 1; // 先设满足题意的子串的最小长度为一个不可能的数，因为长度最大只能是 sLen，而不能比 sLen 大

        int left = 0; // 滑动窗口左边界
        int right = 0; // 滑动窗口右边界

        while (right < sLen) { // 当右边界没有超出范围时
            char r = s.charAt(right); // 右边界位置的当前字符
            if (need.containsKey(r)) { // 如果 need 中包含当前字符 r，则算入统计中
                have.put(r, have.getOrDefault(r, 0) + 1);
            }
            while (check(need, have)) { // 检查是否满足题意
                if (right - left + 1 < minLen) { // 如果当前满足题意的子串的长度更短
                    minLen = right - left + 1; // 则更新最短长度
                    begin = left; // 更新为该子串的起始位置
                }
                char l = s.charAt(left); // 逐步删除滑动窗口的左边界的字符，直到其不满足要求
                if (need.containsKey(l)) { // 如果 t 中包含了该字符，则需要将统计里的该字符的数量减 1
                    have.put(l, have.get(l) - 1);
                }
                left++; // 滑动窗口左边界向右移动
            }
            right++; // 当不满足check时，滑动窗口右边界继续向右移动。
        }
        return (minLen == sLen + 1) ? "" : s.substring(begin, begin + minLen);
    }

    public boolean check(Map<Character, Integer> need, Map<Character, Integer> have) {
        for (char ch : need.keySet()) {
            if (need.get(ch) > have.getOrDefault(ch, 0)) {
                return false;
            }
        }
        return true;
    }

}
