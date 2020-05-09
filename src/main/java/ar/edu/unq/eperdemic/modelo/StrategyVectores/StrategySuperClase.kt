package ar.edu.unq.eperdemic.modelo.StrategyVectores

import ar.edu.unq.eperdemic.modelo.Especie
import java.util.*
import ar.edu.unq.eperdemic.modelo.Vector


open class StrategySuperClase() {

    //  abstract fun poneEnRiesgoA(vectorRecibido: Vector) : Boolean
    // abstract fun tipo(): String //ya esta preguntando por el ENUM
    open fun darContagio(vectorInfectado: Vector, vectorAContagiar: Vector): MutableList<Especie> {
        val enfermedadesContagiadas = mutableListOf<Especie>()
        val enfermedades: MutableSet<Especie> = vectorInfectado.enfermedades
        for (e: Especie in enfermedades) {
            val factorContagio = e.owner?.capacidadContagio
            val porcentajeDeContagioExitoso = 5 + factorContagio!!
            if ((porcentajeDeContagioExitoso > 70) and (!vectorAContagiar.enfermedades.contains(e))) {
                vectorInfectado.infectar(vectorAContagiar, e)
                enfermedadesContagiadas.add(e)
            }
        }
        return enfermedadesContagiadas
    }
}