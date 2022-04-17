package BFS;

import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/sliding-puzzle/
 * https://leetcode-cn.com/problems/sliding-puzzle/solution/bfs-by-leemanshow-3f5d/
 *
 * 广度优先搜索，注意要用sz这个变量来控制每一步的遍历，而不是统计暴力遍历的次数
 */
public class 华容道773 {
    static int[][] neighbors = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

    String rightAns = "123450";

    public int slidingPuzzle(int[][] board) {
        LinkedList<String> queue = new LinkedList<>();
        LinkedHashSet<String> visited = new LinkedHashSet<>();

        StringBuilder sb = new StringBuilder();
        int step = 0;
        for (int[] ints : board) {
            for (int i : ints) {
                sb.append(i);
            }
        }
        queue.offer(sb.toString());
        while (!queue.isEmpty()) {
            //sz在此处代表当前下一步可以选择的数量，比如当step = 0时，选择只有一个，就是输入的情景，当step等于2时，选择就是neighbors数组中0对应的数组的长度
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                String ans = queue.poll();
                if (!visited.contains(ans.toString())) {
                    visited.add(ans.toString());
                    if (ans.equals(rightAns)) {
                        return step;
                    }
                    int index_0 = 0;
                    //System.out.println(ans);
                    for (; ans.charAt(index_0) != '0'; index_0++) ;
                    for (int neighbor : neighbors[index_0]) {
                        String nextAns = swap(ans, index_0, neighbor);
                        queue.offer(nextAns);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String swap(String ans, int index_0, int neighbor) {
        char[] chars = ans.toCharArray();
        Character ch = chars[index_0];
        chars[index_0] = chars[neighbor];
        chars[neighbor] = ch;
        return new String(chars);
    }

}
