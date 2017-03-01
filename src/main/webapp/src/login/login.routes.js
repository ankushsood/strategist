(function() {
'use strict';

	angular.module('login')
	.config(routeConfig);

	/**
	 * Configures the routes and views
	 */
	routeConfig.$inject = ['$stateProvider', '$httpProvider'];
	function routeConfig ($stateProvider, $httpProvider) {
	  $httpProvider.interceptors.push('loginHttpInterceptor');

	  // Routes
	  $stateProvider
		.state('login', {
		  url: '/',
		  templateUrl: 'src/login/login.html',
		  controller : 'LoginController',
		  controllerAs: 'loginCtrl',

	  });
	}

})();
