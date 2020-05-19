package ar.edu.unq.eperdemic.persistencia.dao.hibernate

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector
import ar.edu.unq.eperdemic.persistencia.dao.PatogenoDAO
import ar.edu.unq.eperdemic.persistencia.dao.VectorDAO
import ar.edu.unq.eperdemic.services.runner.TransactionRunner


open class HibernateVectorDAO : HibernateDAO<Vector>(Vector::class.java), VectorDAO {

    override fun recuperar(idDelVector: Int): Vector {
        val vector = this.recuperar(idDelVector.toLong())
        vector.initEstrategia()
        return vector
    }

    override fun recuperarEnfermedades(idDelVector: Int): MutableSet<Especie> {
        val session = TransactionRunner.currentSession
        val hql = """
            select especie
            from especie e join e.vectores v
        """
        val query = session.createQuery(hql, Especie::class.java)
     //   query.setParameter("idVector", idDelVector)
        return query.resultList.toMutableSet()
    }

    override fun crearVector(vector: Vector): Vector {
        this.guardar(vector)
        return (this.recuperar(vector.id))
    }

/*    override fun agregarEnfermedad(vectorId: Int, especieId: Int) {
        val session = TransactionRunner.currentSession
        val hql = ("insert into vector_especie(:idDelVector, :idDeLaEspecie)")
        val query = session.createQuery(hql)
        query.setParameter("idDelVector", vectorId)
        query.setParameter("idDeLaEspecie", especieId)
        query.executeUpdate()
    }*/

    override fun eliminar(idDelVector: Int) {
        val vector =this.recuperar(idDelVector)
        val session = TransactionRunner.currentSession
        vector.location!!.desAlojarVector(vector)
        vector.enfermedades.map { it.vectores.remove(vector)}
        session.delete(vector)
    }

/*    override fun eliminar(idDelVector: Int) {
        val session = TransactionRunner.currentSession
        val hql = ("delete from vector where id = :idDelVector")
        val query = session.createQuery(hql, Vector::class.java)
        query.setParameter("idDelVector", idDelVector)
        query.executeUpdate()
    }*/

    override fun actualizar(vector: Vector): Vector {
        val session = TransactionRunner.currentSession
        session.saveOrUpdate(vector)
        return this.recuperar(vector.id)
    }
}