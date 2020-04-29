package ar.edu.unq.eperdemic.persistencia.dao.hibernate

import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.persistencia.dao.PatogenoDAO
import ar.edu.unq.eperdemic.services.runner.TransactionRunner

open class HibernatePatogenoDAO : HibernateDAO<Patogeno>(Patogeno::class.java), PatogenoDAO {
    override fun recuperarATodos(): List<Patogeno> {

        val session = TransactionRunner.currentSession

        val hql = "from patogeno p " + "order by p.tipo asc"

        val query = session.createQuery(hql, Patogeno::class.java)
        return query.resultList


    }

    override fun recuperar(idDelPatogeno: Int): Patogeno {

        val session = TransactionRunner.currentSession
        return session.get(Patogeno::class.java,idDelPatogeno)

    }


    override fun actualizar(unPatogeno: Patogeno) {

            val session = TransactionRunner.currentSession

            val hql = ("select id cantidadDeEspecies from patogeno p " + "where p.id = :unId ")

            val query = session.createQuery(hql, Patogeno::class.java)
            query.setParameter("id",unPatogeno.id)

            query.setParameter("cantidadDeEspecies",unPatogeno.cantidadDeEspecies)

        }

        override fun crear(patogeno: Patogeno): Int {
            val session = TransactionRunner.currentSession
            session.save(patogeno)

           /* val hql = ("select id from patogeno" + "where tipo = :unTipo ")
            val query = session.createQuery(hql, Patogeno::class.java)
            query.setParameter("unTipo",patogeno.tipo)*/

            return (session.get(Patogeno::class.java,patogeno.tipo)).id!!


        }
    }
