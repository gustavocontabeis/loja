/**
 * Exemplo:
 * @link: usado para iteragir com a DOM
 * @require: faz iterargir com outras diretivas. Colocar a diretiva declarada nos parâmetros (ngModel->ctlr)
 * ctrl.$viewValue pega o value do elemento.
 * ctrl.$setViewValue define o valor do elemento
 * ctrl.$render(); exibe o valor no elemento.
 */
angular.module("app").directive("uiDate", function($filter){
		return {
			require: "ngModel",
			link: function (scope, element, attrs, ctrl) {
				var _formatDate = function (date) {
					date = date.replace(/[^0-9]+/g, "");
					if(date.length > 2) {
						date = date.substring(0,2) + "/" + date.substring(2);
					}
					if(date.length > 5) {
						date = date.substring(0,5) + "/" + date.substring(5,9);
					}
					return date;
				};

				element.bind("keyup", function () {
					ctrl.$setViewValue(_formatDate(ctrl.$viewValue));
					ctrl.$render();
				});

//				ctrl.$parsers.push(function (value) {
//					if (value.length === 10) {
//						var dateArray = value.split("/");
//						var ret = new Date(dateArray[2], dateArray[1]-1, dateArray[0]).getTime();
//						return ret;
//					}
//				});

				ctrl.$formatters.push(function (value) {
					return $filter("date")(value, "dd/MM/yyyy");
				});
			}
		};
	});
