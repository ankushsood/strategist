(function() {
"use strict";

angular.module('faculty', ['ui.router',  'ngCookies', 'login.services', 'angularModalService', 'vAccordion', 'ngAnimate', 'ui.tree', 'textAngular', 'mwl.calendar', 'ui.bootstrap'])
.factory('alert', alert);
})();


  alert.$inject=['$uibModal'];
  function alert($uibModal) {

    function show(action, event, bookList, chapterList) {
      return $uibModal.open({
        templateUrl: 'src/faculty/standard/courseSchedulePlanner/subjectPlanModalContent.html',
        controller: function() {
          var vm = this;
          vm.action = action;
          vm.event = event;
		  vm.bookList = bookList;
		  vm.chapterList = chapterList;
        },
        controllerAs: 'vm'
      });
    }

    return {
      show: show
    };

  }
  