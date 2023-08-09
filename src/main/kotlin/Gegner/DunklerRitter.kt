package Gegner

import Helden.Hero
import Helden.Ritter
import geringerSchaden
import mittlererSchaden
import kotlin.random.Random

class DunklerRitter (name: String, hpGegner: Int = 1500) : Gegner(name,hpGegner){

      fun schlagen (Hero: Hero){
        var kleinerSchaden = geringerSchaden()
        println("Der dunkle Ritter trifft -${Hero.name}- und verursacht $kleinerSchaden Schaden.")
        Hero.hpHero-=kleinerSchaden
    }

    fun stechen (Hero: Hero) {
        var mittlererSchaden = mittlererSchaden()
        println("Der dunkle Ritter trifft -${Hero.name}- und verursacht $mittlererSchaden Schaden.")
        Hero.hpHero-=mittlererSchaden
    }

    fun attackDunklerRitter (Hero: Hero) {
        var randomNumber = (1..2).random()
        when (randomNumber) {
            1 -> schlagen(Hero)
            2-> stechen(Hero)
        }
    }


}
