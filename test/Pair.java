public class Pair {
  public static Object o1;
  public static Object o2;
  public Pair(Object o1, Object o2) { this.o1 = o1; this.o2 = o2; }
 
  public static boolean same(Object o1, Object o2) {
    return o1 == null ? o2 == null : o1.equals(o2);
  }
 
  static Object getFirst() { return o1; }
  static Object getSecond() { return o2; }
  
  Pair getBoth() { return new Pair(o1,o2); }
  
  void setEmpty(Object o) { o1 = null; o2 = null; }
 
  void setFirst(Object o) { o1 = o; }
  void setSecond(Object o) { o2 = o; }
 
  public boolean equals(Object obj) {
    if( ! (obj instanceof Pair))
      return false;
    Pair p = (Pair)obj;
    return same(p.o1, this.o1) && same(p.o2, this.o2);
  }
 
  public String toString() {
    return "Pair{"+o1+", "+o2+"}";
  }
}
