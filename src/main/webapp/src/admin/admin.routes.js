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
	 /* $stateProvider
		.state('home', {
		  url: '/admin/home',
		  templateUrl: 'src/admin/menu/leftMenu.html',
		  controller : 'AdminHomeController',
		  controllerAs: 'adminHomeCtrl',

	  });
	  
	  */
	  
		$stateProvider
		.state('home', {
			url: '/admin/home',
			views: {
			  '@' : {
				templateUrl: 'layout.html'
			  },
			  'top@home' : 	{
								templateUrl: 'src/admin/menu/adminTop.html'
							},
			  'left@home' : {	
								templateUrl: 'src/admin/menu/leftMenu.html',
								controller : 'AdminHomeController'
							},
			  'main@home' : { 
								templateUrl: 'src/admin/menu/adminMain.html'
							}
			},
		  })
		.state('home.menu', {
			url: '/adminMenu',
		  templateUrl: 'src/admin/menu/leftMenu.html',
		  controller : 'AdminHomeController',
		  controllerAs: 'adminHomeCtrl',
		  })
		.state('home.menu.detail', {
			url: '/adminHome',
			views: {
			  'detail@home' : {
				  templateUrl: 'src/admin/menu/leftMenu.html',
				  controller : 'AdminHomeController',
				  controllerAs: 'adminHomeCtrl'
			  },
			},
		  })
  
	}

})();
