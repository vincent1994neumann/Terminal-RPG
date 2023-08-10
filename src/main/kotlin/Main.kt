import Gegner.DunklerRitter
import Gegner.Gegner
import Gegner.Goblin
import Gegner.Troll
import Helden.Bogenschütze
import Helden.Hero
import Helden.Magier
import Helden.Ritter

fun main() {
    var rundenAnzahl : Int = 0
    var schutzZauberRunden = 0
    // Helden
    var ritter = Ritter("-Ritter-")
    var bogenSchütze = Bogenschütze("-Bogenschütze-")
    var magier = Magier("-Magier-")
    var heldenListe : MutableList<Hero> = mutableListOf(ritter,bogenSchütze,magier)


      // Gegner
    var dunklerRitter = DunklerRitter("-Dunkler Ritter-")
    var troll = Troll("-Troll-")
    var goblin= Goblin("-Goblin-")
    var gegnerListe : MutableList<Gegner> = mutableListOf(troll,dunklerRitter,goblin)

    fun hpÜberischtHero (heldenListe: MutableList<Hero>){
        var eliminierteHelden : MutableList<Hero> = mutableListOf()
            for (hero in heldenListe) {
                if (hero.hpHero <= 0) {
                    eliminierteHelden.add(hero)
                    println("Ihr Held ${hero.name} wurde eliminiert!") }
                else {
                    print("${hero.name} HP: ${hero.hpHero} ")
            }
        }
        println()
        heldenListe.removeAll(eliminierteHelden)
    }

    fun hpÜbersichtGegner (gegnerListe : MutableList<Gegner>){
        var eliminierteGegner : MutableList<Gegner> = mutableListOf()
        for (gegner in gegnerListe){
            if (gegner.hpGegner <= 0){
                eliminierteGegner.add(gegner)
                println()
                println("Der Gegner ${gegner.name} wurde von Dir erfolgreich besiegt!")
            }else{
                print("${gegner.name} HP: ${gegner.hpGegner}; ")
            }
        }
        println()
        gegnerListe.removeAll(eliminierteGegner)
    }

    fun gameOver (){
        var gameOver = false
        if (heldenListe.isEmpty()){
            gameOver = true
        println("Alle Helden sind eliminiert worden- Sie haben VERLOREN!")
        }
    }

    fun dunklerRitterAttacke (){
        dunklerRitter.auswahlAttackeDunklerRitter(heldenListe.random())
    }

    fun trollAttacke (){
        troll.auswahlAttackeTroll(heldenListe)
    }

    fun goblinAttacke(){
        goblin.auswahlAttackeGoblin(heldenListe.random())
    }



    fun angreiferWählen (heldenList: MutableList<Hero>):Hero{
        println(heldenList)
        println("Mit welchem Helden wollen Sie angreifen?")
        var choice = readln().toInt()

        when (choice){
            1 -> return heldenList[0]
            2 -> return heldenList[1]
            3 -> return heldenList[2]
            else -> {
                println("Deine Eingabe war falsch, bitte wähle erneut.")
                angreiferWählen(heldenList)

            }
        }
    return Hero("",2)
    }
    //-------------------------------------------------------------------------
    println(angreiferWählen(heldenListe))


    //Alle Attacken der gegner implementieren und random ausgeben lassen



}