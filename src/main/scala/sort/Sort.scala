package sort

abstract class Sorter[A](comparator: (A, A) => Comparison) {
  def sort(elements: Array[A]): Array[A]

  def firstIsLess(first: A, second: A) = {
    comparator(first, second) == Smaller
  }

  def swap(elements: Array[A], first: Int, second: Int) = {
    val a = elements(first)
    elements.update(first, elements(second))
    elements.update(second, a)
    elements
  }
}

sealed abstract class Comparison

case object Bigger extends Comparison

case object Smaller extends Comparison

case object Equal extends Comparison
