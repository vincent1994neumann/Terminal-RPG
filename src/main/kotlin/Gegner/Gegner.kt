package Gegner

import ANSI_RESET
import Helden.Hero
import geringerSchaden
import mittlererSchaden

open class Gegner (var name : String,var hpGegner: Double){
    var trollProtection : Boolean = false
    var fluchRunden: Int = 0
    var fluchSchaden :Int = 0
    override fun toString(): String {
        return name
    }

    open fun kleineAttackeGegner (Hero: Hero){
        var kleinerSchaden = geringerSchaden()
        println("$ANSI_BROWN $name greift mit Attacke -Schlagen- an.")
        Thread.sleep(500)
        if (Hero.isProtected) {
        }else {
            println("$name verursacht $kleinerSchaden Schaden bei ${Hero.name}. $ANSI_RESET")
            Hero.hpHero-=kleinerSchaden
        }
    }

    open fun mittlereAttackeGegner (Hero: Hero) {
        var mittlererSchaden = mittlererSchaden()
        println("$ANSI_BROWN $name greift mit Attacke -Stechen- an.")
        Thread.sleep(500)
        if (Hero.isProtected) {
        } else {
            println("$name verursacht $mittlererSchaden Schaden bei ${Hero.name}.$ANSI_RESET")
            Hero.hpHero -= mittlererSchaden
        }
    }




}