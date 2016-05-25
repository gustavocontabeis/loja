angular.module("app").controller("produtoDescricaoCtrl", function($scope, $http, $routeParams, produtoAPI, $rootScope, $location, clienteService){
	$scope.carrinho = $rootScope.globals.carrinho;
	$scope.imagem = null;
	$scope.compra = {};
	$scope.quantidade = 1;
	
	$scope.carregarProduto = function(id){
		
		produtoAPI.buscar(id).success(function(data){
			$scope.produto = data.obj;
		}).error(function(data, status){
			msg(data);
		});
		
		produtoAPI.buscarCarrinho().success(function(data){
			$rootScope.globals.carrinho = data.obj;
			$scope.quantidade = data.obj.compras.length;
		}).error(function(data, status){
			msg(data);
		});
		
	};

	$scope.selecionarImagem = function(imagem){
		$scope.imagem = imagem;
	};
	
	$scope.adicionarAoCarrinho = function(produto, quantidade){
		var compra = {};
		compra.produto = produto;
		compra.quantidade = quantidade;
		produtoAPI.adicionarAoCarrinho(compra).success(function(data){
			$rootScope.globals.carrinho = data.obj;  
			$scope.carrinho = $rootScope.globals.carrinho;
			msg(data);
		});
	};
	
	$scope.finalizarCompra = function(){
		if(!clienteService.isLogado()){
			msgErro("É preciso estar logado para finalizar a compra.");
			return false;
		}
		if(!clienteService.isCliente()){
			msgErro("É preciso ter um perfil de cliente para finalizar a compra.");
			return false;
		}
		$location.path("/finalizarCompra");
	};
	
	$scope.carregarProduto($routeParams.id);
	$scope.quantidade = 1;

});

