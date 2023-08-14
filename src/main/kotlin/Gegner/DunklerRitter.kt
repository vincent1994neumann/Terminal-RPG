package Gegner

import Helden.Hero

class DunklerRitter (name: String, hpGegner: Double = 200.0) : Gegner(name,hpGegner){
    override fun kleineAttackeGegner(Hero: Hero) {
        super.kleineAttackeGegner(Hero)
    }

    override fun mittlereAttackeGegner(Hero: Hero) {
        super.mittlereAttackeGegner(Hero)
    }

    fun auswahlAttackeDunklerRitter (Hero: Hero) {
        var randomNumber = (1..100).random()
        when (randomNumber) {
            in 1..65 -> kleineAttackeGegner(Hero)
            in 66..100 -> mittlereAttackeGegner(Hero)
        }
    }




}
