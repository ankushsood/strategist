(function() {
"use strict";

angular.module('strategist', ['ngResource', '720kb.datepicker', 'login','admin', 'angular.morris-chart', 'faculty' /*, 'ui.select2'*/])
.config(config)
.directive("fileinput", [function() {
    return {
      scope: {
        fileinput: "=",
        filepreview: "="
      },
      link: function(scope, element, attributes) {
        element.bind("change", function(changeEvent) {
          scope.fileinput = changeEvent.target.files[0];
          var reader = new FileReader();
          reader.onload = function(loadEvent) {
            scope.$apply(function() {
              scope.filepreview = loadEvent.target.result;
            });
          }
          reader.readAsDataURL(scope.fileinput);
        });
      }
    }
  }]);

  
config.$inject = ['$urlRouterProvider', '$httpProvider'];
function config($urlRouterProvider, $httpProvider) {

var $cookies;
  angular.injector(['ngCookies']).invoke(['$cookies', function(_$cookies_) {
    $cookies = _$cookies_;
  }]);
	console.log($cookies)
  $urlRouterProvider.otherwise('/');
  console.log($httpProvider)
  
  $httpProvider.defaults.headers.common['X-Access-Token'] = $cookies.get('accessToken');
  $httpProvider.defaults.headers.common['Content-Type'] = 'application/json';
  
}

})();
