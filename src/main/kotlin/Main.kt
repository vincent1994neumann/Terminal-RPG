import Gegner.*
import Helden.*
const val ANSI_RESET = "\u001B[0m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_DARK_BLUE = "\u001B[34m"
const val ANSI_YELLOW = "\u001B[33m"
val ANSI_ORANGE = "\u001B[38;2;255;165;0m"
val ANSI_NEON_GREEN = "\u001B[92m"
val ANSI_BRIGHT_BLUE = "\u001B[94m"
val ANSI_BLACK = "\u001B[30m"




fun main() {

    // Gemeinsamer Beutel - globale Instanz, sodass ein BEutel für alle Helden gilt
    var beutel = Beutel(2, 1)
    // Helden
    var held1 = Ritter("Ritter")
    var held2 = Bogenschütze("Bogenschütze")
    var held3 = Magier("Magier")
    var heldenListe: MutableList<Hero> = mutableListOf(held1, held2, held3)


    // Gegner
    var gegner1 = Troll("Troll")
    var gegner2 = DunklerRitter("Dunkler Ritter")
    var gegner3 = Goblin("Goblin")
    var gegnerListe: MutableList<Gegner> = mutableListOf(gegner1, gegner2, gegner3)


    fun gegnerAngreifLogik(hero: Hero) {
        if (hero.isProtected) {
            println("$ANSI_ORANGE Durch den Schutzzauber würde jeder Angriff ins Leere gehen.")
            println(" Runden-Countdown Schutzzauber: ${hero.protectionCountdown} $ANSI_RESET")
        } else {
            println("$ANSI_BROWN Der Gegner holt zur Attacke aus!$ANSI_RESET")
            Thread.sleep(500)
            var angreifenderGegner = gegnerListe.random()
            when (angreifenderGegner) {
                gegner1 -> gegner1.auswahlAttackeTroll(heldenListe)
                gegner2 -> gegner2.auswahlAttackeDunklerRitter(heldenListe.random())
                gegner3 -> gegner3.auswahlAttackeGoblin(heldenListe.random())

            }
            println()
            Thread.sleep(1500)
        }
    }

    fun heroWählen(heldenList: MutableList<Hero>): Hero { //Danke Chat GPT - hier hat Chat GPT mir die Logik kreiert
        println("$ANSI_BRIGHT_BLUE Mit welchem Helden wollen Sie angreifen?$ANSI_RESET")
        var result = ""
        for (i in heldenList.indices) {
            result += "$ANSI_ORANGE ${i + 1}. ${heldenList[i]} $ANSI_RESET"
            if (i != heldenList.size - 1) {
                result += "; "
            }
        }
        println(result)

        println("$ANSI_BRIGHT_BLUE Wählen Sie die entsprechende Nummer:$ANSI_RESET")
        var choice = readln().toIntOrNull() // sollte kein Int eingegeben werden wird Null zurückgegeben

        if (choice != null && choice in 1..heldenList.size) {
            return heldenList[choice - 1]

        } else {
            println("Deine Eingabe war falsch, bitte wähle erneut.")
            return heroWählen(heldenList)
        }
    }



    fun heroAngreifLogik() {
        println("Übersicht Lebenspunkte Gegner:")
        hpÜbersichtGegner(gegnerListe)
        println("Übersicht Lebenspunkte Helden:")
        hpÜberischtHero(heldenListe)
        println()
        println("$ANSI_BRIGHT_BLUE Möchten Sie den Beutel öffnen oder kämpfen?$ANSI_RESET")
        println("$ANSI_ORANGE 1. Kämpfen$ANSI_RESET")
        println("$ANSI_ORANGE 2. Beutel öffnen$ANSI_RESET")
        println("$ANSI_BRIGHT_BLUE Bitte wählen Sie:$ANSI_RESET")
        //GGF noch mal abfangen prüfen
        when (readln().toIntOrNull()) {
            1 -> {
                println()
                var ausgewählterHeld = heroWählen(heldenListe)
                println("$ANSI_GREEN Gewählter Held: ${ausgewählterHeld} $ANSI_RESET")
                ausgewählterHeld.attackeWählen(gegnerListe, heldenListe)
                println()
                hpÜbersichtGegner(gegnerListe)

            }
                2 ->
            {
                println("$ANSI_YELLOW Beutelinhalt:$ANSI_RESET")
                println("$ANSI_YELLOW 1. Heiltrank: $ANSI_RESET ${beutel.heiltränke}")
                println("$ANSI_YELLOW 2. Fluchtrank:$ANSI_RESET ${beutel.fluchTrank}")
                println("Bitte wählen Sie:")
                var choice = readln().toIntOrNull()
                when (choice){
                    1-> {
                        if (beutel.heiltränke < 1) {
                            println("Keine Heiltränke mehr verfügbar!")
                        } else {
                            beutel.aufrufHeiltrank(heldenListe)
                            beutel.heiltränke--
                            println("---------------50 % HP LevelUp---------------")
                            hpÜberischtHero(heldenListe)
                        }
                    }
                    2->{
                        beutel.aufrufFluchTrank(gegnerListe)
                        beutel.fluchEffektAnwenden(gegnerListe)
                        hpÜbersichtGegner(gegnerListe)

                    }
                }
                }
            }
        }


    fun spielrunden() {
        var counter = 1
        var gameOver = false

        while (!gameOver) {
            println("$ANSI_RED----------------- RUNDE $counter -------------------$ANSI_RESET")
            beutel.fluchEffektAnwenden(gegnerListe)
            Thread.sleep(1000)
            isProtected(heldenListe)
            heroAngreifLogik()
            Thread.sleep(500)

            // Prüfe, ob alle Helden besiegt wurden
            if (heldenListe.isEmpty()) {
                println("Alle Helden wurden besiegt!")
                println("Sie haben VERLOREN!!!")
                gameOver = true
                continue
            }



            // Wenn es noch Gegner gibt, lass sie angreifen
            if (gegnerListe.isNotEmpty()) {
                println("--------------- Der Gegner ist dran ---------------")
                Thread.sleep(1000)
                gegnerAngreifLogik(held1)
                hpÜberischtHero(heldenListe)

                // Prüfe erneut, ob alle Helden nach dem Angriff der Gegner besiegt wurden
                if (heldenListe.isEmpty()) {
                    println("Alle Helden wurden nach dem Angriff der Gegner besiegt!")
                    println("Sie haben VERLOREN!!!")
                    gameOver = true
                }

                println("Runde $counter ist vorbei. Es gibt noch keinen Gewinner.")
                Thread.sleep(1500)
                counter++
                println("Mach dich bereit für Runde $counter!")
            } else {
                println("Es wurden alle Gegner erfolgreich eliminiert!")
                Thread.sleep(1500)
                println("Sie haben GEWONNEN!!!")
                Thread.sleep(1500)
                gameOver = true
            }
        }
        println("Das Spiel ist vorbei! Vielen Dank fürs spielen.")
    }





    //-------------------------------------------------------------------------


spielrunden()




}

