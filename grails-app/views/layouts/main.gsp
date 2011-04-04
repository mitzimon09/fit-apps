<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
  <head>
    <title>FIT APPS - <g:layoutTitle default="Grails" /></title>
    <link rel="stylesheet" href="${resource(contextPath:"",dir:'css',file:'main.css')}" />
    <link rel="shortcut icon" href="${resource(contextPath:"",dir:'images',file:'favicon.ico')}" type="image/x-icon" />
    <g:layoutHead />
    <g:javascript library="application" />
  </head>
  <body>
    <div id="spinner" class="spinner" style="display:none;">
      <img src="${resource(contextPath:"",dir:'images',file:'spinner.gif')}" alt="Spinner" />
    </div>
    <div id="header">
      <g:render template="/common/header" />
    </div>
    <g:layoutBody />
    <div id="footer">
      <g:render template="/common/footer" />
    </div>
  </body>
</html>
