package ar.edu.unq.eperdemic.modelo.StrategyVectores

import java.util.*
import ar.edu.unq.eperdemic.modelo.Vector



abstract class StrategySuperClase() {

    abstract fun poneEnRiesgoA(vectorRecibido: Vector)
    abstract fun tipo()
    abstract fun darContagio(vectorInfectado: Vector, vectorAContagiar: Vector) : Boolean
}