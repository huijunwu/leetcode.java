import java.util.HashMap;
import java.util.Map;

class DLNode {
  int key;
  int val;
  DLNode pre;
  DLNode nxt;

  DLNode(int k, int v) {
    key = k;
    val = v;
    pre = null;
    nxt = null;
  }
}


public class LRUCache {
  int capacity;
  DLNode head; // for order
  DLNode tail;
  Map<Integer, DLNode> keyVal; // for key-val store

  public LRUCache(int capacity) {
    this.capacity = capacity;
    head = new DLNode(-1, -1);
    tail = new DLNode(-1, -1);
    head.nxt = tail;
    tail.pre = head;
    keyVal = new HashMap<>();
  }

  private void insert(int k, int v) {
    DLNode n = new DLNode(k, v);
    n.nxt = head.nxt;
    n.pre = head;
    n.nxt.pre = n;
    n.pre.nxt = n;

    keyVal.put(k, n);

    if (keyVal.size() > capacity) {
      delete(tail.pre.key);
    }
  }

  private int delete(int k) {
    if (!keyVal.containsKey(k))
      return -1;

    DLNode n = keyVal.remove(k);
    n.pre.nxt = n.nxt;
    n.nxt.pre = n.pre;

    return n.val;
  }

  public int get(int key) {
    int val = delete(key);
    if (val != -1)
      insert(key, val);
    return val;
  }

  public void set(int key, int value) {
    delete(key);
    insert(key, value);
  }
}
----------------
public class LRUCache {
  class node {
    node prev;
    node next;
    int val;
    int key;
  }

  int capacity = 0;
  node head = null;
  node tail = null;
  Map<Integer, node> m;

  public LRUCache(int capacity) {
    this.capacity = capacity;
    head = new node();
    tail = head;
    m = new HashMap<Integer, node>();
  }

  private void i(node n) {
    n.next = tail.next;
    n.prev = tail;

    tail.next = n;

    tail = n;
  }

  private void d(node n) {
    if (n == tail) {
      tail = n.prev;
    }

    if (n.next != null) {
      n.next.prev = n.prev;
    }
    n.prev.next = n.next;
  }

  public int get(int key) {
    if (m.containsKey(key)) {
      node n = m.get(key);
      d(n);
      i(n);
      return n.val;
    } else {
      return -1;
    }
  }

  public void set(int key, int value) {
    if (m.containsKey(key)) {
      node n = m.get(key);
      d(n);
      i(n);
      n.val = value;
    } else {
      if (m.size() == capacity) {
        node n_ = head.next;
        d(n_);
        m.remove(n_.key);
      }
      node n = new node();
      i(n);
      m.put(key, n);
      n.val = value;
      n.key = key;
    }
  }
}
-------------
public class LRUCache {
  class node {
    int key;
    int val;
    node prev;
    node next;
  }

  int capacity;
  node head = new node();
  node toremove = head;
  Map<Integer, node> m = new HashMap<>();

  public LRUCache(int capacity) {
    this.capacity = capacity;
  }

  void move2head(node n) {
    if (n == toremove) {
      if (m.size() > 1) {
        toremove = n.prev;
      }
    }

    n.prev.next = n.next;
    if (n.next != null) {
      n.next.prev = n.prev;
    }

    n.prev = head;
    n.next = head.next;

    n.prev.next = n;
    if (n.next != null) {
      n.next.prev = n;
    }
  }

  public int get(int key) {
    int ret = -1;
    if (m.containsKey(key)) {
      ret = m.get(key).val;
      move2head(m.get(key));
    }
    return ret;
  }

  public void set(int key, int value) {
    if (m.containsKey(key)) {
      m.get(key).val = value;
      move2head(m.get(key));
    } else {
      node n = new node();
      n.key = key;
      n.val = value;
      n.prev = head;
      n.next = head.next;
      n.prev.next = n;
      if (n.next != null) {
        n.next.prev = n;
      }

      m.put(key, n);
      if (m.size() == 1) {
        toremove = n;
      }

      if (m.size() > capacity) {
        m.remove(toremove.key);
        toremove = toremove.prev;
        toremove.next = null;
      }
    }
  }
}
