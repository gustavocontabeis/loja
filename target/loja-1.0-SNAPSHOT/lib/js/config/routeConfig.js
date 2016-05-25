angular.module("app").config(config).run(run);

config.$inject = ['$routeProvider', '$locationProvider'];
function config($routeProvider, $locationProvider) {
	$routeProvider.when("/fabricante", {
		templateUrl: "fabricante.jsp",
		controller:"fabricanteCtrl"
	});
	$routeProvider.when("/departamento", {
		templateUrl: "departamento.jsp",
		controller:"departamentoCtrl"
	});
	$routeProvider.when("/produto", {
		templateUrl: "produto.jsp",
		controller:"produtoCtrl"
	});
	$routeProvider.when("/produtoImagemAngular", {
		templateUrl: "produtoImagemAngular.jsp"
	});
	$routeProvider.when("/loja", {
		templateUrl: "loja.jsp",
		controller: "lojaCtrl"
	});
	$routeProvider.when("/cliente", {
		templateUrl: "cliente.jsp",
		controller: "clienteCtrl"
	});
	$routeProvider.when("/login", {
		templateUrl: "view/login/login.jsp",
		controller: "loginCtrl",
		controllerAs: 'vm'
	});
	$routeProvider.when("/register", {
		templateUrl: "view/login/register.jsp",
		controller: "registerCtrl",
		controllerAs: 'vm'
	});
	$routeProvider.when("/produtoDescricao/:id", {
		templateUrl: function(params){
			return "produtoDescricao.jsp?id="+params.id;
		},
		controller: "produtoDescricaoCtrl"
	});
	$routeProvider.when("/produtoImagem/:id", {
		templateUrl: function(params){
			return "produtoImagem.jsp?id="+params.id;
		},
		controller: "produtoImagemCtrl"
	});
	$routeProvider.when("/finalizarCompra", {
		templateUrl: "finalizarCompra.jsp",
		controller: "finalizarCompraCtrl"
	});
	$routeProvider.when("/vendasEfetuadas", {
		templateUrl: "vendasEfetuadas.jsp",
		controller: "vendasEfetuadasCtrl"
	});
	$routeProvider.otherwise({redirectTo: "/"});
}

run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
function run($rootScope, $location, $cookieStore, $http) {
    // keep user logged in after page refresh
    $rootScope.globals = $cookieStore.get('globals') || {};
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
    }

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
    	//console.log("$locationChangeStart",event, next, current);
        //redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = isPageProtected($location.path());
        //var restrictedPage = $.inArray($location.path(), ['/loja', '/produtoDescricao/']) === -1;
        //console.log("restrictedPage", restrictedPage);
        var loggedIn = $rootScope.globals.currentUser;
        if (restrictedPage && !loggedIn) {
        	//console.log("redirecionar!");
            $location.path('/login');
        }
    });
};


function isPageProtected(current){
	
	var paginas = ['/fabricante','/departamento','/produto'];
	var comecaCom = [];
	
	/** Por nome correto */
	if($.inArray(current, paginas) >= 0){
		return true;
	}
	
	/**
	 * Por começar por
	 * Ex ["/produtoDescricao/"]
	 * */
	for ( var item in comecaCom) {
		if(current.startsWith(item)){
			return true;
		}
	}
	
	return false;
	
}