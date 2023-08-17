package Helden
import Gegner.Gegner
import geringerSchaden
import mittlererSchaden
import kritischerSchaden

class Bogenschütze (name : String, hpHero: Double = 750.0) : Hero(name,hpHero){

    //Attacken des Bogenschützen
    //Pfeilangriff
    override fun kleineAttacke(gegner: List<Gegner>) {
        // Schadenswert für kleine Attacke ermitteln
        var kleinerSchaden = geringerSchaden()

        // Ein Ziel aus der Gegnerliste auswählen
        var ziel = gegnerWählen(gegner)

        // Information über den Angriff anzeigen
        println("Der $name schießt einen Pfeil auf $ziel.")

        // Überprüfen, ob das Ziel eine Trollschutzfunktion aktiviert hat
        if (ziel.trollProtection) {
            // Wenn der Trollschutz aktiviert ist, wird der Angriff absorbiert
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        } else {
            // Ansonsten wird Schaden am Ziel verursacht und der erzeugte Schaden wird angezeigt
            println("$name trifft $ziel und verursacht $kleinerSchaden Schaden.")
            ziel.hpGegner -= kleinerSchaden
        }

        // Überprüfen, ob das Ziel nach dem Angriff keine HP mehr hat
        if (ziel.hpGegner <= 0) {
            // Wenn das Ziel keine HP mehr hat, wird angezeigt, dass es eliminiert wurde
            println("Der Gegner ${ziel.name} wurde durch den Angriff eliminiert.")
        }
    }


    // Brennenderpfeil
    override fun mittlereAttacke(gegner: List<Gegner>) {
        if (mittlereAttackeVerfügbar <=0) {
            println("Die Spezialattacke von $name wurde bereits verwendet und kann nicht erneut eingesetzt werden!")
            return
        }
        var mittlererSchaden = mittlererSchaden()
        var ziel = gegnerWählen(gegner)
        println("Der $name schießt einen brennenden Pfeil auf $ziel.")
        if (ziel.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        }else {
            println("$name trifft $ziel und verursacht $mittlererSchaden Schaden.")
            ziel.hpGegner-=mittlererSchaden
        }
        if (ziel.hpGegner <= 0) {
            println("Der Gegner ${ziel.name} wurde durch den Angriff eliminiert.")
        }
        println()
        mittlereAttackeVerfügbar--
    }

    override fun spezialAttacke(gegner: List<Gegner>) {
        if (spezialAttackeVerfügbar <= 0) {
            println("Die Spezialattacke von $name wurde bereits verwendet und kann nicht erneut eingesetzt werden!")
            return}
        var kritischerSchaden = kritischerSchaden()
        var ziel = gegnerWählen(gegner)
        println("Der $name schießt einen Präzisonspfeil auf $ziel.")
        if (ziel.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        } else {
            println("Der $name trifft den $ziel und verursacht $kritischerSchaden Schaden.")
            ziel.hpGegner -= kritischerSchaden
        }
        if (ziel.hpGegner <= 0) {
            println("Der Gegner ${ziel.name} wurde durch den Angriff eliminiert.")
        }
        spezialAttackeVerfügbar--
        println()
    }


}