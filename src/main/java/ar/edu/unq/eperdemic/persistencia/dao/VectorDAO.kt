package ar.edu.unq.eperdemic.persistencia.dao


import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyHumano
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategySuperClase
import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector


interface VectorDAO {
    fun crearVector(ubicacion: Ubicacion, estrategia: StrategySuperClase): Vector
    fun recuperar(idDelVector: Int): Vector
    fun eliminar(idDelVector: Int)
    fun infectar(vector: Vector)
    fun actualizar(vector : Vector)

}