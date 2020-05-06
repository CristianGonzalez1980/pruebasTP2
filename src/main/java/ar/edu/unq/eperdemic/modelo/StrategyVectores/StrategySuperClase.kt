package ar.edu.unq.eperdemic.modelo.StrategyVectores


abstract class StrategySuperClase() {
    fun poneEnRiesgoA(vectorRecibido: Vector)
    fun tipo()
    fun darContagio(vectorInfectado: Vector, vectorAContagiar: Vector) : Boolean
}