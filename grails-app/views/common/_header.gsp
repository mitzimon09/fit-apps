<h1><a class="home" href="${createLinkTo(dir:'')}" style="color:white;font-weight:normal;">FIT APPS</a></h1>
<div id="status">
    <sec:ifLoggedIn>
        Bienvenido <sec:loggedInUserInfo field="username" /><br/>
        <g:link class="perfil" controller="logout" style="color:white;font-weight:normal;"><g:message code="general.salir" /></g:link>
    </sec:ifLoggedIn>
</div>
