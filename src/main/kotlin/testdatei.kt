/*
if (hero == Ritter("-Alaric-")){
                    println("Der Ritter ${hero.name} wird durch einen Zauber geschützt und kann nicht angegriffen werden.")
                    }
                    if (hero == Magier("-Houdini-")) {
                    println("Der Magier ${hero.name} wird durch einen Zauber geschützt und kann nicht angegriffen werden.")
                    }
                    if (hero == Bogenschütze("-Robin Hood-")){
                    println("Der Bogenschütze ${hero.name} wird durch einen Zauber geschützt und kann nicht angegriffen werden.")
                    }

 //Chat GPT hat mir hier geholfen zu verstehen warum ich nicht direkt in der schleife einen Helden removen kann soltle er kein Leben mehr haben.
    //Der Lösungsvorschlag war, zu nächst die Helden in einer neuen Liste zu speichern und die nach der schlaufe von der helden liste abzuziehen.





 override fun stechen (Hero: Hero) {
        var mittlererSchaden = mittlererSchaden()

        println("Der Goblin greift an - Attacke -Stechen-")
        if (Hero.isProtected) {
            println("Der Held ${Hero.name} ist durch einen Zauber geschützt und kann nicht angegriffen werden.")
        } else {

            println("Der Goblin setzte die Attacke -Schlagen- ein und verursacht $mittlererSchaden Schaden bei ${Hero.name}.")
            Hero.hpHero -= mittlererSchaden
        }
    }


 */