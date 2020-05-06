package ar.edu.unq.eperdemic.modelo

import javax.persistence.*

@Entity(name = "vector")
@Table(name = "vector")
class Vector() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    var location: Ubicacion? = null
    var infectado: Boolean = false

    constructor(location: Ubicacion) : this() {
        this.location = location
    }

    fun getId(): Long {
        return this.id!!
    }

    fun cambiarDeUbicacion(ubicacion: Ubicacion) {
        this.location!!.desAlojarVector(this)
        ubicacion.alojarVector(this)
    }
}