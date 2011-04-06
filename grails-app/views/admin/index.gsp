<html>
  <head>
    <title><g:message code="admin.titulo"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
  </head>
  <body>
    <div class="nav">
      <span class="menuButton"><a class="home" href="${resource(dir: '')}"><g:message code="general.inicio"/></a></span>
      <span class="menuButton"><g:link class="list" controller="usuario"><g:message code="usuario.lista"/></g:link></span>
      <span class="menuButton"><g:link class="list" controller="concurso"><g:message code="concurso.lista"/></g:link></span>
    </div>
    <div class="body">
      <h1><g:message code="admin.titulo"/></h1>
      <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
      </g:if>
    </div>
  </body>
</html>
