package 二十一天刷题20220405.day6二分搜索的边界问题;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

public class 按权重随机选择528 {
    public int[] per;

    //先拿前缀和，再获取一个随机数，最后二分搜索找右边界
    public 按权重随机选择528(int[] w) {
        per = new int[w.length + 1];
        for (int i = 1; i < per.length; i++) {
            per[i] = w[i - 1] + per[i - 1];
        }
    }

    public int pickIndex() {
        Random ran = new Random();
        int pos = ran.nextInt(per[per.length - 1] + 1);
        //左闭右开
        int left = 0, right = per.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (per[mid] == pos) {
                left = mid + 1;
            } else if (per[mid] < pos) {
                left = mid + 1;
            } else if (per[mid] > pos) {
                right = mid;
            }
        }
        if (left == 0) {
            left++;
        }
        if (left == per.length) {
            left--;
        }
        return --left;
    }

    public static void main(String[] args) {
        按权重随机选择528 aa = new 按权重随机选择528(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10,1001});
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            int ss = aa.pickIndex();
            Integer orDefault = map.getOrDefault(ss, 0);
            map.put(ss, orDefault + 1);
            // System.out.print(ss);
            // System.out.print(",");
        }
        System.out.println(",");
        int sum = map.values().stream().flatMapToInt(IntStream::of).sum();
        System.out.println(sum);
        map.forEach((k, v) -> {
            System.out.println(k + "," + v + "," + v * 100f / sum + "%" + ",标准：" + (aa.per[k + 1] - aa.per[k]) * 100f / aa.per[aa.per.length - 1] + "%");
        });

    }
}
