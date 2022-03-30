package 图论;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leewencan
 * @date 2022/3/25 16:19
 */
public class Djkstra {
    public static Integer max = Integer.MAX_VALUE;
    public static int[][] map = {
            {0, 1, 12, max, max, max},
            {max, 0, 7, 3, max, max},
            {max, max, 0, max, 5, max},
            {max, max, 5, 0, 10, 13},
            {max, max, max, max, 0, 4},
            {max, max, max, max, max, 0}};

    public static int[] getShortest() {
        int[] ints = map[0];
        List<Integer> passed = new ArrayList<>();
        List<Integer> noPass = new ArrayList<>();
        for (int i = 1; i < map.length; i++) {
            noPass.add(i);
        }
        passed.add(0);
        while (noPass.size() > 0) {
            int index = getMin(ints, passed);
            noPass.remove((Object) index);
            int base = ints[index];
            for (int i = 0; i < ints.length; i++) {
                if (map[index][i] < max) {
                    int temp = base + map[index][i];
                    if (temp < ints[i]) {
                        ints[i] = temp;
                    }
                }
            }
        }
        return ints;
    }

    private static int getMin(int[] ints, List<Integer> passed) {
        int min = max;
        int index = 0;
        for (int i = 0; i < ints.length; i++) {
            if (passed.contains(i)) {
                continue;
            }
            if (ints[i] < min) {
                min = ints[i];
                index = i;
            }
        }
        passed.add(index);
        return index;
    }

    public static void main(String[] a) {
        int[] shortest = getShortest();
        for (int i : shortest) {
            System.out.println(i);
        }
    }

}
