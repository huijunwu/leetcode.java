public class Solution {
  private boolean searchMatrix_block(int[][] matrix, int target, int r0, int rm, int c0, int cn) {
    if (r0 > rm || c0 > cn)
      return false;
    int x = r0 + (rm - r0) / 2;
    int y = c0 + (cn - c0) / 2;
    if (matrix[x][y] < target) {
      boolean b = false;
      b = b || searchMatrix_block(matrix, target, x + 1, rm, y + 1, cn);
      b = b || searchMatrix_block(matrix, target, x + 1, rm, c0, y);
      b = b || searchMatrix_block(matrix, target, r0, x, y + 1, cn);
      return b;
    } else if (matrix[x][y] > target) {
      boolean b = false;
      b = b || searchMatrix_block(matrix, target, r0, x - 1, c0, y - 1);
      b = b || searchMatrix_block(matrix, target, r0, x - 1, y, cn);
      b = b || searchMatrix_block(matrix, target, x, rm, c0, y - 1);
      return b;
    } else {
      return true;
    }
  }

  public boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length;
    if (m == 0)
      return false;
    int n = matrix[0].length;
    if (n == 0)
      return false;
    return searchMatrix_block(matrix, target, 0, m - 1, 0, n - 1);
  }
}
