angular.module("app").controller("produtoImagemCtrl", function($scope, $http, $location, $routeParams, produtoAPI){
	
	$scope.buscarProduto = function(id){
		console.log("produtoImagemApp-buscarProduto", id);
		produtoAPI.buscar(id).success(function(data) {
			$scope.produto = data;
		}).error(function(data, status) {
			msg(data);
		});
	}
	
	$scope.buscarProduto($routeParams.id);
	
});
