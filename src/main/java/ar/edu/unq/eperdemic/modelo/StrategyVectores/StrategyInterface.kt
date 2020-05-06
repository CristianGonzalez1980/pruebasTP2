package ar.edu.unq.eperdemic.modelo.StrategyVectores

import ar.edu.unq.eperdemic.modelo.Vector

interface StrategyInterface {

    fun darContagio(vectorInfectado: Vector , vectorAContagiar: Vector ) : Boolean

}