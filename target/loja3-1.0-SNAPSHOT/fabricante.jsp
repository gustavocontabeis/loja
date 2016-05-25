
<div class="panel panel-default">
	<div class="panel-body">
		
		<div>
		
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<button type="button" class="btn btn-primary navbar-btn" data-toggle="modal" data-target="#myModal">Novo</button>
				</div>
			</nav>
			
			<div class="panel panel-default">
				<div class="panel-heading">Listagem de fabricantes</div>
				<div ng-hide="fabricantes.length > 0">Nenhum fabricante na tabela.</div>
				<table class="table table-striped" ng-show="fabricantes.length > 0">
					<tr>
						<th>Sel.</th>
						<th>Cod.</th>
						<th>Nome</th>
					</tr>
					<tr ng-repeat="fabricante in fabricantes">
						<td><input type="checkbox" ng-model="fabricante.selecionado" /></td>
						<td ng-click="selecionar(fabricante)" style="cursor: pointer;">{{fabricante.id}}</td>
						<td ng-click="selecionar(fabricante)" style="cursor: pointer;">{{fabricante.nome}}</td>
					</tr>
				</table>
			</div>

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Cadastro de Fabricante</h4>
						</div>
						<div class="modal-body">
							<form name="fabricanteForm">
								<input class="form-group form-control" ng-model="fabricante.id" name="id" placeholder="Cod." readonly />
								<input class="form-group form-control" ng-model="fabricante.nome" name="nome" placeholder="Nome do fabricante" ng-required="true" ng-minLength="3" />
							</form>
						</div>
						<div class="modal-footer">
							<button ng-click="limpar()" class="btn btn-primary" ng-disabled="fabricanteForm.$invalid">Novo</button>
							<button ng-click="salvar(fabricante)" class="btn btn-primary " ng-disabled="fabricanteForm.$invalid">Salvar</button>
							<button ng-click="excluir(fabricante)" class="btn btn-primary form-group" ng-disabled="!(!!fabricante.id)">Excluir</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
</div>
