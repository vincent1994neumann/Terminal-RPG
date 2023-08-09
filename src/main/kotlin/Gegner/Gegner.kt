package Gegner

open class Gegner (var name : String,var hpGegner: Int){
    var trollProtection : Boolean = false
    override fun toString(): String {
        return name
    }
}