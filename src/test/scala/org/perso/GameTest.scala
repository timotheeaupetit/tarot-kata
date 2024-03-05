package org.perso

import org.perso.Contract._
import org.scalatest.funspec.AnyFunSpec


class GameTest extends AnyFunSpec {

  private val p1 = Player(1, "Octavie")
  private val p2 = Player(2, "Fernand")
  private val p3 = Player(3, "Ursule")
  private val p4 = Player(4, "Hippolyte")

  describe("4 players game") {
    val players = Set(p1, p2, p3, p4)

    it("sum of all scores must equal 0") {
      val game = new Game(
        players,
        challenger = p4,
        contract = Garde,
        trickPoints = 53,
        oudlers = 1
      )

      val scores = game.calculateScores

      assert(scores.values.sum == 0)
    }

    describe("won contract") {
      it("the challenger must win 444 points") {
        val game = new Game(
          players,
          challenger = p2,
          contract = GardeSans,
          trickPoints = 53,
          oudlers = 2
        )

        val scores = game.calculateScores

        assert(scores(p2) == 444)

        val defenderScores = scores - p2

        assert(defenderScores.values.forall(_ == -148))
      }
    }

    describe("lost contract") {
      it("the challenger must lose 468 points") {
        val game = new Game(
          players,
          challenger = p1,
          contract = GardeContre,
          trickPoints = 50,
          oudlers = 1
        )

        val scores = game.calculateScores

        assert(scores(p1) == -468)

        val defenderScores = scores - p1

        assert(defenderScores.values.forall(_ == 156))
      }
    }
  }
}
