package ar.edu.unq.eperdemic.modelo.StrategyVectores

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Vector


class StrategyPersona : StrategySuperClase() {
    fun poneEnRiesgo (vectorRecibido: Vector): Boolean {
        return (vectorRecibido.estrategiaDeContagio.tipo() == "Humano" ) || (vectorRecibido.estrategiaDeContagio.tipo() == "Insecto"))
    }

    fun tipo(): String {
        return "Humano"
    }

    fun darContagio(vectorInfectado: Vector, vectorAContagiar: Vector) : Boolean {
        var resultado = false
        if (this.poneEnRiesgo(vectorAContagiar)) {
            var enfermedades: MutableSet<Especie> = vectorInfectado.enfermedades
            for (e: Especie in enfermedades) {
                var factorContagio = e.owner?.capacidadContagioPersona
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