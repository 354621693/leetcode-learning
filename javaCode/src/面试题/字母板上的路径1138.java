package 面试题;

/**
 * 10进制转5进制
 * 我们从一块字母板上的位置 (0, 0) 出发，该坐标对应的字符为 board[0][0]。
 *
 * 在本题里，字母板为board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"]，如下所示。
 *
 *
 *
 * 我们可以按下面的指令规则行动：
 *
 * 如果方格存在，'U' 意味着将我们的位置上移一行；
 * 如果方格存在，'D' 意味着将我们的位置下移一行；
 * 如果方格存在，'L' 意味着将我们的位置左移一列；
 * 如果方格存在，'R' 意味着将我们的位置右移一列；
 * '!' 会把在我们当前位置 (r, c) 的字符 board[r][c] 添加到答案中。
 * （注意，字母板上只存在有字母的位置。）
 *
 * 返回指令序列，用最小的行动次数让答案和目标 target 相同。你可以返回任何达成目标的路径。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：target = "leet"
 * 输出："DDR!UURRR!!DDD!"
 * 示例 2：
 *
 * 输入：target = "code"
 * 输出："RR!DDRR!UUL!R!"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/alphabet-board-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 字母板上的路径1138 {
    public static String alphabetBoardPath(String target) {
        char starter = 'a';
        int x = 0, y = 0;
        StringBuilder sb = new StringBuilder();
        for (char tc : target.toCharArray()) {
            int xTarget = (tc - starter) / 5;
            int yTarget = (tc - starter) % 5;
            dealSb(xTarget - x, yTarget - y, sb);
            x = xTarget;
            y = yTarget;
            System.out.println(xTarget+","+yTarget);
        }
        return sb.toString();
    }

    private static void dealSb(int x, int y, StringBuilder sb) {
        if (x < 0) {
            dealSb(x,"U",sb);
        }
        if (y < 0) {
            dealSb(y,"L",sb);
        }
        if (y > 0) {
            dealSb(y,"R",sb);
        }
        if (x > 0) {
            dealSb(x,"D",sb);
        }
        sb.append("!");
    }

    private static void dealSb(int step, String ch, StringBuilder sb) {
        step = Math.abs(step);
        for (int i = 0; i < step; i++){
            sb.append(ch);
        }
    }

    public static void main(String a[]){
        String leet = alphabetBoardPath("leet");
        System.out.println(leet);
    }
}
