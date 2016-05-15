angular.module("app").factory("departamentoAPI", function($http){
	return {
		carregarTodos : function(){
			return $http.get("rest/departamento/buscarTodos");
		},
		salvar : function(departamento){
			return $http.post("rest/departamento/salvar", departamento);
		},
		excluir : function(departamento){
			return $http.post("rest/departamento/excluir", departamento);
		},
		buscarPorNome : function(nome){
			return $http.get("rest/departamento/buscarPorNome/"+nome);
		},
	};
});