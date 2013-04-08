package sort


class Selection[A](comparator: (A, A) => CompareResult)(elems: List[A]) extends Sorter(comparator)(elems) {
  def sort(): Sorter[A] = new Selection[A](comparator)(sortIt())

  def sortIt(remaining: List[A] = elems): List[A] = remaining match {
    case Nil => Nil
    case _ => {
      val minimum = min(remaining)
      minimum :: sortIt(removeOne(minimum, remaining))
    }
  }

  def removeOne(elem: A, list: List[A]) = list diff List(elem)

  def min(elements: List[A] = elems): A = elements.reduce((l, r) => if (firstIsLess(l, r)) l else r)

  def newInstance(elements: List[A]): Sorter[A] = new Selection[A](comparator)(elements)
}
