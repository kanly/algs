package dynCon

import annotation.tailrec

class QuickFind(elements: List[Int]) extends UF {
  def this(n: Int) = {
    this(0 to n - 1 toList)
  }

  def union(p: Int, q: Int): UF = new QuickFind(elements.map(x => if (x == p) rootFor(q) else x))

  def connected(p: Int, q: Int): Boolean = elements(p) == elements(q)

  @tailrec
  final def rootFor(p: Int): Int = if (elements(p) == p) p else rootFor(elements(p))

  override def toString = elements.mkString(";")
}
