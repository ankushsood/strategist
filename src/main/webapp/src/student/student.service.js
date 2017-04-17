(function() {
'use strict';

	angular.module('student')
	.config(routeConfig);

	/**
	 * Configures the routes and views
	 */
	routeConfig.$inject = ['$stateProvider', '$httpProvider'];
	function routeConfig ($stateProvider, $httpProvider) {
	  
		$stateProvider
		.state('home', {
			url: '/student/home',
			views: {
			  '@' : {
				templateUrl: 'layout.html'
			  },
			  'top@home' : 	{
								templateUrl: 'src/student/menu/studentTop.html',
								controller : 'StudentHeaderCotroller'
							},
			  'left@home' : {	
								templateUrl: 'src/student/menu/leftMenu.html',
								controller : 'StudentMenuController'
							},
			  'main@home' : { 
								templateUrl: 'src/student/menu/adminMain.html',
								controller : 'StudentHomeController'
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
