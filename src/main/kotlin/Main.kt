import Gegner.DunklerRitter
import Gegner.Gegner
import Gegner.Goblin
import Gegner.Troll
import Helden.Bogenschütze
import Helden.Hero
import Helden.Magier
import Helden.Ritter

fun main() {
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
            println("Durch den Schutzzauber würde jeder Angriff ins Leere gehen.")
            println("Runden-Countdown Schutzzauber: ${hero.protectionCountdown} ")
        } else {
                println("Der Gegner holt zur Attacke aus!")
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

    fun heroWählen(heldenList: MutableList<Hero>): Hero {
        println(heldenList)
        println("Mit welchem Helden wollen Sie angreifen?")
        println("Wählen Sie die entsprechende Nummer:")
        var choice = readln().toIntOrNull() // sollte kein Int eingegeben werden wird Null zurückgegeben

        if (choice != null && choice in 1..heldenList.size) {
            return heldenList[choice - 1]

        } else {
            println("Deine Eingabe war falsch, bitte wähle erneut.")
            return heroWählen(heldenList)
        }
    }

    fun heroAngreifLogik(){
        println("Übersicht Lebenspunkte Gegner:")
        hpÜbersichtGegner(gegnerListe)
        println("Übersicht Lebenspunkte Helden:")
        hpÜberischtHero(heldenListe)
        println()
        var ausgewählterHeld = heroWählen(heldenListe)
        println(ausgewählterHeld)
        ausgewählterHeld.attackeWählen(gegnerListe,heldenListe)
        println()
        hpÜbersichtGegner(gegnerListe)
    }

    fun spielrunden() {
        var counter = 1
        var gameOver = false

        while (!gameOver) {
            println("----------------- RUNDE $counter -------------------")
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
