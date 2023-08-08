package Gegner

import Helden.Hero
import geringerSchaden
import mittlererSchaden

class DunklerRitter (name: String, hpGegner: Int = 1500) : Gegner(name,hpGegner){

    fun schlagen (Hero: Hero){
        var kleinerSchaden = geringerSchaden()
        println("Der dunkle Ritter trifft ${Hero.name} und verursacht $kleinerSchaden Schaden.")
        Hero.hpHero-=kleinerSchaden
    }

    fun stechen (Hero: Hero) {
        var mittlererSchaden = mittlererSchaden()
        println("Der dunkle Ritter trifft ${Hero.name} und verursacht $mittlererSchaden Schaden.")
        Hero.hpHero-=mittlererSchaden
    }
}