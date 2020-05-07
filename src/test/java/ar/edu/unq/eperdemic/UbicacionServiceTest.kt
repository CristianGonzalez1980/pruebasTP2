package ar.edu.unq.eperdemic

import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyHumano
import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector
import ar.edu.unq.eperdemic.persistencia.dao.DataDAO
import ar.edu.unq.eperdemic.persistencia.dao.UbicacionDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateDataDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateUbicacionDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateVectorDAO
import ar.edu.unq.eperdemic.services.UbicacionService
import ar.edu.unq.eperdemic.services.VectorService
import ar.edu.unq.eperdemic.services.runner.UbicacionServiceImp
import ar.edu.unq.eperdemic.services.runner.VectorServiceImp
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UbicacionServiceTest {

    lateinit var service: UbicacionService
    lateinit var serviceVec : VectorService
    lateinit var ubi1: Ubicacion
    lateinit var ubi2: Ubicacion
    lateinit var ubi3: Ubicacion
    lateinit var vectorA: Vector
    lateinit var vectorB: Vector
    lateinit var vectorC: Vector
    lateinit var vectores: MutableList<Vector>
    lateinit var dao: HibernateUbicacionDAO
    lateinit var estrategia  :StrategyHumano

    @Before
    fun crearModelo() {
        this.service = UbicacionServiceImp(HibernateUbicacionDAO(),
                HibernateDataDAO() , HibernateVectorDAO())
        this.serviceVec = VectorServiceImp(HibernateVectorDAO() , HibernateDataDAO())

        //ubi1 = service.crearUbicacion("Bernal" )
        estrategia = StrategyHumano()
       ubi3 = service.crearUbicacion("La Plata")
        ubi2 = service.crearUbicacion("Quilmes")
        vectorA = Vector(ubi3 ,estrategia)
        vectorB = Vector(ubi2 , estrategia)
        vectorC = Vector(ubi2 , estrategia)
        vectorA = serviceVec.crearVector(vectorA)
        service.actualizar(ubi3)
        vectorB = serviceVec.crearVector(vectorB)
        vectorC = serviceVec.crearVector(vectorC)
        vectorA = serviceVec.recuperarVector(vectorA.id!!.toInt())
    }

    @Test
    fun recuperarId(){

        Assert.assertEquals(1 , service.recuperar("La Plata").vectores.size)
    }

    @Test
    fun cambioDeUbicacion() {
        Assert.assertEquals("La Plata" , vectorA.location!!.nombreDeLaUbicacion)
        service.mover(vectorA.id!!.toInt() , "Quilmes")

        var vectorARecuperado  = serviceVec.recuperarVector(vectorA.id!!.toInt())
        Assert.assertEquals("Quilmes" , vectorARecuperado.location!!.nombreDeLaUbicacion)
    }



    @After
    fun cleanup() {
        //Destroy cierra la session factory y fuerza a que, la proxima vez, una nueva tenga
        //que ser creada.
        service.clear()
    }
}