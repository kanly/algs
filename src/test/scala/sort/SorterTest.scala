package sort

import org.scalatest.{FunSuite, FlatSpec}


class SorterTest extends FlatSpec with SorterBehaviors {
  def sorterFunction(first: Int, second: Int) =
    if (first > second) Bigger
    else if (first < second) Smaller
    else Equal

  def intInsertion(elems: List[Int]) = new Insertion[Int](sorterFunction)(elems)

  "Insertion sort " should behave like _plainSorter(intInsertion)
}

trait SorterBehaviors {
  this: FlatSpec =>

  def _plainSorter(sorterGenerator: List[Int] => Sorter[Int]) {
    it should "sort correctly (2,1,4,5,6,1,6,3,1)" in {
      val sorted = sorterGenerator(List(2, 1, 4, 5, 6, 1, 6, 3, 1)) sort()
      assert(sorted isSorted())
      assert(List(1, 1, 1, 2, 3, 4, 5, 6, 6) == sorted.list())
    }
  }
}

class SorterUtilitiesTest extends FunSuite {
  def sorterFunction(first: Int, second: Int) =
    if (first > second) Bigger
    else if (first < second) Smaller
    else Equal

  def insertion(elems: List[Int]) = new Insertion[Int](sorterFunction)(elems)

  test("isSorted method test correctly") {
    val unsorted = List(4, 12, 6, 3, 6, 1, 653, 2, 346, 2356, 2341, 4324)
    val sorted = List(-1, 0, 0, 3, 4, 12, 56, 230, 231, 232)
    val singleton = List(2)
    val empty = List()

    assert(!insertion(unsorted).isSorted())
    assert(insertion(sorted) isSorted())
    assert(insertion(singleton) isSorted())
    assert(insertion(empty) isSorted())
  }
}
