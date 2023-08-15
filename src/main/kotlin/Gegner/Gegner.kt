package Gegner

import `{ANSI_RESET}`
import Helden.Hero
import geringerSchaden
import mittlererSchaden

open class Gegner (var name : String,var hpGegner: Double){
init {
    println("Ein wilder $name wurde beschworen.")
    Thread.sleep(500)
}

    var trollProtection : Boolean = false
    var fluchRunden: Int = 0
    var fluchSchaden :Int = 0
    override fun toString(): String {
        return name
    }

    open fun kleineAttackeGegner (Hero: Hero){
        var kleinerSchaden = geringerSchaden()
        println(" $name $ANSI_BROWN greift mit Attacke -Schlagen- an.$`{ANSI_RESET}`")
        Thread.sleep(500)
        if (Hero.isProtected) {
        }else {
            println(" $name $ANSI_BROWN verursacht $kleinerSchaden Schaden bei $`{ANSI_RESET}` ${Hero.name}.")
            Hero.hpHero-=kleinerSchaden
        }
    }

    open fun mittlereAttackeGegner (Hero: Hero) {
        var mittlererSchaden = mittlererSchaden()
        println(" $name $ANSI_BROWN greift mit Attacke -Stechen- an.$`{ANSI_RESET}`")
        Thread.sleep(500)
        if (Hero.isProtected) {
        } else {
            println(" $name $ANSI_BROWN verursacht $mittlererSchaden Schaden bei $`{ANSI_RESET}` ${Hero.name}.")
            Hero.hpHero -= mittlererSchaden
        }
    }




}