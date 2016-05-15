angular.module("app").controller("fabricanteCtrl",function($scope, $http, fabricanteAPI) {
	
	$scope.fabricantes = [];
	
	$scope.carregarTodos = function() {
		fabricanteAPI.buscarTodos().success(function(data) {
			$scope.fabricantes = data.obj;
		}).error(function(data, status) {
			msgErro("Não foi possível retornar os dados.");
		});
	};

	$scope.limpar = function() {
		delete $scope.fabricante;
		$scope.fabricanteForm.$setPristine();
	};

	$scope.salvar = function(fabricante) {
		fabricanteAPI.salvar(fabricante).success(function(data) {
			$scope.carregarTodos();
			delete $scope.fabricante;
			$scope.fabricanteForm.$setPristine();
			msgOK("Registro salvo com sucesso.");
		}).error(function(data, status) {
			msg(data);
		});
	};

	$scope.isSelecionado = function(fabricantes) {
		return fabricantes.some(function(fabricante) {
			return fabricante.selecionado;
		});
	};

	$scope.excluir = function(fabricante) {
		fabricanteAPI.excluir(fabricante).success(function(data) {
			$scope.carregarTodos();
			delete $scope.fabricante;
			$scope.fabricanteForm.$setPristine();
			msgOK("Registro excluído com sucesso");
		}).error(function(data, status) {
			msgErro("Não foi possível excluir");
		});
	};

	$scope.selecionar = function(fabricante) {
		$scope.fabricante = fabricante;
		$('#myModal').modal('show');
	}

	$('#myModal').on('hidden.bs.modal', function(e) {
		$scope.limpar();
	})
	
	$scope.carregarTodos();
	
});
