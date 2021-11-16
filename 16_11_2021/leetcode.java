public class leetcode {
    //876
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!= null && fast.next !=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    
    //206
    public ListNode reverseList(ListNode head) {
        ListNode prev = null, curr = null, temp = head;
        while(temp!=null){
            curr = temp.next;
            temp.next = prev;
            prev = temp;
            temp = curr;
        }
        return prev;
    }

    //234
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode mid = getmid(head);
        ListNode h2 = reverse(mid.next);
        mid.next = null;
        ListNode h1 = head, check = h2;
        boolean res = true;
        while(h2!=null){
            if(h1.val != h2.val){
                res= false;
             break;
            }
            h1 = h1.next;
            h2 = h2.next;
        }
        h2 = reverse(check);
        mid.next = h2;
        return res;
    }
    public static ListNode reverse(ListNode node){
        ListNode pre = null, curr = null, tmp = node;
        while(tmp!=null){
            curr = tmp.next;
            tmp.next = pre;
            pre = tmp;
            tmp = curr;
        }
        return pre;
    }
    public static ListNode getmid(ListNode node){
        ListNode s = node, f = node;
        while(f.next!= null && f.next.next != null){
            f = f.next.next;
            s = s.next;
        }
        return s;
    }

    //143
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return ;
        ListNode mid = getmid(head);
        ListNode h2 = reverse(mid.next);
        mid.next = null;
        ListNode c1 = head, c2 = h2, p1 = null, p2 = null;
        while(c2!=null){
            p1 = c1.next;
            p2 = c2.next;
            
            c1.next = c2;
            c2.next = p1;
            
            c1= p1;
            c2 = p2;
        }
        
    }
    public ListNode getmid(ListNode node){
        ListNode f = node;
        ListNode s = node;
        while(f.next!= null && f.next.next != null){
            f = f.next.next;
            s = s.next;
        }
        return s;
    }
    public ListNode reverse(ListNode node){
        ListNode pre = null, cur = null, tmp = node;
        while(tmp != null){
            cur = tmp.next;
            tmp.next = pre;
            pre = tmp;
            tmp = cur;
        }
        return pre;
    }
}
