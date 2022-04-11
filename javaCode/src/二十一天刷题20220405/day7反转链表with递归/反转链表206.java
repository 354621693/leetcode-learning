package 二十一天刷题20220405.day7反转链表with递归;

import linkedList.ListNode;

public class 反转链表206 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode lastNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return lastNode;
    }

    public ListNode reverseListIteration(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while(curr !=null){
            ListNode next = head.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
