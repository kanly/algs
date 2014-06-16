package dynCon

import scala.annotation.tailrec

class QuickUnion(elements: List[Int]) extends UF {
  def this(n: Int) = {
    this(0 to n - 1 toList)
  }

  def union(p: Int, q: Int): UF = QuickUnion(elements.updated(rootFor(p), rootFor(q)))

  def connected(p: Int, q: Int): Boolean = rootFor(p) == rootFor(q)

  @tailrec
  final def rootFor(p: Int): Int = if (elements(p) == p) p else rootFor(elements(p))

  override def toString = elements.mkString(";")

}

object QuickUnion {
  def apply(elements: List[Int]) = new QuickUnion(elements)
}


class WeightedQuickUnion(elements: List[Int], weights: List[Int]) extends QuickUnion(elements) {
  def this(n: Int) = {
    this(0 to n - 1 toList, List.fill(n)(1))

  }

  override def union(p: Int, q: Int): UF = {
    val pRoot = rootFor(p)
    val qRoot = rootFor(q)
    if (weights(pRoot) >= weights(qRoot))
      WeightedQuickUnion(elements.updated(qRoot, pRoot), weights.updated(pRoot, (weights(qRoot) + weights(pRoot))))
    else
      WeightedQuickUnion(elements.updated(pRoot, qRoot), weights.updated(qRoot, (weights(pRoot) + weights(qRoot))))
  }

  def w = weights

  def el = elements
}

object WeightedQuickUnion {
  def apply(elements: List[Int], weights: List[Int]) = new WeightedQuickUnion(elements, weights)
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

object PathCompressionQuickUnion {
  def apply(elements: Array[Int]) = new PathCompressionQuickUnion(elements)

  def apply(elements: List[Int]) = new PathCompressionQuickUnion(elements.toArray)
}

class WeightedPathCompressionQuickUnion(elements: Array[Int], weights: Array[Int]) extends UF {
  def this(n: Int) = {
    this(0 to n - 1 toArray, Array.fill(n)(1))
  }

  def union(p: Int, q: Int): UF = {
    val pRoot = rootFor(p)
    val qRoot = rootFor(q)
    if (weights(pRoot) >= weights(q)) {
      elements.update(qRoot, pRoot)
      weights.update(pRoot, weights(qRoot) + weights(pRoot))
    } else {
      elements.update(pRoot, qRoot)
      weights.update(qRoot, weights(pRoot) + weights(qRoot))
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

object WeightedPathCompressionQuickUnion {
  def apply(elements: Array[Int], weights: Array[Int]) = new WeightedPathCompressionQuickUnion(elements, weights)
}


