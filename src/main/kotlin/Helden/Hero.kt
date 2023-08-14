package Helden

import Gegner.Gegner
import geringerSchaden
import hpÜbersichtGegner
import kritischerSchaden
import mittlererSchaden

open class Hero (var name : String, var hpHero:Int, var isProtected : Boolean = false){

    open var protectionCountdown = 0
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
            println("$name trifft $ziel und fügt $kleinerSchaden Schaden zu.")
            ziel.hpGegner -= kleinerSchaden
        }
        if (ziel.hpGegner <= 0) {
            println("Der Gegner ${ziel.name} wurde durch den Angriff eliminiert.")
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
        if (ziel.hpGegner <= 0) {
            println("Der Gegner ${ziel.name} wurde durch den Angriff eliminiert.")
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
        if (ziel.hpGegner <= 0) {
            println("Der Gegner ${ziel.name} wurde durch den Angriff eliminiert.")
        }
        println()
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


    open fun attackeWählen(gegnerList : List<Gegner>, heldenListe: MutableList<Hero>){
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
            attackeWählen(gegnerList,heldenListe)
        }
    }
}



