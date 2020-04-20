package ar.edu.unq.eperdemic.persistencia.dao.jdbc

import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.persistencia.dao.PatogenoDAO
import ar.edu.unq.eperdemic.persistencia.dao.jdbc.JDBCConnector.execute
import java.sql.Connection


class JDBCPatogenoDAO : PatogenoDAO {

    override fun crear(patogeno: Patogeno): Int {
        TODO("not implemented")
    }

    override fun actualizar(patogeno: Patogeno) {
        TODO("not implemented")
    }

    override fun recuperar(patogenoId: Int): Patogeno {
        return execute { conn: Connection ->
            val ps = conn.prepareStatement("SELECT tipo, cantidadDeEspecies FROM patogeno WHERE id = patogenoId")
            ps.setString(1, id)
            val resultSet = ps.executeQuery()
            var patogeno: Patogeno? = null
            while (resultSet.next()) {
                //si patogeno no es null aca significa que el while dio mas de una vuelta, eso
                //suele pasar cuando el resultado (resultset) tiene mas de un elemento.
                if (patogeno != null) {
                    throw RuntimeException("Existe mas de un patogeno con el id $id")
                }
                patogeno = Patogeno(tipo)
                patogeno.cantidadDeEspecies = resultSet.getInt("cantidadDeEspecies")
            }
            ps.close()
            patogeno!!
        }
    }

    override fun recuperarATodos(): List<Patogeno> {
        TODO("not implemented")
    }

    init {
        val initializeScript = javaClass.classLoader.getResource("createAll.sql").readText()
        execute {
            val ps = it.prepareStatement(initializeScript)
            ps.execute()
            ps.close()
            null
        }
    }
}