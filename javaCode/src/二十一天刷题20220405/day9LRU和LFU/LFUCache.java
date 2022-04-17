package 二十一天刷题20220405.day9LRU和LFU;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/lfu-cache/solution/by-leemanshow-kckw/
 *
 * LFU中F代表frequency，所以要维护一个key到value的表、一个key到freq的表，一个freq到key的表
 * 需要注意的是，可能有多个key的freq值都一样，也就是多个key的访问次数都一样，此时先删除最先插入的key
 * 因此freq到key的映射表中考虑采用LinkedHashSet，结合了链表和哈希的功能，可以O(1)查找，又保存了插入的先后顺序。
 * 因为要删除最小的freq，索引考虑维护一个minFreq变量专门来记录
 *
 * @param <K>
 * @param <V>
 */
public class LFUCache<K, V> {
    private final int capacity;
    int minFreq;
    final HashMap<K, V> map;
    final HashMap<K, Integer> keyToFreq;
    final Map<Integer, LinkedHashSet<K>> freqToKey;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKey = new HashMap<>();
    }

    public V get(K key) {
        if (map.containsKey(key)) {
            increaseFreq(key);
            return map.get(key);
        }
        return null;
    }

    public void put(K key, V value) {
        if(capacity<=0){
            return;
        }
        if(map.containsKey(key)){
            map.put(key, value);
            increaseFreq(key);
            return;
        }
        if (map.size() == capacity){
            deleteMinFreq();
        }
        map.put(key, value);
        keyToFreq.put(key, 1);
        LinkedHashSet<K> ks = freqToKey.computeIfAbsent(1, k -> new LinkedHashSet<>());
        ks.add(key);
        minFreq = 1;
    }

    public void delete(K key) {
        if (!map.containsKey(key)) {
            return;
        }
        map.remove(key);
        Integer freq = keyToFreq.get(key);
        keyToFreq.remove(key);
        freqToKey.get(freq).remove(key);
        //因为这里是删除，所以capacity的值必会减一，下次插入值的时候freq会变成1，因此无需现在无须找到删除key后的minFreq
    }


    private void deleteMinFreq() {
        LinkedHashSet<K> ks = freqToKey.get(minFreq);
        K key = ks.iterator().next();
        ks.remove(key);
        if (ks.size() == 0) {
            freqToKey.remove(minFreq);
            //因为这里是删除，所以capacity的值必会减一，下次插入值的时候freq会变成1，因此无需现在无须找到删除key后的minFreq
        }
        map.remove(key);
        keyToFreq.remove(key);
    }

    private void increaseFreq(K key) {
        int freq = keyToFreq.getOrDefault(key, 0);
        if (freq != 0) {
            freqToKey.get(freq).remove(key);
        }
        int latestFreq = freq + 1;
        keyToFreq.put(key, latestFreq);
        LinkedHashSet<K> ks = freqToKey.get(freq);
        ks.remove(key);
        if(ks.size() == 0){
            freqToKey.remove(freq);
            if (freq == minFreq) {
                minFreq = latestFreq;
            }
        }
        LinkedHashSet<K> ksLatest = freqToKey.get(latestFreq);
        if (ksLatest == null) {
            ksLatest = new LinkedHashSet<K>();
            freqToKey.put(latestFreq, ksLatest);
        }
        ksLatest.add(key);
    }

    public static void main(String[] args) {
        LFUCache<Integer,Integer> lfUCache = new LFUCache(2);
        lfUCache.put(1,1);
        lfUCache.put(2,2);
        lfUCache.get(1);
        lfUCache.put(3,3);
        lfUCache.get(2);
        lfUCache.get(3);
        lfUCache.put(4,4);
        lfUCache.get(1);
    }

}
