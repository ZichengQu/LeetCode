/**
 * 时间复杂度：O(3^m * 4^n)
 * 空间复杂度：O(m + n). 空间复杂度主要取决于哈希表以及回溯过程中的递归调用层数，哈希表的大小与输入无关，可以看成常数，递归调用层数最大为 m + n。
 * 其中 m 是输入中对应 3 个字母的数字个数（包括数字 2、3、4、5、6、8），
 * n 是输入中对应 4 个字母的数字个数（包括数字 7、9），m + n 是输入数字的总个数。
 */
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new LinkedList<>();
        int len = digits.length();
        if(len == 0){
            return combinations;
        }

        Map<Character, String> convert = new HashMap<>();
        convert.put('2', "abc");
        convert.put('3', "def");
        convert.put('4', "ghi");
        convert.put('5', "jkl");
        convert.put('6', "mno");
        convert.put('7', "pqrs");
        convert.put('8', "tuv");
        convert.put('9', "wxyz");

        backtrack(combinations, convert, digits, 0, new StringBuilder());

        return combinations;
    }

    private void backtrack(List<String> combinations, Map<Character, String> convert, String digits, int depth, StringBuilder combination){
        if(depth == digits.length()){
            combinations.add(combination.toString());
        }else{
            char number = digits.charAt(depth);
            String letters = convert.get(number);
            for(int i = 0; i < letters.length(); i++){
                combination.append(letters.charAt(i));
                backtrack(combinations, convert, digits, depth + 1, combination);
                combination.deleteCharAt(depth); // 这里是depth，不是i。depth => combination.length() - 1。把最后一个，也就是这一层刚用完的删掉。
            }
        }
    }
}