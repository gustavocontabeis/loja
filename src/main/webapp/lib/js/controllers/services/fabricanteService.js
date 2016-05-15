/**
 * Exemplo de service.
 * Produto utiliza exemplo de factory
 */
angular.module("app").service("fabricanteAPI", function($http){
	
	this.buscarTodos = function(){
		return $http.get("rest/fabricante/buscarTodos");
	};
	
	this.buscarPorNome = function(nome){
		return $http.get("rest/fabricante/buscarPorNome/"+nome);
	};
	
	this.salvar = function(fabricante){
		return $http.post("rest/fabricante/salvar", fabricante);
	};
	
	this.excluir = function(fabricante){
		return $http.post("rest/fabricante/excluir", fabricante);
	};
	
});

