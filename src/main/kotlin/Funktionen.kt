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

fun heroAngriff(hero: Hero, gegner: Gegner){
    while (hero.hpHero >= 0 && gegner.hpGegner >= 0){

    }

}
