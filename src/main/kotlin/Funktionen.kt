import Gegner.ANSI_BROWN
import Gegner.Gegner
import Helden.Beutel
import Helden.Hero
const val ANSI_GREEN = "\u001B[32m"
const val ANSI_DARK_RED = "\u001B[31m"

// Berechnet einen zufälligen Schadenswert zwischen 50 und 150.
fun geringerSchaden(): Int {
    return (100..200).random()
}

// Berechnet einen zufälligen Schadenswert zwischen 150 und 350.
fun mittlererSchaden(): Int {
    return (200..350).random()
}

// Berechnet einen zufälligen Schadenswert zwischen 350 und 650.
fun kritischerSchaden(): Int {
    return (350..500).random()
}

// Berechnet einen zufälligen Schadenswert für Flächenangriffe zwischen 80 und 160.
fun geringerFlächenSchaden(): Int{
    return (100..200).random()
}

// Berechnet einen zufälligen Schadenswert für Flächenangriffe zwischen 161 und 300.
fun mittlererFlächenSchaden(): Int{
    return (200..300).random()
}

// Zeigt die aktuellen HP (Lebenspunkte) der Gegner an und entfernt eliminierte Gegner aus der Liste.
fun hpÜbersichtGegner(gegnerListe: MutableList<Gegner>) {
    var eliminierteGegner: MutableList<Gegner> = mutableListOf()
    for (gegner in gegnerListe) {
        if (gegner.hpGegner <= 0) {
            eliminierteGegner.add(gegner)
            println( " ${gegner.name} wurde von Dir erfolgreich besiegt!")
        } else {
            println("$ANSI_BROWN ${gegner.name} HP: ${gegner.hpGegner.toInt()} $`{ANSI_RESET}`")
        }
    }
    println()
    gegnerListe.removeAll(eliminierteGegner)
}

// Zeigt die aktuellen HP (Lebenspunkte) der Helden an und entfernt eliminierte Helden aus der Liste.
fun hpÜberischtHero(heldenListe: MutableList<Hero>) {
    var eliminierteHelden: MutableList<Hero> = mutableListOf()
    for (hero in heldenListe) {
        if (hero.hpHero <= 0) {
            eliminierteHelden.add(hero)
            println(" ${hero.name} wurde eliminiert!")
        } else {
            println("$ANSI_GREEN ${hero.name} HP: ${hero.hpHero.toInt()} $`{ANSI_RESET}`")
        }
    }
    println()
    heldenListe.removeAll(eliminierteHelden)
}

// Aktualisiert den Schutzstatus der Helden basierend auf dem Countdown.
fun isProtected (heldenListe: MutableList<Hero>){
    for (hero in heldenListe) {
        if (hero.isProtected) {
            hero.protectionCountdown--
        }
        if (hero.protectionCountdown == 0) {
            hero.isProtected = false
        }
    }
}

// Zeigt eine Gesamtübersicht der aktuellen HP (Lebenspunkte) von Gegnern und Helden.
fun hpÜbersicht (gegnerListe: MutableList<Gegner>,heldenListe: MutableList<Hero>){
    println("${ANSI_ORANGE}|------------------ HP Übersicht -----------------|$`{ANSI_RESET}`")
    println()
    println("${ANSI_ORANGE}Übersicht Lebenspunkte$`{ANSI_RESET}` ${ANSI_DARK_RED}Gegner $`{ANSI_RESET}`${ANSI_ORANGE}:$`{ANSI_RESET}`")
    hpÜbersichtGegner(gegnerListe)
    println("${ANSI_ORANGE}Übersicht Lebenspunkte$`{ANSI_RESET}` ${ANSI_NEON_GREEN}Helden $`{ANSI_RESET}`${ANSI_ORANGE}:$`{ANSI_RESET}`")
    hpÜberischtHero(heldenListe)
    println()
}

