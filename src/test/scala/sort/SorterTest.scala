package sort

import org.scalatest.{FunSuite, FlatSpec}


class SorterTest extends FlatSpec with SorterBehaviors {
  def intSorterFunction(first: Int, second: Int) =
    if (first > second) Bigger
    else if (first < second) Smaller
    else Equal

  def stringSorterFunction(first: String, second: String) =
    if (first == null && second == null) Equal
    else if (first == null && second != null) Smaller
    else {
      val compare = first.compareTo(second)
      if (compare > 0) Bigger
      else if (compare < 0) Smaller
      else Equal
    }

  def intInsertion(elems: List[Int]) = new Insertion[Int](intSorterFunction)(elems)

  def stringInsertion(elems: List[String]) = new Insertion[String](stringSorterFunction)(elems)

  def intSelection(elems: List[Int]) = new Selection[Int](intSorterFunction)(elems)

  def stringSelection(elems: List[String]) = new Selection[String](stringSorterFunction)(elems)

  "Insertion sort " should behave like _intSorter(intInsertion)
  it should behave like _stringSorter(stringInsertion)

  "Selection sort " should behave like _intSorter(intSelection)
  it should behave like _stringSorter(stringSelection)
}

trait SorterBehaviors {
  this: FlatSpec =>

  def _intSorter(sorterGenerator: List[Int] => Sorter[Int]) {
    it should "sort correctly (2,1,4,5,6,1,6,3,1)" in {
      val sorted = sorterGenerator(List(2, 1, 4, 5, 6, 1, 6, 3, 1)) sort()

      println(sorted.list().mkString("; "))

      assert(sorted isSorted())
      assert(List(1, 1, 1, 2, 3, 4, 5, 6, 6) == sorted.list)
    }
  }

  def _stringSorter(sorterGenerator: List[String] => Sorter[String]) {
    it should "sort correctly (\"alpha\", \"akka\", \"bebi\", \"bebo\", \"arse\", \"case\", \"Asd\", \"zero\", \"vars\")" in {
      val sorted = sorterGenerator(List("alpha", "akka", "bebi", "bebo", "arse", "case", "Asd", "zero", "vars")) sort()

      println(sorted.list().mkString("; "))

      assert(sorted.isSorted())


      assert(List("Asd", "akka", "alpha", "arse", "bebi", "bebo", "case", "vars", "zero") == sorted.list)
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
