(function() {
'use strict';

angular.module('faculty').controller('FacultyMenuController', FacultyMenuController);
angular.module('faculty').controller('FacultyHomeController', FacultyHomeController);
angular.module('faculty').controller('FacultyHeaderCotroller', FacultyHeaderCotroller);
//angular.module('faculty').controller('FacultyController', FacultyController)
	
	FacultyHeaderCotroller.$inject = ['$location', '$cookieStore'];

	function FacultyMenuController(){
				
	
	}
	function FacultyHomeController(){
				
	
	}
	function FacultyHeaderCotroller($location, $cookieStore){
		var this_ = this;
		this_.sss = 'asdfasfasf';
		this_.logout = function(){
			$cookieStore.remove('accessToken');
			$cookieStore.remove('user');
			$location.path("/");
		}
	}
})();
