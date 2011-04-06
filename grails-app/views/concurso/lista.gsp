<html>
    <head>
        <title><g:message code="concurso.lista" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
		<g:javascript library="jquery" />
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><g:link class="home" action="admin"><g:message code="general.admin" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="nuevo"><g:message code="concurso.nuevo" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="concurso.lista" /></h1>
            <g:if test="${flash.message}">
	        	<div class="message">${flash.message}</div>
            </g:if>
			<div class="search">
              <g:form method="post">
                <div>
                  <table>
                    <tbody>
                      <tr>
                        <td style="vertical-align:middle;width:95px;">
                          <label for="filtro"><g:message code="concurso.buscar" />:</label>
                        </td>
                        <td style="width:320px; vertical-align: middle;">
                          <input type="text" maxlength="64" size="32" id="filtro" name="filtro" value="${params.filtro}" style="width:300px;"/>
                        </td>
                        <td>
                          <g:actionSubmit action="lista" value="${message(code:'general.buscar')}" class="save" style="vertical-align: middle;" />
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </g:form>
            </div>
            <div class="list">
                <table id="concursos">
                    <thead>
                        <tr>
                            <g:sortableColumn property="detalle" title="${message(code:'concurso.detalle')}" />
                            <g:sortableColumn property="nombre" title="${message(code:'concurso.nombre')}" />
                            <g:sortableColumn property="año" title="${message(code:'concurso.año')}" />
                        </tr>
                    </thead>
                    <tbody>
	                    <g:each in="${concursos}" status="i" var="concurso">
	                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
	                            <td><g:link action="ver" id="${concurso.id}">${fieldValue(bean: concurso, field: "detalle")}</g:link></td>
    	                        <td>${fieldValue(bean: concurso, field: "nombre")}</td>
	                            <td><g:formatDate date="${concurso.año}" /></td>
							</tr>
                	    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${totalDeConcursos}" />
            </div>
        </div>
		<g:javascript>
          jQuery$(document).ready(function() {

            highlightTableRows('concursos')
          });
        </g:javascript>
    </body>
</html>

