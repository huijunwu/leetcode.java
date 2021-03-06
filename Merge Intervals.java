import java.util.Comparator;
import java.util.List;

public class Solution {
  public List<Interval> merge(List<Interval> intervals) {
    // lambda engine too slow, cannot pass large data set
    intervals.sort(new Comparator<Interval>() {
      @Override
      public int compare(Interval x, Interval y) {
        if (x.start == y.start) {
          return x.end - y.end;
        }
        return x.start - y.start;
      }
    });
    for (int i = 0; i < intervals.size() - 1; i++) {
      if (intervals.get(i).end >= intervals.get(i + 1).start) {
        intervals.get(i).end = Math.max(intervals.get(i).end, intervals.get(i + 1).end);
        intervals.remove(i + 1);
        i--;
      }
    }
    return intervals;
  }
}
---------------
public class Solution {
  public List<Interval> merge(List<Interval> intervals) {
    List<Interval> sorted =
        intervals.stream().sorted((x, y) -> x.start - y.start).collect(Collectors.toList());
    LinkedList<Interval> ret = new LinkedList<>();
    if (sorted.size() == 0) {
      return ret;
    }
    Interval current = new Interval(sorted.get(0).start, sorted.get(0).end);
    for (int i = 1; i < sorted.size(); i++) {
      Interval interval = sorted.get(i);
      if (current.end < interval.start) {
        ret.offerLast(current);
        current = new Interval(interval.start, interval.end);
      } else {
        current.end = Math.max(current.end, interval.end);
      }
    }
    ret.offerLast(current);
    return ret;
  }
}
