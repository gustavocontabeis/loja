(function () {
    'use strict';

    angular
        .module('app')
        .controller('loginCtrl', loginCtrl);

    loginCtrl.$inject = ['$location', 'AuthenticationService', 'FlashService', '$rootScope'];
    
    function loginCtrl($location, AuthenticationService, FlashService, $rootScope) {
        var vm = this;
        vm.login = login;
        var mensagemDeErro = "";

        (function initController() {
            AuthenticationService.ClearCredentials();
        })();

        function login() {
            vm.dataLoading = true;
            AuthenticationService.Login(vm.username, vm.password, function (response) {
                if (response.ok) {
                    AuthenticationService.SetCredentials(vm.username, vm.password, response.obj);
                    $location.path('/');
                    msg(response);
                } else {
                	console.log("aaaaaa");
                	vm.mensagemDeErro = response.msg;
                	msgErro(response.msg);
                    FlashService.Error(response.msg);
                    vm.dataLoading = false;
                }
            });
        };
        
    }

})();
