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
								templateUrl: 'src/admin/menu/adminTop.html',
								controller : 'AdminHeaderCotroller'
							},
			  'left@home' : {	
								templateUrl: 'src/admin/menu/leftMenu.html',
								controller : 'AdminMenuController'
							},
			  'main@home' : { 
								templateUrl: 'src/admin/menu/adminMain.html',
								controller : 'AdminHomeController'
							}
			},
		  })

		.state('home.addStudent', {
			url: '/addStudent',
			views: {
			  'detail@home' : {
				  templateUrl: 'src/admin/student/add.html',
				  controller : 'AddStudentController',
				  controllerAs: 'addStudentCtrl'
			  },
			},
		  })
		  .state('home.editStudent', {
		  
			url: '/editStudent',
			views: {
			  'detail@home' : {
				  templateUrl: 'src/admin/student/add.html',
				  controller : 'AddStudentController',
				  controllerAs: 'addStudentCtrl'
			  },
			},
		  })
		
	}

	
	angular.module('admin').controller('AdminMenuController', function(){
			})
		.controller('AdminHomeController', function(){
			})
		.controller('AdminHeaderCotroller', function(){
		}).controller('AddStudentController', function(){});
		
		
		
})();
