/**
 * 由于传入的参数num在题中给出明确范围，因此其长度最大为4位数
 * 因此，时间复杂度为 O(1), 空间复杂度为 O(1)
 */
class Solution {
    public String intToRoman(int num) {
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sBuilder = new StringBuilder();

        for(int i = 0; i < nums.length; i++){
            while(num >= nums[i]){
                num -= nums[i];
                sBuilder.append(romans[i]);
            }
            if(num == 0){
                break; // 提前终止
            }
        }      

        return sBuilder.toString();  

    }
}


/**
 * 二刷时，一个非常差的思路，千万不要这么写
 */
class Solution {
    private static Map<Integer, String> hashMap;
    static{
        hashMap = new HashMap<>();
        hashMap.put(1, "I");
        hashMap.put(5, "V");
        hashMap.put(10, "X");
        hashMap.put(50, "L");
        hashMap.put(100, "C");
        hashMap.put(500, "D");
        hashMap.put(1000, "M");
        hashMap.put(4, "IV");
        hashMap.put(9, "IX");
        hashMap.put(40, "XL");
        hashMap.put(90, "XC");
        hashMap.put(400, "CD");
        hashMap.put(900, "CM");
    }

    public String intToRoman(int num) {
        int M = num / 1000;
        int D = num % 1000 / 500;
        int C = num % 500 / 100;
        int L = num % 100 / 50;
        int X = num % 50 / 10;
        int V = num % 10 / 5;
        int I = num % 5;

        StringBuilder sBuilder = new StringBuilder();
        for(int i = 0; i < M; i++){ // 几个千位
            sBuilder.append(hashMap.get(1000));
        }

        if(D == 1 && C == 4){ // 是不是 900
            sBuilder.append(hashMap.get(900));
        }else if(D == 1){ // 是不是 500
            sBuilder.append(hashMap.get(500));
        }

        if(D == 0 && C == 4){ // 是不是 400
            sBuilder.append(hashMap.get(400));
        }else if(!(D == 1 && C == 4)){ // 不是 900 的时候
            for(int i = 0; i < C; i++){
                sBuilder.append(hashMap.get(100));
            }
        }

        if(L == 1 && X == 4){ // 是不是 90
            sBuilder.append(hashMap.get(90));
        }else if(L == 1){ // 是不是 50
            sBuilder.append(hashMap.get(50));
        }

        if(L == 0 && X == 4){ // 是不是 40
            sBuilder.append(hashMap.get(40));
        }else if(!(L == 1 && X == 4)){ // 不是 90 的时候
            for(int i = 0; i < X; i++){
                sBuilder.append(hashMap.get(10));
            }
        }

        if(V == 1 && I == 4){ // 是不是 9
            sBuilder.append(hashMap.get(9));
        }else if(V == 1){ // 是不是 5
            sBuilder.append(hashMap.get(5));
        }

        if(V == 0 && I == 4){ // 是不是 4
            sBuilder.append(hashMap.get(4));
        }else if(!((V == 1 && I == 4))){ // 不是 9 的时候
            for(int i = 0; i < I; i++){
                sBuilder.append(hashMap.get(1));
            }
        }

        return sBuilder.toString();
    }
}