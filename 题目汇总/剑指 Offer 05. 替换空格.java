class Solution {
    public String replaceSpace(String s) {
        // s = s.replaceAll(" ", "%20");
        // return s;

        StringBuilder sBuilder = new StringBuilder();

        int len = s.length();

        for(int i = 0; i < len; i++){
            char ch = s.charAt(i);
            if(ch == ' '){
                sBuilder.append("%20");
            }else{
                sBuilder.append(ch);
            }
        }
        return sBuilder.toString();
    }
}