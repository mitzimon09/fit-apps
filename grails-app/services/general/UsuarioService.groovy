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

    def listaConCantidad(params) {
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
	
    Usuario obtiene(id) {
        def usuario = Usuario.get(id)
        if (!usuario) {
            throw new RuntimeException("No se encontro al usuario $id")
        }
        return usuario
    }
	
    Usuario crea(usuario, roles) {
		log.debug "linea 44 $Usuario.get(springSecurityService.principal.id)"
        def creador = Usuario.get(springSecurityService.principal.id)
        usuario.save()
        for(rol in roles) {
            general.UsuarioRol.create(usuario, rol, false)
        }
//        audita(usuario,Constantes.CREAR)
        audita(usuario,"CREAR")
        return usuario
    }
	
    Usuario actualiza(usuario, roles) {
        log.debug "Actualizando al usuario $usuario, $roles"
        usuario.save()
        if (roles) {
            general.UsuarioRol.removeAll(usuario)
            sessionFactory.currentSession.flush()

            for(rol in roles) {
                general.UsuarioRol.create(usuario, rol, false)
            }
        }
//        audita(usuario,Constantes.ACTUALIZAR)
        audita(usuario,"ACTUALIZAR")
        return usuario
    }
	
    String elimina(id) {
        def usuario = Usuario.get(id)
        String nombre = usuario.username
        UsuarioRol.removeAll(usuario)
//        audita(usuario,Constantes.ELIMINAR)
        audita(usuario,"ELIMINAR")
        usuario.delete()
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
