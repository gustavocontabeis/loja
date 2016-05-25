
<script type="text/javascript">
</script>
<style type="text/css">
.texto-pq{ color: #333;
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
    font-size: 14px;
    line-height: 1.42857;}
</style>
<div class="panel panel-default">
	<div class="panel-body">
		<div>
			<form name="produtoForm">
				<div class="tab-content texto-pq">
					<div id="prod">
						<div class="container-fluid">
							<table style="width: 100%">
								<tr>
									<td width="50%" valign="top" style="padding: 5px;">
										<div class="panel panel-default">
											<div class="panel-body" style="min-height: 190px">
												<div><b>Cod. </b>{{produto.id}}</div> 
												<div><b>Nome: </b>{{produto.nome}}</div>
												<div><b>Descrição: </b>{{produto.descricao}}</div>
												<div><b>Cod. Referência do fabricante: </b>{{produto.codReferenciaFabricante}}</div>
												<div><b>Fabricante: </b>{{produto.fabricante.nome}}</div>
												<div><b>Valor: </b>{{produto.valor.valor | currency}}</div>
												<div align="right">
												<input ng-model="quantidade" placeholder="Quantidade" type="number" style="width: 50px;">
												<button class="btn btn-primary" ng-click="adicionarAoCarrinho(produto, quantidade)"><span class="glyphicon glyphicon-shopping-cart"></span><span class="badge">{{carrinho.compras.length}}</span></button>
												<button class="btn btn-primary" ng-click="finalizarCompra()" title="Finalizar"><span class="glyphicon glyphicon-ok"></span></button>
												</div>
											</div>
										</div>
									</td>
									<td width="50%" valign="top" style="padding: 5px;">
										<div class="panel panel-default">
											<div class="panel-body" style="min-height: 190px">
												<table class="table">
													<tr>
														<td colspan="2" align="center">Caracteristicas</td>
													</tr>
													<tr ng-repeat="car in produto.caracteristicas">
														<td><span>{{car.tipo}}</span></td>
														<td><span>{{car.valor}}</span></td>
													</tr>
												</table>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div>&nbsp;</div>
					<div class="panel panel-default">
						<div class="panel-body">
							<div id="imagens" class="text-left" style="float: left; clear:both;">
								<div class="container" style="width: 600px; padding: 5px;">
									<div class="col-xs-6 col-md-3" ng-repeat="imagem in produto.imagens" style="cursor: pointer;">
									    <a class="thumbnail" ng-click="selecionarImagem(imagem)">
									    	<img src="data:image/svg+xml;base64,{{imagem.arquivo.dados}}" class="img-thumbnail" />
									    </a>
									</div>
								</div>
							</div>
						    <a href="#" onclick="return false;" class="thumbnail" ng-hide="!(!!imagem)">
						    	<img src="data:image/svg+xml;base64,{{imagem.arquivo.dados}}" class="img-thumbnail" />
						    </a>
						</div>
					</div>
				</div>
			</form>
			</div>
		</div>
	</div>
</div>
