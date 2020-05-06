package ar.edu.unq.eperdemic.services.runner

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyPersona
import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector
import ar.edu.unq.eperdemic.persistencia.dao.DataDAO
import ar.edu.unq.eperdemic.persistencia.dao.VectorDAO
import ar.edu.unq.eperdemic.services.VectorService
import ar.edu.unq.eperdemic.services.runner.TransactionRunner.runTrx

class VectorServiceImp (
    private val vectorDAO: VectorDAO,
    private val dataDAO: DataDAO
    ) : VectorService {

    override fun contagiar(vectorInfectado: Vector, vectores: List<Vector>) {
        runTrx {
            var vectoresActualizar :  MutableList<Vector> = ArrayList()
            for (v: Vector in vectores){
                if(vectorInfectado.estrategiaDeContagio!!.darContagio(vectorInfectado , v)){
                    vectoresActualizar.add(v)
                }
            }
            for(v : Vector in vectoresActualizar){
                vectorDAO.actualizar(v)
            }
        }

    }

    override fun infectar(vector: Vector, especie: Especie) {
        TODO("Not yet implemented")
    }

    override fun enfermedades(vectorId: Int): List<Especie> {
        TODO("Not yet implemented")
    }

    override fun crearVector(ubicacion: Ubicacion, estrategia: StrategyPersona): Vector {
        return runTrx {
            var vector = Vector(ubicacion, estrategia)
            vectorDAO.crear(vector) }
    }

    override fun recuperarVector(vectorId: Int): Vector {
        return runTrx { vectorDAO.recuperar(vectorId) }
    }

    override fun borrarVector(vectorId: Int) {
        runTrx { vectorDAO.eliminar(vectorId) }
    }

    public fun clear() {
        runTrx { dataDAO.clear() }
    }
}