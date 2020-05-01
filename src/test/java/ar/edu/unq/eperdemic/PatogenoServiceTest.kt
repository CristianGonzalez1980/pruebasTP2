package ar.edu.unq.eperdemic

import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateDataDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernatePatogenoDAO
import ar.edu.unq.eperdemic.services.PatogenoService
import ar.edu.unq.eperdemic.services.runner.PatogenoServiceImp
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class PatogenoServiceTest {

    lateinit var service: PatogenoService
    lateinit var patogeno: Patogeno
    lateinit var patogeno2: Patogeno
    lateinit var patogeno3: Patogeno




    @Before
    fun crearModelo() {
        this.service = PatogenoServiceImp(
                HibernatePatogenoDAO(),
                HibernateDataDAO()
        )
    }

    @Test
    fun crerUnCuartoPatogenoSeCorroboraNumeroDeId() {
        patogeno = Patogeno("Priones")
        val id = service.crearPatogeno(patogeno)

        Assert.assertEquals(1, id )
    }

    @Test
    fun seAgregaUnaEspecieSeCorroboraLaActualizacionDelPatogeno() {
        patogeno = Patogeno("Virus")
        val id = service.crearPatogeno(patogeno)
       println(patogeno)
        println(service.recuperarPatogeno(id))

        Assert.assertEquals(patogeno.tipo, service.recuperarPatogeno(id).tipo)
    }

    @Test
    fun seRecuperanTodosLosPatogenosSeCorroboraCantidad() {
        patogeno = Patogeno("Hongo")
        service.crearPatogeno(patogeno)
        /*patogeno2 = Patogeno("bbbbb")
        service.crearPatogeno(patogeno2)
        patogeno3 = Patogeno("jkhkhkhkk")
         service.crearPatogeno(patogeno3)*/

        Assert.assertEquals(null, service.recuperarATodosLosPatogenos().size)
    }

   /* @After
    fun emilinarModelo() {
        modelo.eliminarTodo()
    }*/
}