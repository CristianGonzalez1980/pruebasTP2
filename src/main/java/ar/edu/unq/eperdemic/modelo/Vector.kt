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
  
    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var enfermedades : MutableSet<Especie> = HashSet()

    @Transient
    var estrategiaDeContagio : StrategySuperClase? = null



    fun infectar(vector: Vector, especie: Especie){
        vector.enfermedades.add(especie)
    }

    constructor(location: Ubicacion  , estrategia : StrategySuperClase) : this() {
        this.location = location
        location.vectores.add(this)
        this.estrategiaDeContagio = estrategia
    }

    fun contagiar(vectorInfectado : Vector, vectores: List<Vector>){
        for (v: Vector in vectores){
            estrategiaDeContagio?.darContagio(vectorInfectado , v)
        }
    }

    fun cambiarDeUbicacion(ubicacion: Ubicacion) {
        this.location!!.desAlojarVector(this)
        ubicacion.alojarVector(this)
    }

}