package dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

public class EggProblem {
    Map<Integer, Integer> map = new HashMap<>();

    Integer[][] matrix = null;

    public int howManyMove(int k, int n) {
        int ans = Integer.MAX_VALUE;
        if (k <= 1 || n == 1 || n == 0) {
            ans = n;
        }
        int l = 1, h = n;
        for (int i = 1; i <= n; i++) {
            int t1 = howManyMove(k, n - i);
            int t2 = howManyMove(k - 1, i - 1);
//            ans = Math.max(t1, t2) + 1;
            int temp = Math.max(t1, t2) + 1;
//            if (temp < ans) {
//                System.out.println("i=" + i + "ans=" + temp);
//            }
            ans = Math.min(temp, ans);
        }

        return ans;
    }

    public int solveWith2fen(int k, int n) {
        if (k == 1 || n == 0 || n == 1) {
            return n;
        }
        if (matrix == null) {
            createMatrix(k, n);
        }
        if (matrix[k-1][n-1] == null) {
            int l = 0, r = n;
            while (l <= r) {
                int mid = (l + n) / 2;
                int t1 = solveWith2fen(k, n - mid);
                int t2 = solveWith2fen(k - 1, mid - 1);
                if (t2 > t1) {
                    r = mid;
                } else if (t1 > t2) {
                    l = mid;
                } else {
                    l = r = mid;
                }
            }
            int ans = 1 + Math.min(Math.max(solveWith2fen(k, n - l), solveWith2fen(k - 1, l - 1)), Math.max(solveWith2fen(k, n - r), solveWith2fen(k - 1, r - 1)));
            matrix[k-1][n-1] = ans;
        }
        return matrix[k][n];
    }

    private void createMatrix(int k, int n) {
        matrix = new Integer[k][n];
    }

    public static void main(String args[]) {
        EggProblem problem = new EggProblem();
//        int i = problem.howManyMove(2, 100);
        int i = problem.solveWith2fen(2, 6);
        System.out.println("ans=" + i);
    }


    static class Person{
        public void p(int i){

        }
    }
    static class Fucker extends Person{
        public void p(int i){

        }
    }

    static Fucker f = new Fucker();

    public static void larger() {
        EggProblem problem = new EggProblem();
//        int i = problem.howManyMove(2, 100);
        int i = problem.solveWith2fen(2, 6);
        System.out.println("ans=" + i);

    }
}
