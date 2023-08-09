import Gegner.DunklerRitter
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
    var troll = Troll("-Gromnak-", heldenListe)

    fun hpÜberischt (heldenListe: MutableList<Hero>){
        for (hero in heldenListe)
            print("${hero.name} - HP: ${hero.hpHero} ; ")
        println()
        println()
    }



    hpÜberischt(heldenListe)
    troll.attackTroll(heldenListe)
    hpÜberischt(heldenListe)



    //Alle Attacken der gegner implementieren und random ausgeben lassen



}