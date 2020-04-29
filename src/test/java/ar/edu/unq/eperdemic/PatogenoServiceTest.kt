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
        Assert.assertEquals(4, id )
    }

    @Test
    fun seAgregaUnaEspecieSeCorroboraLaActualizacionDelPatogeno() {
        service.agregarEspecie(3, "VacaLoca", "Reino Unido")
        Assert.assertEquals(1, service.recuperarPatogeno(3).cantidadDeEspecies)
    }

    @Test
    fun seRecuperanTodosLosPatogenosSeCorroboraCantidad() {
        Assert.assertEquals(3, service.recuperarATodosLosPatogenos().size)
    }

    /*@After
    fun emilinarModelo() {
        modelo.eliminarTodo()
    }*/
}