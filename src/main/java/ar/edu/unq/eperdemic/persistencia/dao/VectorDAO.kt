package ar.edu.unq.eperdemic.persistencia.dao


import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyPersona
import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector


interface VectorDAO {
    fun crear(ubicacion: Ubicacion, estrategia: StrategyPersona): Vector
    fun recuperar(idDelVector: Int): Vector
    fun eliminar(idDelVector: Int)

    fun actualizar(vector : Vector)

}