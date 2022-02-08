package 数据结构与算法.第二遍.链表;

import 数据结构与算法.ListNode;

public class 链表中倒数第K个结点 {
    public ListNode findKNode(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        //定义两个指针，其中一个先走k步，之后再一起走，速度始终保持一致
        ListNode p1 = head;
        ListNode p2 = head;
        while (head != null && k > 0) {
            p1 = p1.next;
            k--;
        }
        if (k > 0) {
            return null;
        }
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    /**
     * 第三遍-tk.zou
     *
     * @return
     */
    public ListNode findKNode03(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        //定义两个指针
        ListNode p1 = head;
        ListNode p2 = head;
        //其中一个先走k步
        while (head != null && k > 0) {
            p1 = p1.next;
            k--;
        }
        //此时head == null了，即到头了，但k还大于0，说明k大于链表长度，则肯定为null
        if (k > 0) {
            return null;
        }
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        //p1到尽头时，p2即为所求
        return p2;
    }
}
