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
        println("Der $name greift mit Attacke -Schlagen- an.")
        Thread.sleep(500)
        if (Hero.isProtected) {
            println("Der Held ${Hero.name} ist durch einen Zauber geschützt und kann nicht angegriffen werden.")
        }else {
            println("$name setzt die Attacke -Schlagen- ein und verursacht $kleinerSchaden Schaden bei ${Hero.name}.")
            Hero.hpHero-=kleinerSchaden
        }
    }

    open fun mittlereAttackeGegner (Hero: Hero) {
        var mittlererSchaden = mittlererSchaden()
        println("Der $name greift mit Attacke -Stechen- an.")
        Thread.sleep(500)
        if (Hero.isProtected) {
            println("Der Held ${Hero.name} ist durch einen Zauber geschützt und kann nicht angegriffen werden.")
        } else {
            println("$name setzte die Attacke -Schlagen- ein und verursacht $mittlererSchaden Schaden bei ${Hero.name}.")
            Hero.hpHero -= mittlererSchaden
        }
    }




}