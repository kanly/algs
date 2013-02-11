package dynCon

class QuickFind(elements: List[Int]) extends UF {
  def this(n: Int) = {
    this(0 to n-1 toList)
  }

  def union(p: Int, q: Int): UF = {
    val ret = new QuickFind(elements.map(x => if (x == p) q else x))
    println("p:"+p+", q:",q,". Resulting list: ",ret.toString)
    ret
  }

  def connected(p: Int, q: Int): Boolean = {
    println(elements(p) + "==" + elements(q) )
    elements(p) == elements(q)  }

  override def toString = elements.mkString(";")
}
