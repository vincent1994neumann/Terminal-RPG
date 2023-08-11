package Gegner

import Helden.Hero
import geringerSchaden
import mittlererSchaden

open class Gegner (var name : String,var hpGegner: Int){
    var trollProtection : Boolean = false
    override fun toString(): String {
        return name
    }

    open fun kleineAttackeGegner (Hero: Hero){
        var kleinerSchaden = geringerSchaden()
        println("$name greift mit Attacke -Schlagen- an.")
        Thread.sleep(500)
        if (Hero.isProtected) {
            println("${Hero.name} ist durch einen Zauber geschützt und kann nicht angegriffen werden.")
        }else {
            println("$name verursacht $kleinerSchaden Schaden bei ${Hero.name}.")
            Hero.hpHero-=kleinerSchaden
        }
    }

    open fun mittlereAttackeGegner (Hero: Hero) {
        var mittlererSchaden = mittlererSchaden()
        println("$name greift mit Attacke -Stechen- an.")
        Thread.sleep(500)
        if (Hero.isProtected) {
            println("${Hero.name} ist durch einen Zauber geschützt und kann nicht angegriffen werden.")
        } else {
            println("$name verursacht $mittlererSchaden Schaden bei ${Hero.name}.")
            Hero.hpHero -= mittlererSchaden
        }
    }




}