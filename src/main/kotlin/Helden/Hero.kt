package Helden

import ANSI_BRIGHT_BLUE
import ANSI_DARK_RED
import ANSI_GREEN
import ANSI_ORANGE
import `{ANSI_RESET}`
import Gegner.Gegner
import geringerSchaden
import hpÜbersichtGegner
import kritischerSchaden
import mittlererSchaden

open class Hero (var name : String, var hpHero:Double, var isProtected : Boolean = false){

    open var protectionCountdown = 0
    open var spezialAttackeVerfügbar = 1
    open var mittlereAttackeVerfügbar = 2
    override fun toString(): String {
        return name
    }


    open fun kleineAttacke (gegner: List<Gegner>){
        var kleinerSchaden = geringerSchaden()
        var ziel = gegnerWählen(gegner)
        println(" Der $name greift mit Attacke -Schlagen- an.")
        Thread.sleep(500)
        if (ziel.trollProtection) {
            println(" Der Troll absorbiert deinen Angriff mit Magie.")
        }else {
            println("$name trifft $ziel und fügt $kleinerSchaden Schaden zu.")
            ziel.hpGegner -= kleinerSchaden
        }
        if (ziel.hpGegner <= 0) {
            println(" Der Gegner ${ziel.name} wurde durch den Angriff eliminiert.$`{ANSI_RESET}`")
        }
        println()
    }

    open fun mittlereAttacke (gegner: List<Gegner>) {
        if (mittlereAttackeVerfügbar <=0) {
            println("Die Spezialattacke von $name wurde bereits verwendet und kann nicht erneut eingesetzt werden!")
            return
        }
        var mittlererSchaden = mittlererSchaden()
        var ziel = gegnerWählen(gegner)
        println(" Der $name greift mit Attacke -Stechen- an.")
        Thread.sleep(500)
        if (ziel.trollProtection) {
            println(" Der Troll absorbiert deinen Angriff mit Magie.")
        }else {
            println(" $name trifft $ziel und verursacht $mittlererSchaden Schaden.")
            ziel.hpGegner-=mittlererSchaden
        }
        if (ziel.hpGegner <= 0) {
            println("$ANSI_GREEN Der Gegner ${ziel.name} $ANSI_GREEN wurde durch den Angriff eliminiert.$`{ANSI_RESET}`")
        }
        mittlereAttackeVerfügbar--
        println()
    }

    open fun spezialAttacke (gegner: List<Gegner>){
        if (spezialAttackeVerfügbar <= 0) {
            println("Die Spezialattacke von $name wurde bereits verwendet und kann nicht erneut eingesetzt werden!")
            return}

        var kritischerSchaden = kritischerSchaden()
        var ziel = gegnerWählen(gegner)
        println(" Der $name greift mit seiner -Spezialattacke- an. ")
        Thread.sleep(500)
        if (ziel.trollProtection) {
            println(" Der Troll absorbiert deinen Angriff mit Magie. ")
        } else {
            println(" Der $name trifft den $ziel und verursacht $kritischerSchaden Schaden.")
            ziel.hpGegner -= kritischerSchaden
        }
        if (ziel.hpGegner <= 0) {
            println("$ANSI_GREEN Der Gegner ${ziel.name} wurde durch den Angriff eliminiert.$`{ANSI_RESET}`")
        }
        spezialAttackeVerfügbar--
        println()
    }

    fun gegnerWählen (gegnerListe: List<Gegner>) : Gegner {
        println("$ANSI_BRIGHT_BLUE Welchen Gegner möchtest du angreifen?$`{ANSI_RESET}`")
        hpÜbersichtGegner(gegnerListe.toMutableList())
        var resultGegner = ""
        for (i in gegnerListe.indices) {
            resultGegner += "$ANSI_ORANGE ${i + 1}. ${gegnerListe[i]} $`{ANSI_RESET}`"
            if (i != gegnerListe.size - 1) {
                resultGegner += " ;  "
            }
        }
        println(resultGegner)
        println("$ANSI_BRIGHT_BLUE Wählen Sie die entsprechende Nummer: $`{ANSI_RESET}`")


        var choice = readln().toIntOrNull()
        if (choice != null && choice in 1..gegnerListe.size) {
            return gegnerListe[choice - 1]
            println(gegnerListe[choice])
        } else {
            println("$ANSI_DARK_RED Deine Eingabe war falsch, bitte wähle erneut.$`{ANSI_RESET}`")
            return gegnerWählen(gegnerListe)
        }
    }

    open fun attackeWählen(gegnerList : List<Gegner>, heldenListe: MutableList<Hero>){
        println("$ANSI_BRIGHT_BLUE Bitte wähle eine Aktion: $`{ANSI_RESET}`")
        println("$ANSI_ORANGE 1.    Standartangriff $`{ANSI_RESET}`")
        println("$ANSI_ORANGE 2.    Verstärkter Angriff (Verfügbar: $mittlereAttackeVerfügbar) $`{ANSI_RESET}`")
        println("$ANSI_ORANGE 3.    Spezialattacke (Verfügbar: $spezialAttackeVerfügbar) $`{ANSI_RESET}`")
        println("$ANSI_BRIGHT_BLUE Welche Aktion möchtest du auswählen:$`{ANSI_RESET}`")
    try {
        var choice = readln().toInt()
        when (choice){
            1 -> {
                kleineAttacke(gegnerList)
            }
            2 -> {
                mittlereAttacke(gegnerList)
            }
            3 -> {
                if (spezialAttackeVerfügbar> 0){
                    spezialAttacke(gegnerList)
                }else{
                    println("${ANSI_ORANGE}Du hast keine Spezialangriffe mehr verfügbar!$`{ANSI_RESET}`")
                    attackeWählen(gegnerList,heldenListe)
                }
            }
            else -> {
                println("$ANSI_DARK_RED Ungültige Auswahl!$`{ANSI_RESET}`")
                attackeWählen(gegnerList,heldenListe)
            }

        }

    }catch (e : Exception){
            println("$ANSI_DARK_RED Ups, $e ")
            println("Bitte prüfe deine Eingabe. $`{ANSI_RESET}`")
            attackeWählen(gegnerList,heldenListe)
        }
    }
}



