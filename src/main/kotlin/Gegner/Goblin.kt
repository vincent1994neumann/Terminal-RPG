package Gegner

import Helden.Hero
import geringerSchaden
import mittlererSchaden

class Goblin (name: String, hpGegner: Int = 500) : Gegner(name,hpGegner) {

    fun schlagen (Hero: Hero){
        var kleinerSchaden = geringerSchaden()
        println("Der Gegner trifft ${Hero.name} und verursacht $kleinerSchaden Schaden.")
        Hero.hpHero-=kleinerSchaden
    }

    fun stechen (Hero: Hero) {
        var mittlererSchaden = mittlererSchaden()
        println("Der Gegner trifft ${Hero.name} und verursacht $mittlererSchaden Schaden.")
        Hero.hpHero-=mittlererSchaden
    }
}