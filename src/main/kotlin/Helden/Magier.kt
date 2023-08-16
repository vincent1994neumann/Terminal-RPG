package Helden

import ANSI_BRIGHT_BLUE
import ANSI_DARK_RED
import ANSI_GREEN
import ANSI_NEON_GREEN
import ANSI_ORANGE
import `{ANSI_RESET}`
import Gegner.Gegner
import geringerFlächenSchaden
import mittlererFlächenSchaden

class Magier (name : String, hpHero: Double = 100.0) : Hero(name,hpHero) {

    open var feuerBallCounter = 2

    /**
     * Die Methode stellt den Angriff "Hagelschaden" des Helden (insbesondere Magier) dar.
     * Ein Hagelschaden fügt allen Gegnern in der Gegnerliste geringen Schaden zu.
     * Der Schaden wird durch die Funktion `geringerFlächenSchaden` bestimmt.
     *
     * @param gegnerListe Die Liste der Gegner, die vom Hagelschaden getroffen werden sollen.
     */
    fun hagelSchadenHero(gegnerListe: List<Gegner>) {
        // Berechnen des Schadens, den der Hagelschaden verursacht.
        val geringerFlächenSchaden = geringerFlächenSchaden()
        println("Der $ANSI_NEON_GREEN Magier $`{ANSI_RESET}` greift mit der Attacke -Hagelschaden- an.")

        Thread.sleep(500) // Kurze Pause vor dem Anzeigen der Schadensergebnisse.

        // Schaden durch den Hagelschaden an jeden Gegner in der Liste anwenden.
        for (gegner in gegnerListe) {
            if (gegner.trollProtection) { // Überprüfen, ob der Gegner gegen den Hagelschaden-Angriff geschützt ist.
                println("Der Troll absorbiert deinen Angriff mit Magie.")
            } else {
                println("$name trifft $gegner und fügt $geringerFlächenSchaden Schaden zu.")
                gegner.hpGegner -= geringerFlächenSchaden
                if (gegner.hpGegner <= 0) { // Überprüfen, ob der Gegner durch den Hagelschaden-Angriff eliminiert wurde.
                    println("Der Gegner ${gegner.name} wurde durch den Angriff eliminiert.")
                }
            }
        }
        println()
    }


    /**
     * Die Methode stellt den Angriff "Feuerball" des Helden (insbesondere Magier) dar.
     * Ein Feuerball fügt allen Gegnern in der Gegnerliste mittleren Schaden zu.
     * Der Feuerball hat jedoch eine begrenzte Anzahl von Nutzungen,
     * repräsentiert durch die Variable `feuerBallCounter`.
     *
     * @param gegnerListe Die Liste der Gegner, die vom Feuerball getroffen werden sollen.
     */
    fun feuerBallHero(gegnerListe: List<Gegner>) {
        // Überprüfen, ob noch Feuerbälle übrig sind.
        if (feuerBallCounter <= 0){
            println("${ANSI_ORANGE}Die Spezialattacke von $name wurde bereits verwendet und kann nicht erneut eingesetzt werden!$`{ANSI_RESET}`")
            return
        }

        val mittlererFlächenSchaden = mittlererFlächenSchaden()
        println("Der $ANSI_NEON_GREEN Magier $`{ANSI_RESET}` greift mit der Attacke Feuerball an.")

        // Schaden durch den Feuerball an jeden Gegner in der Liste anwenden.
        for (gegner in gegnerListe) {
            if (gegner.trollProtection) { // Überprüfen, ob der Gegner gegen den Feuerball-Angriff geschützt ist.
                println("Der Troll absorbiert deinen Angriff mit Magie.")
            } else {
                println("$name trifft $gegner und verursacht $mittlererFlächenSchaden Schaden.")
                gegner.hpGegner -= mittlererFlächenSchaden
                if (gegner.hpGegner <= 0) { // Überprüfen, ob der Gegner durch den Feuerball-Angriff eliminiert wurde.
                    println("Der Gegner ${gegner.name} wurde durch den Angriff eliminiert.")
                }
            }
        }

        println()
        feuerBallCounter-- // Verringern des Feuerball-Counters, da ein Feuerball verwendet wurde.
    }

