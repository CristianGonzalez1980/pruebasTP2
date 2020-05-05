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
        patogeno = Patogeno("Priones", 30, 20, 22)
        val id = service.crearPatogeno(patogeno)

        Assert.assertEquals(1, id )
    }

    @Test
    fun seCreaUnPatogenoYLuegoAlRecuperarUnPatogenoVerificoQueSeaElMismo() {
        patogeno = Patogeno("Virus", 20, 10, 12)
        val id = service.crearPatogeno(patogeno)

        Assert.assertEquals(patogeno.tipo, service.recuperarPatogeno(id).tipo)
    }

    @Test
    fun seAgregaUnaEspecieAUnPatogenoYSeCorroboraQueSeHallaAgregado() {
        patogeno = Patogeno("Covid", 60, 70, 90)
        val id = service.crearPatogeno(patogeno)
        val especie = service.agregarEspecie(id,"Rojo","Mexico", 23)
        val patogenoRecuperado = service.recuperarPatogeno(id)

        Assert.assertEquals(patogenoRecuperado, especie.owner)

    }
    @Test
    fun recuperarTodosLosPatogenosYCorroborarCantidad() {
        patogeno = Patogeno("Hongo", 90, 30, 62)
        service.crearPatogeno(patogeno)
        patogeno2 = Patogeno("Covid", 50, 20, 22)
        service.crearPatogeno(patogeno2)
        patogeno3 = Patogeno("Priones", 10, 10, 12)
         service.crearPatogeno(patogeno3)

        Assert.assertEquals(3, service.recuperarATodosLosPatogenos().size)
    }

    @Test
    fun agregarEspecieAPatogenoYRecuperarEspecie() {
        patogeno = Patogeno("1-12", 20, 50, 12)
        val id = service.crearPatogeno(patogeno)
        service.agregarEspecie(id,"cruza","Ecuador",44)
        val patogenoRecuperado = service.recuperarPatogeno(id)
        //revisar la implementacion de recuperarEspecie segun lo que pide el enunciado
        //Assert.assertEquals(patogenoRecuperado.id, (service.recuperarEspecie(id).owner)!!.id)

    }

    @After
    fun cleanup() {
        service.clear()

    }

}