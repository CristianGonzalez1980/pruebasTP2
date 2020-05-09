package ar.edu.unq.eperdemic.persistencia.dao

import ar.edu.unq.eperdemic.modelo.Especie

interface EspecieDAO {
    fun recuperarEspecie(id: Int): Especie
}
