(function() {
'use strict';

	
	angular.module('admin').controller('StudentListController', StudentListController );
	angular.module('admin').controller('ViewStudentDetailsController', ViewStudentDetailsController );
	
	
	
	StudentListController.$inject=['ListStudentService']

	function StudentListController(ListStudentService){
		var _this = this;
		console.log('CONTROLLER------------'   );
					_this.test = 'aaaaaaaaa';
		var students = ListStudentService.listStu(function(students) {
			return students;
		});
		
		//ListStudentService.getMessages();
		
		students.$promise.then(function (result) {
			console.log(result[0]);
			_this.studentList = result;
			_this.test = result[0];
		});
		
		
		
		
			
	}
	
	function ViewStudentDetailsController(){
		console.log('aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa');
	}	
		
		
})();
