package org.perso

import org.perso.Contract._

object Main extends App {
  println("Welcome, tarot players!")

  private val p1 = Player(1, "Léontine")
  private val p2 = Player(2, "Fernand")
  private val p3 = Player(3, "Adélaïde")
  private val p4 = Player(4, "Henri")

  private val game = new Game(
    Set(p1, p2, p3, p4),
    challenger = p4,
    contract = Garde,
    trickPoints = 41.5,
    oudlers = 1
  )

  println(game.calculateScores)
}
