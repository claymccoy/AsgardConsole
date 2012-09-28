
@PhoneListCtrl = ($scope, Grails) ->
  $scope.phones = Grails.getResource($scope).query({action: 'query'})
  $scope.orderProp = 'age'

@PhoneDetailCtrl = ($scope, Grails) ->
  $scope.phone = Grails.getResource($scope).get {action: 'query'}, (phone) ->
    $scope.setMainImage(phone.images[0])
  $scope.setMainImage = (imageUrl) ->
    $scope.mainImageUrl = imageUrl

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
  $scope.configureCode = () ->
    $scope.configResult = Grails.getResource($scope).save {action: 'configure'}, {code: $scope.editor.getValue(), consoleParams: $scope.params}
  $scope.addParam = (paramName, paramValue) ->
    $scope.params[paramName] = paramValue
  $scope.configureCode()



