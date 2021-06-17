class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> courses = new ArrayList<>(); // 第i门课程作为前置课程时的后置课程的list
        int[] requestNumber = new int[numCourses]; // 第i门课的前置课程数量
        for(int i = 0; i < numCourses; i++){
            courses.add(new ArrayList<Integer>());
        }
        for(int[] info: prerequisites){
            courses.get(info[1]).add(info[0]);
            requestNumber[info[0]]++;
        }
        Queue<Integer> canStudy = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(requestNumber[i] == 0){
                canStudy.offer(i);
            }
        }
        
        int studied = 0;
        // int[] res = new int[numCourses];
        List<Integer> res = new ArrayList<>();
        while(!canStudy.isEmpty()){
            int preCourse = canStudy.poll(); // 从队列中poll出来，模拟为学习了
            studied++; //学习数量加1
            res.add(preCourse); // 记录该课程到result中
            List<Integer> postCourses = courses.get(preCourse); // 通过前置课程获取其后置课程
            for(int postCourse: postCourses){
                requestNumber[postCourse]--;
                if(requestNumber[postCourse] == 0){
                    canStudy.offer(postCourse);
                }
            }
        }
        int resultLen = studied == numCourses? res.size(): 0;
        // return (int[])res.toArray(new int[res.size()]);
        int[] result = new int[resultLen];
        for(int i = 0; i < resultLen; i++){
            result[i] = res.get(i);
        }
        return result;
         
    }
}