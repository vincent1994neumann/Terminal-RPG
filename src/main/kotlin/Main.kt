import Gegner.*
import Helden.*
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
        if (hero.isProtected) {
            println("${ANSI_ORANGE}Durch den Schutzzauber würde jeder Angriff ins Leere gehen.")
            println("Runden-Countdown Schutzzauber: ${hero.protectionCountdown} $`{ANSI_RESET}`")
        } else {
            println("${ANSI_BROWN}Der Gegner holt zur Attacke aus!$`{ANSI_RESET}`")
            Thread.sleep(500)
            var angreifenderGegner = gegnerListe.random()
            when (angreifenderGegner) {

                is Troll -> gegner1.auswahlAttackeTroll(heldenListe)
                is DunklerRitter -> gegner2.auswahlAttackeDunklerRitter(heldenListe.random())
                is Goblin -> gegner3.auswahlAttackeGoblin(heldenListe.random())

            }
            println()
            Thread.sleep(1500)
        }
    }

    fun heroWählen(heldenList: MutableList<Hero>): Hero { //Danke Chat GPT - hier hat Chat GPT mir die Logik kreiert
        println("Mit welchem Helden wollen Sie angreifen?")
        var result = ""
        for (i in heldenList.indices) {
            result += "${ANSI_ORANGE} ${i + 1}. ${heldenList[i]} $`{ANSI_RESET}`"
            if (i != heldenList.size - 1) {
                result += "; "
            }
        }
        println(result)
        print("${ANSI_BRIGHT_BLUE}Wählen Sie die entsprechende Nummer:  $`{ANSI_RESET}`")
        var choice = readln().toIntOrNull() // sollte kein Int eingegeben werden wird Null zurückgegeben

        if (choice != null && choice in 1..heldenList.size) {
            return heldenList[choice - 1]

        } else {
            println("Deine Eingabe war falsch, bitte wähle erneut.")
            return heroWählen(heldenList)
        }
    }



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




            fun spielrundenUserStart() {
                var counter = 1

                while (true) {
                    println()
                    Thread.sleep(1000)
                    println("${ANSI_RED}------------------- RUNDE $counter --------------------$`{ANSI_RESET}`")
                    println()
                    beutel.fluchEffektAnwenden(gegnerListe)
                    isProtected(heldenListe)
                    Thread.sleep(1000)
                    heroAngreifLogik()
                    Thread.sleep(1000)
                    // Prüfe, ob alle Helden besiegt wurden
                    if (heldenListe.isEmpty()) {
                        println("${ANSI_DARK_RED}Alle Helden wurden besiegt! $`{ANSI_RESET}`")
                        println("${ANSI_DARK_RED} Sie haben VERLOREN!!! $`{ANSI_RESET}`")
                        println()
                        break
                    }

                    if (counter == 3) {
                        var gegner4 = Goblin("${ANSI_DARK_RED}Goblin's Bruder$`{ANSI_RESET}`", 600.0)
                        gegnerListe.add(gegner4)
                        println("Ein wilder ${gegner4.name} wurde beschworen.")
                        println()
                    }

                    if (counter == 6) {
                        var gegner5 = Goblin("${ANSI_DARK_RED}Goblin's Schwester$`{ANSI_RESET}`", 500.0)
                        gegnerListe.add(gegner5)
                        println( println("Ein wilder ${gegner5.name} wurde beschworen."))
                        println()
                    }

                    // Wenn es noch Gegner gibt, lass sie angreifen
                    if (gegnerListe.isNotEmpty()) {
                        println("${ANSI_BROWN}|--------------- Der Gegner ist dran ---------------|$`{ANSI_RESET}`")
                        println()
                        Thread.sleep(1000)
                        gegnerAngreifLogik(held1)
                        hpÜberischtHero(heldenListe)

                        // Prüfe erneut, ob alle Helden nach dem Angriff der Gegner besiegt wurden
                        if (heldenListe.isEmpty()) {
                            println("${ANSI_DARK_RED}Alle Helden wurden nach dem Angriff der Gegner besiegt!$`{ANSI_RESET}`")
                            println("${ANSI_DARK_RED} Sie haben VERLOREN!!!$`{ANSI_RESET}`")
                            println()

                            break
                        }

                        println("${ANSI_BROWN}Runde $`{ANSI_RESET}` $ANSI_DARK_RED $counter $`{ANSI_RESET}` $ANSI_BROWN ist vorbei. Es gibt noch keinen Gewinner.$`{ANSI_RESET}`")
                        Thread.sleep(1500)
                        counter++
                        println("${ANSI_BROWN}Mach dich bereit für Runde $`{ANSI_RESET}` $ANSI_DARK_RED $counter $`{ANSI_RESET}`!")
                        println()
                    } else {
                        println("${ANSI_NEON_GREEN}Es wurden alle Gegner erfolgreich eliminiert!$`{ANSI_RESET}`")
                        Thread.sleep(1500)
                        println("${ANSI_NEON_GREEN}Sie haben GEWONNEN!!!$`{ANSI_RESET}`")
                        Thread.sleep(1500)
                        break
                    }
                }
                println("${ANSI_ORANGE}Das Spiel ist vorbei! Vielen Dank fürs spielen.$`{ANSI_RESET}`")
                println("${ANSI_NEON_GREEN}gespielte Runden: $counter $`{ANSI_RESET}`")

            }


            fun spielrundeGegnerStart() {
                var counter = 1
                var gameOver = false

                while (!gameOver) {
                    println()
                    Thread.sleep(1000)
                    println("${ANSI_RED}|------------------- RUNDE $counter --------------------|$`{ANSI_RESET}`")
                    println()
                    beutel.fluchEffektAnwenden(gegnerListe)
                    hpÜbersicht(gegnerListe, heldenListe)
                    isProtected(heldenListe)
                    Thread.sleep(1000)

                    // Der Gegner greift zuerst an
                    if (gegnerListe.isNotEmpty()) {
                        println("${ANSI_BROWN}|------------- Der Gegner ist dran -----------|$`{ANSI_RESET}`")
                        println()
                        Thread.sleep(1000)
                        gegnerAngreifLogik(held1)
                        hpÜberischtHero(heldenListe)

                        // Prüfe, ob alle Helden nach dem Angriff der Gegner besiegt wurden
                        if (heldenListe.isEmpty()) {
                            println("${ANSI_DARK_RED}Alle Helden wurden nach dem Angriff der Gegner besiegt!$`{ANSI_RESET}`")
                            println("${ANSI_DARK_RED} Sie haben VERLOREN!!! $`{ANSI_RESET}`")
                            println()
                            gameOver = true
                            continue
                        }
                    }

                    println("${ANSI_GREEN}|---------------- Du bist dran ------------------|$`{ANSI_RESET}`")
                    Thread.sleep(1000)
                    println()
                    heroAngreifLogik()
                    Thread.sleep(1000)

                    // Prüfe, ob alle Helden besiegt wurden
                    if (heldenListe.isEmpty()) {
                        println("${ANSI_DARK_RED}Alle Helden wurden besiegt! $`{ANSI_RESET}`")
                        println("${ANSI_DARK_RED} Sie haben VERLOREN!!! $`{ANSI_RESET}`")
                        println()
                        gameOver = true
                        continue
                    }

                    if (counter == 3) {
                        val gegner4 = Goblin("${ANSI_DARK_RED}Goblin's Bruder$`{ANSI_RESET}`", 600.0)
                        gegnerListe.add(gegner4)
                        println("Ein wilder ${gegner4.name} wurde beschworen.")
                        println()
                    }

                    if (counter == 6) {
                        val gegner5 = Goblin("${ANSI_DARK_RED}Goblin's Schwester$`{ANSI_RESET}`", 500.0)
                        gegnerListe.add(gegner5)
                        println("Ein wilder ${gegner5.name} wurde beschworen.")
                        println()
                    }

                    if (gegnerListe.isEmpty()) {
                        println("${ANSI_NEON_GREEN}Es wurden alle Gegner erfolgreich eliminiert!$`{ANSI_RESET}`")
                        Thread.sleep(1500)
                        println("${ANSI_NEON_GREEN}Sie haben GEWONNEN!!!$`{ANSI_RESET}`")
                        Thread.sleep(1500)
                        gameOver = true
                    } else {
                        println("${ANSI_BROWN}Runde $`{ANSI_RESET}` $ANSI_DARK_RED $counter $`{ANSI_RESET}` $ANSI_BROWN ist vorbei. Es gibt noch keinen Gewinner.$`{ANSI_RESET}`")
                        Thread.sleep(1500)
                    }

                    counter++
                    println("${ANSI_BROWN}Mach dich bereit für Runde $`{ANSI_RESET}` $ANSI_DARK_RED $counter $`{ANSI_RESET}`!")
                    println()
                }

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


//werStartet()
//spielrundeGegnerStart()
//startscreen()
//spielrundenUserStart()

            spielrundeGegnerStart()
}


