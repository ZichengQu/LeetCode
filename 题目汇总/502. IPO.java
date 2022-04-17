/**
 * 自己思路，没官解思路好
 * O(nlogn + kn), S(n)
 */
class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        List<int[]> list = new LinkedList<>();
        for(int i = 0; i < profits.length; i++){
            list.add(new int[]{profits[i], capital[i]}); // profit, capital
        }
        // 优先将利润从大到小排，其次将启动资金从小到大排。可通过贪心思想保证第一个满足启动资金的情况下的利润最大
        Collections.sort(list, (arr1, arr2)-> arr1[0] == arr2[0]? arr1[1] - arr2[1]: arr2[0] - arr1[0]); // O(nlogn)

        for(int i = 0; i < k; i++){ // O(kn)
            int oldW = w;
            Iterator it = list.iterator();
            while(it.hasNext()){
                int[] proj = (int[])it.next();
                if(proj[1] <= w){ // 一直往下遍历，直到满足启动资金
                    w += proj[0]; // 更新资金
                    it.remove(); // 用了这个项目，则将其从未使用列表中删除
                    break;
                }
            }
            if(oldW == w){ // 如果遍历一遍后，累计资金没有变化，说明剩余项目都不满足了，则直接退出
                break;
            }
        }

        return w;
    }
}

/**
 * 官解思路
 * O(nlogn + klogn), S(n)
 */
class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int len = profits.length;
        int[][] proj = new int[len][2]; // project
        for(int i = 0; i < len; i++){
            proj[i][0] = profits[i];
            proj[i][1] = capital[i];
        }
        Arrays.sort(proj, (arr1, arr2) -> arr1[1] - arr2[1]); // 只按启动资金从小到大排列

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1); // 存入利润，大顶堆
        int index = 0; // 数组 proj 的下标
        for(int i = 0; i < k; i++){ // 取 k 个
            while(index < len && proj[index][1] <= w){ // 如果启动资金满足条件
                pq.offer(proj[index][0]); // 将该利润放入大顶堆中
                index++;
            }
            if(!pq.isEmpty()){ // 如果大顶堆不空，则从中取出最大利润
                w += pq.poll();
            }else{ // 如果大顶堆空了，说明没有符合条件的项目了
                break;
            }
        }

        return w;
    }
}