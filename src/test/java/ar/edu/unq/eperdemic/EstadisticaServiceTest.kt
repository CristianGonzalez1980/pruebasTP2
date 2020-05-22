package ar.edu.unq.eperdemic

import ar.edu.unq.eperdemic.dto.VectorFrontendDTO
import ar.edu.unq.eperdemic.modelo.*
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.*
import ar.edu.unq.eperdemic.services.EstadisticasService
import ar.edu.unq.eperdemic.services.PatogenoService
import ar.edu.unq.eperdemic.services.UbicacionService
import ar.edu.unq.eperdemic.services.VectorService
import ar.edu.unq.eperdemic.services.runner.EstadisticaServiceImp
import ar.edu.unq.eperdemic.services.runner.PatogenoServiceImp
import ar.edu.unq.eperdemic.services.runner.UbicacionServiceImp
import ar.edu.unq.eperdemic.services.runner.VectorServiceImp
import ar.edu.unq.eperdemic.utils.DataService
import ar.edu.unq.eperdemic.utils.Impl.DataServiceImp
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class EstadisticaServiceTest {

    lateinit var serviceEst: EstadisticasService
    lateinit var serviceUbi: UbicacionService
    lateinit var serviceVec: VectorService
    lateinit var servicePatog: PatogenoService
    lateinit var serviceData: DataService
    lateinit var ubi1: Ubicacion
    lateinit var ubi2: Ubicacion
    lateinit var vectorA: Vector
    lateinit var vectorB: Vector
    lateinit var vectorC: Vector
    lateinit var vectorD: Vector
    lateinit var vectorE: Vector
    lateinit var vectorF: Vector
    lateinit var vectorG: Vector
    lateinit var especie1: Especie
    lateinit var especie2: Especie
    lateinit var especie3: Especie
    lateinit var especie4: Especie
    lateinit var especie5: Especie
    lateinit var especie6: Especie
    lateinit var especie7: Especie
    lateinit var especie8: Especie
    lateinit var especie9: Especie
    lateinit var especie10: Especie
    lateinit var especie11: Especie
    lateinit var especie12: Especie


    @Before
    fun crearModelo() {
        this.serviceUbi = UbicacionServiceImp(HibernateUbicacionDAO(),
                HibernateDataDAO(), HibernateVectorDAO(), VectorServiceImp(HibernateVectorDAO(), HibernateDataDAO(), HibernateEspecieDAO()))
        this.serviceVec = VectorServiceImp(HibernateVectorDAO(), HibernateDataDAO(), HibernateEspecieDAO())
        this.servicePatog = PatogenoServiceImp(HibernatePatogenoDAO(), HibernateEspecieDAO(), HibernateDataDAO())
        this.serviceData = DataServiceImp(HibernateDataDAO())
        this.serviceEst = EstadisticaServiceImp(HibernateEspecieDAO(), HibernateUbicacionDAO(), UbicacionServiceImp(HibernateUbicacionDAO(),
                HibernateDataDAO(), HibernateVectorDAO(), VectorServiceImp(HibernateVectorDAO(), HibernateDataDAO(), HibernateEspecieDAO())))

        //se elimina tdo tipo de informacion persistida hasta el momento
        serviceData.eliminarTodo()

        //se crea "Bernal" y se persiste con el service
        ubi1 = serviceUbi.crearUbicacion("Bernal")
        //se crean 3 vectores con dicha ubicacion se persiste
        vectorA = serviceVec.crearVector(Vector(ubi1, VectorFrontendDTO.TipoDeVector.Persona))
        vectorB = serviceVec.crearVector(Vector(ubi1, VectorFrontendDTO.TipoDeVector.Insecto))
        vectorC = serviceVec.crearVector(Vector(ubi1, VectorFrontendDTO.TipoDeVector.Animal))
        serviceUbi.actualizar(ubi1)

        //se crea "Wilde" y se persiste con el service
        ubi2 = serviceUbi.crearUbicacion("Wilde")
        //se crean 2 vectores con dicha ubicacion se persiste
        vectorD = serviceVec.crearVector(Vector(ubi2, VectorFrontendDTO.TipoDeVector.Persona))
        vectorE = serviceVec.crearVector(Vector(ubi2, VectorFrontendDTO.TipoDeVector.Animal))
        serviceUbi.actualizar(ubi2)

        //se crea el patogeno "Virus"
        var patogenoid: Int = servicePatog.crearPatogeno(Patogeno("virus", 66, 30, 30))
        //se agregan especies al patogeno
        especie1 = servicePatog.agregarEspecie(patogenoid, "Varicela", "Bulgaria", 15)
        especie2 = servicePatog.agregarEspecie(patogenoid, "Viruela", "Francia", 25)
        especie3 = servicePatog.agregarEspecie(patogenoid, "Sarampion", "España", 10)
        especie4 = servicePatog.agregarEspecie(patogenoid, "Paperas", "Reino Unido", 35)
        especie5 = servicePatog.agregarEspecie(patogenoid, "H1N1", "Inglaterra", 55)
        especie6 = servicePatog.agregarEspecie(patogenoid, "Escarlatina", "Francia", 65)
        especie7 = servicePatog.agregarEspecie(patogenoid, "Covid19", "EE:UU", 65)

        //se crea el patogeno "Bacteria"
        var patogenoid2: Int = servicePatog.crearPatogeno(Patogeno("bacteria", 65, 20, 50))
        //se agregan especies al patogeno2
        especie8 = servicePatog.agregarEspecie(patogenoid2, "Colera", "Mexico", 75)
        especie9 = servicePatog.agregarEspecie(patogenoid2, "Fiebre Amarilla", "Uruguay", 85)
        especie10 = servicePatog.agregarEspecie(patogenoid2, "Anthrax", "China", 55)
        especie11 = servicePatog.agregarEspecie(patogenoid2, "Salmonela", "Portugal", 12)
        especie12 = servicePatog.agregarEspecie(patogenoid2, "Paludismo", "Cuba", 45)

        //se infectan los vectores con efermedades
        serviceVec.infectar(vectorA, especie1)
        serviceVec.infectar(vectorA, especie10)
        serviceVec.infectar(vectorA, especie3)
        serviceVec.infectar(vectorB, especie4)
        serviceVec.infectar(vectorB, especie5)
        serviceVec.infectar(vectorB, especie6)
        serviceVec.infectar(vectorC, especie7)
        serviceVec.infectar(vectorC, especie8)
        serviceVec.infectar(vectorC, especie9)
        serviceVec.infectar(vectorD, especie10)
        serviceVec.infectar(vectorD, especie11)
        serviceVec.infectar(vectorD, especie12)
        serviceVec.infectar(vectorE, especie1)
        serviceVec.infectar(vectorE, especie2)
        serviceVec.infectar(vectorE, especie3)
        serviceVec.infectar(vectorE, especie7)

        /*  hasta el momento A Y D (humanos), C Y E (animales), B (insecto) infectados con:
        *
        *   A: varicela, Anthrax, sarampion
        *   D: anthrax, salmonela, paludismo
        *
        *   C: covid19, colera, fiebre amarilla
        *   E: varicela, viruela, sarampion, covid19
        *
        *   B: paperas, h1n1, escarlatina
        *
        * */

        //se muda el vectorA a Wilde
        serviceUbi.mover(vectorA.id!!.toInt(), "Wilde")
        /*  intentara contagia a residentes vectorD y vectorE
        *   solo pone en riesgo a vectorD ya que es humano (vectorE es animal)
        *   por el momento que solo la estrategia define contagio exitoso
        *   porcentaje mayor a 70 (ya tenemos 5 por falso random)
        *   Verificamos:
        *   patogenos con capacidad de contagio mayores a 65:
        *   patogeno1 66 (contagio exitoso)
        *   patogeno2 65 (contagio fallido)
        *   enfermedades de vector A: varicela, Anthrax, sarampion
        *
        *   enfermedades de vector D finales:
        *   varicela, sarampion,
        *   anthrax, salmonela, paludismo
        */

        //se muda el vectorE a Bernal
        serviceUbi.mover(vectorE.id!!.toInt(), "Bernal")
        /*  intentara contagia a residentes vectorB y vectorC
        *   solo pone en riesgo a vectorB ya que es insecto (vectorC es animal)
        *   por el momento que solo la estrategia define contagio exitoso
        *   porcentaje mayor a 70 (ya tenemos 5 por falso random)
        *   Verificamos:
        *   patogenos con capacidad de contagio mayores a 65:
        *   patogeno1 66 (contagio exitoso)
        *   patogeno2 65 (contagio fallido)
        *   enfermedades de vector E: varicela, viruela, sarampion, covid19
        *
        *   enfermedades de vector B finales:
        *   varicela, viruela, sarampion, covid19
        *   paperas, h1n1, escarlatina
        */

        //se crea un vector sano en Bernal
        vectorF = serviceVec.crearVector(Vector(ubi1, VectorFrontendDTO.TipoDeVector.Animal))

        //se crea un vector humano en Wilde y se infecta con Anthrax
        vectorG = serviceVec.crearVector(Vector(ubi2, VectorFrontendDTO.TipoDeVector.Persona))
        serviceVec.infectar(vectorG, especie10)
    }
    /* ----insecto   B: paperas, h1n1, escarlatina, varicela, viruela, sarampion, covid19
    *
    * varicela 3-
    * Anthrax 3-
    * sarampion 3-
    * covid19 2-
    * paludismo-
    * salmonela-
    * colera-
    * fiebre amarilla-
    * viruela-
    *
    */
    @Test
    fun `se verifica especie que infecto mas humanos`() {
        //      vectorA, vectorD y vectorG son humanos (anthrax tienen todos)
        //      que pasa si hay empates?
        Assert.assertEquals(serviceEst.especieLider(), especie10)
    }

    @Test
    fun `se obtiene lista de 10 especies con mayor infeccion en humanos y animales`() {
        //       varicela, sarampion esta en ambos grupos solamente
        //       no se consideran que hayan infectado humanos y animales por separado, solo combinados?
        Assert.assertEquals(serviceEst.lideres(), mutableListOf(especie1, especie3))//falta comparar con la salida
    }

    @Test
    fun `se verifica reporte de vectores presentes vectores infectados y especie lider en ubicacion`() {
        //bernal: vectores B,C,E y F (presentes e infectados) lider: covid19
        //wilde: vectores D y A (presentes e infectados) lideres: (varicela, anthrax, sarampion)
        //verificar vacios    //que pasa si hay empates?
        var vectoresPresente = mutableListOf<Vector>(vectorB, vectorC, vectorE, vectorF)
        var vectoresInfectados = mutableListOf<Vector>(vectorB, vectorC, vectorE)
        var especieLider = especie7
        Assert.assertEquals(serviceEst.reporteDeContagios("Bernal").vectoresPresentes, vectoresPresente.size)
        Assert.assertEquals(serviceEst.reporteDeContagios("Bernal").vectoresInfecatods, vectoresInfectados.size)
        Assert.assertEquals(serviceEst.reporteDeContagios("Bernal").nombreDeEspecieMasInfecciosa, especieLider.nombre)
    }

    @After
    fun cleanup() {
        serviceData.eliminarTodo()
        //Destroy cierra la session factory y fuerza a que, la proxima vez, una nueva tenga
        //que ser creada.
    }
}