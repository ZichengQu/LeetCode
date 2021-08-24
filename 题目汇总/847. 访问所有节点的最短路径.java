/**
 * 官解链接：https://leetcode-cn.com/problems/shortest-path-visiting-all-nodes/solution/fang-wen-suo-you-jie-dian-de-zui-duan-lu-mqc2/
 * O(n^2 * 2^n) 假设 n 是顶点数，则边数为 n^2，status 共 2^n 种情况
 * S(n * 2^n) 队列使用的空间
 */
class Solution {
    public int shortestPathLength(int[][] graph) {
        int num = graph.length;
        Queue<int[]> queue = new LinkedList<>(); // [开始顶点，status，dist]
        boolean[][] seen = new boolean[num][1 << num]; // 记录以每一个顶点开始的每一个状态是否出现过
        for(int i = 0; i < num; i++){
            int status = 1 << i; // 第 i 个顶点的初始状态，只有第 i 个位置被访问过，标志为 1
            queue.offer(new int[]{i, status, 0});
            seen[i][status] = true; // 将这个顶点的这个状态标志为出现过
        }
        int res = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            int[] tuple = queue.poll();
            int u = tuple[0]; // 当前顶点
            int status = tuple[1]; // 当前顶点的状态
            int dist = tuple[2]; // 当前顶点的遍历距离
            if(status == (1 << num) - 1){ // 如果以当前顶点开始的所有位置都访问过了
                // res = Math.min(res, dist); // 常规想法
                res = dist; // 第一个达到的就是最小值 (BFS)，然后直接 break 就可以
                break;
            }
            for(int v: graph[u]){ // 遍历所有邻居
                int new_status = (status | (1 << v)); // 不能更改 status 的值，接下来的循环仍会使用到
                if(seen[v][new_status]){ // 如果以这个顶点开始的这个状态出现过，则跳过
                    continue;
                }
                queue.offer(new int[]{v, new_status, dist + 1});
                seen[v][new_status] = true;
            }
        }

        return res;

    }
}