public class Solution {
  private int findPeakElement_range(int[] nums, int start, int end) {
    if (start + 3 > end)
      return -1;
    int mid = (start + end) / 2;
    if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) {
      return mid;
    } else {
      int left = findPeakElement_range(nums, start, mid + 1);
      int right = findPeakElement_range(nums, mid, end);
      if (left >= 0)
        return left;
      if (right >= 0)
        return right;
      return -1;
    }
  }

  public int findPeakElement(int[] nums) {
    if (nums.length == 1)
      return 0;
    if (nums[0] > nums[1])
      return 0;
    if (nums[nums.length - 1] > nums[nums.length - 2])
      return nums.length - 1;
    return findPeakElement_range(nums, 0, nums.length);
  }
}

public class Solution {
  public int findPeakElement(int[] nums) {
    int low = 0;
    int high = nums.length - 1;
    while (high - low >= 2) {
      int mid = low + (high - low) / 2;
      if (nums[mid - 1] < nums[mid] && nums[mid] < nums[mid + 1]) {
        low = mid + 1;
      } else if (nums[mid - 1] > nums[mid] && nums[mid] > nums[mid + 1]) {
        high = mid - 1;
      } else if (nums[mid - 1] > nums[mid] && nums[mid] < nums[mid + 1]) {
        low = mid + 1; // high = mid-1;
      } else {
        return mid;
      }
    }
    int ret = low;
    for (int i = low; i <= high; i++) {
      if (nums[i] > nums[ret]) {
        ret = i;
      }
    }
    return ret;
  }
}
