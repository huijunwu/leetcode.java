public class Solution {
  private boolean addOperators_check(String num, int start, int end) {
    if (start < end) {
      if (num.charAt(start) == '0') {
        if (start + 1 == end)
          return true;
        else
          return false;
      } else
        return true;
    } else
      return false;
  }

  private boolean addOperators_target(Deque<Long> exp, int target) {
    Deque<Long> stack = new ArrayDeque<>();
    stack.offerLast(exp.pollFirst());
    while (!exp.isEmpty()) {
      Long op = exp.pollFirst();
      Long rnd = exp.pollFirst();
      if (op == -3) {
        stack.offerLast(stack.pollLast() * rnd);
      } else {
        stack.offerLast(op);
        stack.offerLast(rnd);
      }
    }
    Long ret = stack.pollFirst();
    while (!stack.isEmpty()) {
      Long op = stack.pollFirst();
      Long rnd = stack.pollFirst();
      if (op == -1) {
        ret += rnd;
      } else if (op == -2) {
        ret -= rnd;
      }
    }
    return ret == target;
  }

  private void addOperators_(String num, int target, int start, List<String> ret, Deque<Long> exp,
      long dp[][]) {
    if (start == num.length()) {
      if (addOperators_target(new ArrayDeque<Long>(exp), target)) { // report
        ret.add(exp.stream().map(x -> {
          if (x == -1)
            return "+";
          else if (x == -2)
            return "-";
          else if (x == -3)
            return "*";
          else
            return x.toString();
        }).reduce("", (acc, e) -> acc + e));
      }
    } else {
      exp.offerLast(-1L);
      for (int i = start + 1; i <= num.length(); i++) {
        if (dp[start][i] >= 0) {
          exp.offerLast(dp[start][i]);
          addOperators_(num, target, i, ret, exp, dp);
          exp.pollLast();
        }
      }
      exp.pollLast();
      exp.offerLast(-2L);
      for (int i = start + 1; i <= num.length(); i++) {
        if (dp[start][i] >= 0) {
          exp.offerLast(dp[start][i]);
          addOperators_(num, target, i, ret, exp, dp);
          exp.pollLast();
        }
      }
      exp.pollLast();
      exp.offerLast(-3L);
      for (int i = start + 1; i <= num.length(); i++) {
        if (dp[start][i] >= 0) {
          exp.offerLast(dp[start][i]);
          addOperators_(num, target, i, ret, exp, dp);
          exp.pollLast();
        }
      }
      exp.pollLast();
    }
  }

  public List<String> addOperators(String num, int target) {
    int len = num.length();
    long[][] dp = new long[len][len + 1];
    for (int i = 0; i < len; i++) {
      Arrays.fill(dp[i], -1);
    }
    for (int i = 0; i < len; i++) {
      for (int j = i + 1; j <= len; j++) {
        if (addOperators_check(num, i, j)) {
          dp[i][j] = Long.parseLong(num.substring(i, j));
        }
      }
    }
    List<String> ret = new ArrayList<>();
    Deque<Long> exp = new ArrayDeque<>();
    for (int i = 1; i <= num.length(); i++) {
      if (dp[0][i] >= 0) {
        exp.offerLast(dp[0][i]);
        addOperators_(num, target, i, ret, exp, dp);
        exp.pollLast();
      }
    }
    return ret;
  }
}
---------------
public class Solution {
  private void dfs(String num, long target, int start, String stack, List<String> ret, long total,
      long last) { // total and last to calc during dfs
    if (start == num.length()) {
      if (total == target)
        ret.add(stack.toString());
      return;
    }
    if (num.charAt(start) == '0') {
      dfs(num, target, start + 1, stack + "+0", ret, total + 0, 0);
      dfs(num, target, start + 1, stack + "-0", ret, total + (-0), 0);
      dfs(num, target, start + 1, stack + "*0", ret, total - last + (last * 0), last * 0);
    } else {
      for (int end = start + 1; end <= num.length(); end++) {
        long n = Long.parseLong(num.substring(start, end));
        dfs(num, target, end, stack + "+" + n, ret, total + n, n);
        dfs(num, target, end, stack + "-" + n, ret, total + (-n), -n); // - => +
        dfs(num, target, end, stack + "*" + n, ret, total - last + (last * n), last * n); // * => +
      }
    }
  }

  public List<String> addOperators(String num, int target) {
    List<String> ret = new ArrayList<>();
    if (num.length() == 0) {
      return ret;
    }
    if (num.charAt(0) == '0') {
      dfs(num, target, 1, "0", ret, 0, 0);
    } else {
      for (int len = 1; len <= num.length(); len++) {
        String s = num.substring(0, len);
        long n = Long.parseLong(s);
        dfs(num, target, len, s, ret, n, n);
      }
    }
    return ret;
  }
}
