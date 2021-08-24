// class Solution {
//     public int hIndex(int[] citations) {
//         Arrays.sort(citations);
//         // System.out.println(Arrays.toString(citations));

//         int maxH = 0;
//         for(int i = 0; i < citations.length; i++){
//             // System.out.println(citations[i]);
//             // System.out.println(citations.length - i);
//             // System.out.println(maxH);
//             // System.out.println();

//             if(citations[i] <= citations.length - i){
//                 if(citations[i] > maxH){
//                     maxH = citations[i];
//                 }
//             }else{
//                 return Math.max(citations.length - i, maxH);
//             }
//         }
//         return maxH;
//     }
// }
// // 0 1 3 5 6

/**
    官方更好
 */
public class Solution {
    public int hIndex(int[] citations) {
        // 排序（注意这里是升序排序，因此下面需要倒序扫描）
        Arrays.sort(citations);
        // 线性扫描找出最大的 i
        int i = 0;
        while (i < citations.length && citations[citations.length - 1 - i] > i) {
            i++;
        }
        return i;
    }
}
