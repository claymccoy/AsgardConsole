<!doctype html>
<html>
<head>
  <title>com.netflix.asgard.console.Asgard Console</title>
  <meta name="layout" content="main">
  <r:require modules="console"/>
</head>
<body>

<div id="codeFrame" ng-mouseleave="configureCode()" ng-hide="${params.hideCode}"><textarea id="code" name="code">
${code}</textarea></div>
<h1>{{configResult.title}}</h1>
<div id="controls" class="well well-small" ng-hide="${params.hideControls}">
  <div class="row-fluid">
    <div class="span2"><button class="btn btn-primary" type="button" ng-click="executeCode()">Execute</button></div>
    <div class="span10"><div class="progress progress-striped active"><div ng-show="executing" class="bar" style="width: 100%;"></div></div></div>
    <div id="params" ng-repeat="consoleParam in configResult.consoleParams">
      <div class="span2">{{consoleParam.name}}</div>
      <div class="span10">
        <input class="param" title="{{consoleParam.description}}" type="text" ng-model="param"
               ng-change="addParam(consoleParam.name, param)" placeholder="{{consoleParam.defaultValue}}" value="{{consoleParam.value}}">
      </div>
    </div>
  </div>
</div>

%{--<div class="text-success" >{{result.resultEval}}</div>--}%
<div class="text-error" >{{result.error}}</div>
<div ng-bind-html-unsafe="result.html"></div>
</body>

</html>