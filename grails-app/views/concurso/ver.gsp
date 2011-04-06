<html>
    <head>
        <title><g:message code="concurso.ver" args="[concurso.detalle]" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="home" action="admin"><g:message code="general.admin" /></g:link></span>
            <span class="menuButton"><g:link class="list" action="lista"><g:message code="concurso.lista" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="nuevo"><g:message code="concurso.nuevo" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="concurso.ver" args="[concurso.detalle]" /></h1>
            <g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="concurso.detalle" /></td>
                            <td valign="top" class="value">${fieldValue(bean: concurso, field: "detalle")}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="concurso.nombre" /></td>
                            <td valign="top" class="value">${fieldValue(bean: concurso, field: "nombre")}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${concurso?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edita" value="${message(code: 'default.button.edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="elimina" value="${message(code: 'default.button.delete')}" onclick="return confirm('${message(code: 'concurso.confirma.baja', args:[concurso.detalle])}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>

