import java.util.ArrayDeque;

public class Solution {
	public int maximalRectangle(char[][] matrix) {
		if (matrix.length == 0) {
			return 0;
		}

		int[][] dp = new int[matrix.length][matrix[0].length];
		for (int col = 0; col < matrix[0].length; col++) {
			dp[0][col] = matrix[0][col] - '0';
		}
		for (int row = 1; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				dp[row][col] = matrix[row][col] == '1' ? dp[row - 1][col] + 1 : 0;
			}
		}

		int ret = 0;
		for (int row = 0; row < dp.length; row++) {
			ret = Math.max(ret, largestRectangleArea(dp[row]));
		}
		return ret;
	}

	public int largestRectangleArea(int[] height) {
		/*
		 * For every bar 'x', we calculate the area with 'x' as the smallest bar in the rectangle. If we calculate such area for every bar
		 * 'x' and find the maximum of all areas, our task is done. How to calculate area with 'x' as smallest bar? We need to know index of
		 * the first smaller (smaller than 'x') bar on left of 'x' and index of first smaller bar on right of 'x'. Let us call these indexes
		 * as 'left index' and 'right index' respectively. We traverse all bars from left to right, maintain a stack of bars. Every bar is
		 * pushed to stack once. A bar is popped from stack when a bar of smaller height is seen. (so the bars in stack are ascendant) When
		 * a bar is popped, we calculate the area with the popped bar as smallest bar 'x'. How do we get left and right indexes of the
		 * popped bar - the current index tells us the 'right index' and index of previous item in stack (peek()) is the 'left index'.
		 */
		if (height.length == 0) {
			return 0;
		}

		int ret = 0;

		ArrayDeque<Integer> stack = new ArrayDeque<Integer>(); // index in stack
		for (int i = 0; i < height.length; i++) {
			while (!stack.isEmpty() && height[i] < height[stack.peek()]) {
				int x = stack.pop();
				if (stack.isEmpty()) {
					ret = Math.max(ret, height[x] * (i - (-1) - 1));
				} else {
					ret = Math.max(ret, height[x] * (i - stack.peek() - 1));
				}
			}
			stack.push(i);
		}
		while (!stack.isEmpty() && 0 < height[stack.peek()]) {
			int x = stack.pop();
			if (stack.isEmpty()) {
				ret = Math.max(ret, height[x] * (height.length - (-1) - 1));
			} else {
				ret = Math.max(ret, height[x] * (height.length - stack.peek() - 1));
			}
		}

		return ret;
	}
}