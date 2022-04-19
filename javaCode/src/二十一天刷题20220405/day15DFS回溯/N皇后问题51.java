package 二十一天刷题20220405.day15DFS回溯;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * n皇后问题 研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的n皇后问题 的解决方案。
 * 每一种解法包含一个不同的n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * 示例 2：
 * 输入：n = 1
 * 输出：[["Q"]]
 * <p>
 * 链接：https://leetcode-cn.com/problems/n-queens
 *
 * 深度优先搜索解决，注意：
 * 这里的解法棋盘是从上到下一行一行尝试放置，显然每行只能放置一个Q，所以我们做选择的代码就只需要遍历row指向的那一行，然后进入canPut函数进行判断。
 * 这个函数只需要判断列，左上、右上方向有没有冲突就行了。因为是从上到下放置的，所以左下、右下是肯定没有Q能射到我们。
 * 复杂度：
 * 时间复杂度方面：决策树的最大宽度是n的n次方，即为O(n^n)，而canPut函数里的复杂度为O(n)，所以总复杂度为O(n^(n+1))
 * 空间复杂度：O(n)
 * https://leetcode-cn.com/problems/n-queens/solution/dfs-by-leemanshow-no7i/
 */
public class N皇后问题51 {
    List<List<String>> res = new LinkedList<>();
    String emptyBoard = null;

    public List<List<String>> solveNQueens(int n) {
        ArrayList<String> board = new ArrayList<>(n + 1);
        //填充棋盘
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(".");
        }
        emptyBoard = sb.toString();
        for (int i = 0; i < n; i++) {
            board.add(emptyBoard);
        }
        traverse(board, 0);
        return res;
    }

    private void traverse(ArrayList<String> board, int row) {
        //终止条件
        if (row == board.size()) {
            res.add(new ArrayList<>(board));
            return;
        }
        //做选择
        for (int i = 0; i < board.get(0).length(); i++) {
            if (canPut(board, row, i)) {
                char[] chars = board.get(row).toCharArray();
                chars[i] = 'Q';
                board.set(row, new String(chars));
                traverse(board, row + 1);
                //撤销选择
                board.set(row, emptyBoard);
            }
        }
    }

    private boolean canPut(ArrayList<String> board, int row, int index) {
        // char[] chars = board.get(row).toCharArray();
        // chars[index] = 'X';
        // board.set(row, new String(chars));
        // System.out.println(board);
        // board.set(row, emptyBoard);

        int length = board.get(0).length();
        //判断列是否冲突
        for (int i = 0; i < row; i++) {
            if (board.get(i).charAt(index) == 'Q') {
                return false;
            }
        }
        //判断左上角是否冲突
        for (int i = row - 1, temp = index - 1; i >= 0 && temp >= 0; i--, temp--) {
            if (board.get(i).charAt(temp) == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, temp = index + 1; i >= 0 && temp < length; i--, temp++) {
            if (board.get(i).charAt(temp) == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        N皇后问题51 ss = new N皇后问题51();
        List<List<String>> lists = ss.solveNQueens(8);
        Iterator<List<String>> iterator = lists.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
