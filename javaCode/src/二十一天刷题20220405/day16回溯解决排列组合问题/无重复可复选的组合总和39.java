package 二十一天刷题20220405.day16回溯解决排列组合问题;

import java.util.LinkedList;
import java.util.List;

public class 无重复可复选的组合总和39 {
    LinkedList<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> list = new LinkedList<>();
    int targetNum = 0;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        targetNum = target;
        traverse(candidates, 0,0);
        return result;
    }

    public void traverse(int[] candidates, int index, int sumNow) {
        if (sumNow == targetNum) {
            result.add(new LinkedList<>(list));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (targetNum < sumNow + candidates[i]) {
                continue;
            }
            sumNow += candidates[i];
            list.add(candidates[i]);
            //在不可复选的组合问题里，这里的值是i+1，此问题中用i，就达到了数组中的数字可重复使用，又达到了子集的效果（集中的元素不看顺序）
            traverse(candidates, i, sumNow);
            list.removeLast();
            sumNow -= candidates[i];

        }
    }
}
