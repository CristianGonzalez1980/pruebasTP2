package ar.edu.unq.eperdemic.utils

import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector
import ar.edu.unq.eperdemic.persistencia.dao.UbicacionDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateUbicacionDAO
import ar.edu.unq.eperdemic.services.runner.TransactionRunner.runTrx
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UbicacionDaoTest {

    private val dao: UbicacionDAO = HibernateUbicacionDAO()
    lateinit var ubicacion1: Ubicacion
    lateinit var ubicacion2: Ubicacion
    lateinit var ubicacion3: Ubicacion
    lateinit var ubiGuardada: Ubicacion
    lateinit var vectorA: Vector
    lateinit var vectorB: Vector
    lateinit var vectorC: Vector
    lateinit var vectorD: Vector

    @Before
    fun crearModelo(){
        ubicacion1 = Ubicacion("Bernal")
        ubicacion2 = Ubicacion("Quilmes")
        vectorA = Vector()
        vectorB = Vector()
        vectorC = Vector()
        vectorD = Vector()
        ubicacion3 = Ubicacion("La Plata", mutableSetOf(vectorC, vectorD))
    }

    @Test
    fun chequeoDeGuardarUbicaciones() {
        var ubi: Ubicacion = runTrx{dao.crear(ubicacion1)}
        var ubiGuardada: Ubicacion = runTrx{dao.recuperar(ubicacion1.nombreDeLaUbicacion!!)}
        Assert.assertEquals(ubi.nombreDeLaUbicacion, ubiGuardada.nombreDeLaUbicacion)
    }

    @Test
    fun seAsientanVectoresEnLaUbicacion() {
        ubicacion2.alojarVector(vectorA)
        ubicacion2.alojarVector(vectorB)
        runTrx { dao.actualizar(ubicacion2)}
        var ubiPersistida: Ubicacion = runTrx{dao.recuperar(ubicacion2.nombreDeLaUbicacion!!)}
        Assert.assertEquals(2, ubiPersistida.vectores.size)

    }

    @Test
    fun moverDeUbicacionVectores() {
        //ve
    }
}