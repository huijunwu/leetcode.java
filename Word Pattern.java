public class Solution {
  public boolean wordPattern(String pattern, String str) {
    String ss[] = str.split(" ");
    if (pattern.length() != ss.length)
      return false;
    Map<Character, String> c2s = new HashMap<>();
    for (int i = 0; i < ss.length; i++) {
      char c = pattern.charAt(i);
      if (c2s.containsKey(c)) {
        if (!c2s.get(c).equals(ss[i])) {
          return false;
        }
      } else {
        c2s.put(c, ss[i]);
      }
    }
    return c2s.values().stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).values()
        .stream().filter(x -> x > 1).count() == 0;
  }
}
