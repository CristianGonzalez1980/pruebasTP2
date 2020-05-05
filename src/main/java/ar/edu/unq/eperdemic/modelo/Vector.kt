package ar.edu.unq.eperdemic.modelo

class Vector( var id: Int?,
              var nombreDeLocacionActual: String) {
    fun getId() : Int{
        return this.id!!
    }
}