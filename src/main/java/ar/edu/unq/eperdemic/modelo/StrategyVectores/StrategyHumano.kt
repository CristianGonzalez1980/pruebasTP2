package ar.edu.unq.eperdemic.modelo.StrategyVectores

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Vector


class StrategyHumano : StrategySuperClase() {
    override fun poneEnRiesgoA(vectorRecibido: Vector): Boolean {
        return ((vectorRecibido.estrategiaDeContagio!!.tipo() == "Persona") || (vectorRecibido.estrategiaDeContagio!!.tipo() == "Insecto"))
    }

    override fun tipo(): String {
        return "Persona"
    }

    override fun darContagio(vectorInfectado: Vector, vectorAContagiar: Vector) : MutableList<Especie> {
        val enfermedadesContagiadas = mutableListOf<Especie>()
        if (this.poneEnRiesgoA(vectorAContagiar)) {
            val enfermedades: MutableSet<Especie> = vectorInfectado.enfermedades
            for (e: Especie in enfermedades) {
                val factorContagio = e.owner?.capacidadContagio
                val porcentajeDeContagioExitoso = 5 + factorContagio!!
                if ((porcentajeDeContagioExitoso > 70) and (!vectorAContagiar.enfermedades.contains(e))) {
                    vectorInfectado.infectar(vectorAContagiar, e)
                    enfermedadesContagiadas.add(e)
                }
            }
        }
        return enfermedadesContagiadas
    }
}