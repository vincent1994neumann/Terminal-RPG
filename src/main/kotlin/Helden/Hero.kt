package Helden

import Gegner.Gegner
import geringerSchaden
import hpÜbersichtGegner
import kritischerSchaden
import mittlererSchaden

open class Hero (var name : String, var hpHero:Int){
open var isProtected : Boolean = false
    override fun toString(): String {
        return name
    }


    open fun kleineAttacke (gegner: List<Gegner>){
        var kleinerSchaden = geringerSchaden()
        var ziel = gegnerWählen(gegner)
        println("Der $name greift mit Attacke -Schlagen- an.")
        Thread.sleep(500)
        if (ziel.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        }else {
            println("$name trifft $ziel und verursacht $kleinerSchaden Schaden.")
            ziel.hpGegner -= kleinerSchaden
        }
        println()
    }

    open fun mittlereAttacke (gegner: List<Gegner>) {
        var mittlererSchaden = mittlererSchaden()
        var ziel = gegnerWählen(gegner)
        println("Der $name greift mit Attacke -Stechen- an.")
        Thread.sleep(500)
        if (ziel.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        }else {
            println("$name trifft $ziel und verursacht $mittlererSchaden Schaden.")
            ziel.hpGegner-=mittlererSchaden
        }
        println()
    }

    open fun spezialAttacke (gegner: List<Gegner>){
        var kritischerSchaden = kritischerSchaden()
        var ziel = gegnerWählen(gegner)
        println("Der $name greift mit seiner -Spezialattacke- an.")
        Thread.sleep(500)
        if (ziel.trollProtection) {
            println("Der Troll absorbiert deinen Angriff mit Magie.")
        } else {
            println("Der $name trifft den $ziel und verursacht $kritischerSchaden Schaden.")
            ziel.hpGegner -= kritischerSchaden
        }
        println()
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

    fun gegnerWählen (gegnerListe: List<Gegner>) : Gegner {
        println(gegnerListe)
        hpÜbersichtGegner(gegnerListe.toMutableList())
        println("Welchen Gegner möchtest du angreifen?")
        println("Wählen Sie die entsprechende Nummer:")
        var choice = readln().toIntOrNull()
        if (choice != null && choice in 1..gegnerListe.size) {
            return gegnerListe[choice - 1]
            println(gegnerListe[choice])
        } else {
            println("Deine Eingabe war falsch, bitte wähle erneut.")
            return gegnerWählen(gegnerListe)
        }
    }


    open fun attackeWählen(gegnerList : List<Gegner>){
        println("Bitte wähle die Attacke, mit der du angreifen möchtest.")
        println("1. Schlagen")
        println("2. Stechen")
        println("3. Spezialattacke")
        println("4. Tasche öffnen")
        println()
        println("Welche Attacke möchtest du auswählen:")
    try {
        var choice = readln().toInt()
        when (choice){
            1 -> kleineAttacke(gegnerList)
            2 -> mittlereAttacke(gegnerList)
            3 -> spezialAttacke(gegnerList)
        }
    }catch (e : Exception){
            println("Ups, $e ")
            println("Bitte prüfe deine Eingabe.")
            attackeWählen(gegnerList)
        }
    }
}



