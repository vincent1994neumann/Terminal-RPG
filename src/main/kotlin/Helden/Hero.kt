package Helden

import Gegner.Gegner
import geringerSchaden
import kritischerSchaden
import mittlererSchaden

open class Hero (var name : String, var hpHero:Int){
open var isProtected : Boolean = false



    open fun kleineAttacke (Gegner: Gegner){
        var kleinerSchaden = geringerSchaden()
        println("Der $name greift an - Attacke -Schlagen- ")
        Thread.sleep(500)
        if (Gegner.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        }else {
            println("$name trifft $Gegner und verursacht $kleinerSchaden Schaden.")
            Gegner.hpGegner -= kleinerSchaden
        }
    }

    open fun mittlereAttacke (Gegner: Gegner) {
        var mittlererSchaden = mittlererSchaden()
        println("Der $name greift an - Attacke -Stechen- ")
        Thread.sleep(500)
        if (Gegner.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        }else {
            println("$name trifft $Gegner und verursacht $mittlererSchaden Schaden.")
            Gegner.hpGegner-=mittlererSchaden
        }
    }

    open fun spezialAttacke (Gegner: Gegner){
        var kritischerSchaden = kritischerSchaden()
        println("Der $name greift mit seiner Spezialattacke an.")
        Thread.sleep(500)
        if (Gegner.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        } else {
            println("Der $name trifft den $Gegner und verursacht $kritischerSchaden Schaden.")
            Gegner.hpGegner -= kritischerSchaden
        }
    }

    fun stillAliveHero(hero: Hero):Boolean{
        if (hero.hpHero <= 0){
            println("$name der Gegner ist eliminiert worden!")
            return false
        }else  {
            println("$name hat noch $hpHero Lebenspunkte.")
            return true
        }
    }

    fun printAttack(gegner: Gegner){
        println("Welche Attacke möchtest du auswählen")
        var choice = readln().toInt()
        when (choice){
            1 -> kleineAttacke(gegner)
            2 -> mittlereAttacke(gegner)
        }
    }


    fun angreiferWählen (heldenList: MutableList<Hero>){
        println("Mit welchem Helden wollen Sie angreifen?")
        println(heldenList)

    }

    //Wel


}



