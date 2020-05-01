package ar.edu.unq.eperdemic.services.runner

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.persistencia.dao.DataDAO
import ar.edu.unq.eperdemic.persistencia.dao.PatogenoDAO
import ar.edu.unq.eperdemic.services.PatogenoService
import ar.edu.unq.eperdemic.services.runner.TransactionRunner.runTrx

class PatogenoServiceImp(
        private val patogenoDAO: PatogenoDAO,
        private val dataDAO: DataDAO
) : PatogenoService {
    override fun recuperarEspecie(id: Int): Especie {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun esPandemia(especieId: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cantidadDeInfectados(especieId: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun agregarEspecie(id: Int, nombreEspecie: String, paisDeOrigen: String): Especie {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun crearPatogeno(patogeno: Patogeno): Int{

        return runTrx { patogenoDAO.crear(patogeno) }
    }
    override fun recuperarPatogeno(id: Int): Patogeno {

        return runTrx { patogenoDAO.recuperar(id) }
    }
    override fun recuperarATodosLosPatogenos(): Collection<Patogeno>{

        return runTrx { patogenoDAO.recuperarATodos}
    }

    fun clear() {
        runTrx { dataDAO.clear() }
    }
}