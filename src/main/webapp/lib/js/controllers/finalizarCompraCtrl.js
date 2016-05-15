angular.module("app").controller("finalizarCompraCtrl",function($scope, $http, $rootScope, produtoAPI, clienteService, carrinhoComprasService) {
	
	$scope.enderecos = [];
	$scope.endereco = {};
	$scope.carrinho = {};
	$scope.carrinho.enderecoEntrega = {};
	$scope.carrinho.pagamento = {};
	
	$scope.init=function(){
		var carrinho = {}; 
		produtoAPI.buscarCarrinho().success(function(data){
			carrinho = data.obj;
			$rootScope.globals.carrinho = carrinho; 
			$scope.carrinho = carrinho;
			if(clienteService.isCliente()){
				$scope.buscarEnderecos();
			}
		}).error(function(data){
			msg(data);
		});
	}
	
	$scope.calcularFrete=function(cep){
		produtoAPI.calcularFrete(cep).success(function(data){
			$scope.carrinho.valorFrete = data.obj;
			$scope.carrinho.valorFinal = ($scope.carrinho.valorFinal + $scope.carrinho.valorFrete); 
		}).error(function(data){
			msg(data);
		});
	}
	
	$scope.removerCompraDoCarrinho = function(compra) {
		produtoAPI.removerCompraDoCarrinho(compra).success(function(data){
			//$scope.valorFrete = data.obj;
			msg(data);
		}).error(function(data){
			msg(data);
		});
		$scope.carrinho.compras.splice( $scope.carrinho.compras.indexOf(compra),1);
	}
	
	$scope.buscarEnderecos = function() {
		clienteService.buscarEnderecos($rootScope.globals.usuario).success(function(data){
			$scope.enderecos = data.obj.enderecos;
		}).error(function(data){
			msg(data);
		});
	}
	
	$scope.selecionarEndereco = function(endereco) {
		$scope.carrinho.enderecoEntrega = endereco;
		$('#linkEnderecoSelecionado').tab('show');
	}
	
	$scope.salvarEndereco = function(endereco){
		clienteService.salvarEndereco($scope.endereco, $rootScope.globals.usuario).success(function(data){
			$scope.buscarEnderecos();
		}).error(function(data){msg(data)});
	}
	
	$scope.excluirEndereco = function(endereco){
		clienteService.excluirEndereco(endereco).success(function(data){
			$scope.buscarEnderecos();
		}).error(function(data){msg(data)});
	}
	
	$scope.finalizarCompra = function(){
		carrinhoComprasService.finalizarCompra($scope.carrinho).success(function(data){
			if(!data.ok){
				msgErro(data.msg)
			}else{
				$scope.carrinho = data.obj; 
			}
			msgOK("A compra foi concluída com sucesso.");
			delete $scope.carrinho;
			$scope.enderecos = [];
		}).error(function(data){
			msg(data)
		});
	}
	
	$scope.init();
});
