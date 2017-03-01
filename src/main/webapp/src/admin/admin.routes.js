(function() {
'use strict';

	angular.module('admin')
	.config(routeConfig);

	/**
	 * Configures the routes and views
	 */
	routeConfig.$inject = ['$stateProvider', '$httpProvider'];
	function routeConfig ($stateProvider, $httpProvider) {
	 // $httpProvider.interceptors.push('loginHttpInterceptor');

	  // Routes
	  $stateProvider
		.state('home', {
		  url: '/admin/home',
		  templateUrl: 'src/admin/home.html',
		  controller : 'AdminHomeController',
		  controllerAs: 'adminHomeCtrl',

	  });
	}

})();
