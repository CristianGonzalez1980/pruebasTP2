package ar.edu.unq.eperdemic.modelo
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity(name = "especie")
@Table(name = "especie")
class Especie(): Serializable{

    @Id
    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(64)")
    var nombre: String? = null
    var paisDeOrigen: String? = null

    @ManyToOne
    var owner: Patogeno? = null

    constructor(owner: Patogeno, nombre: String, paisDeOrigen: String) : this() {
        this.owner = owner
        this.nombre = nombre
        this.paisDeOrigen = paisDeOrigen
    }

    override fun toString(): String {
        return nombre!!
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val especie = o as Especie?
        return owner!!.id == (especie!!.owner!!).id
    }

    override fun hashCode(): Int {
        return Objects.hash(owner!!.id)
    }



}