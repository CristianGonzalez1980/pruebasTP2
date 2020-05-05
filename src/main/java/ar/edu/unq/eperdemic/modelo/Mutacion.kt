package ar.edu.unq.eperdemic.modelo

import javax.persistence.*

//class Mutacion(var id: Int?) {   Originalmente estaba así, lo dejo por las dudas
@Entity(name = "mutacion")
@Table(name = "mutacion")
class Mutacion() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null;
    var puntosAdnNecesarios: Int? = null

    @ManyToMany
    var mutacionesNecesarias: MutableList<Mutacion> = ArrayList()

    @ManyToOne
    var owner: Especie? = null

    constructor(owner:Especie ,puntos: Int) : this() {
        this.puntosAdnNecesarios = puntos
        this.owner = owner
    }

    fun getAdnNecesario () : Int? {
        return (this.puntosAdnNecesarios)
    }

    fun mutacionesNecesarias () : List<Mutacion> {
        return (this.mutacionesNecesarias)
    }

}