package ar.edu.unq.eperdemic.spring.controllers.dto

import ar.edu.unq.eperdemic.modelo.Especie

class EspecieDTO (val nombre : String,
                  val paisDeOrigen: String,
                  val patogenoId: Int,
                  val adn: Int){


    companion object {
        fun from(especie:Especie) =
                especie.owner!!.id?.let {
                    EspecieDTO(especie.nombre!!,
                            especie.paisDeOrigen!!,
                            it.toInt(), especie.adn!!.toInt())
                }
    }


}


