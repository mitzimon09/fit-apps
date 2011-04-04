<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="main" />
        <title><g:message code="usuario.perfil" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${resource(dir: '')}"><g:message code="general.inicio" /></a></span>
        </div>
        <div class="body">
            <h1><g:message code="usuario.perfil" /></h1>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${usuario}">
                <div class="errors">
                    <g:renderErrors bean="${usuario}" as="list" />
                </div>
            </g:hasErrors>
            <g:form method="post" >
                <input type="hidden" name="id" value="${usuario?.id}" />
                <input type="hidden" name="version" value="${usuario?.version}" />
                <input type="hidden" name="perfil" value="true" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="nombre"><g:message code="usuario.nombre" /></label></td>
                                <td valign="top" class="value ${hasErrors(bean:usuario,field:'nombre','errors')}">
                                    <input type="text" id="nombre" name="nombre" value="${usuario.nombre}"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="apellido"><g:message code="usuario.apellido" /></label></td>
                                <td valign="top" class="value ${hasErrors(bean:usuario,field:'apellido','errors')}">
                                    <input type="text" id="nombre" name="apellido" value="${usuario.apellido}"/>
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name"><label for="passwd"><g:message code="usuario.password" /></label></td>
                                <td valign="top" class="value ${hasErrors(bean:usuario,field:'password','errors')}">
                                    <input type="password" id="password" name="password" value="${usuario.password.encodeAsHTML()}"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" value="${message(code: 'usuario.actualizaPerfil')}" action="actualizaPerfil" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
