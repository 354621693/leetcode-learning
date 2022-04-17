package 二十一天刷题20220405.day9LRU和LFU;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/lru-cache/solution/by-leemanshow-42gq/
 * @author leewencan
 * @date 2022/4/13 19:00
 * @description LRU缓存机制
 * 首先思考方法，
 * 1、在LRUCache中，要求get（）和put()的时间复杂度都为O(1),首先我们应该想到HashMap，因为哈希表的查找和插入时间复杂度就是O(1)；
 * 2、要求当容量到顶时，先逐出 最久未使用的key再插入，那就要要做到value有顺序，有序可以使用数组或者链表，但是数组的插入很慢，链表的插入和删除很快，但是查找很慢
 * 3、可以使用HashMap+链表的形式，为了快速删除，我们使用双向链表（每个节点储存上一个、下一个节点，这样删除该节点时，只需要将他的上下节点连接起来就行了。）
 * 4、map中存储的key是题目中的key，value是Node的应用；链表中存的是key，题目中的value值。（为甚么要在链表中存key值呢？因为在删除最久未使用的节点的时候，我们删除的是链表最末尾的节点，然后我们还需要将map中对应的值删除，所以我们要在链表节点中存储key值，才能在O（1）复杂度找到map中的key）
 * <p>
 * put的时候：
 * 1、更新map
 * 2、判断容量，如果此时map的大小等于最大容量，执行删除最久未使用的key
 * 3、在链表中删除该节点
 * 4、将该节点更新到链表的尾部
 * <p>
 * get的时候：
 * 1、从map找到key对应的Node
 * 2、在链表中删除该节点
 * 3、将该节点插入到链表的尾部
 * <p>
 * 删除：
 * 1、到链表头部的节点对应的key，在map中将此key删除
 * 2、将链表头部删除
 */
public class LRUCache {
    private final int capacity;
    private final HashMap<Integer, LinkedNode> map;
    private final DoubleLinked doubleLinked;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        doubleLinked = new DoubleLinked();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            LinkedNode node = map.get(key);
            updateToTail(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        LinkedNode node = map.get(key);
        if (node != null) {
            node.value = value;
            updateToTail(node);
        } else {
            if (map.size() == capacity) {
                LinkedNode head = doubleLinked.deleteHead();
                map.remove(head.key);
            }
            node = new LinkedNode(key, value);
            map.put(key,node);
            doubleLinked.insertToTail(node);
        }
    }

    private void updateToTail(LinkedNode node) {
        doubleLinked.insertToTail(doubleLinked.delete(node));
    }


    class DoubleLinked {
        /**
         * 固定的头部和尾部，不存有意义的值，只用来定位；因此真正有意义的头部是head.next，尾部是tail.prev
         * 定义：新的值或者最新改动的值都插到尾部，头部存放的是最旧的值
         */
        private final LinkedNode head;
        private final LinkedNode tail;

        public DoubleLinked() {
            head = new LinkedNode();
            tail = new LinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        private void insertToTail(LinkedNode node) {
            LinkedNode tailNow = getTail();
            tailNow.next = node;
            node.prev = tailNow;
            node.next = this.tail;
            this.tail.prev = node;
        }

        private LinkedNode delete(LinkedNode node) {
            LinkedNode prev = node.prev;
            LinkedNode next = node.next;
            prev.next = next;
            next.prev = prev;
            return node;
        }

        private LinkedNode deleteHead() {
            return delete(this.head.next);
        }

        private LinkedNode getTail() {
            return tail.prev;
        }

    }

    class LinkedNode {
        int key;
        int value;
        LinkedNode prev;
        LinkedNode next;

        public LinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public LinkedNode() {
        }
    }


    public static void main(String a[]){
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.get(1);
        lruCache.put(3,3);
        lruCache.get(2);
        return;
    }
}
