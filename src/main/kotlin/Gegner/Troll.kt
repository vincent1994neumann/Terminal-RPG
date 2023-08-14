package Gegner

import ANSI_RESET
import Helden.Bogenschütze
import Helden.Hero
import Helden.Magier
import Helden.Ritter
import geringerFlächenSchaden
import kritischerSchaden
import mittlererSchaden
const val ANSI_BROWN = "\u001B[0;33m"

class Troll (name: String, hpGegner: Double = 200.0) : Gegner(name,hpGegner){
        //Attacke des Trolls

    fun keulenSchwung(heldenListe: MutableList<Hero>){
        var keulenSchadenGegner = geringerFlächenSchaden()
        Thread.sleep(500)
        println("$ANSI_BROWN ${this.name} setzt die Attacke -Keulen Schwung- ein und trifft alle Helden mit ${geringerFlächenSchaden()} Schaden.")
            for (hero in heldenListe){
                if (hero.isProtected){
                    println("Die Attacke ist bei ${hero.name} Wirkungslos, da der Schutzzauber vom Magier wirkt.")
                    println("Der Angriff schlug fehl.$ANSI_RESET")
                    println()
                }else {
                hero.hpHero -= keulenSchadenGegner                                    // ich muss zuerst prüfen, ob der Held beschützt wird
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

    fun schlagenTroll (heldenListe: MutableList<Hero>){
        var mittlererSchaden = mittlererSchaden()
        var auswahlHit = (0..<heldenListe.size).random()
        var eliminierteHelden : MutableList<Hero> = mutableListOf()
            if (heldenListe[auswahlHit].isProtected) {
            } else {
                println("$ANSI_BROWN Der Troll setzt die Attacke -Schlagen- ein und verursacht $mittlererSchaden Schaden bei ${heldenListe[auswahlHit].name}.")
                heldenListe[auswahlHit].hpHero -= mittlererSchaden
                if (heldenListe[auswahlHit].hpHero <= 0) {
                    println("${heldenListe[auswahlHit].name} wurde eliminiert. $ANSI_RESET")
                    eliminierteHelden.add(heldenListe[auswahlHit])
                }
            }
        heldenListe.removeAll(eliminierteHelden)
    }


    fun umrennenTroll(heldenListe: MutableList<Hero>) {
        var kritischerSchaden = kritischerSchaden()
        var auswahlHit = (0..<heldenListe.size).random()
        var eliminierteHelden: MutableList<Hero> = mutableListOf()

        for (hero in heldenListe)
        if (heldenListe[auswahlHit].isProtected) {
        } else {
            println("$ANSI_BROWN Der Troll setzt die Attacke -Umrennen- ein und verursacht $kritischerSchaden Schaden beim Helden ${heldenListe[auswahlHit].name}.")
            heldenListe[auswahlHit].hpHero -= kritischerSchaden
            if (heldenListe[auswahlHit].hpHero <= 0) {
                println("${heldenListe[auswahlHit].name} wurde eliminiert. $ANSI_RESET")
                eliminierteHelden.add(heldenListe[auswahlHit])
            }
        }
    }

    fun auswahlAttackeTroll (heldenListe: MutableList<Hero>) {
        var randomNumber = (1..100).random()
        when (randomNumber) {
            in 1..55 -> keulenSchwung(heldenListe)
            in 56..80 -> schlagenTroll(heldenListe)
            in 81..100 -> umrennenTroll(heldenListe)
        }
    }
}
