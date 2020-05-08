package ar.edu.unq.eperdemic.persistencia.dao


import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyHumano
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategySuperClase
import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector


interface VectorDAO {
    fun crearVector(vector: Vector): Vector
    fun recuperar(idDelVector: Int): Vector
    fun recuperarEnfermedades(idDelVector: Int): MutableList<Especie>
    fun eliminar(idDelVector: Int)
    //fun infectar(vector: Vector)
    fun actualizar(vector : Vector): Vector
}