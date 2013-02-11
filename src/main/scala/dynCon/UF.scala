package dynCon

abstract class UF {

  def union(p:Int,q:Int): UF
  def connected(p:Int, q:Int): Boolean
}
