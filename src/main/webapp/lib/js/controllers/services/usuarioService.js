angular.module("app").factory("usuarioAPI", function($http){
	return {
		logar : function(usuario){
			return $http.post("rest/usuario/logar", usuario);
		},
		buscar : function(){
			return $http.get("rest/usuario/buscar");
		},
		sair : function(){
			return $http.get("rest/usuario/sair");
		}
	};
});