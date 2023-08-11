package Helden

import Gegner.Gegner
import geringerFlächenSchaden
import mittlererFlächenSchaden

class Magier (name : String, hpHero: Int = 50) : Hero(name,hpHero) {
var abklingZeit = 2

    fun hagelSchadenHero(gegnerListe: List<Gegner>) { //Muatbelist damit ich ggf später die Liste verändern kann, z.B. wenn ein Gegener eliminiert wurde
        val geringerFlächenSchaden = geringerFlächenSchaden()
        println("Der Magier greift mit der Attacke -Hagelschaden- an.")
        Thread.sleep(500)
        for (gegner in gegnerListe) {
            if (gegner.trollProtection) {
                println("Der Troll absorbiert deinen Angriff mit Magie.")
            } else {
                println("$name trifft $gegner und verursacht $geringerFlächenSchaden Schaden.")
                gegner.hpGegner -= geringerFlächenSchaden
                if (gegner.hpGegner <= 0) {
                    println("Der ${gegner.name} wurde durch den Angriff eliminiert.")
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
                    println("Der ${gegner.name} wurde durch den Angriff eliminiert.")
                }
            }
        }
        println()
    }
        fun schutzZauberHero(rundeAnzahl:Int) {
            if (0 >= 4 && !isProtected && rundeAnzahl % 4 == 0) {
                isProtected = true
                println("Der Schutzzauber vom Magier wirkt für die nächste Spielrunde.")
            }else {
                println("Der Schutzzauber kann aktuell noch nicht angewendet werden, erst in XXX Runden")
            }

            }


            //Muss den Boolean in der Hero Klasse auf true setzen für eine Runde.

    override fun attackeWählen(gegnerListe: List<Gegner>) {
        println("Bitte wähle die Attacke, mit der du angreifen möchtest.")
        println("1. Hagelschaden (Flächenschaden)")
        println("2. Feuerball (Flächenschaden)")
        if (1==1){
            println("3. Schutzzauber (Schütz alle Helden für die nächste Runde)")
        }
        println()

        println("Welche Attacke möchtest du auswählen:")
        try {
            var choice = readln().toInt()
            when (choice){
                1 -> hagelSchadenHero(gegnerListe)
                2 -> feuerBallHero(gegnerListe)
                3 -> {

                }
            }
        }catch (e : Exception){
            println("Ups, $e ")
            println("Bitte prüfe deine Eingabe.")
            attackeWählen(gegnerListe)
        }

    }
}