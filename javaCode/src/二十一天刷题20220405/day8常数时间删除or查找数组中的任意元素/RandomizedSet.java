package 二十一天刷题20220405.day8常数时间删除or查找数组中的任意元素;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author leewencan
 * @date 2022/4/13 16:20
 */
class RandomizedSet {
    //用ArrayList加HashMap
    private ArrayList<Integer> array;
    private HashMap<Integer,Integer> map;
    public RandomizedSet() {
        array = new ArrayList<>();
        map = new HashMap<>();
    }

    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }
        array.add(val);
        map.put(val,array.size()-1);
        return true;
    }

    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }
        int index = map.get(val);
        map.remove(val);
        int lastVal = array.remove(array.size()-1);
        //先删了，所以size已经减小了
        if(index == array.size()){
            return true;
        }
        array.set(index,lastVal);
        map.put(lastVal,index);
        return true;
    }

    public int getRandom() {
        Random random = new Random();
        int index = random.nextInt(array.size());
        return array.get(index);
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(1);
        randomizedSet.insert(2);
        randomizedSet.remove(1);
        System.out.println(randomizedSet.getRandom());
        System.out.println(randomizedSet.getRandom());

    }
}
