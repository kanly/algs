package sort


class Insertion[A] extends Sort[A]{
  def sort(elements: List[A])(comparator: (A, A) => Comparison): List[A] = ???
}
