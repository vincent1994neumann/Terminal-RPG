package Helden

import Gegner.Gegner
import geringerFlächenSchaden
import mittlererFlächenSchaden
import org.w3c.dom.css.Counter

class Magier (name : String, hpHero: Int = 10000) : Hero(name,hpHero) {

    fun hagelSchadenHero(gegnerListe: List<Gegner>) { //Muatbelist damit ich ggf später die Liste verändern kann, z.B. wenn ein Gegener eliminiert wurde
        val geringerFlächenSchaden = geringerFlächenSchaden()
        println("Der Magier greift mit der Attacke -Hagelschaden- an.")
        Thread.sleep(500)
        for (gegner in gegnerListe) {
            if (gegner.trollProtection) {
                println("Der Troll absorbiert deinen Angriff mit Magie.")
            } else {
                println("$name trifft $gegner und fügt $geringerFlächenSchaden Schaden zu.")
                gegner.hpGegner -= geringerFlächenSchaden
                if (gegner.hpGegner <= 0) {
                    println("Der Gegner ${gegner.name} wurde durch den Angriff eliminiert.")
                }
            }
        }
        println()
    }

    fun feuerBallHero(gegnerListe: List<Gegner>) { //Muatbelist damit ich ggf später die Liste verändern kann, z.B. wenn ein Gegener eliminiert wurde
        val mittlererFlächenSchaden = mittlererFlächenSchaden()
        println("Der Magier greift mit der Attacke Feuerball an.")
        for (gegner in gegnerListe) {
            if (gegner.trollProtection) {
                println("Der Troll absorbiert deinen Angriff mit Magie.")
            } else {
                println("$name trifft $gegner und verursacht $mittlererFlächenSchaden Schaden.")
                gegner.hpGegner -= mittlererFlächenSchaden
                if (gegner.hpGegner <= 0) {
                    println("Der Gegner ${gegner.name} wurde durch den Angriff eliminiert.")
                }
            }
        }
        println()
    }
    fun schutzZauberHero(heldenListe: MutableList<Hero>) {
        for (hero in heldenListe) {
            hero.isProtected = true
            hero.protectionCountdown = 2 // Für zwei Runden geschützt, nicht vier.
            println("${hero.name}'s Schutzzauber wurde aktiviert.")
        }
        println("Rundencountdown Schutzzauber: $protectionCountdown")
    }


            //Muss den Boolean in der Hero Klasse auf true setzen für eine Runde.

    override fun attackeWählen(gegnerListe: List<Gegner>, heldenListe: MutableList<Hero>) {
        println("Bitte wähle die Attacke, mit der du angreifen möchtest.")
        println("1. Hagelschaden (Flächenschaden)")
        println("2. Feuerball (Flächenschaden)")
        if (protectionCountdown == 0){
            println("3. Schutzzauber (Schütz alle Helden für die nächsten 2.Runden)")
        }
        println()

        println("Welche Attacke möchtest du auswählen:")
        try {
            var choice = readln().toInt()
            when (choice){
                1 -> hagelSchadenHero(gegnerListe)
                2 -> feuerBallHero(gegnerListe)
                3 -> {schutzZauberHero(heldenListe)
                }
            }
        }catch (e : Exception){
            println("Ups, $e ")
            println("Bitte prüfe deine Eingabe.")
            attackeWählen(gegnerListe,heldenListe)
        }

    }
}