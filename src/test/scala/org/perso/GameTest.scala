package org.perso

import org.perso.Contract._
import org.scalatest.funspec.AnyFunSpec


class GameTest extends AnyFunSpec {

  private val p1 = Player(1, "Octavie")
  private val p2 = Player(2, "Fernand")
  private val p3 = Player(3, "Ursule")
  private val p4 = Player(4, "Hippolyte")
  private val p5 = Player(5, "Huguette")

  describe("3 players game") {
    val players = Set(p1, p3, p4)

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
      it("the challenger must win 296 points") {
        val game = new Game(
          players,
          challenger = p3,
          contract = GardeSans,
          trickPoints = 53,
          oudlers = 2
        )

        val scores = game.calculateScores

        assert(scores(p3) == 296)

        val defenderScores = scores - p3

        assert(defenderScores.values.forall(_ == -148))
      }
    }

    describe("lost contract") {
      it("the challenger must lose 312 points") {
        val game = new Game(
          players,
          challenger = p1,
          contract = GardeContre,
          trickPoints = 50,
          oudlers = 1
        )

        val scores = game.calculateScores

        assert(scores(p1) == -312)

        val defenderScores = scores - p1

        assert(defenderScores.values.forall(_ == 156))
      }
    }
  }

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
          trickPoints = 52.5,
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

  describe("5 players game") {
    val players = Set(p1, p2, p3, p4, p5)

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
      it("the challenger must win 296 points and the partner 148") {
        val game = new Game(
          players,
          challenger = p2,
          contract = GardeSans,
          trickPoints = 53,
          oudlers = 2,
          partner = Some(p1)
        )

        val scores = game.calculateScores

        assert(scores(p2) == 296)
        assert(scores(p1) == 148)

        val defenderScores = scores - p2 - p1

        assert(defenderScores.values.forall(_ == -148))
      }

      it("the challenger must win 592 points without a partner") {
        val game = new Game(
          players,
          challenger = p2,
          contract = GardeSans,
          trickPoints = 53,
          oudlers = 2
        )

        val scores = game.calculateScores

        assert(scores(p2) == 592)

        val defenderScores = scores - p2

        assert(defenderScores.values.forall(_ == -148))
      }
    }

    describe("lost contract") {
      it("the challenger must lose 312 points and the partner 156") {
        val game = new Game(
          players,
          challenger = p1,
          contract = GardeContre,
          trickPoints = 50,
          oudlers = 1,
          partner = Some(p5)
        )

        val scores = game.calculateScores

        assert(scores(p1) == -312)
        assert(scores(p5) == -156)

        val defenderScores = scores - p1 - p5

        assert(defenderScores.values.forall(_ == 156))
      }

      it("the challenger must lose 624 points without a partner") {
        val game = new Game(
          players,
          challenger = p1,
          contract = GardeContre,
          trickPoints = 50.5,
          oudlers = 1,
        )

        val scores = game.calculateScores

        assert(scores(p1) == -624)

        val defenderScores = scores - p1

        assert(defenderScores.values.forall(_ == 156))
      }
    }
  }

  describe("calculateBasePoints") {
    val players = Set(p1, p2, p3, p4)

    it("should give 25 for a Petite won with 3 oudlers and 36 trick points") {
      val game = new Game(
        players,
        challenger = p1,
        contract = Petite,
        trickPoints = 36,
        oudlers = 3
      )

      assert(game.calculateBasePoints() == 25)
    }

    it("should give 26 for a Petite won with 3 oudlers and 36.5 trick points") {
      val game = new Game(
        players,
        challenger = p1,
        contract = Petite,
        trickPoints = 36.5,
        oudlers = 3
      )

      assert(game.calculateBasePoints() == 26)
    }

    it("should give -26 for a Petite lost with 3 oudlers and 35.5 trick points") {
      val game = new Game(
        players,
        challenger = p1,
        contract = Petite,
        trickPoints = 35.5,
        oudlers = 3
      )

      assert(game.calculateBasePoints() == -26)
    }
  }
}
