angular.module("app").controller("departamentoCtrl", function($scope, $http, departamentoAPI){
	
	$scope.departamentos = [];
	
	$scope.carregarTodos = function(){
		departamentoAPI.carregarTodos().success(function(data){
  	  	  	$scope.departamentos = data.obj;
  	  	}).error(function(data, status){
  	  		msgErro("Não foi possível retornar os dados.");
  	  	});
  	 };
  	 
  	$scope.limpar = function(){
  		delete $scope.departamento;
	  	$scope.departamentoForm.$setPristine();
  	};
  	 
  	$scope.salvar = function(departamento){
  		departamentoAPI.salvar(departamento).success(function(data){
  			$scope.carregarTodos();
  			delete $scope.departamento;
  			$scope.departamentoForm.$setPristine();
  			msgOK("Registro salvo com sucesso.");
  		}).error(function(data, status){
  			msg(data);
  		});
  	};
  	 
  	$scope.isSelecionado = function(departamentos){
  		return departamentos.some(function(departamento){return departamento.selecionado;});
  	};
  	 
  	$scope.excluir = function(departamento){
  		departamentoAPI.excluir(departamento).success(function(data){
  			$scope.carregarTodos();
  			delete $scope.departamento;
  			$scope.departamentoForm.$setPristine();
  			msgOK("Registro excluído com sucesso");
  		}).error(function(data, status){
  			msgErro("Não foi possível excluir");
  		});
  	};
  	 
  	$scope.copiar = function(departamento){
  		var dep2 = angular.copy(departamento);
  		dep2.id = null;
  		$scope.departamento = dep2;
  	};
  	 
  	$scope.selecionar = function(departamento) {
  		$scope.departamento = departamento;
  		$('#myModal').modal('show');
  	}
  	 
  	$('#myModal').on('hidden.bs.modal', function (e) {
  		$scope.limpar();
  	})
  	 
  	$scope.carregarTodos();
  	
});
