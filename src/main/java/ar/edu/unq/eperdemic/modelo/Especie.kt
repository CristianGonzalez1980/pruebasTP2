package ar.edu.unq.eperdemic.modelo
import javax.persistence.*

@Entity
class Especie() {

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


}