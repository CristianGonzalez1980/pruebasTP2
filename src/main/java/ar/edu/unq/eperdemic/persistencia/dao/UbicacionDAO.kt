package ar.edu.unq.eperdemic.persistencia.dao

import ar.edu.unq.eperdemic.modelo.Ubicacion

interface UbicacionDAO {

    fun crear(ubicacion: Ubicacion) : Ubicacion
    fun recuperar(nombreDeLaUbicacion: String) : Ubicacion
    fun actualizar(ubicacion: Ubicacion)
   // fun desalojar( vectorId: Int , nombreUbicacion: String)
    //fun mover ( vectorId: Int , nombreUbicacion: String)
}