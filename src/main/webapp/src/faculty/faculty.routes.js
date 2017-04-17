(function() {
'use strict';

	angular.module('faculty')
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
		.state('teacher', {
			url: '/home',
			views: {
			  '@' : {
				templateUrl: 'layout.html'
			  },
			  'top@teacher' : 	{
								templateUrl: 'src/faculty/menu/facultyTop.html',
								controller : 'FacultyHeaderCotroller',
								controllerAs: 'facultyHeader'
							},
			  'left@teacher' : {	
								templateUrl: 'src/faculty/menu/facultyLeft.html',
								controller : 'FacultyMenuController'
							},
			  'main@teacher' : { 
								templateUrl: 'src/faculty/menu/facultyMain.html',
								controller : 'FacultyHomeController'
							}
			},
		  })
		  .state('teacher.standardList', {
			url: '/standards',
			views: {
			  'detail@teacher' : {
				  templateUrl: 'src/faculty/standard/standardList.html',
				  controller : 'StandardListController',
				  controllerAs: 'standardListCtrl'
			  },
			},
		  })
		
	}

})();
