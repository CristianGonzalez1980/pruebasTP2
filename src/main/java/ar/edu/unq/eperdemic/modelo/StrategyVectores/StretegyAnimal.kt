package ar.edu.unq.eperdemic.modelo.StrategyVectores

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Vector


class StrategyAnimal : StrategySuperClase() {
    override fun poneEnRiesgoA(vectorRecibido: Vector): Boolean {
        return ((vectorRecibido.estrategiaDeContagio!!.tipo() == "Humano") || (vectorRecibido.estrategiaDeContagio!!.tipo() == "Insecto"))
    }

    override fun tipo(): String {
        return "Animal"
    }

    override fun darContagio(vectorInfectado: Vector, vectorAContagiar: Vector): Boolean {
        var resultado = false
        if (this.poneEnRiesgoA(vectorAContagiar)) {
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