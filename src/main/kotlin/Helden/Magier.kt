package Helden

import Gegner.Gegner
import geringerFlächenSchaden
import mittlererFlächenSchaden

class Magier (name : String, hpHero: Int = 50) : Hero(name,hpHero) {

    fun hagelSchadenHero(gegnerListe: MutableList<Gegner>) { //Muatbelist damit ich ggf später die Liste verändern kann, z.B. wenn ein Gegener eliminiert wurde
        val geringerFlächenSchaden = geringerFlächenSchaden()
        println("Der Magier greift an mit der Attacke: Hagelschaden")
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
    }

    fun feuerBallHero(gegnerListe: MutableList<Gegner>) { //Muatbelist damit ich ggf später die Liste verändern kann, z.B. wenn ein Gegener eliminiert wurde
        val mittlererFlächenSchaden = mittlererFlächenSchaden()
        println("Der Magier greift an mit der Attacke: Feuerball")
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
    }
        fun schutzZauberHero(rundeAnzahl:Int) {
            if (rundeAnzahl >= 4 && !isProtected && rundeAnzahl % 4 == 0) {
                isProtected = true
                println("Der Schutzzauber vom Magier wirkt für die nächste Spielrunde.")
            }else {
                println("Der Schutzzauber kann aktuell noch nicht angewendet werden, erst in XXX Runden")
            }

            }


            //Muss den Boolean in der Hero Klasse auf true setzen für eine Runde.


}