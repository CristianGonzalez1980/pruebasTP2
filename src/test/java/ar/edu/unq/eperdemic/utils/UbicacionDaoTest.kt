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
    lateinit var ubicacion4: Ubicacion
    lateinit var ubiGuardada: Ubicacion
    lateinit var vectorA: Vector
    lateinit var vectorB: Vector
    lateinit var vectorC: Vector
    lateinit var vectorD: Vector
    lateinit var vectorE: Vector


    @Before
    fun crearModelo() {
        ubicacion1 = Ubicacion("Bernal")
        ubicacion2 = Ubicacion("Quilmes")
        vectorA = Vector()
        vectorB = Vector()
        vectorC = Vector()
        vectorD = Vector()
        vectorE = Vector()
        ubicacion3 = Ubicacion("La Plata")
        ubicacion4 = Ubicacion("CityBell")
    }

    @Test
    fun chequeoDeGuardarUbicaciones() {
        var ubi: Ubicacion = runTrx { dao.crear(ubicacion1) }
        var ubiGuardada: Ubicacion = runTrx { dao.recuperar(ubicacion1.nombreDeLaUbicacion!!) }
        Assert.assertEquals(ubi.nombreDeLaUbicacion, ubiGuardada.nombreDeLaUbicacion)
    }

    @Test
    fun seAsientanVectoresEnLaUbicacion() {
        ubicacion2.alojarVector(vectorA)
        ubicacion2.alojarVector(vectorB)
        runTrx { dao.actualizar(ubicacion2) }
        var ubiPersistida: Ubicacion = runTrx { dao.recuperar(ubicacion2.nombreDeLaUbicacion!!) }
        Assert.assertEquals(2, ubiPersistida.vectores.size)

    }

    @Test
    fun moverDeUbicacionVectores() {
        var laPlata: Ubicacion = runTrx {
            dao.crear(ubicacion3)
            ubicacion3.alojarVector(vectorC)
            ubicacion3.alojarVector(vectorD)
            dao.actualizar(ubicacion3)
            dao.recuperar(ubicacion3.nombreDeLaUbicacion!!)
        }
        Assert.assertEquals(2, laPlata.vectores.size)
        var laPlataR: Ubicacion = runTrx {
            laPlata.desAlojarVector(vectorC)
            laPlata.desAlojarVector(vectorD)
            dao.actualizar(laPlata)
            dao.recuperar(laPlata.nombreDeLaUbicacion!!)
        }
        Assert.assertEquals(0, laPlata.vectores.size)
        var cityBell: Ubicacion = runTrx {
            dao.crear(ubicacion4)
            ubicacion4.alojarVector(vectorE)
            dao.actualizar(ubicacion4)
            dao.recuperar(ubicacion4.nombreDeLaUbicacion!!)
        }
        Assert.assertEquals(1, cityBell.vectores.size)
        var cityBellR: Ubicacion = runTrx {
            cityBell.alojarVector(vectorC)
            cityBell.alojarVector(vectorD)
            dao.actualizar(cityBell)
            dao.recuperar(cityBell.nombreDeLaUbicacion!!)
        }
        Assert.assertEquals(3, cityBellR.vectores.size)
    }
}