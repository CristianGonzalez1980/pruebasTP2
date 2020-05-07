package ar.edu.unq.eperdemic

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyHumano
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyAnimal
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyInsecto
import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateDataDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernatePatogenoDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateVectorDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateUbicacionDAO
import ar.edu.unq.eperdemic.services.PatogenoService
import ar.edu.unq.eperdemic.services.runner.PatogenoServiceImp
import ar.edu.unq.eperdemic.services.runner.UbicacionServiceImp
import ar.edu.unq.eperdemic.services.runner.VectorServiceImp
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class VectorServiceTest {

    lateinit var servicePatog: PatogenoServiceImp
    lateinit var serviceVect: VectorServiceImp
    lateinit var serviceUbic: UbicacionServiceImp
    lateinit var vectorA: Vector
    lateinit var vectorB: Vector
    lateinit var vectorC: Vector
    lateinit var vectorD: Vector
    lateinit var vectorARecuperado : Vector
    lateinit var vectorBRecuperado : Vector
    lateinit var vectorCRecuperado : Vector
    lateinit var vectorDRecuperado : Vector
    lateinit var vectores: MutableList<Vector>
    lateinit var especie1: Especie
    lateinit var especie2: Especie
    lateinit var especie3: Especie
    lateinit var estrategia: StrategyHumano
    lateinit var estrategia1: StrategyAnimal
    lateinit var patogeno: Patogeno



    @Before
    fun crearModelo() {
        this.servicePatog = PatogenoServiceImp(HibernatePatogenoDAO(), HibernateDataDAO())
        this.serviceVect = VectorServiceImp(HibernateVectorDAO(), HibernateDataDAO())
        this.serviceUbic = UbicacionServiceImp(HibernateUbicacionDAO(), HibernateDataDAO())
        estrategia = StrategyHumano()
        estrategia1 = StrategyAnimal()
        patogeno = Patogeno("Virus", 80, 80, 80)
        val id = servicePatog.crearPatogeno(patogeno)
        patogeno = servicePatog.recuperarPatogeno(id)
        especie1 = patogeno.agregarEspecie("Dengue", "Argentina", 15)
        val ubicacion1 = serviceUbic.crearUbicacion("Argentina")
        vectorA = Vector(ubicacion1, estrategia)
        vectorB = Vector(ubicacion1, estrategia)
        vectorC = Vector(ubicacion1, estrategia1)
        vectorD = Vector(ubicacion1, estrategia1)
        vectorA.enfermedades.add(especie1)
        vectorC.enfermedades.add(especie1)
        vectorA = serviceVect.crearVector(vectorA)
        vectorB = serviceVect.crearVector(vectorB)
        vectorC = serviceVect.crearVector(vectorC)
        vectorD = serviceVect.crearVector(vectorD)
        vectores = ArrayList()
        vectores.add(vectorB)
        vectores.add(vectorD)
    }



    @Test
    fun contagiarExitoso() {
        Assert.assertTrue(vectorB.enfermedades.isEmpty())
        vectorA.contagiar(vectorC, vectores)
        val vectorBRecuperadoPost = serviceVect.actualizar(vectorB)
        Assert.assertEquals(1,vectorBRecuperadoPost.enfermedades.size)
    }

    @Test
    fun contagioNoExitoso() {
        Assert.assertTrue(vectorB.enfermedades.isEmpty())
        vectorA.contagiar(vectorC, vectores)
        val vectorDRecuperadoPost = serviceVect.actualizar(vectorD)
        Assert.assertEquals(0,vectorDRecuperadoPost.enfermedades.size)
    }

    @After
    fun cleanup() {
        //Destroy cierra la session factory y fuerza a que, la proxima vez, una nueva tenga
        //que ser creada.
        serviceVect.clear()
    }

}