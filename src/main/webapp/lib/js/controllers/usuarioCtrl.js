angular.module("app").controller("usuarioCtrl", function($scope, $http, usuarioAPI, config, $rootScope){
	
	//$scope.teste = "xxxx";
	//$scope.usu = $rootScope.globals.currentUser.username;
	
	$scope.logar = function(usuario){
		usuarioAPI.logar(usuario).success(function(data) {
			if(data.ok){
				$scope.logado = data.obj;
				config.usuario = data.obj;
				msg(data);
			}else{
				msg(data);
			}
		}).error(function(data, status) {
			msg(data);
		});
	};
	$scope.buscar = function(){
		usuarioAPI.buscar().success(function(data) {
			console.log(data);
		}).error(function(data, status) {
			msg(data);
		});
	};
	$scope.sair = function(){
		usuarioAPI.sair().success(function(data) {
			if(data.ok){
				delete $scope.logado;
				msg(data);
			}
		}).error(function(data, status) {
			msg(data);
		});
	};
	$scope.isLogado = function(){
		return $scope.logado != null;
	};
	
	$scope.usuarioLogado = function(){
		console.log("isLogado");
		if($rootScope 
				&& $rootScope.globals
				&& $rootScope.globals.currentUser
				&& $rootScope.globals.currentUser.username){
			return $rootScope.globals.currentUser.username;
		}else{
			return "Nenhum usuario Logado";
		}

	};
});