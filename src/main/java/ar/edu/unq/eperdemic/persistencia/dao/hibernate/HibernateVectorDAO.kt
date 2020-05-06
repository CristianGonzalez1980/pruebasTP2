package ar.edu.unq.eperdemic.persistencia.dao.hibernate

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.modelo.Vector
import ar.edu.unq.eperdemic.persistencia.dao.PatogenoDAO
import ar.edu.unq.eperdemic.persistencia.dao.VectorDAO
import ar.edu.unq.eperdemic.services.runner.TransactionRunner


open class HibernateVectorDAO : HibernateDAO<Vector>(Vector::class.java), VectorDAO {

    override fun recuperar(idDelVector: Int): Vector {
        return this.recuperar(idDelVector.toLong())
    }

    override fun crear(vector: Vector): Vector {
        this.guardar(vector)
        return (this.recuperar(vector.id))
    }

    override fun eliminar(idDelVector: Int) {
        val session = TransactionRunner.currentSession

        val hql = ("delete from vector where id = :idDelVector")

        var query =  session.createQuery(hql, Vector::class.java)

        query.setParameter("idDelVector" , idDelVector)
    }

    override fun actualizar(vector: Vector) {
        val session = TransactionRunner.currentSession

        val hql = ("update from vector where id = :idDelVector")

       var query =  session.createQuery(hql, Vector::class.java)

        query.setParameter("idDelVector" , vector.id)


    }






}