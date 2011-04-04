<html>
    <head>
        <title><g:message code="usuario.edita" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:javascript library="jquery" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="admin" controller="admin"><g:message code="general.admin" /></g:link></span>
            <span class="menuButton"><g:link class="list" action="lista"><g:message code="usuario.lista" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="usuario.edita" /> ${usuario.username}</h1>
            <g:if test="${flash.message}">
              <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${usuario}">
            <div class="errors">
                <g:renderErrors bean="${usuario}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="actualiza" method="post" >
                <g:hiddenField name="id" value="${usuario?.id}" />
                <g:hiddenField name="version" value="${usuario?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="username"><g:message code="usuario.username" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: usuario, field: 'username', 'errors')}">
                                    <g:textField name="username" maxlength="64" value="${usuario?.username}" style="width:400px;"/>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="password"><g:message code="usuario.password" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: usuario, field: 'password', 'errors')}">
                                    <g:passwordField name="password" maxlength="128" value="${usuario?.password}" style="width:400px;"/>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nombre"><g:message code="usuario.nombre" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: usuario, field: 'nombre', 'errors')}">
                                    <g:textField name="nombre" maxlength="64" value="${usuario?.nombre}" style="width:400px;"/>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="apellido"><g:message code="usuario.apellido" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: usuario, field: 'apellido', 'errors')}">
                                    <g:textField name="apellido" maxlength="64" value="${usuario?.apellido}" style="width:400px;"/>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="enabled"><g:message code="usuario.enabled" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: usuario, field: 'enabled', 'errors')}">
                                    <g:checkBox name="enabled" value="${usuario?.enabled}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="correo"><g:message code="usuario.correo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: usuario, field: 'correo', 'errors')}">
                                    <g:textField name="correo" maxlength="64" value="${usuario?.correo}" style="width:400px;"/>
                                </td>
                            </tr>

                            <g:if test="${roles}">
                                <tr class="prop">
                                    <td valign="top" class="name">
                                        <label for="roles"><g:message code="usuario.roles" /></label>
                                    </td>
                                    <td valign="top" class="value ${hasErrors(bean: usuario, field: 'roles', 'errors')}">
                                        <g:each var="entry" in="${roles}">
                                            <g:checkBox name="${entry.key.authority}" value="${entry.value}"/> ${entry.key.authority}<br/>
                                        </g:each>
                                    </td>
                                </tr>
                            </g:if>

                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="edita" class="save" value="${message(code: 'default.button.update.label', default: 'Edita')}" /></span>
                </div>
            </g:form>
        </div>
    <g:javascript>
        //document.forms[0].nombre.focus();
        //document.forms[0].nombre.select();
        $(document).ready(function() {
            $('#username').focus();
        });
    </g:javascript>
    </body>
</html>
