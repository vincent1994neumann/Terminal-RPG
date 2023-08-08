package Helden
import Gegner.Gegner
import geringerSchaden
import mittlererSchaden
import kritischerSchaden

class Bogenschütze (name : String, hpHero: Int = 750) : Hero(name,hpHero){

    //Attacken des Bogenschützen

    fun einzelSchussPfeil (Gegner: Gegner){
        var kleinerSchaden = geringerSchaden()
        println("Der Bogenschütze trifft den Gegner und verursacht $kleinerSchaden Schaden.")
        Gegner.hpGegner-=kleinerSchaden
    }

    fun brennenderPfeil (Gegner: Gegner){
        var mittlererSchaden = mittlererSchaden()
        println("Der Bogenschütze trifft den Gegner mit einen brennenden Pfeil und verursacht $mittlererSchaden Schaden.")
        Gegner.hpGegner-=mittlererSchaden
    }

    fun spezialAttackeBogen (Gegner: Gegner){
        var kritischerSchaden = kritischerSchaden()
        println("Der Bogenschütze trifft den Gegner mit einen Snipershot und verursacht $kritischerSchaden Schaden.")
        Gegner.hpGegner-=kritischerSchaden
    }

}