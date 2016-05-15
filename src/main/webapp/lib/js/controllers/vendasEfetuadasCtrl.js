angular.module("app").controller("vendasEfetuadasCtrl",function($scope, $http, vendasEfetuadasAPI) {
	
	$scope.carrinhos = [];
	$scope.carrinho = {};
	
	$scope.carregarTodos = function() {
		console.log("xxxxxxxxxxxxxxxxxxxx");alert();
		vendasEfetuadasAPI.buscarTodos().success(function(data) {
			console.log("xx");
			$scope.carrinhos = data.obj;
		}).error(function(data, status) {
			msgErro("Não foi possível retornar os dados.");
		});
	};

	$scope.salvar = function(carrinho) {
		vendasEfetuadasAPI.salvar(carrinho).success(function(data) {
			$scope.carregarTodos();
			delete $scope.carrinho;
			$scope.carrinhoForm.$setPristine();
			msgOK("Registro salvo com sucesso.");
		}).error(function(data, status) {
			msg(data);
		});
	};

	$scope.excluir = function(carrinho) {
		vendasEfetuadasAPI.excluir(carrinho).success(function(data) {
			$scope.carregarTodos();
			delete $scope.carrinho;
			$scope.carrinhoForm.$setPristine();
			msgOK("Registro excluído com sucesso");
		}).error(function(data, status) {
			msgErro("Não foi possível excluir");
		});
	};

	$scope.selecionar = function(carrinho) {
		$scope.carrinho = carrinho;
		$('#myModal').modal('show');
	}

	$('#myModal').on('hidden.bs.modal', function(e) {
		$scope.limpar();
	})
	
	$scope.carregarTodos();
});
