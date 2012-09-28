(function() {
  var appName, module;
  module = angular.module('phonecatServices', ['ngResource']);
  module.service('Grails', function($resource) {
    return {
      getResource: function(scope) {
        return $resource("/" + appName + "/:controller/:action/:id", {
          controller: scope.controller || '',
          action: scope.action || '',
          id: scope.id || ''
        }, function() {});
      }
    };
  });
  angular.module('phonecatFilters', []).filter('checkmark', function() {
    return function(input) {
      if (input) {
        return '\u2713';
      } else {
        return '\u2718';
      }
    };
  });
  this.PhoneListCtrl = function($scope, Grails) {
    $scope.phones = Grails.getResource($scope).query({
      action: 'query'
    });
    return $scope.orderProp = 'age';
  };
  this.PhoneDetailCtrl = function($scope, Grails) {
    $scope.phone = Grails.getResource($scope).get({
      action: 'query'
    }, function(phone) {
      return $scope.setMainImage(phone.images[0]);
    });
    return $scope.setMainImage = function(imageUrl) {
      return $scope.mainImageUrl = imageUrl;
    };
  };
  this.ConsoleCtrl = function($scope, Grails) {
    $('body').tooltip({
      selector: '.param',
      placement: 'right'
    });
    $scope.params = {};
    $scope.editor = CodeMirror.fromTextArea(document.getElementById("code"), {
      lineNumbers: true,
      matchBrackets: true,
      mode: "text/x-groovy"
    });
    $scope.executeCode = function() {
      return $scope.result = Grails.getResource($scope).save({
        action: 'execute'
      }, {
        code: $scope.editor.getValue(),
        consoleParams: $scope.params,
        consoleParamTypes: $scope.configResult.consoleParams
      });
    };
    $scope.configureCode = function() {
      return $scope.configResult = Grails.getResource($scope).save({
        action: 'configure'
      }, {
        code: $scope.editor.getValue(),
        consoleParams: $scope.params
      });
    };
    $scope.addParam = function(paramName, paramValue) {
      return $scope.params[paramName] = paramValue;
    };
    return $scope.configureCode();
  };
  appName = 'AsgardConsole';
  angular.module(appName, ['phonecatFilters', 'phonecatServices']);
}).call(this);
