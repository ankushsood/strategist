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

		.state('home.studentList', {
			url: '/studets',
			views: {
			  'detail@home' : {
				  templateUrl: 'src/admin/student/list.html',
				  controller : 'StudentListController',
				  controllerAs: 'stuList',
				 

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
		  .state('home.viewStudentDetails', {
			url: '/profile',
			views: {
			  'detail@home' : {
				  templateUrl: 'src/admin/student/studentProfile.html',
				  controller : 'ViewStudentDetailsController',
				  controllerAs: 'studentCtrl'
			  },
			},
		  })
		
	}

})();
