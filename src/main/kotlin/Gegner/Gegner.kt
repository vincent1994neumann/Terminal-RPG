package Gegner

// Importe für spezifische Funktionen/Objekte
import `{ANSI_RESET}`
import Helden.Hero
import geringerSchaden
import mittlererSchaden

// Hauptklasse für Gegner-Entitäten
open class Gegner(var name: String, var hpGegner: Double) {

    // Eigenschaften des Gegners
    var trollProtection: Boolean = false // Schutz vor Troll-Angriffen
    var fluchRunden: Int = 0             // Anzahl der Runden, die der Gegner verflucht ist
    var fluchSchaden: Int = 0            // Schaden pro Runde aufgrund von Fluch

    // Gibt den Namen des Gegners zurück
    override fun toString(): String {
        return name
    }

    // Kleine Attacke des Gegners
    open fun kleineAttackeGegner(Hero: Hero) {
        val kleinerSchaden = geringerSchaden() // Zufälliger kleiner Schaden
        println("$name $ANSI_BROWN greift mit Attacke -Schlagen- an.$`{ANSI_RESET}`")

        // Falls der Held nicht geschützt ist, wird Schaden zugefügt
        if (!Hero.isProtected) {
            println("$name $ANSI_BROWN verursacht $kleinerSchaden Schaden bei $`{ANSI_RESET}` ${Hero.name}.")
            Hero.hpHero -= kleinerSchaden
        }
    }

    // Mittlere Attacke des Gegners
    open fun mittlereAttackeGegner(Hero: Hero) {
        val mittlererSchaden = mittlererSchaden() // Zufälliger mittlerer Schaden
        println("$name $ANSI_BROWN greift mit Attacke -Stechen- an.$`{ANSI_RESET}`")

        // Falls der Held nicht geschützt ist, wird Schaden zugefügt
        if (!Hero.isProtected) {
            println("$name $ANSI_BROWN verursacht $mittlererSchaden Schaden bei $`{ANSI_RESET}` ${Hero.name}.")
            Hero.hpHero -= mittlererSchaden
        }
    }
}
