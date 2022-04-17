package 二十一天刷题20220405.day13;

import java.util.LinkedList;

public class 判断二分图BFS {
    LinkedList<Integer> queue = new LinkedList<>();

    boolean[] visited;

    //颜色数组，true和false对应两种颜色，这样第一节点的颜色就默认是false了
    boolean[] color;

    boolean valid = true;

    public boolean isBipartite(int[][] graph) {
        visited = new boolean[graph.length];
        color = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                bfs(graph, i);
            }
        }
        return valid;
    }

    //广度优先遍历一般适用于一个队列来保证遍历的顺序，在循环开始之前，先将第一个节点放到队列中，循环终止的条件就是队列为空，在每一次循环中，只处理当前节点的数据，处理完后，将当前节点持有的所有子节点加入队列，结束该次循环。
    private void bfs(int[][] graph, int vertex) {
        visited[vertex] = true;
        queue.offer(vertex);
        while (!queue.isEmpty() && valid) {
            Integer v = queue.poll();
            for (int nextV : graph[v])
                if (!visited[nextV]) {
                    color[nextV] = !color[v];
                    visited[nextV] = true;
                    queue.offer(nextV);
                } else {
                    if (color[nextV] == color[v]) {
                        valid = false;
                        break;
                    }
                }
        }
    }
}
