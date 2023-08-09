package Gegner

import Helden.Hero
import geringerFlächenSchaden
import kritischerSchaden
import mittlererSchaden

class Troll (name: String, val heldenListe :MutableList<Hero>, hpGegner: Int = 2000) : Gegner(name,hpGegner){
    var schutzWall = false
    //Attacke des Trolls

    fun keulenSchwung(HeroMutableList: MutableList<Hero>){
        var keulenSchadenGegner = geringerFlächenSchaden()
        for (hero in HeroMutableList){
            hero.hpHero-= keulenSchadenGegner
            println("Der Troll ${this.name} setzt die Attacke Keulen Schwung ein und trifft alle Helden mit ${geringerFlächenSchaden()} Schaden.")
            if (hero.isProtected){
                hero.hpHero+=keulenSchadenGegner
                println("Der Gegner ist durch einen Zauber geschützt.")
            }
            if(hero.hpHero<=0){
                println("Der Held ${hero.name} wurde eliminiert")
            }
        }
    }

    fun schlagenTroll (HeroMutableList: MutableList<Hero>){
        var mittlererSchaden = mittlererSchaden()
        var auswahlHit = (0..<heldenListe.size).random()
        heldenListe[auswahlHit].hpHero-=mittlererSchaden
        println("Der Troll setzt die Attacke -Schlagen- ein und verursacht $mittlererSchaden Schaden bei ${heldenListe[auswahlHit].name}.")
            for (hero in HeroMutableList){
              if (hero.isProtected){
                  hero.hpHero += mittlererSchaden
                  println("Der Gegner ist durch einen Zauber geschützt.")
              }
                if(hero.hpHero<=0){
                    println("Der Held ${hero.name} wurde eliminiert")
                }
            }
    }

    fun umrennenTroll(HeroMutableList: MutableList<Hero>){
        var kritischerSchaden = kritischerSchaden()
        var auswahlHit = (0..<heldenListe.size).random()
        heldenListe[auswahlHit].hpHero-=kritischerSchaden
        println("Der Troll setzt die Attacke -Umrennen- ein und verursacht $kritischerSchaden Schaden bei ${heldenListe[auswahlHit].name}.")
    }

    fun attackTroll (Hero: Hero, heldenListe: MutableList<Hero>) {
        var randomNumber = (1..100).random()
        when (randomNumber) {
            in 1..59 -> keulenSchwung(heldenListe)
            in 60..84 -> schlagenTroll(heldenListe)
            in 85..100 -> umrennenTroll(heldenListe)
        }
}}
