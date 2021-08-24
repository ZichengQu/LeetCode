/**
 * 官解BFS思路
 * O(V + E), S(V)
 */
class Solution {
    public boolean isBipartite(int[][] graph) {
        int unLabeled = 0; // 未分类
        int setA = 1; // 集合A
        int setB = 2; // 集合B

        int len = graph.length;
        int[] type = new int[len]; // 每个顶点的集合类型

        Arrays.fill(type, unLabeled); // 初始化

        for(int i = 0; i < len; i++){ // 加这个for循环的目的是可能有的子图和子图之间不想连，因此从一个结点BFS，无法访问所有顶点
            if(type[i] == unLabeled){ // 如果当前顶点未被考虑过 (未visited)
                type[i] = setA; // 初始化当前顶点的集合为集合A
                Queue<Integer> queue = new LinkedList<>(); // BFS，从该顶点开始遍历
                queue.offer(i);
                while(!queue.isEmpty()){
                    int u = queue.poll();
                    for(int v: graph[u]){ // 遍历当前顶点的所有的邻居
                        if(type[v] == type[u]){ // 如果该邻居与当前顶点类型相同，则不满足
                            return false;
                        }
                        if(type[v] == unLabeled){ // 如果该邻居未被访问过，
                            int set = type[u] == setA? setB: setA; // 则将该邻居设为与该顶点相反的集合
                            type[v] = set;
                            queue.offer(v); // 将该邻居入队
                        }
                    }
                }
            }
        }
        return true;
    }
}

/**
 * DFS，官解思路
 * O(V + E), S(V)
 */
class Solution {
    private int[] type; // 0代表未分配集合，1代表集合A，-1代表集合B
    private int[][] graph;
    private boolean flag = true;

    public boolean isBipartite(int[][] graph) {
        int len = graph.length;
        this.graph = graph;
        type = new int[len];

        for(int i = 0; i < len; i++){ // 遍历该图中全部结点
            if(type[i] == 0){ // 如果未被分配过
                dfs(i, 1); // 则dfs遍历该结点，并将该结点加入集合A
            }
            if(!flag){ // 提前剪枝，如果中途发现不满足了，则无需继续执行。
                return false;
            }
        }
            
        return flag;
    }

    private void dfs(int u,int c){
        type[u] = c; // 设置该结点为集合c，c∈[A, B], A = 1, B = -1
        for(int v: graph[u]){ // 遍历该结点的每一个邻接点
            if(type[v] == c){ // 如果某一个邻接点和该结点是同一集合
                flag = false; // 则返回false
                return;
            }
            if(type[v] == 0){ // 如果该邻接点未分配
                dfs(v, -c); // 则将该邻接点设为与当前结点相反的集合
            }
            // 如果该邻接点已经是与当前结点相反的集合了，说明该邻接点已经使用过了dfs，相当于visited了，不需要再次dfs，同时该邻接点本身也不违背与当前结点的集合相反的性质。
        }
    }
}
/**
 * 自己的思路，与官解相同，除了dfs的具体实现不同
 * O(V + E), S(V)
 */
class Solution {
    int unAllocated; // 起始状态，未被分配
    int set; // 集合A或B
    public boolean isBipartite(int[][] graph) {
        unAllocated = 0;
        set = 1; // +1 => 集合A，-1 => 集合B

        int len = graph.length;
        int[] type = new int[len];

        for(int u = 0; u < len; u++){ // 访问graph中的所有结点
            if(type[u] == unAllocated){ // 如果该结点没被分配过属于哪个集合
                boolean flag = dfs(graph, u, type, set); // 则分配其为集合A，并将该结点的所有临界点设为集合B。若存在临界点也是集合A，则无法二分图。
                if(!flag){
                    return false; // flag = false
                }
            }
        }

        return true;
    }

    private boolean dfs(int[][] graph, int u, int[] type, int set){
        type[u] = set; // 设当前结点的类型为"set"
        for(int v: graph[u]){ // 对于当前结点的所有邻接点
            if(type[v] == set){ // 若存在与该结点相同集合的邻接点，则无法二分图。
                return false;
            }
            if(type[v] == unAllocated){ // 如果该邻接点未被分配过
                boolean flag = dfs(graph, v, type, -set); // 则将其设置为与该结点类型相反的集合
                if(!flag){
                    return false;
                }
            }
        }
        return true;
    }
}