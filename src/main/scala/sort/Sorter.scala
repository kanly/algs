package sort

abstract class Sorter[A](comparator: (A, A) => CompareResult)(elems: List[A]) {
  val elements = elems

  def sort(): Sorter[A]

  def newInstance(elements: List[A]): Sorter[A]

  def list(): List[A] = elems

  def firstIsLess(first: A, second: A) = comparator(first, second) == Smaller

  def swap(first: Int, second: Int): Sorter[A] = {
    val firstValue = elements(first)
    val secondValue = elements(second)
    newInstance(elements.updated(first, secondValue).updated(second, firstValue))
  }

  def isSorted(): Boolean = {
    elements match {
      case f :: s :: Nil => !firstIsLess(s, f)
      case f :: s :: list => !firstIsLess(s, f) && newInstance(s :: list).isSorted()
      case _ => true
    }
  }

}

object Sorter {


}

sealed abstract class CompareResult

case object Bigger extends CompareResult

case object Smaller extends CompareResult

case object Equal extends CompareResult
