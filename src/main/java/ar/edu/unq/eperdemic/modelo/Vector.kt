package ar.edu.unq.eperdemic.modelo

import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategySuperClase
import javax.persistence.*
import javax.transaction.Transactional
import kotlin.jvm.Transient

@Entity(name = "vector")
@Table(name = "vector")
class Vector() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    var location: Ubicacion? = null

    @OneToMany
    var enfermedades: MutableList<Especie> = mutableListOf()

    @Column(nullable = false, columnDefinition = "VARCHAR(64)")
    var tipo: String? = null

    @Transient
    var estrategiaDeContagio: StrategySuperClase? = null


    fun infectar(vector: Vector, especie: Especie) {
        vector.enfermedades.add(especie)
    }

    constructor(location: Ubicacion, estrategia: StrategySuperClase) : this() {
        this.location = location
        location.vectores.add(this)
        this.estrategiaDeContagio = estrategia
        this.tipo = estrategia.tipo()
    }

    fun contagiar(vectorInfectado: Vector, vectores: List<Vector>): MutableList<Vector> {
        val listaResultante: MutableList<Vector> = mutableListOf()
        for (v: Vector in vectores) {
            if (estrategiaDeContagio?.darContagio(vectorInfectado, v)!!) {
                listaResultante.add(v)
            }
        }
        return listaResultante
    }

    fun cambiarDeUbicacion(ubicacion: Ubicacion) {
        this.location!!.desAlojarVector(this)
        ubicacion.alojarVector(this)
    }

    fun estaInfectado(): Boolean {
        return (this.enfermedades.isNotEmpty())
    }
}