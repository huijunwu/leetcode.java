public class Solution {
  public int addDigits(int num) {
    while (num > 9)
      num = String.valueOf(num).chars().map(x -> x - '0').reduce(0, (acc, e) -> acc + e);
    return num;
  }
}
