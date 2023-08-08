package Helden

import Gegner.Gegner
import geringerSchaden
import mittlererSchaden
import kritischerSchaden

class Ritter (name : String, hpHero :Int = 1800) : Hero(name,hpHero){

    fun schlagen (Gegner:Gegner){
        var kleinerSchaden = geringerSchaden()
        println("Der Ritter trifft den Gegner und verursacht $kleinerSchaden Schaden.")
        Gegner.hpGegner-=kleinerSchaden
    }

    fun stechen (Gegner: Gegner) {
        var mittlererSchaden = mittlererSchaden()
        println("Der Ritter trifft den Gegner und verursacht $mittlererSchaden Schaden.")
        Gegner.hpGegner-=mittlererSchaden
    }

    fun spezialAttackeRitter (Gegner: Gegner){
        var kritischerSchaden = kritischerSchaden()
        println("Der Ritter trifft den Gegner und verursacht $kritischerSchaden Schaden.")
        Gegner.hpGegner-=kritischerSchaden
    }

}
