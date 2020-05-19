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
    var mutacionesNecesarias: MutableList<Mutacion> = mutableListOf()

    @ManyToOne
    var owner: Especie? = null

    var potencialidad: Potencialidad? = null

    constructor(puntos: Int, mutaciones: MutableList<Mutacion>, potencialidad: Potencialidad) : this() {
        this.puntosAdnNecesarios = puntos
        this.mutacionesNecesarias = mutaciones
        this.potencialidad = potencialidad
    }

    fun getAdnNecesario(): Int? {
        return (this.puntosAdnNecesarios)
    }

    fun mutacionesNecesarias(): List<Mutacion> {
        return (this.mutacionesNecesarias)
    }

    fun potenciarEspecie(especie: Especie) {

        if (this.potencialidad!!.name == "Contagio") {
            especie.owner!!.incrementarCapacidadDeContagio()
        }

        if (this.potencialidad!!.name == "Defensa") {
            especie.owner!!.incrementarDefensa()
        }

        if (this.potencialidad!!.name == "Letalidad") {
            especie.owner!!.incrementarLetalidal()
        }
    }

}