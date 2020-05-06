package ar.edu.unq.eperdemic.modelo

import java.util.HashSet
import javax.persistence.*

@Entity(name = "ubicacion")
@Table(name = "ubicacion")
class Ubicacion() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(64)")
    var nombreDeLaUbicacion: String? = null
    @OneToMany(mappedBy = "location", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var vectores: MutableSet<Vector> = HashSet()

    constructor(nombreUbicacion: String) : this() {
        this.nombreDeLaUbicacion = nombreUbicacion
    }

    constructor(nombreUbicacion: String, vectoresIniciales: MutableSet<Vector>) : this() {
        this.nombreDeLaUbicacion = nombreUbicacion
        this.vectores = vectoresIniciales
    }

    fun alojarVector(vector: Vector) {
        this.vectores.add(vector)
        vector.location = this
    }

    override fun toString(): String {
        return nombreDeLaUbicacion!!
    }
}