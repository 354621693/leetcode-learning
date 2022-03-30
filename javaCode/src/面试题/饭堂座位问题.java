package 面试题;

import java.util.Arrays;
import java.util.Scanner;

public class 饭堂座位问题 {
    /**
     * 描述
     * 单位食堂为员工提供就餐环境，但是在疫情期间，根据防疫管控的要求，需要控制同时进入食堂就餐的人数。
     * 单位食堂很大，我们使用一个n*m的四连通网格图来表示，食堂里摆放着许多桌子，每张桌子大小均为1*1。现在使用"."（点号)表示空地，"#"号表示桌子，食堂的情况示例如下图：
     * 许多的桌子形成许多桌子连通块，这些桌子连通块都是实心的，不会出现一堆桌子包围着几个空地的情况；也就是说所有空地都是连通的。
     * 每张桌子都有上、下、左、右4个座位，但仅当它的一侧为空地时才可以坐人；因为食堂四周都是墙，被墙挡住的座位是不能坐人的；有的桌子被其它桌子夹在中间，被夹在中间的桌子也不能坐人。如下图所标，标记为蓝色的座位才可以坐人，共有15个可以坐人的座位：
     * 现在根据防控管控的要求，限制在同一桌子连通块中，两个连续的座位不能同时坐人。这里的连续包含拐角的连续，即拐角两侧不能同时坐人；比如上图中（红色标记），最多只能坐8人。
     * 请编写程序，在给定食堂中空地（"."）、桌子（"#"）的情况下，算出食堂最多能坐多少个人，以及所有可行的坐人方案的方案数量（可以一个人也不坐），答案对998244353取模。
     * <p>
     * 输入
     * 第一行给出两个正整数n,m（空格隔开）
     * 接下来有n行，每行m个字符，每个字符均为”.”或”#”
     * <p>
     * 输出
     * 一行两个数（空格隔开），表示饭堂最多能坐多少个人、所有可行的坐人方案的方案数。
     * 输入样例1
     * 1 2
     * #.
     * 输出样例1
     * 1 2
     * 输入样例2
     * 3 3
     * ...
     * .#.
     * ...
     * 输出样例2
     * 2 7
     */

    String[] array = {};
    public static char[][] fanTang = {
            {'.', '.', '.'},
            {'.', '.', '.'},
            {'#', '#', '.'},
            {'.', '.', '.'},
            {'.', '.', '#'}
    };
    static int[][] vent = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        System.out.print(col + "" + row);
        scanner.nextLine();
        String[][] fan = new String[row][col];
        for (int i = 0; i < row; i++) {
            fan[i] = scanner.nextLine().split(" ");
            System.out.print(Arrays.toString(fan[i]));
        }
    }

    public static int[][] checkChair(String[][] fan) {
        int[][] res = new int[fan.length][fan[0].length];
        for (int i = 0; i <= res.length - 1; i++) {
            for (int j = 0; j < res[0].length; j++) {
                if (fan[i][j].equals("#")) {
                    res[i][j] = -1;
                    checkPerPoint(fan, res, i, j);
                }
            }
        }
        return res;
    }

    private static void checkPerPoint(String[][] fan, int[][] res, int x, int y) {
        for (int i = 0; i < vent.length; i++) {
            int xNow = x + vent[i][0];
            int yNow = x + vent[i][1];
            if (legal(xNow, yNow, fan) && fan[xNow][yNow].equals("#")){
                res[x][yNow] = -1;
                checkPerPoint(fan, res,xNow, yNow);
            }
        }
    }

    private static boolean legal(int xNow, int yNow, String[][] fan) {
        return xNow >= 0 && yNow >= 0 && xNow < fan.length && yNow < fan[0].length;
    }


}
