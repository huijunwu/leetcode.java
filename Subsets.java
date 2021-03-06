public class Solution {
  private void subsets_bt(int[] nums, int k, List<List<Integer>> ret, Deque<Integer> stack) {
    if (stack.size() == k) {
      List<Integer> report = new ArrayList<Integer>();
      for (Integer idx : stack) {
        report.add(nums[idx]);
      }
      Collections.sort(report);
      ret.add(report);
    } else {
      for (int i = stack.isEmpty() ? 0 : stack.peekLast() + 1; i < nums.length; i++) {
        if (nums.length - i >= k - stack.size()) {
          stack.offerLast(i);
          subsets_bt(nums, k, ret, stack);
          stack.pollLast();
        }
      }
    }
  }

  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ret = new ArrayList<>();
    for (int i = 0; i <= nums.length; i++) {
      List<List<Integer>> report = new ArrayList<>();
      subsets_bt(nums, i, report, new ArrayDeque<Integer>());
      ret.addAll(report);
    }
    return ret;
  }
}
----------
public class Solution {
  private Set<List<Integer>> s(int[] nums, int start) {
    Set<List<Integer>> ret = new HashSet<>();
    if (start == nums.length) {
      ret.add(new ArrayList<Integer>());
      return ret;
    }
    for (int i = start; i < nums.length; i++) { // pick nums[i]
      Set<List<Integer>> r = s(nums, i + 1);
      ret.addAll(r);
      for (List<Integer> l : r) {
        List<Integer> al = new ArrayList<>(l);
        al.add(0, nums[i]);
        ret.add(al);
      }
    }
    return ret;
  }

  public List<List<Integer>> subsets(int[] nums) {
    Arrays.sort(nums);
    return new ArrayList<List<Integer>>(s(nums, 0));
  }
}
--------------------
public class Solution {
  // startPos/head: only process the head of the rest/current list
  List<List<Integer>> s(int[] nums, int startPos) {
    List<List<Integer>> ret = new ArrayList<>(); // return

    if (startPos == nums.length) { // termination
      List<Integer> l = new ArrayList<>(); // termination item
      ret.add(l);
      return ret;
    }

    // the recursion does not have branches
    // because the input parameters are too simple to generate variations
    List<List<Integer>> r = s(nums, startPos + 1);

    // two cases to construct the result or process the current position
    for (List<Integer> l : r) { // explore case 1: pick
      List<Integer> l2 = new ArrayList<>(l);
      l2.add(0, nums[startPos]);
      ret.add(l2);
    }
    ret.addAll(r); // explore case 2: not pick

    return ret;
  }

  public List<List<Integer>> subsets(int[] nums) {
    Arrays.sort(nums);
    // sorted distinct list and 0/1 for each item, thus no duplicates
    return s(nums, 0);
  }
}
