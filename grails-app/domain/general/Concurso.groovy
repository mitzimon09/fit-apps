package general

class Concurso {
	String nombre
	String semestre
	Date año
    String detalle
    
    static transients = ['detalle']

    static constraints = {
        nombre blank: false, size:1..64
        semestre maxSize:32, inList:['Ago-Dic','Ene-May']
    }

    static mapping = {
        table 'concursos'
    }

    static namedQueries = {
        listaConFiltro { filtro ->
            filtro = "%$filtro%"
            or {
                ilike('nombre',filtro)
                ilike('semestre',filtro)
                ilike('año',filtro)
            }
        }
    }

    String getDetalle() {
        return "$semestre|$año"
    }

    String toString() {
        return "$semestre|$año"
    }
}
