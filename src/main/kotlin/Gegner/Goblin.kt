// Importiert die benötigten Klassen.
package Gegner

import Helden.Hero

/**
 * Repräsentiert einen Goblin, einen speziellen Typ von Gegner im Spiel.
 * @property name Der Name des Goblins.
 * @property hpGegner Die aktuellen Gesundheitspunkte (HP) des Goblins.
 */
class Goblin(name: String, hpGegner: Double = 600.0) : Gegner(name, hpGegner) {

    /**
     * Führt einen leichten Angriff des Goblins gegen einen Helden aus.
     * @param Hero Das Heldenobjekt, das angegriffen wird.
     */
    override fun kleineAttackeGegner(Hero: Hero) {
        super.kleineAttackeGegner(Hero)
    }

    /**
     * Führt einen mittleren Angriff des Goblins gegen einen Helden aus.
     * @param Hero Das Heldenobjekt, das angegriffen wird.
     */
    override fun mittlereAttackeGegner(Hero: Hero) {
        super.mittlereAttackeGegner(Hero)
    }

    /**
     * Entscheidet zufällig, welche Attacke der Goblin gegen den Helden ausführen wird.
     * Es gibt eine 50%ige Chance für einen leichten oder mittleren Angriff.
     * @param Hero Das Heldenobjekt, das angegriffen wird.
     */
    fun auswahlAttackeGoblin(Hero: Hero) {
        var randomNumber = (1..100).random()
        when (randomNumber) {
            in 1..50 -> kleineAttackeGegner(Hero)
            in 50..100 -> mittlereAttackeGegner(Hero)
        }
    }
}
