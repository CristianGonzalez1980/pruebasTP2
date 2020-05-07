package ar.edu.unq.eperdemic

import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector
import ar.edu.unq.eperdemic.persistencia.dao.DataDAO
import ar.edu.unq.eperdemic.persistencia.dao.UbicacionDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateDataDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateUbicacionDAO
import ar.edu.unq.eperdemic.services.UbicacionService
import ar.edu.unq.eperdemic.services.runner.UbicacionServiceImp
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UbicacionServiceTest {

    lateinit var service: UbicacionService
    lateinit var ubi1: Ubicacion
    lateinit var ubi2: Ubicacion
    lateinit var ubi3: Ubicacion
    lateinit var vectorA: Vector
    lateinit var vectorB: Vector
    lateinit var vectorC: Vector
    lateinit var vectores: MutableList<Vector>
    lateinit var dao: HibernateUbicacionDAO

    @Before
    fun crearModelo() {
        this.service = UbicacionServiceImp(HibernateUbicacionDAO(),
                HibernateDataDAO())

        ubi1 = service.crearUbicacion("Bernal")
        ubi3 = service.crearUbicacion("a Plata")
        vectorA = Vector()
        vectorB = Vector()
        vectorC = Vector()
        ubi2 = service.crearUbicacion("Quilmes")
    }
    @Test
    fun guardarUbicacion() {
        var ciudad: String = ubi1.nombreDeLaUbicacion!!
        var ubicacionCreada = service.crearUbicacion(ciudad)
        Assert.assertEquals(ciudad,ubicacionCreada.nombreDeLaUbicacion)
    }

    /*
    @After
    fun cleanup() {
        //Destroy cierra la session factory y fuerza a que, la proxima vez, una nueva tenga
        //que ser creada.
        UbicacionServiceImp.clear()
    }*/
}