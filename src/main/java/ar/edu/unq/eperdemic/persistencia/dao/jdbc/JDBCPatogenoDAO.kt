package ar.edu.unq.eperdemic.persistencia.dao.jdbc

import ar.edu.unq.eperdemic.modelo.Patogeno
import ar.edu.unq.eperdemic.persistencia.dao.PatogenoDAO
import ar.edu.unq.eperdemic.persistencia.dao.jdbc.JDBCConnector.execute
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json
import java.sql.Connection
import java.sql.Statement
import java.sql.ResultSet
import java.util.logging.Handler
import javax.naming.Context


class JDBCPatogenoDAO : PatogenoDAO {

    override fun crear(patogeno: Patogeno): Int {
        var idGenerado = 0
        return execute { conn: Connection ->
            val ps = conn.prepareStatement("INSERT INTO patogeno (cantidadDeEspecies, tipo) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)
            ps.setInt(1, patogeno.cantidadDeEspecies)
            ps.setString(2, patogeno.tipo)
            ps.execute()
            if (ps.updateCount != 1) {
                throw RuntimeException("No se inserto el patogeno $patogeno")
            }
            val generatedKeys: ResultSet = ps.getGeneratedKeys()
            if (generatedKeys.next()) {
                idGenerado = generatedKeys.getInt(1)

                ps.close()
            }
            idGenerado
        }
    }

    override fun actualizar(patogeno: Patogeno)  {
        try {
            execute { conn: Connection ->
                val ps =
                        conn.prepareStatement("UPDATE patogeno SET cantidadDeEspecies = ? WHERE id = ?")
                ps.setInt(1, patogeno.cantidadDeEspecies)
                ps.setInt(2, patogeno.id!!)
                ps.execute()
                if (ps.updateCount != 1) {

                }
                ps.close()
            }
        } catch (e:Exception){
            throw RuntimeException("No se actualizo el patogeno $patogeno")
        }
    }

    override fun recuperar(patogenoId: Int): Patogeno {
           return execute { conn: Connection ->
               val ps = conn.prepareStatement("SELECT cantidadDeEspecies, tipo FROM patogeno WHERE id = ?")
               ps.setInt(1, patogenoId)
               val resultSet = ps.executeQuery()
               var patogeno: Patogeno? = null

               while (resultSet.next()) {
                   //si patogeno no es null aca significa que el while dio mas de una vuelta, eso
                   //suele pasar cuando el resultado (resultset) tiene mas de un elemento.
                   if (patogeno != null) {
                       throw RuntimeException("No existe ningun patogeno con el id $patogenoId")

                   }
                   patogeno = Patogeno(resultSet.getString("tipo"))
                   patogeno.id = patogenoId
                   patogeno.cantidadDeEspecies = resultSet.getInt("cantidadDeEspecies")
               }
               ps.close()
               patogeno!!
           }

    }

    override fun recuperarATodos(): List<Patogeno> {
        val listaResultante : ArrayList<Patogeno> = ArrayList(emptyList())
        return execute { conn: Connection ->
            val ps = conn.prepareStatement("SELECT id FROM patogeno")
            val resultSet = ps.executeQuery()
            val list = resultSet.use {
                generateSequence {
                    if (resultSet.next())
                        resultSet.getInt(1)
                    else null
                }.toList()
            }
            for (id in list) {
                listaResultante.add(this.recuperar(id))
            }
            ps.close()
            listaResultante
        }
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