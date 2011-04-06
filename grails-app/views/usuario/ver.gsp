<html>
    <head>
        <title><g:message code="usuario.ver" args="[usuario.username]" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="home" action="admin"><g:message code="general.admin" /></g:link></span>
            <span class="menuButton"><g:link class="list" action="lista"><g:message code="usuario.lista" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="nuevo"><g:message code="usuario.nuevo" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="usuario.ver" args="[usuario.username]" /></h1>
            <g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="usuario.username" /></td>
                            <td valign="top" class="value">${fieldValue(bean: usuario, field: "username")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="usuario.nombreCompleto" /></td>
                            <td valign="top" class="value">${fieldValue(bean: usuario, field: "nombreCompleto")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="usuario.correo" /></td>
                            <td valign="top" class="value">${fieldValue(bean: usuario, field: "correo")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="usuario.enabled" /></td>
							<td><g:checkBox name="enabled" value="${usuario.enabled}" disabled="true"/></td>
						</tr>
                        <g:if test="${roles}">
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="roles"><g:message code="usuario.roles" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: usuario, field: 'roles', 'errors')}">
                                    <g:each var="entry" in="${roles}">
                                        <g:checkBox disabled="true" name="${entry.key.authority}" value="${entry.value}"/> ${entry.key.authority}<br/>
                                    </g:each>
                                </td>
                            </tr>
                        </g:if>
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${usuario?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edita" value="${message(code: 'default.button.edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete')}" onclick="return confirm('${message(code: 'usuario.confirma.baja', args:[usuario.username])}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
