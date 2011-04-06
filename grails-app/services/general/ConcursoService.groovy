package general

import auditoria.general.XConcurso

class ConcursoService {

    static transactional = true
    def springSecurityService
    def sessionFactory

    List<Concurso> lista(def params) {
        log.debug "Lista de concursos"
        def concursos = []
        if (params?.filtro) {
            concursos = Concurso.listaConFiltro(params.filtro).list(params)
        } else {
            concursos = Concurso.list(params)
        }
        return concursos
    }

    def listaConCantidad(def params) {
        def concursos = []
        def cantidad = 0
        if (params?.filtro) {
            concursos = Concurso.listaConFiltro(params.filtro).list(params)
            cantidad = Concurso.listaConFiltro(params).count()
        } else {
            concursos = Concurso.list(params)
            cantidad = Concurso.count()
        }
        return [lista:concursos, cantidad:cantidad]
    }

    Concurso obtiene(String id) {
        return Concurso.get(id)
    }
	
    Concurso crea(Concurso concurso) {
        concurso.save()
        audita(concurso,"CREAR")
        return concurso
    }

    Concurso actualiza(Concurso concurso) {
        log.debug "Actualizando al concurso $concurso"
        concurso.save()
        audita(concurso,"ACTUALIZAR")
        return concurso
    }

    String elimina(String id) {
        def concurso = Concurso.get(id)
        String detalle = concurso.detalle
        concurso.delete()
        audita(concurso,"ELIMINAR")
        return detalle
    }

    void audita(concurso, actividad) {
        log.debug "[AUDITA] $actividad concurso $concurso"
        def creador = springSecurityService.authentication.name
        def xconcurso = new XConcurso(concurso.properties)
        xconcurso.concursoId = concurso.id
        xconcurso.creador = creador
        xconcurso.actividad = actividad
        xconcurso.save()
	}
}
