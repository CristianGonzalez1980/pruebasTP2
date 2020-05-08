package ar.edu.unq.eperdemic.persistencia.dao.hibernate

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyAnimal
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyHumano
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyInsecto
import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector
import ar.edu.unq.eperdemic.persistencia.dao.PatogenoDAO
import ar.edu.unq.eperdemic.persistencia.dao.VectorDAO
import ar.edu.unq.eperdemic.services.runner.TransactionRunner


open class HibernateVectorDAO : HibernateDAO<Vector>(Vector::class.java), VectorDAO {

    override fun recuperar(idDelVector: Int): Vector {
        val vectorRecuperado = (this.recuperar(idDelVector.toLong()))
        if (vectorRecuperado.tipo == "Humano") {
            vectorRecuperado.estrategiaDeContagio = StrategyHumano()
        }
        if (vectorRecuperado.tipo == "Animal") {
            vectorRecuperado.estrategiaDeContagio = StrategyAnimal()
        }
        if (vectorRecuperado.tipo == "Insecto") {
            vectorRecuperado.estrategiaDeContagio = StrategyInsecto()
        }
        vectorRecuperado.enfermedades = this.recuperarEnfermedades(idDelVector)
        return vectorRecuperado
    }

    override fun recuperarEnfermedades(idDelVector: Int): MutableList<Especie> {
        val session = TransactionRunner.currentSession

        val hql = ("select enfermedades_nombre from vector_especie where vector_id = :idVector")

        val query =  session.createQuery(hql, Especie::class.java)

        query.setParameter("idVector" , idDelVector)

        return query.resultList
    }

    override fun crearVector(vector: Vector): Vector {
        this.guardar(vector)
        return (this.recuperar(vector.id))
    }

    override fun eliminar(idDelVector: Int) {
        val session = TransactionRunner.currentSession

        val hql = ("delete from vector where id = :idDelVector")

        val query =  session.createQuery(hql, Vector::class.java)

        query.setParameter("idDelVector" , idDelVector)

        query.executeUpdate()
    }

    override fun actualizar(vector: Vector): Vector {
        val session = TransactionRunner.currentSession
        session.saveOrUpdate(vector)
        return this.recuperar(vector.id)
    }
}