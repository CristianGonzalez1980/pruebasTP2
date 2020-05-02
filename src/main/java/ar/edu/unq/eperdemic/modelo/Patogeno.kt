package ar.edu.unq.eperdemic.modelo

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
class Patogeno(){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Column(nullable = false, length = 500)
    var tipo: String? = null

    @OneToMany(mappedBy = "owner", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var especies: MutableSet<Especie> = HashSet()

    val cantidadDeEspecies: Int
        get() = especies.size

    constructor(unTipo: String) : this() {

        this.tipo = unTipo
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val patogeno = o as Patogeno?
        return id == patogeno!!.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }

    override fun toString(): String {
        return this.tipo!!
    }

    fun crearEspecie(nombreEspecie: String, paisDeOrigen: String) : Especie{
        var nuevaEspecie: Especie = Especie(this, nombreEspecie, paisDeOrigen)
        this.especies.add(nuevaEspecie)
        return nuevaEspecie

    }
}