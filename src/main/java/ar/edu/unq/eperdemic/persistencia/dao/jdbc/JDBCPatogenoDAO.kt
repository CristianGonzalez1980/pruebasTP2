package ar.edu.unq.eperdemic.persistencia.dao.jdbc

import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.persistencia.dao.PatogenoDAO
import ar.edu.unq.eperdemic.persistencia.dao.jdbc.JDBCConnector.execute
import java.sql.Connection



class JDBCPatogenoDAO : PatogenoDAO {

    override fun crear(patogeno: Patogeno): Int {
        return execute { conn: Connection ->
            val ps = conn.prepareStatement("INSERT INTO patogeno (id, cantidadDeEspecies, tipo) VALUES (?,?,?)")
            ps.setInt(1, patogeno.id!!)
            ps.setInt(2, patogeno.cantidadDeEspecies)
            ps.setString(3, patogeno.tipo)
            ps.execute()
            if (ps.updateCount != 1) {
                throw RuntimeException("No se inserto el patogeno $patogeno")
            }

            ps.close()
            patogeno.id!!
        }
    }

    override fun actualizar(patogeno: Patogeno) {
        execute { conn: Connection ->
            val ps =
                    conn.prepareStatement("UPDATE patogeno (cantidadDeEspecies) VALUE patogeno.cantidadDeEspecies WHERE id = patogeno.id")
            ps.execute()
            if (ps.updateCount != 1) {
                throw RuntimeException("No se actualizo el patogeno $patogeno")
            }
            ps.close()
            null
        }
    }

    override fun recuperar(patogenoId: Int): Patogeno {
        return execute { conn: Connection ->
            val ps = conn.prepareStatement("SELECT cantidadDeEspecies, tipo FROM patogeno WHERE id = patogenoId")
            ps.setInt(1, patogenoId)
            val resultSet = ps.executeQuery()
            var patogeno: Patogeno? = null
            while (resultSet.next()) {
                //si patogeno no es null aca significa que el while dio mas de una vuelta, eso
                //suele pasar cuando el resultado (resultset) tiene mas de un elemento.
                if (patogeno != null) {
                    throw RuntimeException("Existe mas de un patogeno con el id $patogenoId")
                }
                patogeno = Patogeno(ps.toString())
                patogeno.id = patogenoId
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