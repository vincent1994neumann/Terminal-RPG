package Helden

import Gegner.Gegner
import geringerSchaden
import hpÜberischtHero
import hpÜbersichtGegner
import kritischerSchaden
import mittlererSchaden

open class Hero (var name : String, var hpHero:Double, var isProtected : Boolean = false){

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

    open fun trinkHeiltrank(heldenListe: MutableList<Hero>) {
        if (0==0) {//beutel.benutzeHeiltrank()
            println("Alle Helden erhalten + 50% HP.")
            for (hero in heldenListe) {
                hero.hpHero *= 1.5

            }
            hpÜberischtHero(heldenListe)

        }
    }

    open fun trinkAngriffstrank(heldenListe: MutableList<Hero>){
        if (0==0
        ){//beutel.benutzeAngriffstrank()
            println("Alle Helden erhalten + 50% HP.")

            for(hero in heldenListe){

            }
        }
    }



    open fun attackeWählen(gegnerList : List<Gegner>, heldenListe: MutableList<Hero>){
        println("Bitte wähle eine Aktion:")
        println("1. Attacke:            Schlagen")
        println("2. Attacke:            Stechen")
        println("3. Attacke:            Spezialattacke")
        println()
        println("Welche Aktion möchtest du auswählen:")
    try {
        var choice = readln().toInt()
        when (choice){
            1 -> {
                kleineAttacke(gegnerList)
                hpÜberischtHero(heldenListe)
            }
            2 -> {
                mittlereAttacke(gegnerList)
                hpÜberischtHero(heldenListe)
            }
            3 -> {
                spezialAttacke(gegnerList)
                hpÜberischtHero(heldenListe)
            }
            else -> {
                println("Ungültige Auswahl!")
                attackeWählen(gegnerList,heldenListe)
            }

        }

    }catch (e : Exception){
            println("Ups, $e ")
            println("Bitte prüfe deine Eingabe.")
            attackeWählen(gegnerList,heldenListe)
        }
    }
}



