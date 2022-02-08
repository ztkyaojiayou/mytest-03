package 数据结构与算法.第二遍.链表;

import 数据结构与算法.ListNode;

public class 合并两个排序链表 {
    /**
     * 思路一：迭代
     * 设置dummy为哑结点，放置于新链表之前，最后返回的就是dummy.next；设置cur为当前节点，从dummy开始
     * 当两个链表都非空时进入循环，令新链表的下一个节点cur.next为val更小的节点，相应的链表节点后移一位
     * 每次循环记得cur也要后移一位
     * 如果循环结束后还有链表非空，cur指向非空链表
     * 返回dummy.next
     *
     * 作者：edelweisskoko
     * 链接：https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/solution/jian-zhi-offer-25-he-bing-liang-ge-pai-x-b8g0/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param l1
     * @param l2
     * @return
     */
    //方法1：非递归版
    public ListNode MergeList(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        //1.开始比较，cur结点连接较小值的结点
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            //移动cur节点，用于连接下一个节点
            cur = cur.next;
        }

        //2.若比较到最后，发现其中一个链表为空，则直接连到了一个链表即可
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
       //3.返回新链表的头结点即可
        return pre.next;
    }


    /**
     * 思路2：递归
     * 特判：如果有一个链表为空，返回另一个链表
     * 如果l1节点值比l2小，下一个节点应该是l1，应该return l1，在return之前，指定l1的下一个节点应该是l1.next和l2俩链表的合并后的头结点
     * 如果l1节点值比l2大，下一个节点应该是l2，应该return l2，在return之前，指定l2的下一个节点应该是l1和l2.next俩链表的合并后的头结点
     */
    public ListNode MergeList02(ListNode list1, ListNode list2) {
        //递归结束的条件
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        //一般情况
        if (list1.val <= list2.val) {
            list1.next = MergeList02(list1.next, list2);
            return list1;
        } else {
            list2.next = MergeList02(list1, list2.next);
            return list2;
        }
    }


    /**
     * 第三遍-tk.zou
     *
     * @return
     */
    //常规版--掌握
    public ListNode MergeList03(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (list1 != null && list2 != null){
            //比较，找较小者
            if (list1.val <= list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else {
                cur.next = list2;
                list2 = list2.next;
            }
            //cur右移
            cur = cur.next;
        }

        //若到最后，其中一个链表已为null，则直接连接到另一个链表即可
        if (list1 != null){
            cur.next = list1;
        }
        if (list2 != null){
            cur.next = list2;
        }
        //返回头结点
        return dummy.next;
    }

    //递归版--掌握
    public ListNode MergeList04(ListNode list1, ListNode list2) {
        //递归出口
        if (list1 == null){
            return list2;
        }
        if (list2 == null){
            return list1;
        }
        //一般情况
        if (list1.val <= list2.val){
            list1.next = MergeList04(list1.next,list2);
            return list1;
        }else {
            list2.next = MergeList04(list1,list2.next);
            return list2;
        }
    }
}

