import Gegner.DunklerRitter
import Gegner.Goblin
import Gegner.Troll
import Helden.Bogenschütze
import Helden.Hero
import Helden.Magier
import Helden.Ritter



fun main() {
    // Helden
    var ritter = Ritter("-Alaric-")
    var bogenSchütze = Bogenschütze("-Robin Hood-")
    var magier = Magier("-Houdini-")
    var heldenListe : MutableList<Hero> = mutableListOf(ritter,bogenSchütze,magier)

      // Gegner
    var dunklerRitter = DunklerRitter("-Peter-")
    var troll = Troll("-Gromnak-")
    var goblin= Goblin("-Gobi-")

    fun hpÜberischt (heldenListe: MutableList<Hero>){
        var eliminierteHelden : MutableList<Hero> = mutableListOf()
        for (hero in heldenListe) {
            if (hero.hpHero <= 0) {
                eliminierteHelden.add(hero)
                println("Der Held ${hero.name} wurde eliminiert!")
            } else {
                print("${hero.name} HP: ${hero.hpHero} ")
            }

        }
        println()
        heldenListe.removeAll(eliminierteHelden)
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



    hpÜberischt(heldenListe)
    dunklerRitterAttacke()
    hpÜberischt(heldenListe)
    dunklerRitter.auswahlAttackeDunklerRitter(heldenListe.random())
    hpÜberischt(heldenListe)
    trollAttacke()
    hpÜberischt(heldenListe)
    trollAttacke()
    hpÜberischt(heldenListe)
    goblinAttacke()
    hpÜberischt(heldenListe)


    gameOver()


    //Alle Attacken der gegner implementieren und random ausgeben lassen



}