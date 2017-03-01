(function () {
"use strict";

angular.module('login')
.controller('LoginController', LoginController)


LoginController.$inject = ['$rootScope', '$location', '$cookieStore', 'UserService'	];
function LoginController( $rootScope, $location, $cookieStore, UserService) {
  var loginCtrl = this;
    loginCtrl.login = function() {
    var params = {username: loginCtrl.username, password: loginCtrl.password};
	UserService.authenticate($.param(params), function(authenticationResult) {
			var accessToken = authenticationResult.token;
			$rootScope.accessToken = accessToken;
			if (loginCtrl.rememberMe) {
				$cookieStore.put('accessToken', accessToken);
			}
			UserService.get(function(user) {
				$rootScope.user = user;
				$cookieStore.put('user', user);
				console.log('$rootScope.user' + JSON.stringify($rootScope.user));
				if($rootScope.user.roles.ROLE_ADMIN == true){
					console.log('I am Admin Bitch!!!!!!!!!!!');
					$location.path("/admin/home");
				}else{
					console.log('Fuck off M not admin!!!!!!!!!!!');
				$location.path("/");
				}
				
			});
			
			
			console.log('$rootScope.user = ' + $cookieStore.get('accessToken'));
			
			
		});
  };
}




		
})();
