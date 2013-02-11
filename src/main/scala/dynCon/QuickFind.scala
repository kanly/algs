package dynCon

class QuickFind(elements: List[Int]) extends UF {
  def this(n: Int) = {
    this(0 to n - 1 toList)
  }

  def union(p: Int, q: Int): UF = new QuickFind(elements.map(x => if (x == p) q else x))

  def connected(p: Int, q: Int): Boolean = elements(p) == elements(q)

  override def toString = elements.mkString(";")
}
