package Helden

import Gegner.Gegner

class Ritter (name : String, hpHero :Double = 1750.0, isProtected : Boolean = false) : Hero(name,hpHero, isProtected){

    override fun kleineAttacke(gegner: List<Gegner>) {
        super.kleineAttacke(gegner)
    }

    override fun mittlereAttacke(gegner: List<Gegner>) {
        super.mittlereAttacke(gegner)
    }

    override fun spezialAttacke(gegner: List<Gegner>) {
        super.spezialAttacke(gegner)
    }

}
