package Helden
import Gegner.Gegner
import geringerSchaden
import mittlererSchaden
import kritischerSchaden

class Bogenschütze (name : String, hpHero: Int = 300) : Hero(name,hpHero){

    //Attacken des Bogenschützen
    //Pfeilangriff
    override fun kleineAttacke(Gegner: Gegner) {
        var kleinerSchaden = geringerSchaden()
        println("Der $name schießt einen Pfeil auf $Gegner.")
        Thread.sleep(500)
        if (Gegner.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        }else {
            println("$name trifft $Gegner und verursacht $kleinerSchaden Schaden.")
            Gegner.hpGegner -= kleinerSchaden
        }
    }

    // Brennenderpfeil
    override fun mittlereAttacke(Gegner: Gegner) {
        var mittlererSchaden = mittlererSchaden()
        println("Der $name schießt einen brennenden Pfeil auf $Gegner.")
        Thread.sleep(500)
        if (Gegner.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        }else {
            println("$name trifft $Gegner und verursacht $mittlererSchaden Schaden.")
            Gegner.hpGegner-=mittlererSchaden
        }
    }

    override fun spezialAttacke(Gegner: Gegner) {
        var kritischerSchaden = kritischerSchaden()
        println("Der $name schießt einen Präzisonspfeil auf $Gegner.")
        Thread.sleep(500)
        if (Gegner.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        } else {
            println("Der $name trifft den $Gegner und verursacht $kritischerSchaden Schaden.")
            Gegner.hpGegner -= kritischerSchaden
        }
    }


}