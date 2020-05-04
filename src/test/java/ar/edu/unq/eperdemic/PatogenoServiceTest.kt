package ar.edu.unq.eperdemic

import ar.edu.unq.eperdemic.modelo.Especie
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
    fun crearUnPatogenoYCorroborarId() {
        patogeno = Patogeno("Priones")
        val id = service.crearPatogeno(patogeno)

        Assert.assertEquals(1, id )
    }

    @Test
    fun seCreaUnPatogenoYLuegoAlRecuperarUnPatogenoVerificoQueSeaElMismo() {
        patogeno = Patogeno("Virus")
        val id = service.crearPatogeno(patogeno)

        Assert.assertEquals(patogeno.tipo, service.recuperarPatogeno(id).tipo)
    }

    @Test
    fun seAgregaUnaEspecieAUnPatogenoYSeCorroboraQueSeAllaAgregado() {
        patogeno = Patogeno("Covid")
        val id = service.crearPatogeno(patogeno)
        val especie = service.agregarEspecie(id,"Rojo","Mexico")
        val patogenoRecuperado = service.recuperarPatogeno(id)


        Assert.assertEquals(patogenoRecuperado, especie.owner)

    }
    @Test
    fun recuperarTodosLosPatogenosYCorroborarCantidad() {
        patogeno = Patogeno("Hongo")
        service.crearPatogeno(patogeno)
        patogeno2 = Patogeno("Covid")
        service.crearPatogeno(patogeno2)
        patogeno3 = Patogeno("Priones")
         service.crearPatogeno(patogeno3)

        Assert.assertEquals(3, service.recuperarATodosLosPatogenos().size)
    }

    @Test
    fun seAgregaUnaEspecieAUnPatogenoYLuegoSeRecuperaEsaEspecie() {
        patogeno = Patogeno("1-12")
        val id = service.crearPatogeno(patogeno)
        service.agregarEspecie(id,"cruza","Ecuador")
        val patogenoRecuperado = service.recuperarPatogeno(id)


        Assert.assertEquals(patogenoRecuperado.id, (service.recuperarEspecie(id).owner)!!.id)

    }

    @After
    fun cleanup() {

        service.clear()
    }

}