package ar.edu.unq.eperdemic.utils.jdbc

import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.persistencia.dao.PatogenoDAO
import ar.edu.unq.eperdemic.persistencia.dao.jdbc.JDBCConnector
import ar.edu.unq.eperdemic.persistencia.dao.jdbc.JDBCPatogenoDAO
import ar.edu.unq.eperdemic.utils.DataService

class DataServiceJDBC : DataService {

    private val patogenoDao: PatogenoDAO = JDBCPatogenoDAO()

    override fun crearSetDeDatosIniciales() {
        patogenoDao.crear(Patogeno("Virus"))
        patogenoDao.crear(Patogeno("Bacteria"))
        patogenoDao.crear(Patogeno("Hongo"))
    }

    override fun eliminarTodo() {
        val initializeScript = javaClass.classLoader.getResource("deleteAll.sql").readText()
        JDBCConnector.execute {
            val ps = it.prepareStatement(initializeScript)
            ps.execute()
            ps.close()
            null
        }
    }
}