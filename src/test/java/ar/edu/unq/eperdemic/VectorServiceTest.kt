package ar.edu.unq.eperdemic

import ar.edu.unq.eperdemic.dto.VectorFrontendDTO
import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyHumano
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyAnimal
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyInsecto
import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.*
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
    lateinit var vectorE: Vector
    lateinit var vectores: MutableList<Vector>
    lateinit var estrategia: StrategyHumano
    lateinit var estrategia1: StrategyAnimal
    lateinit var patogeno: Patogeno
    lateinit var mosquito: Especie
    lateinit var covid19: Especie
    lateinit var gripeAviar: Especie
    lateinit var ubicacion1: Ubicacion


    @Before
    fun crearModelo() {
        this.servicePatog = PatogenoServiceImp(HibernatePatogenoDAO(), HibernateEspecieDAO(), HibernateDataDAO())
        this.serviceVect = VectorServiceImp(HibernateVectorDAO(), HibernateDataDAO(), HibernateEspecieDAO())
        this.serviceUbic = UbicacionServiceImp(HibernateUbicacionDAO(), HibernateDataDAO(), HibernateVectorDAO(), VectorServiceImp(HibernateVectorDAO(), HibernateDataDAO(), HibernateEspecieDAO()))
        serviceVect.clear()
        estrategia = StrategyHumano()
        estrategia1 = StrategyAnimal()
        val id = servicePatog.crearPatogeno(Patogeno("Virus", 80, 80, 80))
        patogeno = servicePatog.recuperarPatogeno(id)
        mosquito = servicePatog.agregarEspecie(patogeno.id!!.toInt(), "Dengue", "Argentina", 15)
        covid19 = servicePatog.agregarEspecie(patogeno.id!!.toInt(), "Coronavirus", "China", 55)
        gripeAviar = servicePatog.agregarEspecie(patogeno.id!!.toInt(), "H5N1", "EEUU", 40)
        ubicacion1 = serviceUbic.crearUbicacion("Argentina")
        vectorA = serviceVect.crearVector(Vector(ubicacion1, VectorFrontendDTO.TipoDeVector.Persona))
        vectorB = serviceVect.crearVector(Vector(ubicacion1, VectorFrontendDTO.TipoDeVector.Persona))
        vectorC = serviceVect.crearVector(Vector(ubicacion1, VectorFrontendDTO.TipoDeVector.Animal))
        vectorD = serviceVect.crearVector(Vector(ubicacion1, VectorFrontendDTO.TipoDeVector.Animal))
        vectorE = serviceVect.crearVector(Vector(ubicacion1, VectorFrontendDTO.TipoDeVector.Animal))
        serviceVect.infectar(vectorA, mosquito)
        serviceVect.infectar(vectorC, mosquito)
        serviceVect.infectar(vectorE, mosquito)
        serviceVect.infectar(vectorE, covid19)
        serviceVect.infectar(vectorE, gripeAviar)

        vectores = ArrayList()
    }

    @Test
    fun contagioExitosoPersonaAPersona() {
        vectores.add(vectorB)
        Assert.assertTrue(vectorB.enfermedades.isEmpty())
        serviceVect.contagiar(vectorA, vectores)
        val vectorBRecuperadoPost = serviceVect.recuperarVector(vectorB.id!!.toInt())
        Assert.assertEquals(1, vectorBRecuperadoPost.enfermedades.size)
    }

    @Test
    fun contagioNoExitosoPersonaAAnimal() {
        vectores.add(vectorD)
        Assert.assertTrue(vectorD.enfermedades.isEmpty())
        serviceVect.contagiar(vectorC, vectores)
        val vectorDRecuperadoPost = serviceVect.recuperarVector(vectorD.id!!.toInt())
        Assert.assertEquals(0, vectorDRecuperadoPost.enfermedades.size)
    }

    @Test
    fun infectarVectorSano() {
        serviceVect.infectar(vectorD, covid19)
        val vectorDRecuperadoPost = serviceVect.recuperarVector(vectorD.id!!.toInt())
        Assert.assertEquals(1,vectorDRecuperadoPost.enfermedades.size )

    }
    @Test
    fun infectarCon2EspecieAVectorConDengue() {
        serviceVect.infectar(vectorA, covid19)
        serviceVect.infectar(vectorA, gripeAviar)
        val vectorDRecuperadoPost = serviceVect.recuperarVector(vectorA.id!!.toInt())
        Assert.assertEquals(3,vectorDRecuperadoPost.enfermedades.size )

    }

    @Test
    fun enfermedadesDelVector(){
        val vectorERecuperadoPost = serviceVect.recuperarVector(vectorE.id!!.toInt())
        Assert.assertEquals(3,vectorERecuperadoPost.enfermedades.size)
    }

    @After
    fun cleanup() {
        //Destroy cierra la session factory y fuerza a que, la proxima vez, una nueva tenga
        //que ser creada.
       serviceVect.clear()
    }
}