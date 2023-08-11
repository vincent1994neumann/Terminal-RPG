import Gegner.DunklerRitter
import Gegner.Gegner
import Gegner.Goblin
import Gegner.Troll
import Helden.Bogenschütze
import Helden.Hero
import Helden.Magier
import Helden.Ritter

fun main() {
    var rundenAnzahl: Int = 0
    var schutzZauberRunden = 0


    // Helden
    var ritter = Ritter("-Ritter-")
    var bogenSchütze = Bogenschütze("-Bogenschütze-")
    var magier = Magier("-Magier-")
    var heldenListe: MutableList<Hero> = mutableListOf(ritter, bogenSchütze, magier)


    // Gegner
    var dunklerRitter = DunklerRitter("-Dunkler Ritter-")
    var troll = Troll("-Troll-")
    var goblin = Goblin("-Goblin-")
    var gegnerListe: MutableList<Gegner> = mutableListOf(troll, dunklerRitter, goblin)


    fun gameOver():Boolean {
        return true
        if (heldenListe.isEmpty()) {
            return false
            println("Alle Helden sind eliminiert worden- Sie haben VERLOREN!")
        }
    }

    fun gegnerAngreifLogik(){
        println("Der Gegner holt zur Attacke aus!")
        Thread.sleep(500)
        var angreifenderGegner = gegnerListe.random()
        println("Der Gegner greift mit $angreifenderGegner deine Helden an.")
        Thread.sleep(500)
        when (angreifenderGegner){
            troll -> troll.auswahlAttackeTroll(heldenListe)
            dunklerRitter -> dunklerRitter.auswahlAttackeDunklerRitter(heldenListe.random())
            goblin -> goblin.auswahlAttackeGoblin(heldenListe.random())
        }
        println()
        Thread.sleep(1500)
        println("Übersicht Lebenspunkte Gegner:")
        hpÜbersichtGegner(gegnerListe)
        println("Übersicht Lebenspunkte Helden:")
        hpÜberischtHero(heldenListe)
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

    }

    fun spielrunden () {
        while (gameOver()) {
            var gespielteRunden = 0
            heroAngreifLogik()
            println()
            println("Jetzt ist der Gegner dran!")
            Thread.sleep(1500)
            gegnerAngreifLogik()
            gespielteRunden++
        }
    }

    //-------------------------------------------------------------------------







}
