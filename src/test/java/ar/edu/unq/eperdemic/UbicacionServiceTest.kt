package ar.edu.unq.eperdemic

import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.persistencia.dao.DataDAO
import ar.edu.unq.eperdemic.persistencia.dao.UbicacionDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateDataDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateUbicacionDAO
import ar.edu.unq.eperdemic.services.UbicacionService
import ar.edu.unq.eperdemic.services.runner.UbicacionServiceImp
import org.junit.After
import org.junit.Before
import org.junit.Test

class UbicacionServiceTest {

    lateinit var service: UbicacionService
    lateinit var ubi1: Ubicacion
    lateinit var ubi2: Ubicacion
    lateinit var ubi3: Ubicacion

    @Before
    fun crearModelo() {
        this.service = UbicacionServiceImp(HibernateUbicacionDAO(),
                HibernateDataDAO())

        ubi1 = Ubicacion("Bernal")
        ubi2 = Ubicacion("Quilmes")
        ubi3 = Ubicacion("La Plata")
    }
    @Test
    fun guardarUbicacion() {
        service.crearUbicacion("Bernal")
    }
    /*
    @After
    fun cleanup() {
        //Destroy cierra la session factory y fuerza a que, la proxima vez, una nueva tenga
        //que ser creada.
        UbicacionServiceImp.clear()
    }*/
}