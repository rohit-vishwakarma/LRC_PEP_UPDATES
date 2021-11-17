public class question {
    //21
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) return l1!=null ? l1 : l2;
        
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy, c1 = l1, c2= l2;
        while(c1 !=null && c2!=null){
            if(c1.val <= c2.val){
                pre.next = c1;
                pre = pre.next;
                c1 = c1.next;
            }else{
                pre.next = c2;
                c2 = c2.next;
                pre = pre.next;
            }
        }
        pre.next = c1 != null ? c1 : c2;
        return dummy.next;
    }

    //unfold
    public static ListNode reverse(ListNode node){
        ListNode tmp = node, pre= null, cur = null;
        while(tmp!=null){
            cur = tmp.next;
            tmp.next = pre;
            pre = tmp;
            tmp = cur;
        }
        return pre;
    }
    public static void unfold(ListNode head) {
        if(head == null || head.next == null) return ;
        ListNode c1 = new ListNode(0);
        ListNode c2 = new ListNode(0);
        ListNode p1 = c1, p2 = c2, tmp = head;
        int i =0;
        while(tmp.next!=null){
            if(i%2==1){
                p2.next = tmp;
                p2 = p2.next;
            }else {
                p1.next = tmp;
                p1 = p1.next;
            }
            i++;
            tmp = tmp.next;
        }
        if(tmp!=null) {
            p2.next = tmp;
            p2 = p2.next;
            // tmp = tmp.next;
        }
        p1.next = reverse(c2.next);
        c2.next = null;
        head = c1.next;
    }

    //Merge K sorted linkedlist

    public static ListNode mergeKList(ListNode[] lists, int si, int ei){
        if(si == ei) return lists[si];
        int mid = (si + ei) / 2;
        ListNode left = mergeKList(lists, si, mid);
        ListNode right = mergeKList(lists, mid+1, ei);
        return mergeTwoLists(left, right);
    }
}
