import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
  public int nthSuperUglyNumber(int n, int[] primes) {
    int[] pointer = new int[primes.length];
    Arrays.fill(pointer, 0);

    List<Integer> window = new ArrayList<>();
    window.add(1);

    while (window.size() < n) {
      int idx_ = 0;
      for (int j = idx_ + 1; j < primes.length; j++) {
        if (primes[idx_] * window.get(pointer[idx_]) > primes[j] * window.get(pointer[j])) {
          idx_ = j;
        }
      }
      if (window.get(window.size() - 1) != primes[idx_] * window.get(pointer[idx_])) {
        window.add(primes[idx_] * window.get(pointer[idx_]));
      } else {

      }
      pointer[idx_]++;
    }
    return window.get(n - 1);
  }
}