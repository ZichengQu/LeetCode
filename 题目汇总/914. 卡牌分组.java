class Solution {
    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> hashMap = new HashMap<>(); // num, count
        for(int num: deck){
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1); // 记录每个数字出现的次数
        }

        int maxGCD = 0;
        boolean isFirst = true;
        
        for(int count: hashMap.values()){
            if(isFirst){
                maxGCD = count;
                isFirst = false;
                continue;
            }
            maxGCD = gcd(maxGCD, count);
        }
        return maxGCD >= 2; // 如果最大公约数 >= 2，则代表可分组。
    }

    // 官方题解求最大公约数
    // private int gcd(int x, int y) {
    //     return x == 0? y: gcd(y % x, x);
    // }
    
    // 通过短除法求最大公约数
    // https://blog.csdn.net/qq_44214519/article/details/95524812
    private int gcd(int x, int y){
        int res = 1;
        for(int i = 2; i <= x && i <= y; i++){
            while(x % i == 0 && y % i ==0){
                res *= i;
                x /= i;
                y /= i;
            }
        }
        return res;
    }
}