package 二十一天刷题20220405.day12图论基础;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class 图所有可能的路径797 {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph,0,path);
        return res;
    }

    /**
     * 深度优先（DFS）递归遍历方法
     * @param graph 邻接表
     * @param vertex 即将被遍历的节点
     * @param path 当前在走的路径
     */
    public void traverse(int[][] graph,int vertex, LinkedList<Integer> path){
        path.add(vertex);
        int n = graph.length;
        if(vertex == n-1){
            res.add(new LinkedList<>(path));
            //return;
        }
        for(int NextVertex:graph[vertex]){
            traverse(graph,NextVertex,path);
        }
        path.removeLast();
    }
}
