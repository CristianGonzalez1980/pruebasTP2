package ar.edu.unq.eperdemic.persistencia.dao

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Patogeno

interface PatogenoDAO {
    fun crear(patogeno: Patogeno): Int
    fun actualizar(patogeno: Patogeno )
    fun recuperar(idDelPatogeno: Int): Patogeno
    val recuperarATodos : Collection<Patogeno>
}