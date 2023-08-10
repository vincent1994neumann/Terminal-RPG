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
        println("Der $name greift an - Attacke -Schlagen-")
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
        println("Der $name greift an - Attacke -Stechen-")
        Thread.sleep(500)
        if (Hero.isProtected) {
            println("Der Held ${Hero.name} ist durch einen Zauber geschützt und kann nicht angegriffen werden.")
        } else {
            println("$name setzte die Attacke -Schlagen- ein und verursacht $mittlererSchaden Schaden bei ${Hero.name}.")
            Hero.hpHero -= mittlererSchaden
        }
    }


    fun stillAliveGegner():Boolean{
        if (hpGegner <= 0){
            println("$name der Gegner ist eliminiert worden!")
            return false
        }else  {
            println("$name hat noch $hpGegner Lebenspunkte.")
            return true
        }
    }
}