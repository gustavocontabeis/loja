angular.module("app").controller("appCtrl",function($scope, $rootScope, $cookieStore, $location, AuthenticationService) {
	$scope.isLogged = function(){
		return AuthenticationService.IsLogged();
	}
	$scope.isPerfil = function(perfil){
		if($rootScope.globals.isLogado){
			var perfis = $rootScope.globals.usuario.perfis;
			for (var int = 0; int < perfis.length; int++) {
				if(perfil == (perfis[int].nome)){
					return true;
				}
			}
		}
		return false;
	}
	$scope.idUsuario = function(){
		if($rootScope.globals.isLogado){
			return $rootScope.globals.usuario.id;
		}
	}
	$scope.logout = function(){
		AuthenticationService.ClearCredentials();
	}
	$scope.finalizarCompra = function(){
		AuthenticationService.ClearCredentials();
	}
	$scope.editarPerfil = function(){
		$location.path("/cliente");
	}
	$scope.finalizarCompra = function(){
		$location.path("/finalizarCompra");
	}
});
