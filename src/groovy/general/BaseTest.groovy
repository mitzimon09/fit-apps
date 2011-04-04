package general

import groovy.util.GroovyTestCase
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.authentication.TestingAuthenticationToken

abstract class BaseTest extends GroovyTestCase {

    def authenticateAdmin() {
        def user = general.Usuario.get(1)
        def authorities = [new GrantedAuthorityImpl('ROLE_ADMIN')]
        def credentials = 'test'
        def principal = new GrailsUser(user.username,credentials,true,true,true,true,authorities,user.id)
        authenticate(principal,credentials,authorities)
    }

    def authenticateOrg() {
        def user = general.Usuario.get(1)
        def authorities = [new GrantedAuthorityImpl('ROLE_ORG')]
        def credentials = 'test'
        def principal = new GrailsUser(user.username,credentials,true,true,true,true,authorities,user.id)
        authenticate(principal,credentials,authorities)
    }

    def authenticateEmp() {
        def user = general.Usuario.get(1)
        def authorities = [new GrantedAuthorityImpl('ROLE_EMP')]
        def credentials = 'test'
        def principal = new GrailsUser(user.username,credentials,true,true,true,true,authorities,user.id)
        authenticate(principal,credentials,authorities)
    }

    def authenticateUser() {
        def user = general.Usuario.get(1)
        def authorities = [new GrantedAuthorityImpl('ROLE_USER')]
        def credentials = 'test'
        def principal = new GrailsUser(user.username,credentials,true,true,true,true,authorities,user.id)
        authenticate(principal,credentials,authorities)
    }

    def authenticate(principal, credentials, authorities) {
        def authentication = new TestingAuthenticationToken(principal, credentials, authorities as GrantedAuthority[])
        authentication.authenticated = true
        SCH.context.authentication = authentication
        return authentication
    }

}

