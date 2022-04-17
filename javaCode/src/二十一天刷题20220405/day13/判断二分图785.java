package 二十一天刷题20220405.day13;

/**
 * https://leetcode-cn.com/problems/is-graph-bipartite/solution/shen-by-leemanshow-gry8/
 *  深度优先遍历
 *  深度优先遍历，用两种颜色填充，一开始所有的顶点都是0，然后遍历图并填充颜色，用int[] color来保存，这个数组还具备了visited数组的功能，用来记录已经访问过的节点，防止环状图的出现。
 * 因为这是无向图，当一个已经被填充颜色的节点被访问到的时候，如果将被填充的颜色和这个节点上一次被填充的颜色不同，即代表距离上一次访问这个节点后，经过了奇数次的跳跃（遍历）就到达了这个节点，因此可以判断这个路径上存在至少一个节点违反了规则。
 */
public class 判断二分图785 {
    static int UNVISITED = 0;
    static int RED = 1;
    static int GREEN = 2;

    //颜色记录数组，实际上兼顾了visited数组的功能
    int[] color;
    boolean valid = true;

    public boolean isBipartite(int[][] graph) {
        color = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == UNVISITED) {
                dfs(graph, i, RED);
            }
        }
        return valid;
    }

    void dfs(int[][] graph, int vertex, int color) {
        this.color[vertex] = color;
        int nextColor = color == RED ? GREEN : RED;
        for (int neighbor : graph[vertex]) {
            if(this.color[neighbor] == UNVISITED){
                dfs(graph, neighbor,nextColor);
                //优化，当深度遍历完邻居后看看是否已经有结果了。
                if(!valid){
                    return;
                }
            }else{
                if(this.color[neighbor]!=nextColor){
                    valid = false;
                    return;
                }
            }
        }
    }

}
