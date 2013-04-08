package sort

import org.scalatest.FlatSpec


class SortTest extends FlatSpec with SortBehaviors {
  def sorterFunction(first: Int, second: Int) =
    if (first > second) Bigger
    else if (first < second) Smaller
    else Equal

  def insertion = new Insertion[Int](sorterFunction)

  "Insertion " should behave like _plainSorter(insertion)
}

trait SortBehaviors {
  this: FlatSpec =>
  def _plainSorter(sorter: Sorter[Int]) {
    it should "sort correctly (2,1,4,5,6,1,6,3,1)" in {
      val sorted = sorter.sort(Array(2, 1, 4, 5, 6, 1, 6, 3, 1))
      assert(sorted == Array(1, 1, 1, 2, 3, 4, 5, 6, 6))
    }
  }
}
