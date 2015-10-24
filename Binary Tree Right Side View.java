public class Solution {
  private void rightSideView_(TreeNode root, List<Integer> ret, Deque<TreeNode> stack) {
    if (root != null) {
      stack.push(root);
      if (stack.size() > ret.size())
        ret.add(root.val);
      rightSideView_(root.right, ret, stack);
      rightSideView_(root.left, ret, stack);
      stack.pop();
    }
  }

  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> ret = new ArrayList<>();
    rightSideView_(root, ret, new LinkedList<TreeNode>());
    return ret;
  }
}