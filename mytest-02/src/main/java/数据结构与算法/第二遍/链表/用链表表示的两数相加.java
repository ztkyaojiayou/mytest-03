package 数据结构与算法.第二遍.链表;

import 数据结构与算法.ListNode;

/**
 * 特点：它们各自的位数是按照 逆序 的方式存储的（不是指排序的逆序）
 * 要求：相加后形成一个以个位为头结点的新链表
 */
public class 用链表表示的两数相加 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            //不管是否有进位，但计算公式就是这样的
            int sum = val1 + val2 + carry;
            //算进位
            carry = sum / 10;
            //创建第一个结点，值为：去除进位后的值
            ListNode sum_node = new ListNode(sum % 10);
            head.next = sum_node;
            head = head.next;
            //开始计算下一位
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return dummy.next;
    }

    /**
     * 第三遍-tk.zou
     *
     * @return
     */
    public ListNode addTwoNumbers03(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        //定义一个哑结点，作为头结点
        ListNode dummy = new ListNode(0);
        //使用一个指针遍历
        ListNode curNode = dummy;
        //进位
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            //取当前值
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            //求和
            int cur_sum = val1 + val2 + carry;
            //更新/进位
            carry = cur_sum / 10;
            //求出个位
            int cur_geiwe = cur_sum % 10;
            //建立链表
            ListNode real_head = new ListNode(cur_geiwe);
            curNode.next = real_head;
            //指针右移
            curNode = curNode.next;
            //计算下一个结点
            if (l1 != null){
                l1 = l1.next;
            }
            if (l2 != null){
                l2 = l2.next;
            }
        }
        return dummy.next;
    }
}
