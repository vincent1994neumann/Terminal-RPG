package Helden

class Beutel (
    var heiltränke : Int = 2,
    var angriffstrank: Int =1
)
{

    fun benutzeHeiltrank (): Boolean{
        if (heiltränke > 0){
            heiltränke--
            return true
        } else  {
            println("Keine Heiltränke mehr verfügbar!")
            return false
        }
    }


    fun benutzeAngriffstrank (): Boolean{
        if (angriffstrank > 0){
            angriffstrank--
            return true
        } else  {
            println("Kein Angrifftrank mehr verfügbar!")
            return false
        }
    }
}




//Klasse oder eher als Funktion????