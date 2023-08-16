package Helden

// Import-Anweisungen für die Farbcodes und die Gegner-Klasse
import ANSI_DARK_RED
import ANSI_GREEN
import `{ANSI_RESET}`
import Gegner.Gegner

// Definition der Klasse Beutel mit zwei Eigenschaften: heiltränke und fluchTrank
class Beutel (
    var heiltränke : Int = 2,
    var fluchTrank: Int =1
)
{
    // Funktion, die aktuell keine Implementierung hat (leer)
    fun beutelwählen (){

    }

    // Funktion, die den HP-Wert von allen Helden in der übergebenen Liste um 50% erhöht, falls Heiltränke vorhanden sind
    fun aufrufHeiltrank (heldenListe: MutableList<Hero>){
        if (heiltränke > 0) {
            for (hero in heldenListe){
                hero.hpHero *= 1.5
            }
        }
    }

    // Funktion, die alle Gegner in der übergebenen Liste verflucht, wenn ein Fluchtrank vorhanden ist
    fun aufrufFluchTrank (gegnerListe: List<Gegner>){
        if (fluchTrank > 0){
            for (gegner in gegnerListe){
                gegner.fluchRunden = 3     // Setzt die Dauer des Fluches auf 3 Runden
                gegner.fluchSchaden = 75   // Setzt den Schaden des Fluches auf 75 HP
            }
            fluchTrank--                 // Verringert den Zähler für Fluchtränke um 1
            // Gibt eine Nachricht aus, dass alle Gegner verflucht wurden
            println("${ANSI_GREEN}Alle Gegner wurde für drei Runden verflucht und erleidet je Runde 75 HP Schaden.$`{ANSI_RESET}`")
            println()
        }else  {
            // Gibt eine Nachricht aus, wenn kein Fluchtrank mehr verfügbar ist
            println("${ANSI_DARK_RED}Kein Fluchtrank mehr verfügbar.$`{ANSI_RESET}`")
        }
    }

    // Funktion, die den Fluchschaden auf alle verfluchten Gegner in der übergebenen Liste anwendet
    fun fluchEffektAnwenden(gegnerListe: MutableList<Gegner>) {
        for (gegner in gegnerListe) {
            if (gegner.fluchRunden > 0) {
                gegner.hpGegner -= gegner.fluchSchaden   // Verringert die HP des Gegners um den Fluchschaden
                gegner.fluchRunden--                     // Verringert die verbleibenden Fluchrunden um 1
                if (gegner.fluchRunden == 0) {
                }
            }
        }
    }
}
