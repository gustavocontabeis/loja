/**
 * Exemplo de service.
 * Produto utiliza exemplo de factory
 */
angular.module("app").service("clienteService", function($http, $rootScope){
	
	this.buscarTodos = function(){
		return $http.get("rest/cliente/buscarTodos");
	};
	
	this.buscarPorNome = function(nome){
		return $http.get("rest/cliente/buscarPorNome/"+nome);
	};
	
	this.salvar = function(cliente){
		return $http.post("rest/cliente/salvar", cliente);
	};
	
	this.excluir = function(cliente){
		return $http.post("rest/cliente/excluir", cliente);
	};
	
	this.buscarEnderecos = function(usuario) {
		return $http.post("rest/cliente/buscarEnderecos", usuario);
	}
	
	this.salvarEndereco = function(endereco){
		return $http.post("rest/cliente/salvarEndereco", endereco);
	}
	
	this.excluirEndereco = function(endereco){
		return $http.post("rest/cliente/excluirEndereco", endereco);
	}
	
	this.buscarPorUsuario = function(usuario){
		return $http.post("rest/cliente/buscarPorUsuario", usuario);
	}
	
	this.isLogado = function(){
		var isCliente = false;
		var usu = null
		try{
			usu = $rootScope.globals.usuario;
		}catch(e){
			msgErro(e.message);
		}
		if(!usu){
			return false;
		}else{
			return true;
		}
	}
	
	this.isCliente = function(){
		var isCliente = false;
		var usu = null
		try{
			usu = $rootScope.globals.usuario;
		}catch(e){
			msgErro(e.message);
		}
		if(!usu){
			return false;
		}
		var perfis = $rootScope.globals.usuario.perfis;
		for (var int = 0; int < perfis.length; int++) {
			if ('CLI' == (perfis[int].nome)) {
				isCliente = true;
			}
		}
		return isCliente;
	}
	
});

