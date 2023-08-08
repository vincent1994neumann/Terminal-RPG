package Gegner

import Helden.Hero
import geringerFlächenSchaden

class Troll (name: String, hpGegner: Int = 2000) : Gegner(name,hpGegner){
    var schutzWall = false
    //Attacke des Trolls

    fun keulenSchwung(HeroMutableList: MutableList<Hero>){
        var keulenSchadenGegner = geringerFlächenSchaden()
        for (hero in HeroMutableList){
            hero.hpHero-= keulenSchadenGegner
            if (hero.isProtected){
                hero.hpHero+=keulenSchadenGegner
            }
            if(hero.hpHero<=0){
                println("Der Held ${hero.name} wurde eliminiert")
            }
        }

    }
}

// Sollte Schutzwall true sein, sollen alle Attacken vom nächsten angriff nur auf den Troll gehen