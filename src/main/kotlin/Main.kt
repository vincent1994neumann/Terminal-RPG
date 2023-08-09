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

    fun hpÜberischt (){
        print("HP- Ritter: ${ritter.hpHero}; ")
        print("HP- Bogenschütze: ${bogenSchütze.hpHero}; ")
        print("HP- Magier: ${magier.hpHero}")
        println()
    }



    hpÜberischt()
    dunklerRitter.attackDunklerRitter(ritter)
    hpÜberischt()
    troll.attackTroll(ritter, heldenListe)
    hpÜberischt()

    //Alle Attacken der gegner implementieren und random ausgeben lassen



}