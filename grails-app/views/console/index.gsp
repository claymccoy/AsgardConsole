<!doctype html>
<html>
<head>
  <title>com.netflix.asgard.console.Asgard Console</title>
  <meta name="layout" content="main">
  <r:require modules="codeMirrorGroovy"/>

</head>
<body>

<form><textarea id="code" name="code">
def asgard = asgardLocator.getInstance('nactest')
asgard.region.code
</textarea></form>

<button class="btn" type="button" ng-click="executeCode(editor.getValue())">Execute</button>

<div ng-bind-html-unsafe="result.html"></div>
<div class="alert alert-success" >{{result.resultEval}}</div>
<div class="alert alert-error" >{{result.error}}</div>
</body>

</html>