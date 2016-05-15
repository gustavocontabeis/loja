/**
 * Exemplo de service.
 * Produto utiliza exemplo de factory
 */
angular.module("app").service("vendasEfetuadasAPI", function($http){
	
	this.buscarTodos = function(){
		return $http.get("rest/carrinhoCompras/buscarTodos");
	};
	
});

