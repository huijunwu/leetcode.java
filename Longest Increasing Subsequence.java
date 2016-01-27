public class Solution {
  public int lengthOfLIS(int[] nums) {
    int ret = 0;
    if (nums.length == 0)
      return ret;
    int dp[] = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      dp[i] = 1;
      ret = Math.max(ret, dp[i]);
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
          ret = Math.max(ret, dp[i]);
        }
      }
    }
    return ret;
  }
}
-----------
public class Solution {
  public int lengthOfLIS(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    int dp[] = new int[nums.length];
    Arrays.fill(dp, 1);
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }
    return Arrays.stream(dp).max().getAsInt();
  }
}
