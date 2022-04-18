package 二十一天刷题20220405.day14BFS;

import java.util.LinkedHashSet;
import java.util.LinkedList;

public class 打开转盘锁752 {
    public int openLock(String[] deadends, String target) {
        String word = "0000";
        LinkedList<String> queue = new LinkedList<>();
        LinkedHashSet<String> visited = new LinkedHashSet<>();
        for (String badWord : deadends) {
            visited.add(badWord);
        }
        int step = 0;
        if(!visited.contains(word)){
            queue.offer(word);
        }
        int[] opears = {1, -1};
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                String cur = queue.poll();
                if (cur.equals(target)) {
                    return step;
                }
                //通过遍历找出下一次转动后可能的值
                for (int j = 0; j < 4; j++) {
                    for (int opear : opears) {
                        char[] chars = cur.toCharArray();
                        char c = chars[j];
                        int cc = Integer.parseInt(String.valueOf(c));
                        c = (char) ('0' + ((cc + 10 + opear) % 10));
                        chars[j] = c;
                        String next = new String(chars);
                        // System.out.println(next);
                        if (!visited.contains(next)) {
                            queue.offer(next);
                            visited.add(next);
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static void main(String[] args) {
        打开转盘锁752 ss = new 打开转盘锁752();
        int i = ss.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202");
        return;
    }

    // 将 s[j] 向上拨动一次
    String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }
    // 将 s[i] 向下拨动一次
    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }
}
