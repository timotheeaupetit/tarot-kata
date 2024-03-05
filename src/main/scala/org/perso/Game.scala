package org.perso

class Game(players: Set[Player],
           challenger: Player,
           contract: Contract,
           trickPoints: Float,
           oudlers: Int) {

  def calculateScores: Map[Player, Int] = ???
}
