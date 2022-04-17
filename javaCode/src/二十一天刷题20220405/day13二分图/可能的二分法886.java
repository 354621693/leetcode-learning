package 二十一天刷题20220405.day13二分图;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/possible-bipartition/solution/by-leemanshow-cnnz/
 * 给定一组n人（编号为1, 2, ..., n），我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
 * 给定整数 n和数组 dislikes，其中dislikes[i] = [ai, bi]，表示不允许将编号为 ai和bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
 *
 * 示例 1：
 * 输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
 * 输出：true
 * 解释：group1 [1,4], group2 [2,3]
 *
 * 示例 2：
 * 输入：n = 3, dislikes = [[1,2],[1,3],[2,3]]
 * 输出：false
 *
 * 示例 3：
 * 输入：n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * 输出：false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/possible-bipartition
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 可能的二分法886 {
    static int UNVISITED = 0;
    static int RED = 1;
    static int GREEN = 2;
    int[] color;

    boolean valid = true;

    /**
     * 注意题目里面数据的编号从1开始
     * 尝试用填色法，当没有颜色冲突时并完成所有点的着色，即表示可以分组，这时再按颜色提取两组就完事了
     */

    public boolean possibleBipartition(int n, int[][] dislikes) {
        color = new int[n + 1];
        List<Integer>[] graph = build(dislikes, n);
        for (int i = 1; i <= n; i++) {
            if (color[i] == UNVISITED) {
                dfs(graph, i, RED);
            }
        }
        return valid;
    }

    private void dfs(List<Integer>[] graph, int vertex, int color) {
        this.color[vertex] = color;
        int nextColor = color == RED ? GREEN : RED;
        for (Integer nextV : graph[vertex]) {
            if (this.color[nextV] == UNVISITED) {
                dfs(graph, nextV, nextColor);
                if(valid==false){
                    return;
                }
            } else {
                if (this.color[nextV] != nextColor) {
                    valid = false;
                    return;
                }
            }
        }
    }

    private List<Integer>[] build(int[][] dislikes, int n) {
        List<Integer>[] res = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            res[i] = new LinkedList<>();
        }
        for (int[] dislike : dislikes) {
            res[dislike[0]].add(dislike[1]);
            res[dislike[1]].add(dislike[0]);
        }
        return res;
    }
}
