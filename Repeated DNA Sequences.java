public class Solution {
  public List<String> findRepeatedDnaSequences(String s) {
    Set<String> ret = new HashSet<>();
    Set<String> seen = new HashSet<>();
    for (int i = 10; i <= s.length(); i++) {
      String ss = s.substring(i - 10, i);
      if (seen.contains(ss)) {
        ret.add(ss);
      } else {
        seen.add(ss);
      }
    }
    return new ArrayList<String>(ret);
  }
}
