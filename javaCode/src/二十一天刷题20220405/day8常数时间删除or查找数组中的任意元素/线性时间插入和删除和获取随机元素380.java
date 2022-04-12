package 二十一天刷题20220405.day8常数时间删除or查找数组中的任意元素;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class 线性时间插入和删除和获取随机元素380 {
    Map<Integer, Integer> map;
    Vector<Integer> array;

    public 线性时间插入和删除和获取随机元素380() {
        array = new Vector<>();
        map = new HashMap<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        //插在末尾
        array.add(val);
        map.put(val, array.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val);
        int temp = array.lastElement();
        array.set(array.size() - 1, array.get(index));
        array.set(index, temp);
        map.put(temp, index);
        map.remove(val);
        array.remove(array.size() - 1);
        return true;
    }

    public int getRandom() {
        Random random = new Random();
        int index = random.nextInt(array.size());
        return array.get(index);
    }
}
