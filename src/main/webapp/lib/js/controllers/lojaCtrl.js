
angular.module("app").controller("lojaCtrl", function($scope, $http, $location, produtoAPI){
	
	$scope.departamentos = [];
	$scope.produtos = [];
	
	$scope.carregarDepartamentos = function(){
  	  	$http.get("rest/departamento/buscarTodosEstruturado").success(function(data){
  	  	  	$scope.departamentos = data;
  	  	}).error(function(data, status){
  	  		msgErro("Não foi possível executar esta ação.");
  	  	});
  	 };
  	 
 	$scope.carregarDepartamentosFilhos = function(departamento){
  	  	$http.post("rest/departamento/departamentosFilhos", departamento).success(function(data){
  	  	  	$scope.departamentos = data;
  	  	}).error(function(data, status){
  	  		msgErro("Não foi possível executar esta ação.");
  	  	});
  	 };
  	 
  	 $scope.consultaProdutosPorDepartamento = function(departamento){
  		produtoAPI.consultaProdutosPorDepartamento(departamento).success(function(data){
  			 $scope.produtos = data.obj;
  			 msg(data);
  		 }).error(function(data, status){
  			 msg(data);
  		 });
  	 };
	
  	$scope.consultarProduto = function(produto){
  	  	$location.path('/produtoDescricao/' + produto.id);
  	};
  	 
  	$scope.carregarDepartamentos();
	
});
