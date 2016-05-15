
<script type="text/javascript" src="lib/js/controllers/usuarioCtrl.js"></script>

<div class="panel panel-default">
	<div class="panel-body">
		<div ng-app="usuarioApp">
			<div ng-controller="usuarioCtrl">

				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<button type="button" class="btn btn-primary navbar-btn" data-toggle="modal" data-target="#myModal">Novo</button>
					</div>
				</nav>
				
				<div class="alert alert-danger" role="alert" ng-if="(!!messageErro)">{{messageErro}}</div>

								<form name="fabricanteForm">
									<input class="form-group form-control" ng-model="fabricante.id" name="id" placeholder="Cod." readonly /> 
									<input class="form-group form-control" ng-model="fabricante.nome" name="nome" placeholder="Nome do fabricante" ng-required="true" ng-minLength="3" />
								</form>
								<button ng-click="limpar()" class="btn btn-primary" ng-disabled="fabricanteForm.$invalid">Novo</button>
								<button ng-click="salvar(fabricante)" class="btn btn-primary " ng-disabled="fabricanteForm.$invalid">Salvar</button>
								<button ng-click="excluir(fabricante)" class="btn btn-primary form-group" ng-disabled="!(!!fabricante.id)">Excluir</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
			
			</div>
		</div>
	</div>
</div>
