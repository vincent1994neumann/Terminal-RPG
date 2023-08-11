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


    fun gegnerAngreifLogik(){
        println("Der Gegner holt zur Attacke aus!")
        Thread.sleep(500)
        var angreifenderGegner = gegnerListe.random()
        when (angreifenderGegner){
            gegner1 -> gegner1.auswahlAttackeTroll(heldenListe)
            gegner2 -> gegner2.auswahlAttackeDunklerRitter(heldenListe.random())
            gegner3 -> gegner3.auswahlAttackeGoblin(heldenListe.random())
        }
        println()
        Thread.sleep(1500)
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
        ausgewählterHeld.attackeWählen(gegnerListe)
        println()
        hpÜbersichtGegner(gegnerListe)
    }

    fun spielrunden() {
        var counter = 1
        var gameOver = false

        while (!gameOver) {
            println("----------------- RUNDE $counter -------------------")
            Thread.sleep(1000)
            heroAngreifLogik()
            Thread.sleep(1500)

            if (gegnerListe.isNotEmpty()) {
                println("--------------- Der Gegner ist dran ---------------")
                Thread.sleep(1000)
                gegnerAngreifLogik()
                for (hero in heldenListe) {
                    if (hero.isProtected) {
                        hero.protectionCountdown--
                        if (hero.protectionCountdown == 0) {
                            hero.isProtected = false
                            println("${hero.name} ist nicht mehr durch den Schutzzauber geschützt.")
                        }
                    }
                }
                Thread.sleep(1500)
                println("Runde $counter ist vorbei. Es gibt noch keinen Gewinner.")
                Thread.sleep(1500)
                counter++
                if (gegnerListe.isNotEmpty()) {
                    println("Mach dich bereit für Runde $counter!")
                    Thread.sleep(2000)
                }
            } else {
                println("Es wurden alle Gegner erfolgreich eliminiert!")
                Thread.sleep(1500)
                println("Sie haben GEWONNEN!!!")
                Thread.sleep(1500)
                gameOver = true
            }
        }
    }



    //-------------------------------------------------------------------------


spielrunden()




}
