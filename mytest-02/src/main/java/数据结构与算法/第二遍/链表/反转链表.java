package 数据结构与算法.第二遍.链表;

import 数据结构与算法.ListNode;

public class 反转链表 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur_pre = null;//当前结点的前一个结点，用于反转
        ListNode cur_next = null;// //用于记录当前节点的下一个节点
        while (head != null) {
            //记录当前节点的下一个节点
            cur_next = head.next;
            //反转
            head.next = cur_pre;
            //cur_pre也要往前走，目的是处理下一个节点
            cur_pre = head;//移至反转后的head节点
            //head也要往前走(这也是为什么之前要记录下一个节点原因），目的是处理下一个节点
            head = cur_next;
        }
        //此时cur_pre就成了新链表的头结点了
        return cur_pre;
    }


    /**
     * 第三遍-tk.zou
     *
     * @return
     */
    public ListNode reverseList03(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //定义两个结点，一个用于记录当前节点的前一个结点，另一个则用于记录当前结点的下一个结点
        ListNode cur_pre = null;
        ListNode cur_next = null;
        while (head != null){
            cur_next = head.next;
            //反转--使用head
            head.next = cur_pre;
            //移动到下一个结点再反转，如此往复
            cur_pre = head;
            head = cur_next;
        }
        return cur_pre;
    }
}
