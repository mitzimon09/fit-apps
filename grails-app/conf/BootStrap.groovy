import general.*
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
        log.debug "Creando rol Admin"
        def admin = Rol.findByAuthority('ROLE_ADMIN') ?: new Rol(authority: 'ROLE_ADMIN').save(failOnError: true)

        log.debug "Creando rol User"
        def user = Rol.findByAuthority('ROLE_USER') ?: new Rol(authority: 'ROLE_USER').save(failOnError: true)

        log.debug "Creando usuario Admin"
        def usuario = Usuario.findByUsername('admin')
        if (!usuario){
            usuario = new Usuario(
                username : 'admin'
                , password : springSecurityService.encodePassword('admin')
                , enabled : true
                , nombre:'Evijair'
                , apellidos:'Nuñez'
                , correo:'nujev2008@gmail.com'
            ).save(failOnError: true)
        }

        log.debug "Creando Relacion rol usuario"
        if (!usuario.authorities.contains(admin)){
            UsuarioRol.create(usuario, admin)
        }
    }

    def destroy = {
    }

    def authenticateAdmin() {
        def credentials = '159753'
        def user = new Usuario(
                username:'admin'
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
