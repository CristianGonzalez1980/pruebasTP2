package ar.edu.unq.eperdemic.persistencia.dao.hibernate

import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.persistencia.dao.PatogenoDAO
import ar.edu.unq.eperdemic.services.runner.TransactionRunner

open class HibernatePatogenoDAO : HibernateDAO<Patogeno>(Patogeno::class.java), PatogenoDAO {

    override fun recuperarATodos(): List<Patogeno> {
        val session = TransactionRunner.currentSession

        val hql = ("from patogeno p " + "order by p.tipo asc")

        val query = session.createQuery(hql, Patogeno::class.java)
        return query.resultList
    }

    override fun recuperar(idDelPatogeno: Int): Patogeno {
        return this.recuperar(idDelPatogeno.toLong())
    }

    override fun actualizar(unPatogeno: Patogeno) {

        val session = TransactionRunner.currentSession

        val hql = ("select id cantidadDeEspecies from patogeno " + "where id = :unId ")

        val query = session.createQuery(hql, Patogeno::class.java)
        query.setParameter("unId", unPatogeno.id)
    }

    override fun crear(patogeno: Patogeno): Int {
        this.guardar(patogeno)
        return (this.recuperar(patogeno.id).id!!.toInt())
    }
}
