// Importiert die benötigten Klassen.
package Gegner

import Helden.Hero

/**
 * Repräsentiert einen Dunklen Ritter, einen speziellen Typ von Gegner im Spiel.
 * @property name Der Name des Dunklen Ritters.
 * @property hpGegner Die aktuellen Gesundheitspunkte (HP) des Dunklen Ritters.
 */
class DunklerRitter(name: String, hpGegner: Double = 600.0) : Gegner(name, hpGegner) {

    /**
     * Führt einen leichten Angriff des Dunklen Ritters gegen einen Helden aus.
     * @param Hero Das Heldenobjekt, das angegriffen wird.
     */
    override fun kleineAttackeGegner(Hero: Hero) {
        super.kleineAttackeGegner(Hero)
    }

    /**
     * Führt einen mittleren Angriff des Dunklen Ritters gegen einen Helden aus.
     * @param Hero Das Heldenobjekt, das angegriffen wird.
     */
    override fun mittlereAttackeGegner(Hero: Hero) {
        super.mittlereAttackeGegner(Hero)
    }

    /**
     * Entscheidet zufällig, welche Attacke der Dunkle Ritter gegen den Helden ausführen wird.
     * Es gibt eine 55%ige Chance für einen leichten und eine 45%ige Chance für einen mittleren Angriff.
     * @param Hero Das Heldenobjekt, das angegriffen wird.
     */
    fun auswahlAttackeDunklerRitter(Hero: Hero) {
        var randomNumber = (1..100).random()
        when (randomNumber) {
            in 1..55 -> kleineAttackeGegner(Hero)
            in 56..100 -> mittlereAttackeGegner(Hero)
        }
    }
}

