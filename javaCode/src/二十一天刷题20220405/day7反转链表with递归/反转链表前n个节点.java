package 二十一天刷题20220405.day7反转链表with递归;

import linkedList.ListNode;

/**
 * 因为整个链表反转后原来的 head 变成了整个链表的最后一个节点。但现在 head 节点在递归反转之后不一定是最后一个节点了，所以要记录后驱 temp（第 n + 1 个节点），反转之后将 head 连接上
 * https://labuladong.gitee.io/algo/2/17/17/
 */
public class 反转链表前n个节点 {
    ListNode temp;
    public ListNode reverseList(ListNode head,int n) {
        if(n==1){
            temp = head.next;
            return head;
        }
        ListNode last = reverseList(head.next, n-1);
        head.next.next = head;
        head.next = temp;
        return last;
    }
}
