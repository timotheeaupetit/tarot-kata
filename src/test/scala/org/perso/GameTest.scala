package org.perso

import org.scalatest.funspec.AnyFunSpec


class GameTest extends AnyFunSpec {
  describe("4 players game") {
    val p1 = Player(1, "Léontine")
    val p2 = Player(2, "Fernand")
    val p3 = Player(3, "Adélaïde")
    val p4 = Player(4, "Henri")

    it("must give 100 points to the challenger") {
      assert(false)
    }
  }
}
