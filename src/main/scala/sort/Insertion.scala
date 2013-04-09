package sort


class Insertion[A](comparator: (A, A) => CompareResult)(elements: List[A]) extends Sorter[A](comparator)(elements) {
  override def sort(): Sorter[A] = newInstance(elements.foldLeft(List[A]())(insert))

  def insert(list: List[A], value:A) = list.span(firstIsLess(_,value)) match {
    case (lower,upper) => lower :::  value :: upper
  }

  override def newInstance(elems: List[A]): Sorter[A] = new Insertion[A](comparator)(elems)
}
