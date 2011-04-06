package auditoria.general

class XConcurso {
	String nombre
	String semestre
	Date año
    String detalle
	Long concursoId
    Date lastUpdated
    String actividad
    String creador
        
    static transients = ['detalle']

    static constraints = {
        nombre blank: false, size:1..64
        semestre maxSize:32, inList:['Ago-Dic','Ene-May']
        actividad size:1..32
        creador size:1..64
    }

    static mapping = {
        table 'xconcursos'
    }

    String getDetalle() {
        return "$semestre|$año"
    }

    String toString() {
        return "$semestre|$año"
    }
}
