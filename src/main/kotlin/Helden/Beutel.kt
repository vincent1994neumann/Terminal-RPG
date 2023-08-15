package Helden

import ANSI_GREEN
import ANSI_RESET
import Gegner.Gegner

class Beutel (
    var heiltränke : Int = 2,
    var fluchTrank: Int =1
)
{
    fun aufrufHeiltrank (heldenListe: MutableList<Hero>){
        if (heiltränke > 0) {
                for (hero in heldenListe){
                    hero.hpHero *= 1.5
                }
        }
    }

    fun aufrufFluchTrank (gegnerListe: List<Gegner>){
        if (fluchTrank > 0){
            for (gegner in gegnerListe){
                gegner.fluchRunden = 3
                gegner.fluchSchaden = 75
                }
            fluchTrank--
            println("$ANSI_GREEN Alle Gegner wurde für drei Runden verflucht und erleidet je Runde 75 HP Schaden.$ANSI_RESET")
            println()
            }else  {
                println("Kein Fluchtrank mehr verfügbar.")
        }
        }

    fun fluchEffektAnwenden(gegnerListe: MutableList<Gegner>) {
        for (gegner in gegnerListe) {
            if (gegner.fluchRunden > 0) {
                gegner.hpGegner -= gegner.fluchSchaden
                gegner.fluchRunden--
                if (gegner.fluchRunden == 0) {
                }
            }
        }
    }


}




//Klasse oder eher als Funktion????