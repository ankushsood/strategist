(function() {
'use strict';

	angular.module('faculty')
	.config(routeConfig);

	/**
	 * Configures the routes and views
	 */
	routeConfig.$inject = ['$stateProvider', '$httpProvider'];
	function routeConfig ($stateProvider, $httpProvider) {

	  
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
		  }).state('teacher.standardSubjectBooks', {
			url: '/standardSubjectBooks',
			params : {selectedSubjectId : null},
			views: {
			  'detail@teacher' : {
				  templateUrl: 'src/faculty/standard/subjectBookTemplate.html',
				  controller : 'SubjectBookController',
				  controllerAs: 'subBookController'
			  },
			},
		  }).state('teacher.standardSubjectBooks.bookDetails', {
			params : {selectedBookId : null, selectedBookTitle: null},
			views: {
			  'bookDetails@teacher.standardSubjectBooks' : {
					templateUrl: 'src/faculty/standard/bookDetail.html',
					controller: 'SubjectBookDetailsController',
					controllerAs: 'subBookDetCtrl'
			  },
			},
		  })
		
	}

})();
