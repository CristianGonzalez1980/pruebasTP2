package ar.edu.unq.eperdemic.modelo.StrategyVectores

import java.util.*
import ar.edu.unq.eperdemic.modelo.Vector



abstract class StrategySuperClase() {

    abstract fun poneEnRiesgoA(vectorRecibido: Vector) : Boolean
    abstract fun tipo(): String
    abstract fun darContagio(vectorInfectado: Vector, vectorAContagiar: Vector) : Boolean
}