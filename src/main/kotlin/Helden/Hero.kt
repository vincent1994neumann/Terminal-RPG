package Helden

import ANSI_BRIGHT_BLUE
import ANSI_DARK_RED
import ANSI_GREEN
import ANSI_ORANGE
import `{ANSI_RESET}`
import Gegner.Gegner
import geringerSchaden
import hpÜbersichtGegner
import kritischerSchaden
import mittlererSchaden

// Basisklasse für Helden
open class Hero (var name : String, var hpHero:Double, var isProtected : Boolean = false){

    // Klasseneigenschaften
    open var protectionCountdown = 0
    open var spezialAttackeVerfügbar = 1
    open var mittlereAttackeVerfügbar = 2

    // Gibt den Helden-Namen zurück
    override fun toString(): String {
        return name
    }

    // Kleiner Angriff des Helden
    open fun kleineAttacke (gegner: List<Gegner>){
        var kleinerSchaden = geringerSchaden()
        var ziel = gegnerWählen(gegner)
        println("Der $name greift mit Attacke -Schlagen- an.")
        if (ziel.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        }else {
            println("$name trifft $ziel und fügt $kleinerSchaden Schaden zu.")
            ziel.hpGegner -= kleinerSchaden
        }
        if (ziel.hpGegner <= 0) {
            println("Der Gegner ${ziel.name} wurde durch den Angriff eliminiert.$`{ANSI_RESET}`")
        }
        println()
    }

    // Mittlerer Angriff des Helden
    open fun mittlereAttacke (gegner: List<Gegner>) {
        if (mittlereAttackeVerfügbar <=0) {
            println("Der Verstärkteangriff von $name wurde bereits verwendet und kann nicht erneut eingesetzt werden!")
            return
        }
        var mittlererSchaden = mittlererSchaden()
        var ziel = gegnerWählen(gegner)
        println("Der $name greift mit Attacke -Stechen- an.")
        if (ziel.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        }else {
            println("$name trifft $ziel und verursacht $mittlererSchaden Schaden.")
            ziel.hpGegner-=mittlererSchaden
        }
        if (ziel.hpGegner <= 0) {
            println("${ANSI_GREEN}Der Gegner ${ziel.name} $ANSI_GREEN wurde durch den Angriff eliminiert.$`{ANSI_RESET}`")
        }
        mittlereAttackeVerfügbar--
        println()
    }

    // Spezialangriff des Helden
    open fun spezialAttacke (gegner: List<Gegner>){
        if (spezialAttackeVerfügbar <= 0) {
            println("Die Spezialattacke von $name wurde bereits verwendet und kann nicht erneut eingesetzt werden!")
            return}
        var kritischerSchaden = kritischerSchaden()
        var ziel = gegnerWählen(gegner)
        println("Der $name greift mit seiner -Spezialattacke- an. ")
        if (ziel.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie. ")
        } else {
            println("Der $name trifft den $ziel und verursacht $kritischerSchaden Schaden.")
            ziel.hpGegner -= kritischerSchaden
        }
        if (ziel.hpGegner <= 0) {
            println("${ANSI_GREEN}Der Gegner ${ziel.name} wurde durch den Angriff eliminiert.$`{ANSI_RESET}`")
        }
        spezialAttackeVerfügbar--
        println()
    }

    // Gegner für den Angriff auswählen
    fun gegnerWählen (gegnerListe: List<Gegner>) : Gegner {
        println()
        println("${ANSI_ORANGE}|--------------- HP Übersicht Gegner ---------------|$`{ANSI_RESET}`")
        println()
        hpÜbersichtGegner(gegnerListe.toMutableList())
        println()
        println("Welchen Gegner möchtest du angreifen?")
        var resultGegner = ""
        for (i in gegnerListe.indices) {
            resultGegner += "$ANSI_ORANGE ${i + 1}. ${gegnerListe[i]} $`{ANSI_RESET}`"
            if (i != gegnerListe.size - 1) {
                resultGegner += " ;  "
            }
        }
        println(resultGegner)
        println("Wählen Sie die entsprechende Nummer:")


        var choice = readln().toIntOrNull()
        if (choice != null && choice in 1..gegnerListe.size) {
            return gegnerListe[choice - 1]
            println(gegnerListe[choice])
        } else {
            println("${ANSI_DARK_RED}Ungültige Auswahl!$`{ANSI_RESET}`")
            return gegnerWählen(gegnerListe)
        }
    }

    // Angriffsaktion des Helden auswählen
    open fun attackeWählen(gegnerList: List<Gegner>, heldenListe: MutableList<Hero>) {
        // Zeigt die verfügbaren Aktionen
        println("Bitte wähle eine Aktion:")
        println("$ANSI_ORANGE 1.    Standartangriff $`{ANSI_RESET}`")
        println("$ANSI_ORANGE 2.    Verstärkter Angriff (Verfügbar: $mittlereAttackeVerfügbar) $`{ANSI_RESET}`")
        println("$ANSI_ORANGE 3.    Spezialattacke (Verfügbar: $spezialAttackeVerfügbar) $`{ANSI_RESET}`")

        print("Welche Aktion möchtest du auswählen:  ")

        // Benutzereingabe für Aktion
        var choice = readln().toIntOrNull()

        // Prüft, ob die Benutzereingabe gültig ist
        if (choice != null && choice in 1..3) {
            // Je nach Auswahl wird eine bestimmte Aktion ausgeführt
            when (choice) {
                1 -> {
                    // Führt kleiner Angriff aus
                    kleineAttacke(gegnerList)
                }

                2 -> {
                    // Führt mittlere Attacke aus, wenn sie verfügbar ist
                    if (mittlereAttackeVerfügbar > 0) {
                        mittlereAttacke(gegnerList)
                    } else {
                        println("${ANSI_ORANGE}Du hast keine Mittlere Attacke mehr verfügbar!$`{ANSI_RESET}`")
                        attackeWählen(gegnerList, heldenListe)
                    }
                }

                3 -> {
                    // Führt spezial Attacke aus, wenn sie verfügbar ist
                    if (spezialAttackeVerfügbar > 0) {
                        spezialAttacke(gegnerList)
                    } else {
                        println("${ANSI_ORANGE}Du hast keine Spezialangriffe mehr verfügbar!$`{ANSI_RESET}`")
                        Thread.sleep(1500)
                        attackeWählen(gegnerList, heldenListe)
                    }
                }

                // Behandlung von ungültigen Eingaben
                else -> {
                    println("$ANSI_DARK_RED Ungültige Auswahl!$`{ANSI_RESET}`")
                    attackeWählen(gegnerList, heldenListe)
                }
            }
        } else {
            // Wenn die Eingabe nicht im Bereich 1-3 liegt, wird der Benutzer erneut um Eingabe gebeten
            println("$ANSI_DARK_RED Ungültige Auswahl!$`{ANSI_RESET}`")
            attackeWählen(gegnerList, heldenListe)
        }
    }

}




