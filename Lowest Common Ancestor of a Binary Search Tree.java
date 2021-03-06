public class Solution {
  LinkedList<TreeNode> rootPath(TreeNode root, TreeNode p) {
    LinkedList<TreeNode> ret = new LinkedList<>();
    while (root != p) {
      ret.offerLast(root);
      if (p.val < root.val) {
        root = root.left;
      } else {
        root = root.right;
      }
    }
    ret.offerLast(p);
    return ret;
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    LinkedList<TreeNode> pp = rootPath(root, p);
    LinkedList<TreeNode> qq = rootPath(root, q);

    TreeNode ret = null;
    while (pp.peekFirst() == qq.peekFirst()) {
      ret = pp.pollFirst();
      qq.pollFirst();
    }
    return ret;
  }
}
----------------
public class Solution {
  LinkedList<TreeNode> path(TreeNode root, TreeNode t) {
    LinkedList<TreeNode> ret = new LinkedList<>();
    while (root.val != t.val) {
      ret.offerLast(root);
      if (root.val < t.val) {
        root = root.right;
      } else if (root.val > t.val) {
        root = root.left;
      }
    }
    ret.offer(t);
    return ret;
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    LinkedList<TreeNode> pp = path(root, p);
    LinkedList<TreeNode> qq = path(root, q);
    TreeNode ret = root;
    while (!pp.isEmpty() && !qq.isEmpty() && pp.peekFirst() == qq.peekFirst()) {
      ret = pp.pollFirst();
      qq.pollFirst();
    }
    return ret;
  }
}
