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

class PathCompressionQuickUnion(elements: Array[Int]) extends UF {
  def this(n: Int) = {
    this(0 to n - 1 toArray)
  }

  def union(p: Int, q: Int): UF = {
    elements.update(rootFor(p), rootFor(q))
    this
  }

  def state = elements

  def connected(p: Int, q: Int): Boolean = rootFor(p) == rootFor(q)

  @tailrec
  final def rootFor(p: Int): Int = {
    if (elements(p) == p) p
    else {
      elements.update(p, elements(elements(p)))
      rootFor(elements(p))
    }
  }

  override def toString = elements.mkString(";")
}

class WeightedPathCompressionQuickUnion(elements: Array[Int], weights: Array[Int]) extends UF {
  def this(n: Int) = {
    this(0 to n - 1 toArray, Array.fill(n)(1))
  }

  def union(p: Int, q: Int): UF = {
    if (weights(p) > weights(q)) {
      elements.update(rootFor(q), rootFor(p))
      weights.update(q, weights(q) + 1)
    } else {
      elements.update(rootFor(p), rootFor(q))
      weights.update(p, weights(p) + 1)
    }
    this
  }

  def connected(p: Int, q: Int): Boolean = rootFor(p) == rootFor(q)

  @tailrec
  final def rootFor(p: Int): Int = {
    if (elements(p) == p) p
    else {
      elements.update(p, elements(elements(p)))
      rootFor(elements(p))
    }
  }

  def state = elements

  override def toString = elements.mkString(";")
}


