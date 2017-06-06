(function() {
"use strict";

angular.module('strategist', ['ngResource', '720kb.datepicker', 'login','admin', 'ngTouch', 'angular.morris-chart', 'mwl.calendar', 'ui.bootstrap', 'colorpicker.module', 'faculty' /*, 'ui.select2'*/])
.config(config)
.directive('resize', ['$window', function ($window) {
    return function (scope, element) {
        var w = angular.element($window);
        scope.getWindowDimensions = function () {
            return {
                'h': w.height(),
                'w': w.width()
            };
        };
        scope.$watch(scope.getWindowDimensions, function (newValue, oldValue) {
            scope.windowHeight = newValue.h;
            scope.windowWidth = newValue.w;

            scope.style = function () {
				if(jQuery('#selectedBookDetails').html().trim()!== 'No Chapter Selected' && newValue.w <= 991){
						$('#bookListDiv').hide();
					}else{
						$('#bookListDiv').show();
				}
				return {
                };
            };

        }, true);

        w.bind('resize', function () {
            scope.$apply();
        });
    }
}])
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
