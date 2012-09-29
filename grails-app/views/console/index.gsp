<!doctype html>
<html>
<head>
  <title>com.netflix.asgard.console.Asgard Console</title>
  <meta name="layout" content="main">
  <r:require modules="codeMirrorGroovy"/>

</head>
<body>

<div id="codeFrame" ng-mouseleave="configureCode()"><textarea id="code" name="code">
${code}</textarea></div>

<div id="controls" class="well well-small">
    <div id="params" class="row" ng-repeat="consoleParam in configResult.consoleParams">
      <div class="span2">{{consoleParam.name}}</div>
      <div class="span10">
        <input class="param" title="{{consoleParam.description}}" type="text" ng-model="param"
               ng-change="addParam(consoleParam.name, param)" placeholder="{{consoleParam.defaultValue}}" value="{{consoleParam.value}}">
      </div>
    </div>
    <button class="btn btn-primary" type="button" ng-click="executeCode()">Execute</button>
</div>

%{--<div class="text-success" >{{result.resultEval}}</div>--}%
<div class="text-error" >{{result.error}}</div>
<div ng-bind-html-unsafe="result.html"></div>
</body>

</html>