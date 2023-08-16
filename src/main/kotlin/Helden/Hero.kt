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
    open fun kleineAttacke(gegner: List<Gegner>) {

        // Berechnet den leichten Schaden für den Angriff.
        var kleinerSchaden = geringerSchaden()

        // Lässt den Spieler einen Gegner für den Angriff auswählen.
        var ziel = gegnerWählen(gegner)

        // Nachricht, die den Beginn des Angriffs anzeigt.
        println("Der $name greift mit Attacke -Schlagen- an.")

        // Überprüft, ob der gewählte Gegner eine spezielle Troll-Schutzfähigkeit hat.
        if (ziel.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        } else {
            // Wenn der Gegner keinen Troll-Schutz hat, wird der Schaden angewendet und eine Nachricht angezeigt.
            println("$name trifft $ziel und fügt $kleinerSchaden Schaden zu.")
            ziel.hpGegner -= kleinerSchaden
        }

        // Überprüft, ob der HP-Wert des Gegners 0 oder weniger beträgt und gibt gegebenenfalls eine Nachricht aus.
        if (ziel.hpGegner <= 0) {
            println("Der Gegner ${ziel.name} wurde durch den Angriff eliminiert.$`{ANSI_RESET}`")
        }

        // Leerzeile für bessere Lesbarkeit im Terminal.
        println()
    }


    // Mittlerer Angriff des Helden
        open fun mittlereAttacke(gegner: List<Gegner>) {

        // Überprüft, ob der verstärkte Angriff noch verfügbar ist.
        if (mittlereAttackeVerfügbar <= 0) {
            println("Der Verstärkteangriff von $name wurde bereits verwendet und kann nicht erneut eingesetzt werden!")
            return
        }

        // Berechnet den mittleren Schaden für den Angriff.
        var mittlererSchaden = mittlererSchaden()

        // Lässt den Spieler einen Gegner für den Angriff auswählen.
        var ziel = gegnerWählen(gegner)

        // Nachricht, die den Beginn des Angriffs anzeigt.
        println("Der $name greift mit Attacke -Stechen- an.")

        // Überprüft, ob der gewählte Gegner eine spezielle Troll-Schutzfähigkeit hat.
        if (ziel.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        } else {
            // Wenn der Gegner keinen Troll-Schutz hat, wird der Schaden angewendet und eine Nachricht angezeigt.
            println("$name trifft $ziel und verursacht $mittlererSchaden Schaden.")
            ziel.hpGegner -= mittlererSchaden
        }

        // Überprüft, ob der HP-Wert des Gegners 0 oder weniger beträgt und gibt gegebenenfalls eine Nachricht aus.
        if (ziel.hpGegner <= 0) {
            println("${ANSI_GREEN}Der Gegner ${ziel.name} $ANSI_GREEN wurde durch den Angriff eliminiert.$`{ANSI_RESET}`")
        }

        // Reduziert die Anzahl der verfügbaren mittleren Angriffe um 1.
        mittlereAttackeVerfügbar--

        // Leerzeile für bessere Lesbarkeit im Terminal.
        println()
    }


    // Spezialangriff des Helden
    open fun spezialAttacke(gegner: List<Gegner>) {

        // Überprüft, ob die Spezialattacke noch verfügbar ist.
        if (spezialAttackeVerfügbar <= 0) {
            println("Die Spezialattacke von $name wurde bereits verwendet und kann nicht erneut eingesetzt werden!")
            return
        }

        // Berechnet den kritischen Schaden der Spezialattacke.
        var kritischerSchaden = kritischerSchaden()

        // Lässt den Spieler einen Gegner für den Angriff auswählen.
        var ziel = gegnerWählen(gegner)

        // Nachricht, die den Beginn des Angriffs anzeigt.
        println("Der $name greift mit seiner -Spezialattacke- an. ")

        // Überprüft, ob der gewählte Gegner eine spezielle Troll-Schutzfähigkeit hat.
        if (ziel.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie. ")
        } else {
            // Wenn der Gegner keinen Troll-Schutz hat, wird der Schaden angewendet und eine Nachricht angezeigt.
            println("Der $name trifft den $ziel und verursacht $kritischerSchaden Schaden.")
            ziel.hpGegner -= kritischerSchaden
        }

        // Überprüft, ob der HP-Wert des Gegners 0 oder weniger beträgt und gibt gegebenenfalls eine Nachricht aus.
        if (ziel.hpGegner <= 0) {
            println("${ANSI_GREEN}Der Gegner ${ziel.name} wurde durch den Angriff eliminiert.$`{ANSI_RESET}`")
        }

        // Reduziert die Anzahl der verfügbaren Spezialangriffe um 1.
        spezialAttackeVerfügbar--

        // Leerzeile für bessere Lesbarkeit im Terminal.
        println()
    }


    // Gegner für den Angriff auswählen
    fun gegnerWählen(gegnerListe: List<Gegner>): Gegner {
        // Leerzeile für bessere Sichtbarkeit im Terminal
        println()

        // Header, der die HP-Übersicht der Gegner anzeigt
        println("${ANSI_ORANGE}|--------------- HP Übersicht Gegner ---------------|$`{ANSI_RESET}`")
        println()

        // Anzeige der aktuellen Gesundheitspunkte aller Gegner
        hpÜbersichtGegner(gegnerListe.toMutableList())

        println()
        println("Welchen Gegner möchtest du angreifen?")

        // Aufbau der Auswahlmöglichkeiten für die Gegner
        var resultGegner = ""
        for (i in gegnerListe.indices) {
            resultGegner += "$ANSI_ORANGE ${i + 1}. ${gegnerListe[i]} $`{ANSI_RESET}`"
            if (i != gegnerListe.size - 1) {
                resultGegner += " ;  "
            }
        }

        // Anzeige der Auswahlmöglichkeiten
        println(resultGegner)
        println("Wählen Sie die entsprechende Nummer:")

        // Benutzereingabe
        var choice = readln().toIntOrNull()

        // Überprüfung der Benutzereingabe
        if (choice != null && choice in 1..gegnerListe.size) {
            // Gibt den ausgewählten Gegner zurück
            return gegnerListe[choice - 1]
            // Anmerkung: Der nachfolgende Code wird nie erreicht, da ein Wert mit "return" zurückgegeben wird.
            println(gegnerListe[choice])
        } else {
            // Bei ungültiger Eingabe wird eine Fehlermeldung ausgegeben und der Benutzer erneut aufgefordert, einen Gegner auszuwählen
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




