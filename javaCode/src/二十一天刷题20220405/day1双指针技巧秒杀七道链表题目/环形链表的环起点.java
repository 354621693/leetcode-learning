package 二十一天刷题20220405.day1双指针技巧秒杀七道链表题目;

import linkedList.ListNode;

public class 环形链表的环起点 {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head,quick = head;
        while(quick!=null&&quick.next!=null){
            slow = slow.next;
            quick = quick.next.next;
            if(slow==quick){
                slow = head;
                break;
            }
        }
        if(quick==null||quick.next==null){
            return null;
        }
        while(slow!=quick){
            slow = slow.next;
            quick = quick.next;
        }
        return slow;
    }
}