    /**
     * Aktiviert den Schutzzauber für alle Helden in der übergebenen Liste.
     * Jeder Held in der Liste wird für zwei Runden geschützt.
     *
     * @param heldenListe Die Liste der Helden, für die der Schutzzauber aktiviert werden soll.
     */
    fun schutzZauberHero(heldenListe: MutableList<Hero>) {

        // Durchlaufen der Liste der Helden und Aktivieren des Schutzzaubers für jeden Helden
        for (hero in heldenListe) {
            hero.isProtected = true              // Schutzstatus des Helden setzen
            hero.protectionCountdown = 2         // Countdown des Schutzes auf zwei Runden setzen
            println("${hero.name}'s Schutzzauber wurde aktiviert.")   // Benachrichtigung über den aktivierten Schutzzauber
        }

        // Ausgabe des Schutzzauber-Countdowns für den Anwender
        println()
        println("${ANSI_ORANGE}Runden-Countdown Schutzzauber: $protectionCountdown $`{ANSI_RESET}`")
        println()
    }


    override fun attackeWählen(gegnerListe: List<Gegner>, heldenListe: MutableList<Hero>) {
        // Anzeige der verfügbaren Aktionen
        println("Bitte wähle eine Aktion:")
        println("$ANSI_GREEN -1-   ${ANSI_ORANGE}Hagelschaden (Flächenschaden) $`{ANSI_RESET}`")
        println("$ANSI_GREEN -2-   ${ANSI_ORANGE}Feuerball (Flächenschaden) (Verfügbar: $feuerBallCounter) $`{ANSI_RESET}`")

        // Prüfen, ob der Schutzzauber aktiv ist
        if (!isProtected) {
            println("$ANSI_GREEN -3-   ${ANSI_ORANGE}Schutzzauber (Schutz für 2 Runden/alle Helden )$`{ANSI_RESET}`")
        } else {
            println("${ANSI_ORANGE} -3-$`{ANSI_RESET}`${ANSI_NEON_GREEN}   Der Schutzzauber ist bereits aktiviert!$`{ANSI_RESET}`")
        }

        // Eingabeaufforderung für die Auswahl der Attacke
        println("Welche Attacke möchtest du auswählen:")
        var choice = readln().toIntOrNull()

        // Überprüfen, ob eine gültige Wahl getroffen wurde
        if (choice != null && choice in 1..3) {
            when (choice) {
                1 -> {
                    // Hagelschaden-Attacke ausführen
                    hagelSchadenHero(gegnerListe)
                }
                2 -> {
                    // Überprüfen, ob Feuerbälle verfügbar sind
                    if (feuerBallCounter > 0) {
                        feuerBallHero(gegnerListe)
                    } else {
                        println("${ANSI_ORANGE}Du hast keine Feuerball mehr verfügbar!$`{ANSI_RESET}`")
                        // Bei fehlenden Feuerbällen, erneut Attacke auswählen
                        attackeWählen(gegnerListe, heldenListe)
                    }
                }
                3 -> {
                    // Prüfen, ob der Schutzzauber bereits aktiv ist
                    if (isProtected) {
                        println("${ANSI_ORANGE}Der Schutzzauber ist bereits aktiviert!$`{ANSI_RESET}`")
                    } else {
                        // Schutzzauber aktivieren
                        schutzZauberHero(heldenListe)
                    }
                }
                else -> {
                    // Bei ungültiger Eingabe, Fehlermeldung ausgeben
                    println("${ANSI_DARK_RED}Ihre Eingabe war falsch. $`{ANSI_RESET}`")
                }
            }
        }
    }
}