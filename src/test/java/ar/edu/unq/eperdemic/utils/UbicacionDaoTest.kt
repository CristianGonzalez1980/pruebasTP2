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


   /* @Before
    fun crearModelo() {
        val ubicacionA = Ubicacion("La Plata", mutableSetOf())
        val ubicacionB = Ubicacion("La Plata", mutableSetOf())
        val ubicacionC = Ubicacion("La Plata")
        val ubicacionD = Ubicacion("La Plata")
        ubicacion1 = dao.crear(ubicacionA)
        ubicacion2 = dao.crear(ubicacionB)
        ubicacion3 = dao.crear(ubicacionC)
        ubicacion4 = dao.crear(ubicacionD)
        vectorA = Vector()
        vectorB = Vector()
        vectorC = Vector()
        vectorD = Vector()
        vectorE = Vector()
    }

    @Test
    fun chequeoDeGuardarUbicaciones() {
        val ubi: Ubicacion = runTrx { dao.crear(ubicacion1) }
        val ubiGuardada: Ubicacion = runTrx { dao.recuperar(ubicacion1.nombreDeLaUbicacion!!) }
        Assert.assertEquals(ubi.nombreDeLaUbicacion, ubiGuardada.nombreDeLaUbicacion)
    }

    @Test
    fun seAsientanVectoresEnLaUbicacion() {
        ubicacion2.alojarVector(vectorA)
        ubicacion2.alojarVector(vectorB)
        runTrx { dao.actualizar(ubicacion2) }
        val ubiPersistida: Ubicacion = runTrx { dao.recuperar(ubicacion2.nombreDeLaUbicacion!!) }
        Assert.assertEquals(2, ubiPersistida.vectores.size)

    }
*/
    /*@Test
    fun moverDeUbicacionVectores() {

        //ve

        val laPlata: Ubicacion = runTrx {
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

    }*/
}