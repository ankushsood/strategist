(function() {
'use strict';

angular.module('admin')
.controller('AdminMenuController', AdminMenuController)
.controller('AdminHomeController', AdminHomeController)
.controller('AdminHeaderCotroller', AdminHeaderCotroller)
.controller('AdminController', AdminController)

AdminHeaderCotroller.$inject = ['$location', '$cookieStore'];

	function AdminMenuController(){
				
	
	}
	function AdminHomeController(){
				
	
	}
	function AdminHeaderCotroller($location, $cookieStore){

		
				console.log('-------------------------------');
				
				var this_ = this;
				
				this_.sss = 'asdfasfasf';
				this_.logout = function(){
					console.log('~~~~~~~~~~~~~~' + $cookieStore.get('accessToken'));
					
					
					$cookieStore.remove('accessToken');
					$cookieStore.remove('user');
					$location.path("/");
					console.log('~~~~~~~~~~~~~~' + $cookieStore.get('accessToken'));

					}

	
	}
	function AdminController(){
				
	
	}
	
		
})();
