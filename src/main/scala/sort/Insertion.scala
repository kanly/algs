package sort


class Insertion[A](comparator: (A, A) => CompareResult)(elements:List[A]) extends Sorter[A](comparator)(elements){
  override def sort(): Sorter[A] = ???

  override def newInstance(elems: List[A]): Sorter[A] = new Insertion[A](comparator)(elems)
}
