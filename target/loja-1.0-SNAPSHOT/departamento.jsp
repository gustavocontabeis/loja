<style>
<!--
.nivel1{font-size: 16px; color: rgb(0, 0, 0)}
.nivel2{font-size: 15px; color: rgb(50, 50, 50)}
.nivel3{font-size: 14px; color: rgb(80, 80, 80)}
.nivel4{font-size: 13px; color: rgb(100, 100, 100)}
-->
</style>
<div class="panel panel-default">
	<div class="panel-body">
		<div>
			<div>
				
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<button type="button" class="btn btn-primary navbar-btn" data-toggle="modal" data-target="#myModal">Novo</button>
					</div>
				</nav>
				
				<div class="alert alert-danger" role="alert" ng-if="(!!messageErro)">{{messageErro}}</div>

				<div class="panel panel-default">
					<div class="panel-heading">Listagem de departamentos</div>
					<div ng-hide="departamentos.length > 0">Nenhum departamento na tabela.</div>
					<table class="table table-striped" ng-show="departamentos.length > 0">
						<tr>
							<th>Sel.</th>
							<th>Cod.</th>
							<th>Nível</th>
							<th>Estrutura</th>
							<th>Nome</th>
						</tr>
						<tr ng-repeat="departamento in departamentos" ng-class="{nivel1 : departamento.nivel == 1, nivel2 : departamento.nivel == 2, nivel3 : departamento.nivel == 3, nivel4 : departamento.nivel == 4} col-xs-12">
							<td><input type="checkbox" ng-model="departamento.selecionado" /></td>
							<td ng-click="selecionar(departamento)" style="cursor: pointer;">{{departamento.id}}</td>
							<td ng-click="selecionar(departamento)" style="cursor: pointer;">{{departamento.nivel}}</td>
							<td ng-click="selecionar(departamento)" style="cursor: pointer;">{{departamento.estrutura}}</td>
							<td ng-click="selecionar(departamento)" style="cursor: pointer;">{{departamento.nome}}</td>
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
								<h4 class="modal-title" id="myModalLabel">Cadastro de departamentos</h4>
								<div class="alert alert-info" role="alert" ng-if="exibeMsgOK">{{mensagemOK}}</div>
							</div>
							<div class="modal-body">
								<form name="departamentoForm">
									<input class="form-group form-control" ng-model="departamento.id" name="id" placeholder="Cod." readonly /> 
									<input class="form-group form-control" ng-model="departamento.estrutura" name="estrutura" placeholder="Estrutura" ng-required="true" ng-minLength="3"  mask='999.999.999.999' />
									<input class="form-group form-control" ng-model="departamento.nome" name="nome" placeholder="Nome do departamento" ng-required="true" ng-minLength="3" />
									<input class="form-group form-control" ng-model="departamento.descricao" name="descricao" placeholder="Descrição" ng-required="true" ng-minLength="3" />
								</form>
							</div>
							<div class="modal-footer">
								<button ng-click="limpar()" class="btn btn-primary" ng-disabled="departamentoForm.$invalid">Novo</button>
								<button ng-click="salvar(departamento)" class="btn btn-primary " ng-disabled="departamentoForm.$invalid">Salvar</button>
								<button ng-click="copiar(departamento)" class="btn btn-primary " ng-disabled="departamentoForm.$invalid">Copiar</button>
								<button ng-click="excluir(departamento)" class="btn btn-primary form-group" ng-disabled="!(!!departamento.id)">Excluir</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
