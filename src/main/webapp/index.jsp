<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">
<head>
	<title>AngularJS - loja</title>
	<meta content="text/html;charset='utf-8'">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="lib/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="lib/css/bootstrap-theme.css">
	
	<!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
	
	<link href="lib/jquery/jquery-ui.css" rel="stylesheet" type="text/css" />
	<script src="lib/jquery/external/jquery/jquery.js"></script>
	<script src="lib/jquery/jquery-ui.js"></script>
	<script src="lib/jquery/jquery.ui.datepicker-pt-BR.js"></script>
	
	<script type="text/javascript" src="lib/js/bootstrap.js"></script>
	<script type="text/javascript" src="lib/angular.js"></script>
	<script type="text/javascript" src="lib/angular-route.js"></script>
	<script type="text/javascript" src="lib/angular-cookies.js"></script>
	<script type="text/javascript" src="lib/angular-resource.js"></script>
	<script type="text/javascript" src="lib/angular-locale_pt-br.js"></script>
	<script type="text/javascript" src="lib/js/angularPlugins/ngMask.min.js"></script>
	
	<link href="lib/toastr/toastr.css" rel="stylesheet" type="text/css" />
	<script src="lib/toastr/toastr.min.js"></script>
	
	<script type="text/javascript" src="lib/js/app.js"></script>
	<script type="text/javascript" src="lib/js/config/routeConfig.js"></script>
	<script type="text/javascript" src="lib/js/value/configValue.js"></script>
	<script type="text/javascript" src="lib/js/funcoes.js"></script>
	
	<!-- Directives -->
	<script type="text/javascript" src="lib/js/directives/uiDateDirective.js"></script>
	<script type="text/javascript" src="lib/js/directives/uiAlertDirective.js"></script>
	
	<!-- Services -->
	<script type="text/javascript" src="lib/js/controllers/services/login/authenticationService.js"></script>
	<script type="text/javascript" src="lib/js/controllers/services/login/flashService.js"></script>
	<script type="text/javascript" src="lib/js/controllers/services/login/userService.js"></script>
	<script type="text/javascript" src="lib/js/controllers/services/carrinhoComprasService.js"></script>
	<script type="text/javascript" src="lib/js/controllers/services/clienteService.js"></script>
	<script type="text/javascript" src="lib/js/controllers/services/departamentoService.js"></script>
	<script type="text/javascript" src="lib/js/controllers/services/fabricanteService.js"></script>
	<script type="text/javascript" src="lib/js/controllers/services/produtoService.js"></script>
	<script type="text/javascript" src="lib/js/controllers/services/usuarioService.js"></script>
	<script type="text/javascript" src="lib/js/controllers/services/vendasEfetuadasService.js"></script>
	<script type="text/javascript" src="lib/js/directives/uiDateDirective.js"></script>
	
	<!-- Controllers -->
	<script type="text/javascript" src="lib/js/controllers/appCtrl.js"></script>
	<script type="text/javascript" src="lib/js/controllers/clienteCtrl.js"></script>
	<script type="text/javascript" src="lib/js/controllers/departamentoCtrl.js"></script>
	<script type="text/javascript" src="lib/js/controllers/fabricanteCtrl.js"></script>
	<script type="text/javascript" src="lib/js/controllers/finalizarCompraCtrl.js"></script>
	<script type="text/javascript" src="lib/js/controllers/loginCtrl.js"></script>
	<script type="text/javascript" src="lib/js/controllers/lojaCtrl.js"></script>
	<script type="text/javascript" src="lib/js/controllers/produtoCtrl.js"></script>
	<script type="text/javascript" src="lib/js/controllers/produtoDescricaoCtrl.js"></script>
	<script type="text/javascript" src="lib/js/controllers/produtoImagemCtrl.js"></script>
	<script type="text/javascript" src="lib/js/controllers/usuarioCtrl.js"></script>
	<script type="text/javascript" src="lib/js/controllers/registerCtrl.js"></script>
	<script type="text/javascript" src="lib/js/controllers/vendasEfetuadasCtrl.js"></script>
	
	<!-- file upload -->
	<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
	<link rel="stylesheet" href="lib/uploadAngular/css/jquery.fileupload.css">
	
	<script src="lib/uploadAngular/js/vendor/jquery.ui.widget.js"></script>
	<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
	<script src="lib/uploadAngular/js/jquery.iframe-transport.js"></script>
	<!-- The basic File Upload plugin -->
	<script src="lib/uploadAngular/js/jquery.fileupload.js"></script>
	
</head>
<body ng-app="app">
	<header style="display:none;">
		<div class="page-header">
  			<h1>AngularJS <small>Testes de AngularJS + Bootstrap + Webservice REST</small></h1>
		</div>
	</header>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Nav bar mobile -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
			</div>
			<!-- Nav bar desktop -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" ng-controller="appCtrl">
				<ul class="nav navbar-nav">
					<li><a href="index.jsp?#loja">Home</a></li>
					<li class="dropdown" ng-show="!isLogged()">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Entrar<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#login" >Login</a></li>
							<li><a href="#cliente">Cadastrar-se</a></li>
						</ul>
					</li>
					<li class="dropdown" ng-if="isPerfil('ADM')"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Cadastros<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#fabricante">Fabricante</a></li>
							<li><a href="#departamento">Departamento</a></li>
							<li><a href="#produto">Produto</a></li>
							<li><a href="#vendasEfetuadas">Vendas Efetuadas</a></li>
						</ul>
					</li>
					<li class="dropdown" style="display: none;"><a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Testes<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="login/">Testes login</a></li>
							<li><a href="#produtoImagem">produtoImagem - dropzone </a></li>
							<li><a href="#produtoImagemAngular">produtoImagem - AngularJS</a></li>
						</ul>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a>{{globals.usuario.nome?'Bem vindo, '+globals.usuario.nome+'.':''}}</a>
					</li>
					<li>
						<a href="#" ng-click="logout()" ng-show="isLogged()">Sair</a>
					</li>
					<li>
						<a onclick="return false;">
							<span class="glyphicon glyphicon-user" ng-click="editarPerfil()" ng-show="isPerfil('CLI')" title="Editar perfil (Cliente)" style="cursor: pointer;"></span>
						</a>
					</li>
					<li>
						<a onclick="return false;">
							<span class="glyphicon glyphicon-shopping-cart" ng-click="finalizarCompra()" style="cursor: pointer;" title="Consultar carrinho de compras"></span>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<section>
		<div ng-view></div>
	</section>
	<footer style="position: fixed; bottom: 0; width: 400px; text-align: right; right:0;">
		<p title="{{globals}}">&copy; 2015 Coder Sistemas. Todos os direitos reservados. v1</p>
	</footer>
</body>
</html>