/*
fun heroAngreifLogik() {
    hpÜbersicht(gegnerListe, heldenListe)
    println("${ANSI_GREEN}|--------------- Angriff der Helden ---------------|$`{ANSI_RESET}`")
    println()
    Thread.sleep(1000)
    if (beutel.heiltränke > 0 || beutel.fluchTrank > 0) {
        println("Möchten Sie den Beutel öffnen oder kämpfen?")
    }else {
        println("Ihr Beutel ist leer.")
        println("Sie können nur noch Kämpfen.")
    }
    println("${ANSI_ORANGE}1. Kämpfen$`{ANSI_RESET}`")

    if (beutel.heiltränke > 0 || beutel.fluchTrank > 0) {
        println("${ANSI_ORANGE}2. Beutel öffnen$`{ANSI_RESET}`")
        println()
    }
    if (beutel.heiltränke > 0 || beutel.fluchTrank > 0) {
        print("${ANSI_BRIGHT_BLUE}Bitte wählen Sie: $`{ANSI_RESET}`")
    }
    //GGF noch mal abfangen prüfen
    if (beutel.heiltränke > 0 || beutel.fluchTrank > 0) {


        when (readln().toIntOrNull()) {
            1 -> {
                println()
                var ausgewählterHeld = heroWählen(heldenListe)
                println()
                println("Gewählter Held: ${ausgewählterHeld}")
                println()
                ausgewählterHeld.attackeWählen(gegnerListe, heldenListe)
                println()
                hpÜbersichtGegner(gegnerListe)
            }

            2 -> {
                println()
                println("${ANSI_ORANGE}Beutelinhalt:$`{ANSI_RESET}`")

                println("${ANSI_ORANGE} 1. Heiltrank: $`{ANSI_RESET}` ${beutel.heiltränke}")
                println("${ANSI_ORANGE} 2. Fluchtrank:$`{ANSI_RESET}` ${beutel.fluchTrank}")
                println()
                print("${ANSI_BRIGHT_BLUE}Bitte wählen Sie: $`{ANSI_RESET}`")

                var choice = readln().toIntOrNull()

                when (choice) {
                    1 -> {
                        if (beutel.heiltränke < 1) {
                            println("${ANSI_DARK_RED}Keine Heiltränke mehr verfügbar!$`{ANSI_RESET}`")
                        } else {
                            beutel.aufrufHeiltrank(heldenListe)
                            beutel.heiltränke--
                            println("$ANSI_NEON_GREEN|--------------- 50 % HP LevelUp---------------|$`{ANSI_RESET}`")
                            hpÜberischtHero(heldenListe)
                        }
                    }

                    2 -> {
                        beutel.aufrufFluchTrank(gegnerListe)
                        beutel.fluchEffektAnwenden(gegnerListe)
                        hpÜbersichtGegner(gegnerListe)

                    }
                    else -> {
                        println("$ANSI_DARK_RED Ungültige Auswahl!$`{ANSI_RESET}`")
                        when (choice) {
                            1 -> {
                                if (beutel.heiltränke < 1) {
                                    println("${ANSI_DARK_RED}Keine Heiltränke mehr verfügbar!$`{ANSI_RESET}`")
                                } else {
                                    beutel.aufrufHeiltrank(heldenListe)
                                    beutel.heiltränke--
                                    println("$ANSI_NEON_GREEN|--------------- 50 % HP LevelUp---------------|$`{ANSI_RESET}`")
                                    hpÜberischtHero(heldenListe)
                                }
                            }

                            2 -> {
                                beutel.aufrufFluchTrank(gegnerListe)
                                beutel.fluchEffektAnwenden(gegnerListe)
                                hpÜbersichtGegner(gegnerListe)

                            }
                            else -> {
                                println("$ANSI_DARK_RED Ungültige Auswahl!$`{ANSI_RESET}`")

                            }
                        }
                    }
                }
            }
        }

    } else {
        try {
            when (1) {
                1 -> {
                    println()
                    var ausgewählterHeld = heroWählen(heldenListe)
                    println()
                    println("Gewählter Held: ${ausgewählterHeld}")
                    println()
                    ausgewählterHeld.attackeWählen(gegnerListe, heldenListe)
                    println()
                    hpÜbersichtGegner(gegnerListe)
                }
            }
        } catch (e: Exception) {
            println("$ANSI_DARK_RED Ups, $e ")
            println("Bitte prüfe deine Eingabe. $`{ANSI_RESET}`")
        }
    }
}

 */
