angular.module("app").controller("clienteCtrl", function($scope, $rootScope, $cookieStore, $http, clienteService) {
	
	$scope.limpar = function() {
		delete $scope.cliente;
		$scope.clienteForm.$setPristine();
	};

	$scope.salvar = function(cliente) {
		console.log("salvar");
		$http.post("rest/cliente/salvar", cliente).success(function(data) {
			delete $scope.cliente;
			$scope.clienteForm.$setPristine();
			msg(data);
		}).error(function(data, status) {
			msg(data);
		});
	};

	$scope.excluir = function(cliente) {
		console.log("excluir");
		$http.post("rest/cliente/excluir", cliente).success(function(data) {
			delete $scope.cliente;
			$scope.clienteForm.$setPristine();
		}).error(function(data, status) {
			$scope.messageErro = "Não foi possível executar esta ação."
		});
	};

	$scope.buscarClienteLogado = function() {
		if($rootScope.globals.cliente){
			$scope.cliente = $rootScope.globals.cliente;
			return;
		}
		var perfis = $rootScope.globals.usuario.perfis;
		for (var int = 0; int < perfis.length; int++) {
			if ('CLI' == (perfis[int].nome)) {
				clienteService.buscarPorUsuario($rootScope.globals.usuario)
						.success(function(data) {
							$rootScope.globals.cliente = data.obj;
							$scope.cliente = $rootScope.globals.cliente;
						});
			}
		}
	};
	
	$scope.selecionarEndereco = function(endereco) {
		$scope.endereco = endereco;
	};

	$scope.enderecoOK = function(endereco) {
		delete $scope.endereco;
	};

	$scope.buscarClienteLogado();

});
