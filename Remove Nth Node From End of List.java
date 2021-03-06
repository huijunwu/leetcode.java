public class Solution {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode p = head;
    for (int i = 0; i < n; i++) {
      p = p.next;
    }
    if (p == null) {
      return head.next;
    }
    ListNode q = head;
    while (p.next != null) {
      q = q.next;
      p = p.next;
    }
    q.next = q.next.next;
    return head;
  }
}
