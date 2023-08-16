package Gegner

import ANSI_DARK_RED
import ANSI_GREEN
import ANSI_NEON_GREEN
import `{ANSI_RESET}`
import Helden.Hero
import geringerFlächenSchaden
import kritischerSchaden
import mittlererSchaden
const val ANSI_BROWN = "\u001B[0;33m"

class Troll (name: String, hpGegner: Double = 1000.0) : Gegner(name,hpGegner){
        //Attacke des Trolls

    /**
     * Die Attacke "Keulen Schwung" des Trolls, die gegen alle Helden in der Liste gerichtet ist.
     * @param heldenListe Die Liste der Helden, die angegriffen werden sollen.
     */
    fun keulenSchwung(heldenListe: MutableList<Hero>) {
        // Bestimme den Schaden, der durch den Keulen Schwung verursacht wird.
        var keulenSchadenGegner = geringerFlächenSchaden()

        // Drucke die Angriffsnachricht für den Keulen Schwung.
        println("${this.name} ${ANSI_BROWN} setzt die Attacke -Keulen Schwung- ein und trifft ${ANSI_NEON_GREEN}alle Helden$`{ANSI_RESET}`${ANSI_BROWN} mit ${keulenSchadenGegner} Schaden.$`{ANSI_RESET}`")

        // Gehe durch alle Helden in der Liste.
        for (hero in heldenListe) {
            // Wenn der Held durch einen Schutzzauber geschützt ist, wird der Angriff nicht wirksam.
            if (hero.isProtected) {
                println("Die Attacke ist bei ${hero.name} wirkungslos, da der Schutzzauber vom Magier wirkt.")
                println("$ANSI_GREEN Der Angriff schlug fehl.$`{ANSI_RESET}`")
                println()
            } else {
                // Reduziere die HP des Helden um den Schaden des Keulen Schwungs.
                hero.hpHero -= keulenSchadenGegner
            }
        }
    }

    /*
    //Chat GPT hat mir hier geholfen zu verstehen warum ich nicht direkt in der schleife einen Helden removen kann soltle er kein Leben mehr haben.
    //Der Lösungsvorschlag war, zu nächst die Helden in einer neuen Liste zu speichern und die nach der schlaufe von der helden liste abzuziehen.

for (hero in HeroMutableList){
            if (hero.isProtected){
                println("Der Gegner ${hero.name} ist durch einen Zauber geschützt.")
            }else {
                hero.hpHero -= keulenSchadenGegner
                if (hero.hpHero <= 0) {
                    println("Der Held ${hero.name} wurde eliminiert")
                    heldenListe.remove(hero)
                    println(heldenListe)
                }
            }
        }

*/

    fun schlagenTroll(heldenListe: MutableList<Hero>){
        // Mittleren Schaden berechnen
        var mittlererSchaden = mittlererSchaden()

        // Ein zufälliges Ziel aus der Heldenliste auswählen
        var auswahlHit = (0 until heldenListe.size).random()

        // Liste zum Speichern von eliminierten Helden erstellen
        var eliminierteHelden : MutableList<Hero> = mutableListOf()

        // Überprüfen, ob der gewählte Held geschützt ist
        if (!heldenListe[auswahlHit].isProtected) {

            // Drucke den Angriff und den Schaden, der dem Helden zugefügt wurde
            println("${ANSI_BROWN}Der ${ANSI_DARK_RED}Troll$`{ANSI_RESET}`${ANSI_BROWN} setzt die Attacke -Schlagen- ein und verursacht $mittlererSchaden Schaden bei$`{ANSI_RESET}` ${heldenListe[auswahlHit].name}.")

            // Schaden vom HP des Helden abziehen
            heldenListe[auswahlHit].hpHero -= mittlererSchaden

            // Überprüfen, ob der HP des Helden nach dem Angriff 0 oder darunter ist
            if (heldenListe[auswahlHit].hpHero <= 0) {
                println("${heldenListe[auswahlHit].name} wurde eliminiert. $`{ANSI_RESET}`")

                // Den eliminierten Helden zur Liste der eliminierten Helden hinzufügen
                eliminierteHelden.add(heldenListe[auswahlHit])
            }
        }

        // Alle eliminierten Helden aus der Heldenliste entfernen
        heldenListe.removeAll(eliminierteHelden)
    }



    fun umrennenTroll(heldenListe: MutableList<Hero>) {
        // Kritischen Schaden berechnen
        var kritischerSchaden = kritischerSchaden()

        // Ein zufälliges Ziel aus der Heldenliste auswählen
        var auswahlHit = (0 until heldenListe.size).random()

        // Liste zum Speichern von eliminierten Helden erstellen
        var eliminierteHelden: MutableList<Hero> = mutableListOf()

        // Überprüfen, ob der gewählte Held geschützt ist
        if (!heldenListe[auswahlHit].isProtected) {
            // Drucke den Angriff und den Schaden, der dem Helden zugefügt wurde
            println("${ANSI_BROWN}Der ${ANSI_DARK_RED}Troll$`{ANSI_RESET}`${ANSI_BROWN} setzt die Attacke -Umrennen- ein und verursacht $kritischerSchaden Schaden beim Helden$`{ANSI_RESET}` ${heldenListe[auswahlHit].name}.")

            // Schaden vom HP des Helden abziehen
            heldenListe[auswahlHit].hpHero -= kritischerSchaden

            // Überprüfen, ob der HP des Helden nach dem Angriff 0 oder darunter ist
            if (heldenListe[auswahlHit].hpHero <= 0) {
                println("${heldenListe[auswahlHit].name} wurde eliminiert. $`{ANSI_RESET}`")

                // Den eliminierten Helden zur Liste der eliminierten Helden hinzufügen
                eliminierteHelden.add(heldenListe[auswahlHit])
            }
        }
        // Alle eliminierten Helden aus der Heldenliste entfernen (Dieser Schritt wurde im ursprünglichen Code übersehen)
        heldenListe.removeAll(eliminierteHelden)
    }


    fun auswahlAttackeTroll(heldenListe: MutableList<Hero>) {
        // Eine zufällige Zahl zwischen 1 und 100 generieren
        var randomNumber = (1..100).random()

        // Entscheiden, welche Attacke basierend auf der generierten Zahl verwendet wird
        when (randomNumber) {
            // Bei einer Zahl zwischen 1 und 55 wird die "keulenSchwung"-Attacke verwendet
            in 1..55 -> keulenSchwung(heldenListe)

            // Bei einer Zahl zwischen 56 und 80 wird die "schlagenTroll"-Attacke verwendet
            in 56..80 -> schlagenTroll(heldenListe)

            // Bei einer Zahl zwischen 81 und 100 wird die "umrennenTroll"-Attacke verwendet
            in 81..100 -> umrennenTroll(heldenListe)
        }
    }
}
