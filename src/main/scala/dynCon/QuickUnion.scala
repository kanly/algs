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

  override def toString = elements.mkString(";")

}


class WeightedQuickUnion(elements: List[Int], weights: List[Int]) extends QuickUnion(elements) {
  def this(n: Int) = {
    this(0 to n - 1 toList, List.fill(n)(1))
  }

  override def union(p: Int, q: Int): UF =
    if (weights(p) > weights(q))
      new WeightedQuickUnion(elements.updated(rootFor(q), rootFor(p)), weights.updated(q, weights(q) + 1))
    else
      new WeightedQuickUnion(elements.updated(rootFor(p), rootFor(q)), weights.updated(p, weights(p) + 1))

}
