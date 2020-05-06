package ar.edu.unq.eperdemic.modelo

import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyInterface
import javax.persistence.*
import kotlin.jvm.Transient

@Entity(name = "vector")
@Table(name = "vector")
class Vector() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    var location: Ubicacion? = null
    var infectado: Boolean = false

  
    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var enfermedades : MutableSet<Especie> = HashSet()

    @Transient
    var estrategiaDeContagio : StrategyInterface? = null



    fun infectar(vector: Vector, especie: Especie){
        vector.enfermedades.add(especie)
    }

    constructor(location: Ubicacion  , estrategia : StrategyInterface ) : this() {
        this.location = location

        this.estrategiaDeContagio = estrategia
    }

    fun contagiar(vectorInfectado : Vector, vectores: List<Vector>){
        for (v: Vector in vectores){
            estrategiaDeContagio?.darContagio(vectorInfectado , v)
        
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