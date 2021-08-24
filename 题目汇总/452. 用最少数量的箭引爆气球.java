class Solution {
    public int findMinArrowShots(int[][] points) {
        // 按 end time 非降序排列
        Arrays.sort(points, new Comparator<int[]>(){
            public int compare(int[] point1, int[] point2){
                return point1[1] - point2[1];
            }
        });

        // for(int[] point: points){
        //     System.out.println(Arrays.toString(point));
        // }

        int count = 0;
        int i = 0;
        while(i < points.length){
            int pick = points[i][1]; // 取没插过弓箭的第一个 end time作为插入点
            count++; // 每插一次，计数
            
            while(i + 1 < points.length && points[i + 1][0] <= pick && points[i + 1][1] >= pick){
                i++; // 把该插入点所能插入的其它气球删掉
            }

            i++; // 移到接下来没插入弓箭的第一个 end time 的气球
        }

        return count;
    }
}