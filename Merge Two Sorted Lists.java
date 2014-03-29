/**
 * Definition for singly-linked list.
 */
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class Solution {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode preHead = new ListNode(0);
		ListNode ret = preHead;

		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				ret.next = l1;
				l1 = l1.next;
			} else {
				ret.next = l2;
				l2 = l2.next;
			}
			ret = ret.next;
		}

		if (l1 == null) {
			ret.next = l2;
		} else if (l2 == null) {
			ret.next = l1;
		}

		return preHead.next;
	}
}