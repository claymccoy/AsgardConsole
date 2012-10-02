
@ConsoleCtrl = ($scope, Grails) ->
  $('body').tooltip({
    selector: '.param',
    placement: 'right'
  });
  $scope.params = {}
  $scope.editor = CodeMirror.fromTextArea(document.getElementById("code"), {
    lineNumbers: true,
    matchBrackets: true,
    mode: "text/x-groovy"
  });
  $scope.executeCode = () ->
    $scope.result = Grails.getResource($scope).save {action: 'execute'}, {code: $scope.editor.getValue(), consoleParams: $scope.params, consoleParamTypes: $scope.configResult.consoleParams}
    $scope.$watch('result.html', (newValue, oldValue) ->
      $('.sparkline').sparkline('html', { enableTagOptions: true })
    )
  $scope.configureCode = () ->
    $scope.configResult = Grails.getResource($scope).save {action: 'configure'}, {code: $scope.editor.getValue(), consoleParams: $scope.params}
  $scope.addParam = (paramName, paramValue) ->
    $scope.params[paramName] = paramValue
  $scope.configureCode()



