package ar.edu.unq.eperdemic.services.runner

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyHumano
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategySuperClase
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
            for (vectorAInfectar: Vector in vectores){
                if(vectorInfectado.estrategiaDeContagio!!.darContagio(vectorInfectado , vectorAInfectar)){
                    for (e:Especie in vectorInfectado.enfermedades){
                        this.infectar(vectorAInfectar, e)
                    }
                }
            }
        }

    }

    override fun infectar(vector: Vector, especie: Especie) {
        vector.enfermedades.add(especie)
        vectorDAO.actualizar(vector)
    }

    override fun enfermedades(vectorId: Int): MutableSet<Especie> {
        return runTrx {
            vectorDAO.recuperarEnfermedades(vectorId)
        }
    }

    override fun crearVector(vector: Vector): Vector {
        return runTrx {
            vectorDAO.crearVector(vector) }
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