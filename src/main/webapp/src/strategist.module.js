(function() {
"use strict";

angular.module('strategist', ['ngResource', '720kb.datepicker', 'login','admin', 'ngTouch', 'angular.morris-chart', 'mwl.calendar', 'ui.bootstrap', 'colorpicker.module', 'faculty' /*, 'ui.select2'*/])
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
  }])
 .directive('loading',   ['$http' ,function ($http)
    {
        return {
            restrict: 'A',
            link: function (scope, elm, attrs)
            {
                scope.isLoading = function () {
                    return $http.pendingRequests.length > 0;
                };

                scope.$watch(scope.isLoading, function (v)
                {
                    if(v){
                        elm.show();
                    }else{
                        elm.hide();
                    }
                });
            }
        };

    }]);

config.$inject = ['$urlRouterProvider', '$httpProvider','$touchProvider'];
function config($urlRouterProvider, $httpProvider, $touchProvider) {

$touchProvider.ngClickOverrideEnabled(true);

var $cookies;
  angular.injector(['ngCookies']).invoke(['$cookies', function(_$cookies_) {
    $cookies = _$cookies_;
  }]);
  $urlRouterProvider.otherwise('/');
  
  $httpProvider.defaults.headers.common['X-Access-Token'] = $cookies.get('accessToken');
  $httpProvider.defaults.headers.common['Content-Type'] = 'application/json';
  
}

})();
