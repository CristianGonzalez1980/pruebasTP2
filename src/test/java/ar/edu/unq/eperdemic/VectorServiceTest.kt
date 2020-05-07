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
    lateinit var vectores: MutableList<Vector>
    lateinit var especie1 : Especie
    lateinit var especie2 : Especie
    lateinit var especie3 : Especie
    lateinit var estrategia : StrategyHumano
    lateinit var patogeno : Patogeno



    @Before
    fun crearModelo() {
        this.servicePatog = PatogenoServiceImp(HibernatePatogenoDAO(), HibernateDataDAO())
        this.serviceVect = VectorServiceImp(HibernateVectorDAO(), HibernateDataDAO())
        this.serviceUbic = UbicacionServiceImp(HibernateUbicacionDAO(), HibernateDataDAO() , HibernateVectorDAO())
        estrategia = StrategyHumano()
        patogeno = Patogeno("Virus", 80, 80, 80)
        val id = servicePatog.crearPatogeno(patogeno)
        patogeno = servicePatog.recuperarPatogeno(id)
        especie1 = patogeno.agregarEspecie("Dengue", "Argentina", 15)
        val ubicacion1 = serviceUbic.crearUbicacion("Argentina")
        vectorA = Vector(ubicacion1, estrategia)
        vectorB = Vector(ubicacion1, estrategia)
        vectorA.enfermedades.add(especie1)
        vectorA = serviceVect.crearVector(vectorA)
        print("El id del vectorA es: " + vectorA.id.toString())
        vectorC = serviceVect.crearVector(vectorB)
        print("El id del vectorB es: " + vectorB.id.toString())
        vectores = ArrayList()
        vectores.add(vectorB)
    }



    @Test
    fun contagiarAOtroVector() {
        Assert.assertTrue(vectorB.enfermedades.isEmpty())
        serviceVect.contagiar(vectorA, vectores)
        val vectorRecuperadoPost = serviceVect.recuperarVector(vectorB.id!!.toInt())
        Assert.assertEquals(1,vectorRecuperadoPost.enfermedades.size)
    }

    @After
    fun cleanup() {
        //Destroy cierra la session factory y fuerza a que, la proxima vez, una nueva tenga
        //que ser creada.
        serviceVect.clear()
    }

}