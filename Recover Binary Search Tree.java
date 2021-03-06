public class Solution {
  private void recoverTree_leftright(TreeNode root, int[] pre, TreeNode[] leftWrong) {
    if (leftWrong[0] == null && root != null) {
      recoverTree_leftright(root.left, pre, leftWrong);
      if (leftWrong[0] == null) {
        if (!(pre[0] <= root.val)) {
          leftWrong[0] = leftWrong[1];
          return;
        } else {
          pre[0] = root.val;
          leftWrong[1] = root;
        }
        recoverTree_leftright(root.right, pre, leftWrong);
      }
    }
  }

  private void recoverTree_rightleft(TreeNode root, int[] post, TreeNode[] rightWrong) {
    if (rightWrong[0] == null && root != null) {
      recoverTree_rightleft(root.right, post, rightWrong);
      if (rightWrong[0] == null) {
        if (!(root.val <= post[0])) {
          rightWrong[0] = rightWrong[1];
          return;
        } else {
          post[0] = root.val;
          rightWrong[1] = root;
        }
        recoverTree_rightleft(root.left, post, rightWrong);
      }
    }
  }

  public void recoverTree(TreeNode root) {
    TreeNode[] leftWrong = new TreeNode[2];
    leftWrong[0] = null;
    recoverTree_leftright(root, new int[] {Integer.MIN_VALUE}, leftWrong);

    TreeNode[] rightWrong = new TreeNode[2];
    rightWrong[0] = null;
    recoverTree_rightleft(root, new int[] {Integer.MAX_VALUE}, rightWrong);

    int tmp = leftWrong[0].val;
    leftWrong[0].val = rightWrong[0].val;
    rightWrong[0].val = tmp;
  }
}
-----------------
public class Solution {
  LinkedList<TreeNode> nodes = new LinkedList<>();

  private void in(TreeNode root, LinkedList<TreeNode> inOrderHistory) { // 一遍scan就可以
    if (root != null) {
      in(root.left, inOrderHistory);

      inOrderHistory.offerLast(root);
      if (inOrderHistory.size() == 2) {
        if (inOrderHistory.peekFirst().val > inOrderHistory.peekLast().val) {
          nodes.addAll(inOrderHistory);
        }
        inOrderHistory.pollFirst();
      }

      in(root.right, inOrderHistory);
    }
  }

  private void swap(TreeNode tn1, TreeNode tn2) {
    int t = tn1.val;
    tn1.val = tn2.val;
    tn2.val = t;
  }

  public void recoverTree(TreeNode root) {
    in(root, new LinkedList<TreeNode>());
    swap(nodes.peekFirst(), nodes.peekLast());
  }
}
-----------------
public class Solution {
  TreeNode lastLeft = null;
  TreeNode lastRight = null;

  TreeNode leftright[] = new TreeNode[2];
  TreeNode rightleft[] = new TreeNode[2];

  void leftRight(TreeNode root) {
    if (root != null) {
      leftRight(root.left);
      if (lastLeft != null && lastLeft.val > root.val && leftright[0] == null) {
        leftright[0] = lastLeft;
        leftright[1] = root;
      }
      lastLeft = root;
      leftRight(root.right);
    }
  }

  void rightLeft(TreeNode root) {
    if (root != null) {
      rightLeft(root.right);
      if (lastRight != null && lastRight.val < root.val && rightleft[0] == null) {
        rightleft[1] = lastRight;
        rightleft[0] = root;
      }
      lastRight = root;
      rightLeft(root.left);
    }
  }

  public void recoverTree(TreeNode root) {
    leftRight(root);
    rightLeft(root);
    int tmp = leftright[0].val;
    leftright[0].val = rightleft[1].val;
    rightleft[1].val = tmp;
  }
}
