package ar.edu.unq.eperdemic.modelo

//class Mutacion(var id: Int?) {   Originalmente estaba así, lo dejo por las dudas
class Mutacion() {
    var puntosAdnNecesarios: Int? = null
    var mutacionesNecesarias: List<Mutacion>? = null

    fun getAdnNecesario () : Int? {
        return (this.puntosAdnNecesarios)
    }

    fun mutacionesNecesarias () : List<Mutacion> {
        return (this.mutacionesNecesarias())
    }
    constructor(puntos: Int, mutaciones: List<Mutacion>) : this() {
        this.puntosAdnNecesarios = puntos
        this.mutacionesNecesarias = mutaciones
    }
}