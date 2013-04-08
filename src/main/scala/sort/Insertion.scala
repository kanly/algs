package sort


class Insertion[A](comparator: (A, A) => Comparison) extends Sorter[A]{
  def sort(elements: Array[A]): Array[A] = ???
}
