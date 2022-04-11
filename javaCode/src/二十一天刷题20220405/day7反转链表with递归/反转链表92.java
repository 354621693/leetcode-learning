package 二十一天刷题20220405.day7反转链表with递归;

import linkedList.ListNode;

public class 反转链表92 {
    public ListNode reverseBetweenByIteration(ListNode head, int leftV, int rightV) {
        ListNode left,right,pos = head;
        while(pos.val!=leftV-1){
            pos = pos.next;
        }
        ListNode temphead = pos;
        left = pos.next;
        //pos = pos.next;
        ListNode templ = pos;
        pos = pos.next;
        ListNode tempr;
        while(pos.val!=rightV+1){
            templ = pos;
            tempr = pos.next;
            pos.next = templ;
            pos=tempr;
        }
        right = pos;
        ListNode tempTail = right.next;
        temphead.next = right;
        left.next = tempTail;
        return head;
    }

    /**
     * 以下是递归法
     * https://labuladong.gitee.io/algo/2/17/17/
     */
    public ListNode temp;

    public ListNode reverseBetween(ListNode head, int leftV, int rightV) {
        if(leftV==1){
            return reverseBetween(head,rightV);
        }
        head.next =  reverseBetween(head.next,--leftV,--rightV);
        return head;
    }

    public ListNode reverseBetween(ListNode head, int rightV){
        if(rightV==1){
            temp = head.next;
            return head;
        }
        ListNode last = reverseBetween(head.next,rightV-1);
        head.next.next = head;
        head.next = temp;
        return last;
    }
}
