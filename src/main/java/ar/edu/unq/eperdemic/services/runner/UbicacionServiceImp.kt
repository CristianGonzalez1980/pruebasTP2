package ar.edu.unq.eperdemic.services.runner

import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.persistencia.dao.DataDAO
import ar.edu.unq.eperdemic.persistencia.dao.UbicacionDAO
import ar.edu.unq.eperdemic.persistencia.dao.VectorDAO
import ar.edu.unq.eperdemic.services.UbicacionService

class UbicacionServiceImp(
        private val ubicacionDAO: UbicacionDAO,
        private val dataDAO: DataDAO
) : UbicacionService {
    override fun mover(vectorId: Int, nombreUbicacion: String) {
        TODO("Not yet implemented")
    }

    override fun expandir(nombreUbicacion: String) {
        TODO("Not yet implemented")
    }

    override fun crearUbicacion(nombreUbicacion: String): Ubicacion {
        TODO("Not yet implemented")
    }
}