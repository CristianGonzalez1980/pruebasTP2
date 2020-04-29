package ar.edu.unq.eperdemic.utils
/*
import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.persistencia.dao.PatogenoDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateDataDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernatePatogenoDAO
import ar.edu.unq.eperdemic.services.PatogenoService
import ar.edu.unq.eperdemic.services.runner.PatogenoServiceImp
import ar.edu.unq.eperdemic.utils.jdbc.DataServiceJDBC
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.assertThrows
import java.sql.SQLIntegrityConstraintViolationException
import javax.validation.constraints.AssertTrue

class PatogenoDaoTest {

    lateinit var service: PatogenoService
    lateinit var patogenoRaro: Patogeno


    /*private val modelo: DataService = DataServiceJDBC()
    lateinit var patogenoRaro: Patogeno*/

    @Before
    fun crearModelo() {
        this.service = PatogenoServiceImp(
                HibernatePatogenoDAO(),
                HibernateDataDAO()
        )    }

    @Test
    fun crearUnCuartoPatogenoSeCorroboraNumeroDeId() {
        patogenoRaro = Patogeno("Priones")
        Assert.assertEquals(4, dao.crear(patogenoRaro))
    }
    @Test
    fun crearUnQuintoPatogenoConUnTipoYaExistenteYNoMeLoPermite() {
        try {
            patogenoRaro = Patogeno("Virus")
            Assert.assertEquals(5, dao.crear(patogenoRaro))
        }catch(e:Exception) {
            assertThrows<SQLIntegrityConstraintViolationException> { dao.crear(patogenoRaro) }
        }

    }

    @Test
    fun pruebaRecuperarPatogenoQueNoFueCreadoYMeRespondeQueEsNulo() {
        try {
            patogenoRaro = Patogeno("Priones")
            val patogeno: Patogeno = dao.recuperar(4)
            Assert.assertEquals("Priones", patogeno.tipo)
        }catch(e:Exception) {
            assertThrows<NullPointerException> { dao.recuperar(4) }
        }
    }

    @Test
    fun pruebaRecuperar() {
        val patogeno: Patogeno = dao.recuperar(1)
        Assert.assertEquals("Virus", patogeno.tipo)
    }

    @Test
    fun crearUnPatogenoCuandoLoActualizoMeRespondeQueNoPuedeSerActualizado(){
       try{
                patogenoRaro= Patogeno("Covid")
                patogenoRaro.cantidadDeEspecies = 1
                val idPatogeno= dao.crear(patogenoRaro)
                println(dao.actualizar(patogenoRaro))
           Assert.assertEquals(0, dao.recuperar(idPatogeno).cantidadDeEspecies)
       }catch(e:Exception) {
           assertThrows<Exception> { dao.actualizar(patogenoRaro) }
       }
    }
    @Test
    fun seAgregaUnaEspecieSeCorroboraLaActualizacionDelPatogeno() {
        val patogeno: Patogeno = dao.recuperar(3)
        patogeno.crearEspecie("VacaLoca", "Reino Unido")
        dao.actualizar(patogeno)
        Assert.assertEquals(1, dao.recuperar(3).cantidadDeEspecies)
    }

    @Test
    fun seRecuperanTodosLosPatogenosSeCorroboraCantidad() {
        val patogenos: List<Patogeno> = dao.recuperarATodos()
        Assert.assertEquals(3, patogenos.size)
    }
/*
    @After
    fun emilinarModelo() {
        modelo.eliminarTodo()
    }*/
}*/