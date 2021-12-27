package 数据结构与算法.第二遍.链表;

import 数据结构与算法.ListNode;

/**
 * 注意哑节点的使用,哑节点就是本身的值不重要,但其next指针指向head
 * 也就是实现对head的操作同其他的节点一致
 * 定义快指针和慢指针
 * 快指针和满指针中间隔n步
 * 然后快慢指针同时向后运动
 * 当快指针到达末尾时
 * 慢指针的下一个就是要删除的节点
 * 最后返回dummy.next即可
 * 不是返回head
 * <p>
 * 作者：watch_this
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/19-by-watch_this-jwjm/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
//返回值：返回头结点
public class 删除链表的倒数第N个节点 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //定义哑结点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //定义快慢指针（速度一样，只是快指针先走n步）
        //不能指向头结点
        ListNode quick = dummy;
        ListNode slow = dummy;
        //快指针先走n+1步（只是遍历，并不删除）到要删除的目标结点
        for (int i = 0; i <= n; i++){
            quick = quick.next;
        }
        //再一起走（只是遍历，并不删除）
        while (quick != null) {
            quick = quick.next;
            slow = slow.next;
        }
        //此时快指针刚好走到最后一个结点，而慢指针则刚好走到要删除的结点的前一个结点，于是删除/跳过目标结点即可
        slow.next = slow.next.next;
        return dummy.next;
    }


    /**
     * 第三遍-tk.zou
     *
     * @return
     */
    public ListNode removeNofEnd03(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode quick = dummy;
        ListNode slow = dummy;
        //快指针先走n+1步到要删除的目标结点
        for (int i = 0; i < n; i++) {
            quick = quick.next;
        }
        //在一起走
        while (quick != null){
            quick = quick.next;
            slow = slow.next;
        }
        //此时快指针在最后一个结点，慢指针则刚好在要删除结点的前一个结点，于是删除即可
        slow.next = slow.next.next;
        return dummy.next;
    }


}
