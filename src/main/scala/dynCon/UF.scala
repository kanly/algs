package dynCon

abstract class UF {

  def union(p: Int, q: Int): UF

  def connected(p: Int, q: Int): Boolean
}

object Commons {

  def treeToString(elements: List[Int]): String = {
    treeToString((0 to (elements.size - 1)).zip(elements).toList, Option.empty)
  }

  def treeToString(pairs: List[(Int, Int)], currentNode: Option[Int], currentLevel: Int = 0): String = {
    if (currentNode.isEmpty) {
      pairs.filter((e: ((Int, Int))) => e._1 == e._2).map((e: ((Int, Int))) => treeToString(pairs, Option(e._1))).mkString
    } else {
      val siblings = pairs.filter((e: ((Int, Int))) => currentNode.get == e._2 && e._1 != e._2)
      "| " * currentLevel + currentNode.get + "\n" + (if (siblings.isEmpty) "" else siblings.map((e: ((Int, Int))) => treeToString(pairs, Option(e._1), currentLevel + 1)).mkString(""))
    }
  }
}
