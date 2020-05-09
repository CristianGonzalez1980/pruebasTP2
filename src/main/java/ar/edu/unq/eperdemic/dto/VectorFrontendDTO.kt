package ar.edu.unq.eperdemic.dto

import ar.edu.unq.eperdemic.modelo.Ubicacion
import ar.edu.unq.eperdemic.modelo.Vector
import ar.edu.unq.eperdemic.persistencia.dao.UbicacionDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateDataDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateEspecieDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateUbicacionDAO
import ar.edu.unq.eperdemic.persistencia.dao.hibernate.HibernateVectorDAO
import ar.edu.unq.eperdemic.services.UbicacionService
import ar.edu.unq.eperdemic.services.VectorService
import ar.edu.unq.eperdemic.services.runner.UbicacionServiceImp
import ar.edu.unq.eperdemic.services.runner.VectorServiceImp

class VectorFrontendDTO(val tipoDeVector: TipoDeVector,
                        val nombreDeUbicacionPresente: String) {

    enum class TipoDeVector {
        Persona, Insecto, Animal
    }

    fun aModelo(): Vector {
        var vecDAO: VectorService = VectorServiceImp(HibernateVectorDAO(), HibernateDataDAO(), HibernateEspecieDAO())
        var ubiDAO: UbicacionService = UbicacionServiceImp(HibernateUbicacionDAO(), HibernateDataDAO(), HibernateVectorDAO(), VectorServiceImp(HibernateVectorDAO(), HibernateDataDAO(),HibernateEspecieDAO()))
        val ubicacion: Ubicacion = ubiDAO.recuperar(nombreDeUbicacionPresente)
        return vecDAO.crearVector(Vector(ubicacion, tipoDeVector))
    }
}