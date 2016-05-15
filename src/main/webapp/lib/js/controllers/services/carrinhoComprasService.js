/**
 * Exemplo de service.
 * Produto utiliza exemplo de factory
 */
angular.module("app").service("carrinhoComprasService", function($http){
	
	this.finalizarCompra = function(carrinhoCompras){
		return $http.post("rest/carrinhoCompras/finalizarCompra", carrinhoCompras);
	};
	
});

