package ar.edu.unq.eperdemic

import ar.edu.unq.eperdemic.modelo.Especie
import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyInterface
import ar.edu.unq.eperdemic.modelo.StrategyVectores.StrategyPersona
import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateDataDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateUbicacionDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateVectorDAO
import ar.edu.unq.eperdemic.services.runner.UbicacionServiceImp
import ar.edu.unq.eperdemic.services.runner.VectorServiceImp
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class VectorServiceTest {

    lateinit var service: VectorServiceImp
    lateinit var vectorA: Vector
    lateinit var vectorB: Vector
    lateinit var vectorC: Vector
    lateinit var vectorD: Vector
    lateinit var vectores: MutableList<Vector>
    lateinit var dao: HibernateVectorDAO
    lateinit var especie1 : Especie
    lateinit var especie2 : Especie
    lateinit var especie3 : Especie
    lateinit var estrategia : StrategyInterface
    lateinit var patogeno : Patogeno



    @Before
    fun crearModelo() {
        this.service = VectorServiceImp(HibernateVectorDAO(),
                HibernateDataDAO())


        patogeno = Patogeno("Virus" , 80 , 80 , 80)
        vectorA = Vector()
        //vectorB = Vector()
        //vectorC = Vector()
        vectorD = Vector()
        especie1 = Especie(patogeno , "Dengue", "Argentina" , 15)
        //especie2 = Especie()
        //especie3 = Especie()
        estrategia = StrategyPersona()
        vectores = ArrayList()
        vectores.add(vectorD)
        vectorA.estrategiaDeContagio = estrategia
        vectorA.enfermedades.add(especie1)
        service.crearVector(vectorA)
        service.crearVector(vectorD)







    }



    @Test
    fun contagiarAOtroVector() {
        var vectorRecuperado = service.recuperarVector(vectorD.id!!.toInt())

        Assert.assertTrue(vectorRecuperado.enfermedades.isEmpty())
       // vectorA.contagiar(vectorA , vectores)
        service.contagiar(vectorA , vectores)

        var vectorRecuperadoPost = service.recuperarVector(vectorD.id!!.toInt())

        Assert.assertEquals(1 ,vectorRecuperadoPost.enfermedades.size )

    }

    @After
    fun cleanup() {
        //Destroy cierra la session factory y fuerza a que, la proxima vez, una nueva tenga
        //que ser creada.
        service.clear()

    }

}