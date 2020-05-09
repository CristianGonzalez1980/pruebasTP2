package ar.edu.unq.eperdemic.persistencia.dao.hibernate

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.persistencia.dao.EspecieDAO
import ar.edu.unq.eperdemic.persistencia.dao.PatogenoDAO

open class HibernateEspecieDAO : HibernateDAO<Especie>(Especie::class.java), EspecieDAO {

    override fun recuperarEspecie(id: Int): Especie {
        return this.recuperar(id.toLong())
    }
}