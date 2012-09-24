<!doctype html>
<html>
<head>
  <title>Asgard Console</title>
  <meta name="layout" content="main">
  <r:require modules="codeMirrorGroovy"/>

</head>
<body>

<form><textarea id="code" name="code">
  'test: ' + (1 + 1)
</textarea></form>

<button class="btn" type="button" ng-click="executeCode(editor.getValue())">Execute</button>

<div class="alert alert-success" >{{result.resultEval}}</div>
<div class="alert alert-error" >{{result.error}}</div>
</body>

</html>