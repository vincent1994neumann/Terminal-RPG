package Helden

import Gegner.Gegner
import kritischerSchaden

class Ritter (name : String, hpHero :Int = 50) : Hero(name,hpHero){

    override fun kleineAttacke(Gegner: Gegner) {
        super.kleineAttacke(Gegner)
    }

    override fun mittlereAttacke(Gegner: Gegner) {
        super.mittlereAttacke(Gegner)
    }

    override fun spezialAttacke(Gegner: Gegner) {
        super.spezialAttacke(Gegner)
    }

    fun waehleGegner(gegnerListe : MutableList<Gegner>){
        println("Bitte wähle den Gegner aus den du angreifen möchtest.")

    }
    fun userAttackenAuswahlRitter (){

    }



}
