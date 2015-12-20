public class Solution {
  public int maximalSquare(char[][] matrix) {
    int r = matrix.length;
    if (r == 0) {
      return 0;
    }
    int c = matrix[0].length;
    if (c == 0) {
      return 0;
    }
    int dp[][] = new int[r][c];
    for (int i = 0; i < r; i++) {
      dp[i][0] = matrix[i][0] - '0';
    }
    for (int i = 0; i < c; i++) {
      dp[0][i] = matrix[0][i] - '0';
    }
    for (int i = 1; i < r; i++) {
      for (int j = 1; j < c; j++) {
        if (matrix[i][j] == '0') {
          dp[i][j] = 0;
        } else {
          dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
        }
      }
    }
    int ret = 0;
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        ret = Math.max(ret, dp[i][j]);
      }
    }
    return ret * ret;
  }
}
