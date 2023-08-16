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

    fun hagelSchadenHero(gegnerListe: List<Gegner>) { //Muatbelist damit ich ggf später die Liste verändern kann, z.B. wenn ein Gegener eliminiert wurde
        val geringerFlächenSchaden = geringerFlächenSchaden()
        println("Der $ANSI_NEON_GREEN Magier $`{ANSI_RESET}` greift mit der Attacke -Hagelschaden- an.")
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
        if (feuerBallCounter <= 0){
            println("${ANSI_ORANGE}Die Spezialattacke von $name wurde bereits verwendet und kann nicht erneut eingesetzt werden!$`{ANSI_RESET}`")
        return}
        val mittlererFlächenSchaden = mittlererFlächenSchaden()
        println("Der $ANSI_NEON_GREEN Magier $`{ANSI_RESET}` greift mit der Attacke Feuerball an.")
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
        feuerBallCounter--
    }
    fun schutzZauberHero(heldenListe: MutableList<Hero>) {
        for (hero in heldenListe) {
            hero.isProtected = true
            hero.protectionCountdown = 2 // Für zwei Runden geschützt, nicht vier.
            println("${hero.name}'s Schutzzauber wurde aktiviert.")
        }
        println()
        println("${ANSI_ORANGE}Runden-Countdown Schutzzauber: $protectionCountdown $`{ANSI_RESET}`")
        println()
    }

    override fun attackeWählen(gegnerListe: List<Gegner>, heldenListe: MutableList<Hero>) {
        println("Bitte wähle eine Aktion:")
        println("$ANSI_GREEN -1-   ${ANSI_ORANGE}Hagelschaden (Flächenschaden) $`{ANSI_RESET}`")
        println("$ANSI_GREEN -2-   ${ANSI_ORANGE}Feuerball (Flächenschaden) (Verfügbar: $feuerBallCounter) $`{ANSI_RESET}`")
        if (!isProtected) {
            println("$ANSI_GREEN -3-   ${ANSI_ORANGE}Schutzzauber (Schutz für 2 Runden/alle Helden )$`{ANSI_RESET}`")
        }else {
            println("${ANSI_ORANGE} -3-$`{ANSI_RESET}`${ANSI_NEON_GREEN}   Der Schutzzauber ist bereits aktiviert!$`{ANSI_RESET}`")
        }

        println("Welche Attacke möchtest du auswählen:")
        var choice = readln().toIntOrNull()

        if (choice != null && choice in 1..3) {
            when (choice){
                1 -> {
                    hagelSchadenHero(gegnerListe)

                }

                2 -> {
                    if (feuerBallCounter > 0) {
                            feuerBallHero(gegnerListe)
                        } else {
                            println("${ANSI_ORANGE}Du hast keine Feuerball mehr verfügbar!$`{ANSI_RESET}`")
                            attackeWählen(gegnerListe, heldenListe)
                        }
                    }
                3 -> {
                    if (isProtected){
                        println("${ANSI_ORANGE}Der Schutzzauber ist bereits aktiviert!$`{ANSI_RESET}`")
                    }else{
                        schutzZauberHero(heldenListe)
                    }
                }
                else -> {
                    println("${ANSI_DARK_RED}Ihre Eingabe war falsch. $`{ANSI_RESET}`")
                }
            }
        }
    }
}