<html>
    <head>
        <title><g:message code="concurso.edita" args="[concurso.detalle]" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:javascript library="jquery" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="home" action="admin"><g:message code="general.admin" /></g:link></span>
            <span class="menuButton"><g:link class="list" action="lista"><g:message code="concurso.lista" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="concurso.edita" args="[concurso.detalle]" /></h1>
            <g:if test="${flash.message}">
	            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${concurso}">
            <div class="errors">
                <g:renderErrors bean="${concurso}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="actualiza" method="post" >
                <g:hiddenField name="id" value="${concurso?.id}" />
                <g:hiddenField name="version" value="${concurso?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
	                                <label for="nombre"><g:message code="concurso.nombre" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: concurso, field: 'nombre', 'errors')}">
                                    <g:textField name="nombre" maxlength="64" value="${concurso?.nombre}" />
                                </td>
                            </tr>

							<tr class="prop">
                                <td valign="top" class="name">
                                  <label for="semestre"><g:message code="concurso.semestre" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: concurso, field: 'semestre', 'errors')}">
                                    <g:select name="semestre" from="${concurso.constraints.semestre.inList}" value="${concurso?.semestre}" valueMessagePrefix="concurso.semestre"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="año"><g:message code="concurso.año" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: concurso, field: 'año', 'errors')}">
                                    <g:datePicker name="año" precision="year" value="${concurso?.año}"  />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="lista" class="save" value="${message(code: 'default.button.update')}" /></span>
                </div>
            </g:form>
        </div>
	<g:javascript>
        jQuery(document).ready(function() {
            jQuery('#username').focus();
        });
    </g:javascript>
    </body>
</html>

