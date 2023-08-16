import Gegner.ANSI_BROWN
import Gegner.Gegner
import Helden.Hero
const val ANSI_GREEN = "\u001B[32m"
const val ANSI_DARK_RED = "\u001B[31m"




fun geringerSchaden(): Int {
    return (50..150).random()
}

fun mittlererSchaden(): Int {
    return (150..350).random()
}

fun kritischerSchaden(): Int {
    return (350..650).random()
}

fun geringerFlächenSchaden(): Int{
    return (80..160).random()
}

fun mittlererFlächenSchaden(): Int{
    return (161..300).random()
}

fun hpÜbersichtGegner(gegnerListe: MutableList<Gegner>) {
    var eliminierteGegner: MutableList<Gegner> = mutableListOf()
    for (gegner in gegnerListe) {
        if (gegner.hpGegner <= 0) {
            eliminierteGegner.add(gegner)

            println(" Der Gegner ${gegner.name} wurde von Dir erfolgreich besiegt!")
        } else {
            println("$ANSI_BROWN ${gegner.name} HP: ${gegner.hpGegner.toInt()} $`{ANSI_RESET}`")
        }
    }
    println()
    gegnerListe.removeAll(eliminierteGegner)
}


fun hpÜberischtHero(heldenListe: MutableList<Hero>) {
    var eliminierteHelden: MutableList<Hero> = mutableListOf()
    for (hero in heldenListe) {
        if (hero.hpHero <= 0) {
            eliminierteHelden.add(hero)
            println("  Ihr Held ${hero.name} wurde eliminiert!")
        } else {
            println("$ANSI_GREEN ${hero.name} HP: ${hero.hpHero.toInt()} $`{ANSI_RESET}`")

        }
    }
    println()
    heldenListe.removeAll(eliminierteHelden)
}
fun isProtected (heldenListe: MutableList<Hero>){

    for (hero in heldenListe) {
    if (hero.isProtected) {
        hero.protectionCountdown--
    }
        if (hero.protectionCountdown == 0) {
        hero.isProtected = false
        }
    }
}

fun hpÜbersicht (gegnerListe: MutableList<Gegner>,heldenListe: MutableList<Hero>){
    println("${ANSI_ORANGE}----------------- HP Übersicht -----------------$`{ANSI_RESET}`")
    println()
    println("${ANSI_ORANGE}Übersicht Lebenspunkte$`{ANSI_RESET}` ${ANSI_DARK_RED}Gegner $`{ANSI_RESET}`${ANSI_ORANGE}:$`{ANSI_RESET}`")
    hpÜbersichtGegner(gegnerListe)
    println("${ANSI_ORANGE}Übersicht Lebenspunkte$`{ANSI_RESET}` ${ANSI_NEON_GREEN}Helden $`{ANSI_RESET}`${ANSI_ORANGE}:$`{ANSI_RESET}`")
    hpÜberischtHero(heldenListe)
    println()
    Thread.sleep(1000)
}