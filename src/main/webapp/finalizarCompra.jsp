<div class="container-fluid">
	<div class="row-fluid">
		<div class="col-md-12">
			<div class="panel panel-default"> 
				<div class="panel-heading">Produtos</div>
				<div class="panel-body" style="min-height: 190px">
					<table class="table table-striped">
						<tr>
							<td>Produto</td>
							<td>Valor</td>
							<td>Quantidade</td>
							<td>Desconto</td>
							<td>Total</td>
							<td>&nbsp;</td>
						</tr>
						<tr ng-repeat="compra in carrinho.compras">
							<td>{{compra.produto.nome}}</td>
							<td>{{compra.valorUnitario | currency }}</td>
							<td>{{compra.quantidade}}</td>
							<td>{{compra.valorDesconto | currency }}</td>
							<td>{{compra.valorTotal | currency }}</td>
							<td><span class="glyphicon glyphicon-trash" ng-click="removerCompraDoCarrinho(compra)" style="cursor: pointer;" title="Excluir este produto do meu carrinho."></span></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="col-md-6">
			<div class="panel panel-default" style="min-height: 120px;">
				<div class="panel-body">
					<form class="form-inline">
						<input ng-model="cep" placeHolder="CEP" class="form-control" mask="99999-999" size="6"/>
						<button ng-click="calcularFrete(cep)" class="btn btn-primary">Calcular</button>
					</form>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="panel panel-default" style="min-height: 120px;">
				<div class="panel-body">
					<div><b>Valor total: </b>{{carrinho.valorTotal | currency }}</div>
					<div><b>Descontos: </b>{{carrinho.valorDesconto | currency }}</div>
					<div><b>Frete: </b>{{carrinho.valorFrete | currency }}</div>
					<div><b>Valor final: </b>{{carrinho.valorFinal | currency }}</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading">Entregar em</div>
				<div class="panel-body">
					<script type="text/javascript">
						<!--
						$('.nav-tabs a').click(function (e) {
						    e.preventDefault();
						    $(this).tab('show');
						});
						//-->
					</script>
					<ul class="nav nav-tabs">
						<li role="presentation" class="active"><a id="linkEnderecoSelecionado" data-toggle="tab" href="#enderecoSelecionado" role="tab" aria-controls="enderecoSelecionado">Endereço de entrega</a></li>
						<li role="presentation"><a data-toggle="tab" href="#selecionarEndereco" role="tab" aria-controls="selecionarEndereco">Selecionar endereço</a></li>
						<li role="presentation"><a data-toggle="tab" href="#cadastrarNovoEndereco" role="tab" aria-controls="cadastrarNovoEndereco">Cadastrar novo endereço</a></li>
					</ul>
					<div class="tab-content">
						<div id="enderecoSelecionado" class="tab-pane fade in active" role="tabpanel">
							<div class="container-fluid">
								<div style="padding: 10px">
									<div class="alert alert-warning" role="alert" ng-show="(carrinho.enderecoEntrega.logradouro == null)">Nenhum endereço selecionado.</div>
									<div ng-show="(carrinho.enderecoEntrega.logradouro != null)" >A/C {{carrinho.enderecoEntrega.contato}}, {{carrinho.enderecoEntrega.logradouro}}, {{carrinho.enderecoEntrega.cidade}}/{{carrinho.enderecoEntrega.uf}}, CEP: {{carrinho.enderecoEntrega.cep}}</div>
								</div> 
							</div>
						</div>
						<div id="selecionarEndereco" class="tab-pane fade" role="tabpanel">
							<div class="container-fluid">
								<table class="table table-striped">
									<tr ng-repeat="endereco2 in enderecos" ng-click="selecionarEndereco(endereco2)" style="cursor: pointer;">
										<td>{{endereco2.logradouro}}</td>
										<td>{{endereco2.cidade}}</td>
										<td>{{endereco2.uf}}</td>
										<td><span class="glyphicon glyphicon-trash" ng-click="excluirEndereco(endereco2)"></span></td>
									</tr>
								</table>
							</div>
						</div>
						<div id="cadastrarNovoEndereco" class="tab-pane fade" role="tabpanel">
							<div class="container-fluid">
								<div class="row">
									<div class="col-md-12">
										<form name="enderecoForm">
											<input class="form-group form-control" ng-model="endereco.id" name="id" placeholder="Cod." readonly> 
											<input class="form-group form-control" ng-model="endereco.contato" name="contato" placeholder="Contato" required ng-minLenght="3">
											<input class="form-group form-control" ng-model="endereco.logradouro" name="logradouro" placeholder="Logradouro" required ng-minLength="3"> 
											<input class="form-group form-control" ng-model="endereco.cidade" name="cidade" placeholder="Cidade" required ng-minLength="3">
											<input class="form-group form-control" ng-model="endereco.cep" name="cep" placeholder="CEP" required ng-minLength="3" mask="99999-999">
											<input class="form-group form-control" ng-model="endereco.uf" name="uf" placeholder="UF" required mask="w?s?" repeat="2" restrict="reject"> 
											<button ng-click="salvarEndereco(endereco)" class="btn btn-primary" ng-disabled="enderecoForm.$invalid">Salvar endereço</button>
										</form>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">Forma de pagamento</div>
					<div class="panel-body">
						<form name="pagamentoForm" class="form-inline">
							<select class="form-group form-control" ng-model="carrinho.pagamento.bandeira" name="bandeira" placeholder="Bandeira">
								<option value="" selected="selected">[Selecione]</option>
								<option value="VISA">Visa</option>
								<option value="MASTER_CARD">MasterCard</option>
							</select>
							<input class="form-group form-control" ng-model="carrinho.pagamento.nrCartao" name="numero" placeholder="Número do cartão" mask="9999 9999 9999 9999" > 
							<input class="form-group form-control" ng-model="carrinho.pagamento.vencimento" name="vencimento" placeholder="Vencimento (mês/ano)" ng-required="true" mask="19/99" >
							<input class="form-group form-control" ng-model="carrinho.pagamento.nomeTitular" name="Nome do Titular (igual ao impresso no cartão)" placeholder="Nome do titular" ng-required="true" ng-minLength="3"> 
							<input class="form-group form-control" ng-model="carrinho.pagamento.codSeguranca" name="codSeguranca" placeholder="Código de Seguranca" ng-required="true" mask="999">
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="col-md-12">
				<button class="btn btn-primary" ng-click="finalizarCompra()" ng-disabled="enderecoForm.$valid && pagamentoForm.$valid">Finalizar Pedido</button>
			</div>
		</div>
	</div>
</div>
