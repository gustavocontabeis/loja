angular.module("app").controller("produtoCtrl", function($scope, $http, $location, produtoAPI, fabricanteAPI, departamentoAPI){
	
	$scope.produtos = [];
	$scope.departamentos = [];
	$scope.fabricantes = [];
	$scope.caracteristicaProduto = null;
	
	$scope.adicionarImagens = function(produto){
		$('#myModal').modal('hide');
		$location.path('/produtoImagem/'+produto.id);
	};
  	 
	$scope.novaCaracteristica = function(){
		if(!$scope.produto.caracteristicas)
			$scope.produto.caracteristicas = [];
		$scope.produto.caracteristicas.push({tipo: '', valor: ''});
	};
  	 
 	$scope.carregarTodos = function(){
 		produtoAPI.getProdutos().success(function(data){
  	  	  	$scope.produtos = data.obj;
  	  	}).error(function(data, status){
  	  		msg(data);
  	  	});
  	 };
  	 
   	$scope.carregarDepartamentos = function(){
  	  	$http.get("rest/departamento/buscarTodos").success(function(data){
  	  	  	$scope.departamentos = data.obj;
  	  	  	msg(data);
  	  	}).error(function(data, status){
  	  		msg(data);
  	  	});
  	 };
  	 
  	 $scope.carregarFabricantes = function(){
  		 $http.get("rest/fabricante/buscarTodos").success(function(data){
  			 $scope.fabricantes = data.obj;
  		 }).error(function(data, status){
  			 msg(data);
  		 });
  	 };

  	 $scope.carregarAutocompleteFabricantes = function(fabricante){
   		fabricanteAPI.buscarPorNome(fabricante.nome).success(function(data){
   			 $scope.fabricantes = data.obj;
   		 }).error(function(data, status){
   			 msg(data);
   		 });
   	 };
           	 
   	 $scope.carregarAutocompleteDepartamentos = function(testeA){
   		 if(testeA == "" || testeA.length < 3){
   			 $scope.exibeAutocomplDepto = false;
   		 }else{
   			 departamentoAPI.buscarPorNome(testeA).success(function(data){
   				 $scope.departamentos = data.obj;
   			 }).error(function(data, status){
   				 msg(data);
   			 });
   			 $scope.exibeAutocomplDepto = true;
   		 }
   	 };
           	 
  	$scope.limpar = function(){
  		delete $scope.produto;
	  	$scope.produtoForm.$setPristine();
  	};
  	 
  	$scope.salvar = function(produto){
  		produtoAPI.salvar(produto).success(function(data){
  			$scope.carregarTodos();
  			delete $scope.produto;
  			$scope.produtoForm.$setPristine();
  			msg(data);
  		}).error(function(data, status){
  			msg(data);
  		});
  	};
  	 
  	$scope.isSelecionado = function(produtos){
  		return produtos.some(function(produto){return produto.selecionado;});
  	};
  	 
  	$scope.excluir = function(produto){
  		$http.post("rest/produto/excluir", produto).success(function(data){
  			$scope.carregarTodos();
  			delete $scope.produto;
  			$scope.produtoForm.$setPristine();
  		});
  	};
  	 
   	$scope.excluirImagem = function(produto, imagem){
  	  	$http.post("rest/produto/excluirImagem", imagem).success(function(data){
  	  	produtoAPI.buscar(produto.id).success(function(data){
  	  			toastr.info("Imagem excluída com sucesso.");
  	  		}).error(function(data){
  	  			msg(data);
  	  		});
  	  	});
  	 };
  	 
  	 $scope.selecionar = function(produto) {
  		produtoAPI.buscar(produto.id).success(function(data){
  			 $scope.produto = data.obj;
  		 }).error(function(data){
  			 msg(data);
  		 });
  		 $('#myModal').modal('show');
  	 }
  	 
  	 $('#myModal').on('hidden.bs.modal', function (e) {
  		 $scope.limpar();
  	 })
  	 
  	$scope.carregarTodos();
  	$scope.carregarDepartamentos();
  	$scope.carregarFabricantes();
});
