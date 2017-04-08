(function() {
"use strict";

angular.module('strategist', ['ngResource', '720kb.datepicker', 'login','admin', 'ui.bootstrap'])
.config(config);

config.$inject = ['$urlRouterProvider'];
function config($urlRouterProvider) {
  // If user goes to a path that doesn't exist, redirect to public root
  $urlRouterProvider.otherwise('/');
}


})();
