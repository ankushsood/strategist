(function() {
"use strict";

angular.module('faculty', ['ui.router',  'ngCookies', 'login.services', 'angularModalService', 'vAccordion', 'ngAnimate', 'ui.tree', 'textAngular', 'mwl.calendar', 'ui.bootstrap'])
.factory('alert', alert);
})();


Array.prototype.removeValue = function(name, value){
   var array = $.map(this, function(v,i){
      return v[name] === value ? null : v;
   });
   this.length = 0; //clear original array
   this.push.apply(this, array); //push all elements except the one we want to delete
}