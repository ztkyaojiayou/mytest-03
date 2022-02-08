package 数据结构与算法.第二遍.链表;

import 数据结构与算法.ListNode;

/**
 * 思路：准备两个指针fast和slow，循环链表，slow指针初始也指向head，每次循环向前走一步，
 * fast指针初始指向head，每次循环向前两步，如果没有环，则快指针会抵达终点，如果有环，那么快指针会追上慢指针
 * <p>
 * 作者：chen-wei-f
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle/solution/141-huan-xing-lian-biao-by-chen-wei-f-bh3y/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
class 环形链表01_判断 {
    //入门版，只判断是否有环
    public boolean hasCycle(ListNode head) {
        //特判
        if (head == null && head.next == null) {
            return false;
        }
        //快慢双指针
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}

class 环形链表02_找到该入口节点 {
    public ListNode findTheNodeOfLoop(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        //此时表示快慢指针已经相遇，且相遇点已经找到，于是再定义一个慢指针，
        // 和之前的慢指针一起从头结点开始跑，他们俩相遇的节点即为该环形链表的入口节点，很妙~
        //注意：当然也可以废物利用，把快指针重新指向头结点来当慢指针使用~
        ListNode slow2 = head;
        while (slow != slow2) {
            slow = slow.next;
            slow2 = slow2.next;
        }
        return slow;
    }
}

/**
 * 第三遍-tk.zou
 *
 * @return
 */
class Demo03 {
    //入门版，只判断是否有环
    public boolean hasCycle03(ListNode head) {
        if (head == null && head.next == null) {
            return false;
        }
        ListNode quick = head;
        ListNode slow = head;
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
            if (quick == slow) {
                return true;
            }
        }
        return false;
    }

    //找到该入口节点
    public ListNode findTheNodeOfLoop03(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //定义快慢指针
        ListNode quick = head;
        ListNode slow = head;
        //找到相遇点
        while (quick != null && quick.next != null){
            quick = quick.next.next;
            slow = slow.next;
            if (quick == slow){
                break;
            }
        }
        //此时在定义一个指针，从头开始，和慢指针一起走，相遇即为入口
        ListNode slow2 = head;
        while (slow != slow2){
            slow = slow.next;
            slow2 = slow2.next;
        }
        return slow;
    }
}
