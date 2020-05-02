package ar.edu.unq.eperdemic.modelo

import javax.persistence.*

@Entity
class Ubicacion() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(64)")
    var nombreUbicacion: String? = null

    constructor(nombreUbicacion: String) : this() {
        this.nombreUbicacion = nombreUbicacion
    }

    override fun toString(): String {
        return nombreUbicacion!!
    }
}