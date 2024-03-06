package org.perso

enum Contract(val coefficient: Int) {
  case Petite extends Contract(1)
  case Garde extends Contract(2)
  case GardeSans extends Contract(4)
  case GardeContre extends Contract(6)
}
