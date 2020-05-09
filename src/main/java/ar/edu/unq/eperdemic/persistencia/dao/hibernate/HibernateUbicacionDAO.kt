package ar.edu.unq.eperdemic.persistencia.dao.hibernate

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.persistencia.dao.UbicacionDAO
import ar.edu.unq.eperdemic.services.runner.TransactionRunner

open class HibernateUbicacionDAO : HibernateDAO<Ubicacion>(Ubicacion::class.java), UbicacionDAO {

    override fun crear(ubicacion: Ubicacion): Ubicacion {
        this.guardar(ubicacion)
        return this.recuperar(ubicacion.nombreDeLaUbicacion!!)
    }

    override fun recuperar(nombreDeLaUbicacion: String): Ubicacion {
        val session = TransactionRunner.currentSession
        val hql = ("from ubicacion " + " where nombreDeLaUbicacion = :id")
        val query = session.createQuery(hql, Ubicacion::class.java)
        query.setParameter("id", nombreDeLaUbicacion)
        return query.singleResult
    }

    override fun actualizar(ubicacion: Ubicacion) {
        val session = TransactionRunner.currentSession
        session.saveOrUpdate(ubicacion)
    }
}