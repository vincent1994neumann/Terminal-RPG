package Gegner

import Helden.Hero

class DunklerRitter (name: String, hpGegner: Double = 1500.0) : Gegner(name,hpGegner){
    override fun kleineAttackeGegner(Hero: Hero) {
        super.kleineAttackeGegner(Hero)
    }

    override fun mittlereAttackeGegner(Hero: Hero) {
        super.mittlereAttackeGegner(Hero)
    }

    fun auswahlAttackeDunklerRitter (Hero: Hero) {
        var randomNumber = (1..100).random()
        when (randomNumber) {
            in 1..55 -> kleineAttackeGegner(Hero)
            in 56..100 -> mittlereAttackeGegner(Hero)
        }
    }




}
