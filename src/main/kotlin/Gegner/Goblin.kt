package Gegner

import Helden.Hero

class Goblin (name: String, hpGegner: Int = 600) : Gegner(name,hpGegner) {

    override fun kleineAttackeGegner(Hero: Hero) {
        super.kleineAttackeGegner(Hero)
    }

    override fun mittlereAttackeGegner(Hero: Hero) {
        super.mittlereAttackeGegner(Hero)
    }


    fun auswahlAttackeGoblin (Hero: Hero) {
        var randomNumber = (1..100).random()
        when (randomNumber) {
            in 1..65 -> kleineAttackeGegner(Hero)
            in 66..100 -> mittlereAttackeGegner(Hero)
        }
    }
}