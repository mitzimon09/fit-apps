import general.*
import auditoria.general.*
import inventario.*
import auditoria.inventario.*
import contabilidad.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.authentication.TestingAuthenticationToken

class BootStrap {

    def springSecurityService
    def organizacionService
    def sessionFactory

    def init = { servletContext ->
        log.info "Validando roles"
        if (Rol.count() != 2) {
            def rolAdmin = general.Rol.findByAuthority('ROLE_ADMIN')
            if (!rolAdmin) {
                rolAdmin = new general.Rol(authority: 'ROLE_ADMIN').save(flush:true)
            }
            def rolUser = general.Rol.findByAuthority('ROLE_USER')
            if (!rolUser) {
                rolUser = new general.Rol(authority: 'ROLE_USER').save(flush:true)
            }
        }

        log.info "Validando usuarios"
        def admin = general.Usuario.findByBase(true)
        if (!admin) {
            def password = springSecurityService.encodePassword('159753')
            admin = new general.Usuario(
                username:'nujev'
                ,password:password
                ,enabled:true
                ,base:true
                ,nombre:'Evijair'
                ,apellido:'Nuñez'
                ,correo:'nujev2008@gmail.com')
            admin.save(flush:true)
            general.UsuarioRol.create(admin, rolAdmin, true)

            def xusuario = new XUsuario(admin.properties)
            xusuario.usuarioId = admin.id
            xusuario.almacenId = almacen.id
            xusuario.actividad = Constantes.CREAR
            xusuario.creador = 'nujev'
            xusuario.roles = 'ROLE_ADMIN'
            xusuario.save()

        }

		log.info("La aplicacion ha sido cargada exitosamente")
    }

    def destroy = {
    }

    def authenticateAdmin() {
        def credentials = '159753'
        def user = new Usuario(
                username:'nujev'
                ,password:credentials
            )
        def authorities = [new GrantedAuthorityImpl('ROLE_ADMIN'),new GrantedAuthorityImpl('ROLE_USER')]
        def principal = new GrailsUser(user.username,credentials,true,true,true,true,authorities,user.id)
        authenticate(principal,credentials,authorities)
    }

    def authenticate(principal, credentials, authorities) {
        def authentication = new TestingAuthenticationToken(principal, credentials, authorities as GrantedAuthority[])
        authentication.authenticated = true
        SCH.context.authentication = authentication
        return authentication
    }

    def logout() {
        SCH.context.authentication = null
    }
    
} 
