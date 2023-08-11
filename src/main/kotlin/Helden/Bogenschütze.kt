package Helden
import Gegner.Gegner
import geringerSchaden
import mittlererSchaden
import kritischerSchaden

class Bogenschütze (name : String, hpHero: Int = 300) : Hero(name,hpHero){

    //Attacken des Bogenschützen
    //Pfeilangriff
    override fun kleineAttacke(gegner: List<Gegner>) {
        var kleinerSchaden = geringerSchaden()
        var ziel = gegnerWählen(gegner)
        println("Der $name schießt einen Pfeil auf $ziel.")
        Thread.sleep(500)
        if (ziel.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        }else {
            println("$name trifft $ziel und verursacht $kleinerSchaden Schaden.")
            ziel.hpGegner -= kleinerSchaden
        }
        if (ziel.hpGegner <= 0) {
            println("Der Gegner ${ziel.name} wurde durch den Angriff eliminiert.")
        }
    }

    // Brennenderpfeil
    override fun mittlereAttacke(gegner: List<Gegner>) {
        var mittlererSchaden = mittlererSchaden()
        var ziel = gegnerWählen(gegner)
        println("Der $name schießt einen brennenden Pfeil auf $ziel.")
        Thread.sleep(500)
        if (ziel.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        }else {
            println("$name trifft $ziel und verursacht $mittlererSchaden Schaden.")
            ziel.hpGegner-=mittlererSchaden
        }
        if (ziel.hpGegner <= 0) {
            println("Der Gegner ${ziel.name} wurde durch den Angriff eliminiert.")
        }
    }

    override fun spezialAttacke(gegner: List<Gegner>) {
        var kritischerSchaden = kritischerSchaden()
        var ziel = gegnerWählen(gegner)
        println("Der $name schießt einen Präzisonspfeil auf $ziel.")
        Thread.sleep(500)
        if (ziel.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        } else {
            println("Der $name trifft den $ziel und verursacht $kritischerSchaden Schaden.")
            ziel.hpGegner -= kritischerSchaden
        }
        if (ziel.hpGegner <= 0) {
            println("Der Gegner ${ziel.name} wurde durch den Angriff eliminiert.")
        }
    }


}