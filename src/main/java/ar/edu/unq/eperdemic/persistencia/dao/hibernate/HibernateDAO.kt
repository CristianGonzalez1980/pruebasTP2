package ar.edu.unq.eperdemic.persistencia.dao.hibernate

import ar.edu.unq.eperdemic.services.runner.TransactionRunner
import java.io.Serializable


open class HibernateDAO<T>(private val entityType: Class<T>) {


    fun guardar(item: T) {
        val session = TransactionRunner.currentSession
        session.save(item)
    }

    fun recuperar(id: Long?): T {
        val session = TransactionRunner.currentSession
        return session.get(entityType, id)
    }
}