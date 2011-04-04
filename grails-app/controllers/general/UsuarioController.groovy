package general

import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

//@Secured(['ROLE_ADMIN'])
class UsuarioController {
    
    def usuarioService
    def springSecurityService

    def index = {
        redirect(action: lista, params: params)
    }

    def lista = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10, 100)
        def resultado = usuarioService.listaConCantidad(params)
        [usuarios: resultado.lista, totalDeUsuarios: resultado.cantidad]
    }

    def nuevo = {
        Usuario usuario = new Usuario(params)
        usuario.enabled = true

        def roles = obtieneListaDeRoles(null)
        
        return [usuario:usuario, roles:roles]
    }

    def crea = {
        def usuario
        try {
            Usuario.withTransaction {
                usuario = new Usuario(params)
                usuario.password = springSecurityService.encodePassword(params.password)
                def roles = [] as Set
                if (params.ROLE_ADMIN) {
                    roles << Rol.findByAuthority('ROLE_ADMIN')
                } else {
                    roles << Rol.findByAuthority('ROLE_USER')
                }

                usuario = usuarioService.crea(usuario, roles)

                flash.message = message(code:"usuario.crea",args:[usuario])
                redirect(action:"ver", id:usuario.id)
            }
        } catch(Exception e) {
            log.error("No se pudo crear el usuario",e)
            if (usuario) {
                usuario.discard()
            }
            flash.message = message(code:"usuario.noCrea")
            render(view:"nuevo", model: [usuario: usuario])
        }
    }

    def ver = {
        def usuario = usuarioService.obtiene(params.id)
        def roles = obtieneListaDeRoles(usuario)
        return [usuario:usuario, roles:roles]
    }

    def edita = {
        def usuario = usuarioService.obtiene(params.id)
        def roles = obtieneListaDeRoles(usuario)
        return [usuario:usuario, roles:roles]
    }

    def actualiza = {
        def usuario = usuarioService.obtiene(params.id)
        try {
            Usuario.withTransaction {
                def cambioPasswd = false
                if (!usuario.password.equals(params.password)) {
                    cambioPasswd = true
                }
                usuario.properties = params
                if (cambioPasswd) {
                    usuario.password = springSecurityService.encodePassword(params.password)
                }
                def roles = [] as Set
                if (params.ROLE_ADMIN) {
                    roles << Rol.findByAuthority('ROLE_ADMIN')
                } else {
                    roles << Rol.findByAuthority('ROLE_USER')
                }

                usuario = usuarioService.actualiza(usuario,roles)

                if (springSecurityService.loggedIn &&
                    springSecurityService.principal.username == usuario.username) { 
                    springSecurityService.reauthenticate usuario.username
                }
                flash.message = message(code:"usuario.actualiza",args:[usuario])
                redirect(action:"ver",id:usuario.id)
            }
        } catch(Exception e) {
            log.error("No se pudo actualizar el usuario",e)
            if (usuario) {
                usuario.discard()
            }
            flash.message = message(code:"usuario.noActualiza")
            render(view:"edita",model:[usuario:usuario])
        }

    }

    def elimina = {
        try {
            Usuario.withTransaction {
                def nombre = usuarioService.elimina(params.id)
                flash.message = message(code:"usuario.baja", args:[nombre])
                redirect(action:'lista')
            }
        } catch(Exception e) {
            log.error("No se pudo dar de baja el usuario",e)
            flash.message = message(code:"usuario.noBaja")
            render(view:'ver',model:[usuario:usuarioService.obtiene(params.id)])
        }
    }

    Map obtieneListaDeRoles(usuario) {
        def roles = Rol.list()

        def rolesFiltrados = [] as Set
        //def creador = usuarioService.obtiene(springSecurityService.principal().id)
        if (SpringSecurityUtils.ifAnyGranted('ROLE_ADMIN')) {
            rolesFiltrados = roles
        } else if (roles.authority.equals('ROL_USER')) {
            rolesFiltrados << rol
        }

        roles.sort { r1, r2 ->
            r1.authority <=> r2.authority
        }
        Set userRoleNames = []
        for (role in usuario?.authorities) {
            userRoleNames << role.authority
        }
        LinkedHashMap<Rol, Boolean> roleMap = [:]
        for (role in roles) {
            roleMap[(role)] = userRoleNames.contains(role.authority)
        }
        return roleMap
    }

    @Secured(['ROLE_USER'])
    def perfil = {
        def usuario = Usuario.get(springSecurityService.getPrincipal().id)
        return [usuario:usuario]
    }

    @Secured(['ROLE_USER'])
    def actualizaPerfil = {
        def usuario = usuarioService.obtiene(params.id)
        try {
            Usuario.withTransaction {
                def cambioPasswd = false
                if (!usuario.password.equals(params.password)) {
                    cambioPasswd = true
                }
                usuario.properties = params
                if (cambioPasswd) {
                    usuario.password = springSecurityService.encodePassword(params.password)
                }

                usuario = usuarioService.actualiza(usuario,null)

                flash.message = message(code:"usuario.mensaje.actualizaPerfil",args:[usuario])
                redirect(uri:'/')
            }
        } catch(Exception e) {
            log.error("No se pudo actualizar el usuario",e)
            if (usuario) {
                usuario.discard()
            }
            flash.message = message(code:"usuario.mensaje.noActualizaPerfil")
            render(view:"edita",model:[usuario:usuario])
        }
    }
}
