angular.module("app").factory("produtoAPI", function($http){

	// _variavel = convenção variável privada
	
	var _getProdutos = function(){
		return $http.get("rest/produto/buscarTodos");
	};
	
	var _salvar = function(produto){
		return $http.post("rest/produto/salvar", produto);
	};
	
	var _buscar = function(id){
		return $http.get("rest/produto/buscarProduto/"+id);
	};
	
	var _consultaProdutosPorDepartamento = function(departamento){
		return $http.get("rest/produto/consultaProdutosPorDepartamento/"+departamento.estrutura);
	};
	
	var _adicionarAoCarrinho = function(adicionarAoCarrinho){
		return $http.post("rest/produto/adicionarAoCarrinho/", adicionarAoCarrinho);
	}
	
	var _buscarCarrinho = function(buscarCarrinho){
		return $http.post("rest/produto/buscarCarrinho/", buscarCarrinho);
	}
	
	var _removerCompraDoCarrinho = function(compra){
		return $http.post("rest/produto/removerCompraDoCarrinho/", compra);
	}
	
	var _calcularFrete = function(cep){
		return $http.post("rest/produto/calcularFrete/", cep);
	}
	
	//transforma o privado em publico. por ser uma function ela tem q ter o retorno do Objeto.
	return {
		getProdutos : _getProdutos,
		salvar : _salvar,
		buscar : _buscar,
		consultaProdutosPorDepartamento : _consultaProdutosPorDepartamento,
		buscarCarrinho : _buscarCarrinho, 
		adicionarAoCarrinho : _adicionarAoCarrinho,
		removerCompraDoCarrinho : _removerCompraDoCarrinho,
		calcularFrete : _calcularFrete
	}
	
});
