<html>
  <head>
    <title><g:message code="general.inicio" /></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
  </head>
  <body>
    <div class="nav">
      <span class="menuButton"><a class="home" href="${resource(dir: '')}"><g:message code="general.inicio" /></a></span>
      <span class="menuButton"><g:link class="list" controller="admin"><g:message code="general.admin" /></g:link></span>
      <span class="menuButton"><g:link class="list" controller="usuario" action="perfil"><g:message code="general.perfil" /></g:link></span>
      <span class="menuButton"><g:link class="list" controller="logout" ><g:message code="general.salir" /></g:link></span>
    </div>
    <div class="body">
      <h1>FIT APPS</h1>
      <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
      <p>Bienvenido al Sistema para el registro del Concuso de Programación FIT:</p>
      <ul>
        <li>Creación de Concursos</li>
        <li>Gestion de Usuario</li>
        <li>Gestion de Equipos</li>
        <li>Registro de Equipos</li>
      </ul>
    </div>
  </body>
</html>
