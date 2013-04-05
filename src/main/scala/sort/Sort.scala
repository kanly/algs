package sort

abstract class Sort[A] {
  def sort(elements: List[A])(comparator: (A,A) => Comparison): List[A]
}

sealed abstract class Comparison
case object Bigger extends Comparison
case object Smaller extends Comparison
case object Equal extends Comparison
