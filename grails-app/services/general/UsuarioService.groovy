package general

import auditoria.general.XUsuario

class UsuarioService {

    static transactional = true
    def springSecurityService
    def sessionFactory

    List<Usuario> lista(def params) {
        log.debug "Lista de usuarios"
        def usuarios = []
        if (params?.filtro) {
            usuarios = Usuario.listaConFiltro(params.filtro).list(params)
        } else {
            usuarios = Usuario.list(params)
        }
        return usuarios
    }

    def listaConCantidad(def params) {
        def usuarios = []
        def cantidad = 0
        if (params?.filtro) {
            usuarios = Usuario.listaConFiltro(params.filtro).list(params)
            cantidad = Usuario.listaConFiltro(params).count()
        } else {
            usuarios = Usuario.list(params)
            cantidad = Usuario.count()
        }
        return [lista:usuarios, cantidad:cantidad]
    }

    Usuario obtiene(String id) {
        return Usuario.get(id)
    }
	
    Usuario crea(Usuario usuario, Set roles) {
        def creador = Usuario.get(springSecurityService.principal.id)
        usuario.save()
        for(rol in roles) {
            general.UsuarioRol.create(usuario, rol, false)
        }
        audita(usuario,"CREAR")
        return usuario
    }

    Usuario actualiza(Usuario usuario, Set roles) {
        log.debug "Actualizando al usuario $usuario, $roles"
        usuario.save()
        if (roles) {
            general.UsuarioRol.removeAll(usuario)
            sessionFactory.currentSession.flush()

            for(rol in roles) {
                general.UsuarioRol.create(usuario, rol, false)
            }
        }
        audita(usuario,"ACTUALIZAR")
        return usuario
    }

    String elimina(String id) {
        def usuario = Usuario.get(id)
        String nombre = usuario.username
        UsuarioRol.removeAll(usuario)
        usuario.delete()
        audita(usuario,"ELIMINAR")
        return nombre
    }

    void audita(usuario, actividad) {
        log.debug "[AUDITA] $actividad usuario $usuario"
        def creador = springSecurityService.authentication.name
        //def xusuario = new XUsuario(usuario.properties)
        def xusuario = new XUsuario(usuario.properties)
        StringBuilder roles = new StringBuilder()
        for(rol in usuario.authorities) {
            roles.append(rol.authority)
            roles.append("|")
        }
        xusuario.roles = roles.toString()
        xusuario.usuarioId = usuario.id
        xusuario.creador = creador
        xusuario.actividad = actividad
        xusuario.save()
	}
}
