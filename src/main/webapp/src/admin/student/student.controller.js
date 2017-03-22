(function() {
'use strict';

	
	angular.module('admin').controller('StudentListController', StudentListController );
	angular.module('admin').controller('ViewStudentDetailsController', ViewStudentDetailsController );
	angular.module('admin').controller('AddStudentBadge', AddStudentBadge );
	
	
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
	
	
	ViewStudentDetailsController.$inject=['ModalService']

	function ViewStudentDetailsController(ModalService){
		var _this = this;
		_this.show = function() {
			ModalService.showModal({
            templateUrl: 'src/admin/student/addBadge.html',
			controller: "AddStudentBadge",
			controllerAs: 'addBadge'

        }).then(function(modal) {
            modal.element.modal();
			console.log('THEN-------------');
            modal.close.then(function(result) {
				console.log('---------asdfsafdff-------' + result.desc + "-----------" );
            });
        });
    };
		
	}

	AddStudentBadge.$inject=['close']
	function AddStudentBadge( close) {
		var _this = this;
		_this.close = function(result) {
			close(result, 500); // close, but give 500ms for bootstrap to animate
		};

	}

	
})();
