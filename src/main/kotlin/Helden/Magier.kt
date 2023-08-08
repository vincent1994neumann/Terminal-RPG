package Helden

import Gegner.Gegner
import geringerFlächenSchaden
import mittlererFlächenSchaden

class Magier (name : String, hpHero: Int = 1000) : Hero(name,hpHero) {

    fun hagelSchadenHero(gegnerMutableList: MutableList<Gegner>) { //Muatbelist damit ich ggf später die Liste verändern kann, z.B. wenn ein Gegener eliminiert wurde
        val geringerFlächenSchaden = geringerFlächenSchaden()
        for (gegner in gegnerMutableList) {
            gegner.hpGegner -= geringerFlächenSchaden
            if (gegner.hpGegner <= 0) {
                println("${gegner.name} wurde eliminiert.")
            }
        }
    }

    fun feuerBallHero(gegnerMutableList: MutableList<Gegner>) { //Muatbelist damit ich ggf später die Liste verändern kann, z.B. wenn ein Gegener eliminiert wurde
        val mittlererFlächenSchaden = mittlererFlächenSchaden()
        for (gegner in gegnerMutableList) {
            gegner.hpGegner -= mittlererFlächenSchaden
            if (gegner.hpGegner <= 0) {
                println("${gegner.name} wurde eliminiert.")
            }
        }
    }

    fun schutzZauberHero(heroList: List<Hero>){
        //Muss den Boolean in der Hero Klasse auf true setzen für eine Runde.
    }
}