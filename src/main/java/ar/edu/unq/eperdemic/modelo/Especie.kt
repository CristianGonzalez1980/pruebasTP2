package ar.edu.unq.eperdemic.modelo

import javax.persistence.*

@Entity
class Especie(unPatogeno: Patogeno,
              unNombre: String,
              unPaisDeOrigen: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var nombre: String = unNombre
    @Column(nullable = false, length = 500)
    var patogeno: Patogeno = unPatogeno
    var paisDeOrigen: String = unPaisDeOrigen



   /*@ManyToOne
    var owner: Patogeno? = null*/

}