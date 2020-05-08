package ar.edu.unq.eperdemic.persistencia.dao.hibernate

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.persistencia.dao.PatogenoDAO
import ar.edu.unq.eperdemic.services.runner.TransactionRunner


open class HibernatePatogenoDAO : HibernateDAO<Patogeno>(Patogeno::class.java), PatogenoDAO {

    override fun recuperarEspecie(idEspecie: Int): Especie {
        val session = TransactionRunner.currentSession
        val hql = ("from especie where id = idDeLaEspecie")
        val query = session.createQuery(hql, Especie::class.java)
        query.setParameter("idDeLaEspecie", idEspecie)
        return query.singleResult
    }

    override fun agregarEspecie(idPatogeno: Int, nombreEspecie: String, paisDeOrigen: String, adn: Int): Especie {
        var patogeno = this.recuperar(idPatogeno)
        val especie = Especie(patogeno,nombreEspecie,paisDeOrigen, adn)
        patogeno.agregarEspecie(especie)

        val session = TransactionRunner.currentSession
        session.save(especie)
        return especie

    }

    override fun recuperarATodos(): List<Patogeno> {
        val session = TransactionRunner.currentSession
        val hql = ("from patogeno p " + "order by p.tipo asc")
        val query = session.createQuery(hql, Patogeno::class.java)
        return query.resultList
    }

    override fun recuperar(idDelPatogeno: Int): Patogeno {
        return this.recuperar(idDelPatogeno.toLong())
    }

    override fun actualizar(patogeno: Patogeno) {
        val session = TransactionRunner.currentSession
        session.saveOrUpdate(patogeno)
    }

    override fun crear(patogeno: Patogeno): Int {
        this.guardar(patogeno)
        return (this.recuperar(patogeno.id).id!!.toInt())
    }
}
