class Solution {
    public int solve(int num) {
        int count = 0;

        while(num >= 5){
            num -= 5;
            count += 2;
            num += 2;
        }

        while(num >= 3){
            num -= 3;
            count += 1;
            num += 1;
        }

        return count;
    }
}