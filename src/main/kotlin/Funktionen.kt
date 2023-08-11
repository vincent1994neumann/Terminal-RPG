import Gegner.Gegner
import Helden.Hero

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
            println()
            println("Der Gegner ${gegner.name} wurde von Dir erfolgreich besiegt!")
        } else {
            print("${gegner.name} HP: ${gegner.hpGegner}; ")
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
            println("Ihr Held ${hero.name} wurde eliminiert!")
        } else {
            print("${hero.name} HP: ${hero.hpHero} ")

        }
    }
    println()
    heldenListe.removeAll(eliminierteHelden)
}


