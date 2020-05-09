package ar.edu.unq.eperdemic.modelo.StrategyVectores

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Vector


class StrategyInsecto : StrategySuperClase() {
    override fun poneEnRiesgoA(vectorRecibido: Vector): Boolean {
        return ((vectorRecibido.estrategiaDeContagio!!.tipo() == "Animal") || (vectorRecibido.estrategiaDeContagio!!.tipo() == "Persona"))
    }

    override fun tipo(): String {
        return "Insecto"
    }

    override fun darContagio(vectorInfectado: Vector, vectorAContagiar: Vector): Boolean {
        var resultado = false
        if (this.poneEnRiesgoA(vectorAContagiar)) {
            var enfermedades: MutableSet<Especie> = vectorInfectado.enfermedades
            for (e: Especie in enfermedades) {
                var factorContagio = e.owner?.capacidadContagio
                var porcentajeDeContagioExitoso = 5 + factorContagio!!
                if (porcentajeDeContagioExitoso > 70) {
                    vectorInfectado.infectar(vectorAContagiar, e)
                    resultado = resultado || true
                }
            }
        }
        return resultado
    }
}