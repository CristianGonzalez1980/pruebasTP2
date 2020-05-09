package ar.edu.unq.eperdemic.services.runner

import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector
import ar.edu.unq.eperdemic.persistencia.dao.DataDAO
import ar.edu.unq.eperdemic.persistencia.dao.UbicacionDAO
import ar.edu.unq.eperdemic.persistencia.dao.VectorDAO
import ar.edu.unq.eperdemic.services.UbicacionService
import ar.edu.unq.eperdemic.services.runner.TransactionRunner.runTrx

class UbicacionServiceImp(
        private val ubicacionDAO: UbicacionDAO,
        private val dataDAO: DataDAO,
        private val vectorDAO: VectorDAO,
        private val vectorServiceImp: VectorServiceImp

) : UbicacionService {

    override fun mover(vectorId: Int, nombreUbicacion: String) {
        runTrx {
            var vector = vectorDAO.recuperar(vectorId)
            var ubicacionNueva = ubicacionDAO.recuperar(nombreUbicacion)
            var ubicacionVieja = ubicacionNueva.alojarVector(vector)
            if (vector.estaInfectado()) {
        //        var vectoresAInfectar = vectorServiceImp.recuperarVectores(nombreUbicacion)
                vectorServiceImp.contagiar(vector, ubicacionNueva.vectores.toList())
            }
            ubicacionDAO.actualizar(ubicacionVieja)
            ubicacionDAO.actualizar(ubicacionNueva)
            vectorDAO.actualizar(vector)
        }
    }

    override fun actualizar(ubicacion: Ubicacion) {
        runTrx { ubicacionDAO.actualizar(ubicacion) }
    }

    override fun expandir(nombreUbicacion: String) {
        val ubicacion: Ubicacion = this.recuperar(nombreUbicacion)
        var vectores: MutableList<Vector> = ubicacion.vectores.toMutableList()
        var vectorInfectado: Vector? = vectores.find { it.estaInfectado() }
        if (vectorInfectado != null) {
            vectorServiceImp.contagiar(vectorInfectado, vectores)
        }
    }

    override fun crearUbicacion(nombre: String): Ubicacion {
        return runTrx {
            val ubicacion = Ubicacion(nombre)
            ubicacionDAO.crear(ubicacion)
        }
    }

    override public fun clear() {
        runTrx { dataDAO.clear() }
    }

    override fun recuperar(ubicacion: String): Ubicacion {
        return runTrx { ubicacionDAO.recuperar(ubicacion) }
    }
}