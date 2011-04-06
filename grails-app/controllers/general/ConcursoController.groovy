package general

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])

class ConcursoController {

    def concursoService
    def springSecurityService

    def index = {
        redirect(action: lista, params: params)
    }

    def lista = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10, 100)
        def resultado = concursoService.listaConCantidad(params)
        [concursos: resultado.lista, totalDeConcursos: resultado.cantidad]
    }

    def nuevo = {
        Concurso concurso = new Concurso(params)

        return [concurso:concurso]
    }

    def crea = {
        def concurso
        try {
            Concurso.withTransaction {
                concurso = new Concurso(params)
                concurso = concursoService.crea(concurso)

                flash.message = message(code:"concurso.crea",args:[concurso.detalle])
                redirect(action:"lista", id:concurso.id)
            }
        } catch(Exception e) {
            log.error("No se pudo crear el concurso",e)
            if (concurso) {
                concurso.discard()
            }
            flash.message = message(code:"concurso.noCrea")
            render(view:"nuevo", model: [concurso: concurso])
        }
    }

    def ver = {
        def concurso = concursoService.obtiene(params.id)
        return [concurso:concurso]
    }

    def edita = {
        def concurso = concursoService.obtiene(params.id)
        return [concurso:concurso]
    }

    def actualiza = {
        def concurso = concursoService.obtiene(params.id)
        try {
            Concurso.withTransaction {
                concurso.properties = params

                concurso = concursoService.actualiza(concurso)

                flash.message = message(code:"concurso.actualiza",args:[concurso.detalle])
                redirect(action:"lista",id:concurso.id)
            }
        } catch(Exception e) {
            log.error("No se pudo actualizar el concurso",e)
            if (concurso) {
                concurso.discard()
            }
            flash.message = message(code:"concurso.noActualiza")
            render(view:"edita",model:[concurso:concurso])
        }
    }

    def elimina = {
        try {
            Concurso.withTransaction {
                def detalle = concursoService.elimina(params.id)
                flash.message = message(code:"concurso.baja",args:[detalle])
                redirect(action:'lista')
            }
        } catch(Exception e) {
            log.error("No se pudo dar de baja el concurso",e)
            flash.message = message(code:"concurso.noBaja")
            render(view:'ver',model:[concurso:concursoService.obtiene(params.id)])
        }
    }
}

