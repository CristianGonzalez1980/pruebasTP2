package ar.edu.unq.eperdemic.persistencia.dao


import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyPersona
import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector


interface VectorDAO {
    fun crearVector(ubicacion: Ubicacion, estrategia: StrategyPersona): Vector
    fun recuperar(idDelVector: Int): Vector
    fun eliminar(idDelVector: Int)
    fun infectar(vectir: Vector)
    fun actualizar(vector : Vector)

}