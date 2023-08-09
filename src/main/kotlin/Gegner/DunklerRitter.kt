package Gegner

import Helden.Hero
import geringerSchaden
import mittlererSchaden

class DunklerRitter (name: String, hpGegner: Int = 1500) : Gegner(name,hpGegner){

      fun schlagen (Hero: Hero){
        var kleinerSchaden = geringerSchaden()
        println("Der dunkle Ritter setzte die Attacke -Schlagen- ein und verursacht $kleinerSchaden Schaden bei ${Hero.name}.")
        Hero.hpHero-=kleinerSchaden
    }

    fun stechen (Hero: Hero) {
        var mittlererSchaden = mittlererSchaden()
        println("Der dunkle Ritter setzte die Attacke -Stechen- ein und verursacht $mittlererSchaden Schaden bei ${Hero.name}.")
        Hero.hpHero-=mittlererSchaden
    }

    fun auswahlAttackeDunklerRitter (Hero: Hero) {
        var randomNumber = (1..100).random()
        when (randomNumber) {
            in 1..65 -> schlagen(Hero)
            in 66..100 -> stechen(Hero)
        }
    }




}
