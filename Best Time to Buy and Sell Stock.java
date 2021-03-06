public class Solution {
  public int maxProfit(int[] prices) {
    if (prices.length == 0)
      return 0;
    int min_ever_seen = prices[0];
    int max_profit = 0;
    for (int i = 1; i < prices.length; i++) { // buy at min_ever_seen, sell at prices[i]
      max_profit = Math.max(max_profit, prices[i] - min_ever_seen);
      min_ever_seen = Math.min(min_ever_seen, prices[i]);
    }
    return max_profit;
  }

  public int maxProfit_(int[] prices) {
    int ret = 0;
    int minIdx = 0; // last
    for (int i = 1; i < prices.length; i++) { // scan
      ret = Math.max(ret, prices[i] - prices[minIdx]);
      if (prices[i] < prices[minIdx]) {
        minIdx = i;
      }
    }
    return ret;
  }
}
