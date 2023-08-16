import Gegner.*
import Helden.*
import kotlin.concurrent.thread

const val ANSI_BOLD = "\u001B[1m"
const val `{ANSI_RESET}` = "\u001B[0m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_DARK_BLUE = "\u001B[34m"
const val ANSI_YELLOW = "\u001B[33m"
val ANSI_ORANGE = "\u001B[38;2;255;165;0m"
val ANSI_NEON_GREEN = "\u001B[92m"
val ANSI_BRIGHT_BLUE = "\u001B[94m"
val ANSI_BLACK = "\u001B[30m"




fun main() {

    // Gemeinsamer Beutel - globale Instanz, sodass ein Beutel für alle Helden gilt
    var beutel = Beutel(2, 1)
    // Helden
    var held1 = Ritter( "${ANSI_NEON_GREEN}Ritter$`{ANSI_RESET}`")
    var held2 = Bogenschütze("${ANSI_NEON_GREEN}Bogenschütze$`{ANSI_RESET}`")
    var held3 = Magier("${ANSI_NEON_GREEN}Magier$`{ANSI_RESET}`")
    var heldenListe: MutableList<Hero> = mutableListOf(held1, held2, held3)


    // Gegner
    var gegner1 = Troll("${ANSI_DARK_RED}Troll$`{ANSI_RESET}`")
    var gegner2 = DunklerRitter("${ANSI_DARK_RED}Dunkler Ritter$`{ANSI_RESET}`")
    var gegner3 = Goblin("${ANSI_DARK_RED}Goblin$`{ANSI_RESET}`")
    var gegnerListe: MutableList<Gegner> = mutableListOf(gegner1, gegner2, gegner3)


    fun gegnerAngreifLogik(hero: Hero) {
        // Zeigt die aktuellen HP-Werte von Gegnern und Helden
        hpÜbersicht(gegnerListe,heldenListe)

        // Gibt eine Meldung aus, dass der Gegner am Zug ist
        println("${ANSI_BROWN}|--------------- Der Gegner ist dran --------------|$`{ANSI_RESET}`")
        println()

        // Überprüft, ob der Held durch einen Schutzzauber geschützt ist
        if (hero.isProtected) {
            // Gibt eine Meldung aus, dass der Held durch einen Schutzzauber geschützt ist
            println("${ANSI_ORANGE}Durch den Schutzzauber würde jeder Angriff ins Leere gehen.")
            println("Runden-Countdown Schutzzauber: ${hero.protectionCountdown} $`{ANSI_RESET}`")
            println()
        } else {
            // Gibt eine Meldung aus, dass der Gegner angreift
            println("${ANSI_BROWN}Der Gegner holt zur Attacke aus!$`{ANSI_RESET}`")

            // Wählt zufällig einen Gegner aus der Liste aus, der angreifen wird
            var angreifenderGegner = gegnerListe.random()

            // Entscheidet basierend auf dem Typ des angreifenden Gegners, welche Angriffsmethode verwendet wird
            when (angreifenderGegner) {
                is Troll ->
                    // Der Troll führt seine spezifische Angriffsfunktion aus
                    gegner1.auswahlAttackeTroll(heldenListe)
                is DunklerRitter ->
                    // Der Dunkle Ritter greift einen zufällig ausgewählten Helden an
                    gegner2.auswahlAttackeDunklerRitter(heldenListe.random())
                is Goblin ->
                    // Der Goblin greift einen zufällig ausgewählten Helden an
                    gegner3.auswahlAttackeGoblin(heldenListe.random())
            }
            println()
        }
    }


    fun heroWählen(heldenList: MutableList<Hero>): Hero {
        // Gibt eine Aufforderung aus, welcher Held für den Angriff gewählt werden soll
        println("Mit welchem Helden wollen Sie angreifen?")

        var result = "" // Variable, um die Helden und ihre Nummern zu speichern

        // Durchläuft die Liste der Helden und erstellt eine Zeichenfolge mit den Namen und Nummern der Helden
        for (i in heldenList.indices) {
            result += "${ANSI_ORANGE} ${i + 1} - ${heldenList[i]} $`{ANSI_RESET}`"
            // Fügt ein Semikolon hinzu, wenn es nicht der letzte Held in der Liste ist
            if (i != heldenList.size - 1) {
                result += "; "
            }
        }

        // Gibt die Liste der Helden mit ihren Nummern aus
        println(result)
        println("Wählen Sie die entsprechende Nummer:")

        // Liest die Benutzereingabe; falls die Eingabe kein Integer ist, wird null zurückgegeben
        var choice = readln().toIntOrNull()

        // Überprüft, ob die gewählte Nummer gültig und in der Liste der Helden vorhanden ist
        if (choice != null && choice in 1..heldenList.size) {
            return heldenList[choice - 1] // Gibt den entsprechenden Helden basierend auf der Auswahl zurück

        } else {
            // Gibt eine Nachricht aus, wenn die Auswahl ungültig ist, und ruft die Funktion erneut auf
            println("${ANSI_DARK_RED}Ungültige Auswahl!$`{ANSI_RESET}`")
            return heroWählen(heldenList) // Rekursiver Aufruf, um den Benutzer erneut auswählen zu lassen
        }
    }


    fun heroAngreifLogik() {
        hpÜbersicht(gegnerListe, heldenListe) // Zeigt den aktuellen Gesundheitszustand von Gegnern und Helden

        Thread.sleep(1000) // Lässt das Programm für 1 Sekunde pausieren

        println("${ANSI_GREEN}|--------------- Angriff der Helden ---------------|$`{ANSI_RESET}`")
        println()

// Beginnt eine Schleife, um den Benutzer zur Eingabe aufzufordern
        while (true) {

            // Überprüft, ob noch Tränke im Beutel vorhanden sind
            if (beutel.heiltränke > 0 || beutel.fluchTrank > 0) {
                println("Möchten Sie den Beutel öffnen oder kämpfen?")
            } else {
                println("Ihr Beutel ist leer.")
                println("Sie können nur noch Kämpfen.")
            }
            println("${ANSI_ORANGE}-1- Kämpfen$`{ANSI_RESET}`") // Option zum Kämpfen

            // Zeigt die Beutel-Option nur an, wenn noch Tränke im Beutel sind
            if (beutel.heiltränke > 0 || beutel.fluchTrank > 0) {
                println("${ANSI_ORANGE}-2- Beutel öffnen$`{ANSI_RESET}`")
            }

            println("Bitte wählen:")
            var choice = readln().toIntOrNull() // Liest die Benutzerauswahl

            // Handhabt die Benutzerauswahl
            when (choice) {
                1 -> { // Option zum Kämpfen
                    val ausgewählterHeld = heroWählen(heldenListe) // Lässt den Benutzer einen Helden wählen
                    println()
                    println("Gewählter Held: $ausgewählterHeld")
                    println()
                    ausgewählterHeld.attackeWählen(
                        gegnerListe,
                        heldenListe
                    ) // Lässt den Benutzer eine Attacke für den gewählten Helden wählen
                    hpÜbersichtGegner(gegnerListe) // Zeigt die Gesundheit der Gegner nach dem Angriff
                    return
                }

                2 -> { // Option zum Öffnen des Beutels
                    if (beutel.heiltränke > 0 || beutel.fluchTrank > 0) {
                        println("${ANSI_NEON_GREEN}Beutelinhalt:$`{ANSI_RESET}`")
                        println("${ANSI_ORANGE} -1- Heiltrank: $`{ANSI_RESET}` ${beutel.heiltränke}")
                        println("${ANSI_ORANGE} -2- Fluchtrank:$`{ANSI_RESET}` ${beutel.fluchTrank}")
                        var auswahl = readln().toIntOrNull() // Liest die Trank-Auswahl
                        when (auswahl) {
                            1 -> { // Heiltrank
                                if (beutel.heiltränke < 1) {
                                    println("${ANSI_DARK_RED}Keine Heiltränke mehr verfügbar!$`{ANSI_RESET}`")
                                } else {
                                    beutel.aufrufHeiltrank(heldenListe) // Verwendet den Heiltrank
                                    beutel.heiltränke--
                                    println("$ANSI_NEON_GREEN|--------------- 50 % HP LevelUp---------------|$`{ANSI_RESET}`")
                                    hpÜberischtHero(heldenListe) // Zeigt die Gesundheit der Helden nach Verwendung des Heiltranks
                                    return
                                }
                            }

                            2 -> { // Fluchtrank
                                beutel.aufrufFluchTrank(gegnerListe) // Verflucht die Gegner
                                beutel.fluchEffektAnwenden(gegnerListe) // Wendet den Flucheffekt auf die Gegner an
                                hpÜbersichtGegner(gegnerListe) // Zeigt die Gesundheit der Gegner nach dem Fluch
                                return
                            }

                            else -> {
                                println("${ANSI_DARK_RED}Ungültige Auswahl!$`{ANSI_RESET}`") // Fehlermeldung für ungültige Trank-Auswahl
                            }
                        }
                    } else {
                        println("${ANSI_DARK_RED}Dein Beutel ist leer!$`{ANSI_RESET}`") // Fehlermeldung, wenn der Beutel leer ist
                    }
                }

                else -> {
                    println("${ANSI_DARK_RED}Ungültige Auswahl!$`{ANSI_RESET}`") // Fehlermeldung für ungültige Hauptauswahl
                }
            }
        }
    }

    fun spielrundenUserStart() {
                var counter = 1

                while (true) {
                    println()
                    println("${ANSI_RED}${ANSI_BOLD}----------------------- RUNDE $counter ------------------------$`{ANSI_RESET}`")
                    println()
                    beutel.fluchEffektAnwenden(gegnerListe)
                    isProtected(heldenListe)
                    Thread.sleep(1000)
                    heroAngreifLogik()
                    Thread.sleep(1000)
                    // Wenn es noch Gegner gibt, lass sie angreifen
                    if (gegnerListe.isNotEmpty()) {
                        gegnerAngreifLogik(held1)
                        Thread.sleep(1000)
                        hpÜberischtHero(heldenListe)
                        Thread.sleep(1000)
                        if (counter == 3) {
                            var gegner4 = Goblin("${ANSI_DARK_RED}Goblin's Bruder$`{ANSI_RESET}`", 600.0)
                            gegnerListe.add(gegner4)
                            println("Ein wilder ${gegner4.name} wurde beschworen.")
                            println()
                            Thread.sleep(1000)
                        }

                        if (counter == 6) {
                            var gegner5 = Goblin("${ANSI_DARK_RED}Goblin's Schwester$`{ANSI_RESET}`", 500.0)
                            gegnerListe.add(gegner5)
                            println( println("Ein wilder ${gegner5.name} wurde beschworen."))
                            println()
                            Thread.sleep(1000)
                        }


                        if (heldenListe.isEmpty()) {
                            println("${ANSI_DARK_RED}Alle Helden wurden nach dem Angriff der Gegner besiegt!$`{ANSI_RESET}`")
                            Thread.sleep(2000)
                            println("${ANSI_DARK_RED}Sie haben VERLOREN!!!$`{ANSI_RESET}`")
                            println()
                            break
                        }
                        println("${ANSI_DARK_BLUE}Runde $`{ANSI_RESET}` $ANSI_DARK_RED $counter $`{ANSI_RESET}` ${ANSI_DARK_BLUE} ist vorbei. Es gibt noch keinen Gewinner.$`{ANSI_RESET}`")
                        counter++
                        Thread.sleep(1000)
                        println("${ANSI_DARK_BLUE}Mach dich bereit für Runde $`{ANSI_RESET}` $ANSI_DARK_RED $counter $`{ANSI_RESET}`!")
                        println()
                    } else {
                        println("${ANSI_NEON_GREEN}Es wurden alle Gegner erfolgreich eliminiert!$`{ANSI_RESET}`")
                        Thread.sleep(2000)
                        println("${ANSI_NEON_GREEN}Sie haben GEWONNEN!!!$`{ANSI_RESET}`")
                        break
                    }
                }
        println()
                println("${ANSI_ORANGE}Das Spiel ist vorbei! Vielen Dank fürs spielen.$`{ANSI_RESET}`")
                println("${ANSI_NEON_GREEN}gespielte Runden: $counter $`{ANSI_RESET}`")

            }

    fun spielrundeGegnerStart() {
                var counter = 1

                while (true) {
                    println()
                    println("${ANSI_RED}${ANSI_BOLD}----------------------- RUNDE $counter ------------------------$`{ANSI_RESET}`")
                    println()
                    beutel.fluchEffektAnwenden(gegnerListe)
                    isProtected(heldenListe)
                    Thread.sleep(1000)
                    gegnerAngreifLogik(hero = heldenListe[0])
                    Thread.sleep(1000)
                    // Der Gegner greift zuerst an
                    if (heldenListe.isNotEmpty()) {
                        heroAngreifLogik()
                        Thread.sleep(1000)
                        hpÜbersichtGegner(gegnerListe)
                        Thread.sleep(1000)
                        if (counter == 3) {
                            val gegner4 = Goblin("${ANSI_DARK_RED}Goblin's Bruder$`{ANSI_RESET}`", 600.0)
                            gegnerListe.add(gegner4)
                            println("Ein wilder ${gegner4.name} wurde beschworen.")
                            println()
                            Thread.sleep(1000)
                        }

                        if (counter == 6) {
                            val gegner5 = Goblin("${ANSI_DARK_RED}Goblin's Schwester$`{ANSI_RESET}`", 500.0)
                            gegnerListe.add(gegner5)
                            println("Ein wilder ${gegner5.name} wurde beschworen.")
                            println()
                            Thread.sleep(1000)
                        }


                        if (gegnerListe.isEmpty()) {
                            println("${ANSI_NEON_GREEN}Es wurden alle Gegner erfolgreich eliminiert!$`{ANSI_RESET}`")
                            Thread.sleep(2000)
                            println("${ANSI_NEON_GREEN}Sie haben GEWONNEN!!!$`{ANSI_RESET}`")
                            println()
                            break
                        }
                        println("${ANSI_DARK_BLUE}Runde $`{ANSI_RESET}` $ANSI_DARK_RED $counter $`{ANSI_RESET}` ${ANSI_DARK_BLUE} ist vorbei. Es gibt noch keinen Gewinner.$`{ANSI_RESET}`")
                        counter++
                        Thread.sleep(1000)
                        println("${ANSI_DARK_BLUE}Mach dich bereit für Runde $`{ANSI_RESET}` $ANSI_DARK_RED $counter $`{ANSI_RESET}`!")
                        println()
                    } else {
                        println("${ANSI_DARK_RED}Alle Helden wurden nach dem Angriff der Gegner besiegt!$`{ANSI_RESET}`")
                        Thread.sleep(2000)
                        println("${ANSI_DARK_RED}Sie haben VERLOREN!!!$`{ANSI_RESET}`")
                        break
                    }
                }
        println()
                println("${ANSI_ORANGE}Das Spiel ist vorbei! Vielen Dank fürs spielen.$`{ANSI_RESET}`")
                println("${ANSI_NEON_GREEN}gespielte Runden: $counter $`{ANSI_RESET}`")

            }


    fun startscreen() {
                println()
                println()
                println(
                    """
       T${ANSI_BRIGHT_BLUE}~$`{ANSI_RESET}`${ANSI_YELLOW}~$`{ANSI_RESET}`                      
       |                    ${ANSI_NEON_GREEN}Willkommen bei$`{ANSI_RESET}`
       |                ${ANSI_NEON_GREEN}ClashRoyalFürAnfänger$`{ANSI_RESET}`
      /"\\                 
  T~T~|'|T~T~             ${ANSI_NEON_GREEN}Das Spiel startet... $`{ANSI_RESET}` 
  |  | | | |  T${ANSI_BRIGHT_BLUE}~$`{ANSI_RESET}`${ANSI_YELLOW}~$`{ANSI_RESET}`               
  |  | | | |  |     /"\\  /"\\    /"\\  /"\\     
  ~T~-~T~ | |  |   +----T----+   +----T----+
  |  |   ~T~  |    |         |   |         |  
  |  |       /"\\  |         |   |         |  
  |  |     T~T~T   +----T----+   +----T----+    
  |  |     | | |   |         |   |         |  
~~T~T~~T~T~~T~T~~  +---------+   +---------+     
    """
                )
                Thread.sleep(5000)
                println("${ANSI_ORANGE}In \"ClashRoyalFürAnfänger\" kämpfen deine Helden in Runden gegen ansteigende Gegnerhorden.")
                println("Nutze spezielle Fähigkeiten, um schnell zu siegen.")
                println("Ziel ist es, die Gegner in möglichst wenigen Runden zu besiegen.")
                println("Das Spiel endet, wenn eine Seite komplett besiegt ist.")
                Thread.sleep(8500)
                println()
                println("Kannst du bis zum Ende bestehen?$`{ANSI_RESET}`")
                Thread.sleep(3000)

                print(
                    """
                                                           
${ANSI_GREEN}|__________________ ${ANSI_NEON_GREEN}LET'S GO$`{ANSI_RESET}`${ANSI_GREEN} __________________|$`{ANSI_RESET}`         
                        
      """
                )
                Thread.sleep(1000)


            }

    fun werStartet() {
                println()
                println("${ANSI_YELLOW}|--------------- Kopf Oder Zahl ---------------|")

                println()
                println("Wer darf als erstes Angreifen?")
                println("Gewinne das Kopf oder Zahl Spiel, um die erste Attacke auszuführen.$`{ANSI_RESET}`")


                print("${ANSI_BRIGHT_BLUE}Wähle Kopf ${ANSI_ORANGE}(k)$`{ANSI_RESET}`${ANSI_BRIGHT_BLUE} oder Zahl $`{ANSI_RESET}`${ANSI_ORANGE}(z)$`{ANSI_RESET}`:$`{ANSI_RESET}`")
                var userEingabe: String
                try {
                    userEingabe = readln()
                    if (userEingabe.lowercase() != "k" && userEingabe.lowercase() != "z") {
                        throw IllegalArgumentException("Ungültige Eingabe")
                    }

                    var kopfOderZahl = listOf("k", "z")
                    val ergebnis = kopfOderZahl.random()
                    println("${ANSI_YELLOW}Die Münze landet und zeigt $ergebnis $`{ANSI_RESET}`${ANSI_ORANGE}(z = Zahl | k = Kopf)$`{ANSI_RESET}`")
                    println()
                    if (userEingabe == ergebnis) {
                        println("${ANSI_ORANGE}Glückwunsch! Erste Hürde geschafft! Du fängst an!$`{ANSI_RESET}`")
                        spielrundenUserStart()
                    } else {
                        println("${ANSI_ORANGE}Du hast Verloren, der Gegner fängt an!$`{ANSI_RESET}`")
                        spielrundeGegnerStart()
                    }
                } catch (e: Exception) {
                    println("${ANSI_DARK_RED}Es gab einen Fehler $e. Bitte wähle zwischen 'k' & 'z'.$`{ANSI_RESET}`")
                    werStartet()
                }
            }

            //-------------------------------------------------------------------------

startscreen()

werStartet()


}


