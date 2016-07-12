<div class="panel panel-default">
	<div class="panel-body">
		<div>
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<button type="button" class="btn btn-primary navbar-btn" data-toggle="modal" data-target="#myModal">Novo</button>
				</div>
			</nav>
			<div class="panel panel-default">
				<div class="panel-heading">Panel heading</div>
				<div ng-hide="fabricantes.length > 0">Nenhum fabricante na tabela.</div>
				<table class="table table-striped" ng-show="carrinhos.length > 0">
					<tr>
						<th>Cod.</th>
						<th>Data da compra</th>
						<th>Cliente</th>
						<th>Quantidade de ítens</th>
						<th>Valor Total.</th>
					</tr>
					<tr ng-repeat="carrinho in carrinhos">
						<td ng-click="selecionar(carrinho)" style="cursor: pointer;">{{carrinho.id}}</td>
						<td ng-click="selecionar(carrinho)" style="cursor: pointer;">{{carrinho.dataCompra | date  :  "dd/MM/yyyy"}}</td>
						<td ng-click="selecionar(carrinho)" style="cursor: pointer;">{{carrinho.cliente.usuario.nome}}</td>
						<td ng-click="selecionar(carrinho)" style="cursor: pointer;">{{carrinho.compras.length}}</td>
						<td ng-click="selecionar(carrinho)" style="cursor: pointer;">{{carrinho.valorTotal  | currency}}</td>
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
							<h4 class="modal-title" id="myModalLabel">Consutar Venda</h4>
						</div>
						<div class="modal-body">
							<form name="fabricanteForm">
								<input class="form-group form-control" ng-model="carrinho.id" name="id" placeholder="Cod." readonly /> 
								<input class="form-group form-control" ng-model="carrinho.dataCompra" name="dataCompra" placeholder="Data da compra" ng-required="true" ng-minLength="3" ui-date />
								<input class="form-group form-control" ng-model="carrinho.cliente.usuario.nome" name="cliente_nome" placeholder="Nome do cliente" ng-required="true" ng-minLength="3" />
								<input class="form-group form-control" ng-model="carrinho.enderecoEntrega.logradouro" name="enderecoEntrega_logradouro" placeholder="LOgradouro" ng-required="true" ng-minLength="3" />
								<input class="form-group form-control" ng-model="carrinho.enderecoEntrega.cidade" name="enderecoEntrega_cidade" placeholder="Cidade" ng-required="true" ng-minLength="3" />
								<input class="form-group form-control" ng-model="carrinho.valorTotal | currency" name="valorTotal" placeholder="Nome do cliente" ng-required="true" ng-minLength="3" />
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
