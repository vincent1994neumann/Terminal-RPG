package Gegner

import Helden.Hero
import geringerFlächenSchaden
import kritischerSchaden
import mittlererSchaden

class Troll (name: String, hpGegner: Int = 2000) : Gegner(name,hpGegner){
    var schutzWall = false
    //Attacke des Trolls

    fun keulenSchwung(heldenListe: MutableList<Hero>){
        var keulenSchadenGegner = geringerFlächenSchaden()
        println("Der Troll ${this.name} setzt die Attacke -Keulen Schwung- ein und trifft alle Helden mit ${geringerFlächenSchaden()} Schaden.")

        for (hero in heldenListe){
            if (hero.isProtected){
                println("Die Helden sind durch einen Zauber geschützt.")
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
        println("Der Troll setzt die Attacke -Schlagen- ein und verursacht $mittlererSchaden Schaden bei ${heldenListe[auswahlHit].name}.")

        if (heldenListe[auswahlHit].isProtected){
            println("Der Held -${heldenListe[auswahlHit].name}- ist durch einen Zauber geschützt.")
        }else {
            heldenListe[auswahlHit].hpHero-=mittlererSchaden
                if(heldenListe[auswahlHit].hpHero<=0){
                    println("Der Held ${heldenListe[auswahlHit].name} wurde eliminiert")
                    eliminierteHelden.add(heldenListe[auswahlHit])
                }
              }
        heldenListe.removeAll(eliminierteHelden)
    }


    fun umrennenTroll(heldenListe: MutableList<Hero>) {
        var kritischerSchaden = kritischerSchaden()
        var auswahlHit = (0..<heldenListe.size).random()
        var eliminierteHelden: MutableList<Hero> = mutableListOf()
        println("Der Troll setzt die Attacke -Umrennen- ein und verursacht $kritischerSchaden Schaden beim Helden ${heldenListe[auswahlHit].name}.")

        if (heldenListe[auswahlHit].isProtected) {
            println("Der Held -${heldenListe[auswahlHit].name}- ist durch einen Zauber geschützt.")
        } else {
            heldenListe[auswahlHit].hpHero -= kritischerSchaden
            if (heldenListe[auswahlHit].hpHero <= 0) {
                println("Der Held ${heldenListe[auswahlHit].name} wurde eliminiert")
                eliminierteHelden.add(heldenListe[auswahlHit])
            }
        }
    }

    fun auswahlAttackeTroll (heldenListe: MutableList<Hero>) {
        var randomNumber = (1..100).random()
        when (randomNumber) {
            in 1..59 -> keulenSchwung(heldenListe)
            in 60..84 -> schlagenTroll(heldenListe)
            in 85..100 -> umrennenTroll(heldenListe)
        }
    }
}
