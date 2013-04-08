package sort

import org.scalatest.FlatSpec


class SortTest {

}

trait SortBehaviors {
  this: FlatSpec =>
  def plainSorter(sorter: Sort[Int]) {
    def sorterFunction(first:Int,second:Int) = (first-second) match {
      case _ > 0 => Bigger
      case _ < 0 => Smaller
      case _ => Equal
    }
    it should "sort correctly (2,1,4,5,6,1,6,3,1)" in {
      val sorted=sorter.sort(List(2,1,4,5,6,1,6,3,1))
      assert(sorted==List(1,1,1,2,3,4,5,6,6))
    }
  }
}
