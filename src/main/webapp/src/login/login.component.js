(function () {
"use strict";

angular.module('login')
.controller('LoginController', LoginController);


LoginController.$inject = ['$location', 'LoginService', 'CurrentUserService'];
function LoginController($location, LoginService, CurrentUserService) {
  var $ctrl = this;

  $ctrl.logout = function () {
    // Make sure user is logged in
    if (!CurrentUserService.isAuthenticated()) {
      return;
    }

    LoginService.logout(CurrentUserService.getAccessToken()).then(function () {
      CurrentUserService.saveToken('', '');
      $location.path("/");
    });
  };
}


})();
