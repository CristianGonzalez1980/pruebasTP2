package ar.edu.unq.eperdemic.services

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategySuperClase
import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector

interface VectorService {

    fun contagiar(vectorInfectado: Vector, vectores: List<Vector>)
    fun infectar(vector: Vector, especie: Especie)
    fun enfermedades(vectorId: Int): MutableSet<Especie>

    /* Operaciones CRUD */
    fun crearVector(vector: Vector): Vector
    fun recuperarVector(vectorId: Int): Vector
    fun borrarVector(vectorId: Int)
    fun actualizar(vector: Vector): Vector
}