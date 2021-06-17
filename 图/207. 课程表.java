class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>(); // 第i门课程的后置课程的list
        int[] requestNumber = new int[numCourses]; // 第i门课的前置课程数量

        for(int i = 0; i < numCourses; i++){ // 有多少个课程，则分别为每个创建一个list。
            edges.add(new ArrayList<Integer>());
        }
        for(int[] info: prerequisites){ 
            edges.get(info[1]).add(info[0]); // 用来记录该课程是哪些课程的前置课程。
            requestNumber[info[0]] += 1; // 记录该课程的前置课程数量
        }
        Queue<Integer> canStudy = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(requestNumber[i] == 0){
                canStudy.offer(i); // 将前置课程为空的课程放入队列中
            }
        }
        int studied = 0;
        while(!canStudy.isEmpty()){
            studied++;
            int preCourse = canStudy.poll();
            List<Integer> courses = edges.get(preCourse); // 获取该前置课程的后置课程
            for(int course: courses){
                requestNumber[course] -= 1; // 把这些后置课程的前置课程的数量减去1，因为之前poll出了（学习了）一个前置课程。
                if(requestNumber[course] == 0){
                    canStudy.offer(course);
                }
            }
        }
        return studied == numCourses;
    }
}