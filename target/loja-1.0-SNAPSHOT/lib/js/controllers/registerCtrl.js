(function () {
    'use strict';

    angular
        .module('app')
        .controller('registerCtrl', registerCtrl);

    registerCtrl.$inject = ['UserService', '$location', '$rootScope', 'FlashService'];
    function registerCtrl(UserService, $location, $rootScope, FlashService) {
    	console.log("registerCtrl!");
        var vm = this;

        vm.register = register;

        function register() {
        	console.log("register!");
            vm.dataLoading = true;
            UserService.Create(vm.user)
                .then(function (response) {
                    if (response.success) {
                        FlashService.Success('Registration successful', true);
                        $location.path('/login');
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }
        
        vm.register2 = function register2() {
        	console.log("register2!");
        }
    }

})();
