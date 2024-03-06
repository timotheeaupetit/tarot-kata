package org.perso

class Game(players: Set[Player],
           challenger: Player,
           contract: Contract,
           trickPoints: Float,
           oudlers: Int,
           partner: Option[Player] = None) {

  def calculateScores: Map[Player, Int] = {
    val diff = oudlers match {
      case 0 => trickPoints - 56
      case 1 => trickPoints - 51
      case 2 => trickPoints - 41
      case 3 => trickPoints - 36
    }

    val basePoints = ((25 + diff.abs.toInt) * diff / diff.abs).toInt

    val baseScore = basePoints * contract.coefficient

    players.map {
      case p if p == challenger => partner match {
        case Some(x) => (p, 2 * baseScore)
        case _ => (p, (players.size - 1) * baseScore)
      }
      case p => partner match {
        case Some(x) if x == p => (p, baseScore)
        case _ => (p, -baseScore)
      }
    }.toMap
  }
}
