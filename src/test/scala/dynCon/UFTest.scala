package dynCon

import org.scalatest.FlatSpec


class UFTest extends FlatSpec with UFBehaviors {

  def quickFind = new QuickFind(10)

  def quickUnion = new QuickUnion(10)

  def weightedQuickUnion = new WeightedQuickUnion(10)

  def pathCompressionQuickUnion = new PathCompressionQuickUnion(10)

  def weightedPathCompressionQuickUnion = new WeightedPathCompressionQuickUnion(10)

  "Quick find " should behave like _10SizedUF(quickFind)
  "Quick union " should behave like _10SizedUF(quickUnion)
  "Weighted quick union " should behave like _10SizedUF(weightedQuickUnion)
  it should "be weighted " in {
    val string = weightedQuickUnion.union(9,1).union(9,7).union(4,6).union(9,5).union(1,8).union(0,3).union(5,2).union(0,4).union(6,1).toString
    assert(string === "9;9;9;0;0;9;4;9;9;9")
  }
  "Path compression quick union" should behave like _10SizedUF(pathCompressionQuickUnion)
  "Weighted path compression quick union" should behave like _10SizedUF(weightedPathCompressionQuickUnion)
  it should "be weighted and compressed" in {
    val string = weightedPathCompressionQuickUnion.union(9,1).union(9,7).union(4,6).union(9,5).union(1,8).union(0,3).union(5,2).union(0,4).union(6,1).toString
    assert(string === "0;9;9;0;0;9;0;9;9;0")
  }


}

trait UFBehaviors {
  this: FlatSpec =>

  def _10SizedUF(uf: => UF) {

    it should "not find a connection" in {
      val linkedUf = uf.union(0, 2).union(1, 3).union(0, 1).union(7, 8).union(8, 9).union(6, 7).union(3, 5)
      assert(!linkedUf.connected(0, 9))
    }

    it should "find a connection" in {
      val linkedUf = uf.union(0, 3).union(3, 8).union(8, 2).union(2, 5)
      assert(linkedUf.connected(5, 3))
      assert(linkedUf.connected(3, 5))
    }
  }
}

