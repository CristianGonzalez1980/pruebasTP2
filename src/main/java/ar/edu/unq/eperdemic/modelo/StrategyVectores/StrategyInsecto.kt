package ar.edu.unq.eperdemic.modelo.StrategyVectores

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Vector


class StrategyInsecto : StrategySuperClase() {
    fun poneEnRiesgoA(vectorRecibido: Vector): Boolean {
        return ((vectorRecibido.tipo!!.name /*estrategiaDeContagio!!.tipo()*/ == "Animal") || (vectorRecibido.tipo!!.name/*.estrategiaDeContagio!!.tipo()*/ == "Persona"))
    }

/*    override fun tipo(): String { ya esta preguntado por el ENUM
        return "Insecto"
    }*/

    override fun darContagio(vectorInfectado: Vector, vectorAContagiar: Vector) : MutableList<Especie> {
        return if (this.poneEnRiesgoA(vectorAContagiar)) {
            super.darContagio(vectorInfectado, vectorAContagiar)
        } else mutableListOf()

/*

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
        return enfermedadesContagiadas*/
    }
}