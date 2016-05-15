/**
 * Exemplo
 * <div ui-alert titulo="Este é o título.">Esta é a mensagem</div>
 * @: com @ ele cria a variavel local, dentro do escopo da diretiva com o mesmo nome 'titulo'
 * =: com = ele vai pegar o $scope.erro (externo) com o mesmo nome.
 * transclude: exibe o corpo da tag.
 * replace
 * restrict: A (Atributo), E (elemento), AE (os dois), tem outro q é comentário...
 */
angular.module("app").directive("uiAlert", function(){
	return {
		templateUrl: "lib/view/templates/ui-alert.html",
		scope:{
			titulo: "@",
			erro: "=",
		},
		transclude: true
	};
});