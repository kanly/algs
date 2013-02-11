package dynCon

import annotation.tailrec

class QuickUnion(elements: List[Int]) extends UF {
  def this(n: Int) = {
    this(0 to n - 1 toList)
  }

  def union(p: Int, q: Int): UF = new QuickUnion(elements.updated(rootFor(p), rootFor(q)))

  def connected(p: Int, q: Int): Boolean = rootFor(p) == rootFor(q)

  @tailrec
  final def rootFor(p: Int): Int = if (elements(p) == p) p else rootFor(elements(p))

}
