
<script type="text/javascript">
	
</script>
<style type="text/css">
.texto-pq {
	color: #333;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	font-size: 14px;
	line-height: 1.42857;
}
</style>
<form name="produtoForm">
	<div class="panel-body">
		<div class="panel panel-default">
			<div class="container-fluid">
				<div class="row">
					<div>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<div class="form-group">
								<label>Cod. </label>
								<div class="form-control">{{produto.id}}</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<div class="form-group">
								<label>Nome</label>
								<div class="form-control">{{produto.nome}}</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<div class="form-group">
								<label>Descrição</label>
								<div class="form-control">{{produto.descricao}}</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<div class="form-group">
								<label>Cod. Referência do fabricante</label>
								<div class="form-control">{{produto.codReferenciaFabricante}}</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<div class="form-group">
								<label>Fabricante</label>
								<div class="form-control">{{produto.fabricante.nome}}</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-12">
							<div class="form-group">
								<label>Valor</label>
								<div class="form-control">{{produto.valor.valor | currency}}</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<div class="panel panel-default">
			<div class="container-fluid">
				<div class="row">
					<div>
						<div class="col-md-6 col-sm-6 col-xs-12" ng-repeat="car in produto.caracteristicas">
							<div class="form-group">
								<label>{{car.tipo}}</label>
								<div class="form-control">{{car.valor}}</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Miniaturas -->
	<div class="panel-body">
		<div class="panel panel-default">
			<div class="container-fluid">
				<div ng-repeat="imagem in produto.imagens" style="float:left;">
					<a class="thumbnail" ng-click="selecionarImagem(imagem)" data-toggle="modal" data-target="#modalProduto" style="width: 50px; padding: 5px;" > 
						<img ng-src="data:image/svg+xml;base64,{{imagem.arquivo.dados}}" class="img-thumbnail" />
					</a>
				</div>
			</div>
		</div>
	</div>
	<div style="clear: both;"></div>
</form>

<div class="modal fade" id="modalProduto" tabindex="-1" role="dialog" aria-labelledby="modalProdutoLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalProdutoLabel">{{produto.nome}}</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<div class="row">
			<a href="#" onclick="return false;" class="thumbnail" ng-hide="!(!!imagem)" style="cursor: default;">
				<img src="imageServlet?id={{imagem.arquivo.id}}" class="img-thumbnail" />
			</a>
		</div>
      </div>
      <div class="modal-footer">
		<div class="col-md-1 col-sm-1 col-xs-1" ng-repeat="imagem in produto.imagens">
			<a class="thumbnail" ng-click="selecionarImagem(imagem)" style="width: 50px; padding: 5px;">
				<!-- 
				<img ng-src="data:image/svg+xml;base64,{{imagem.arquivo.dados}}" class="img-thumbnail" />
				 --> 
				<img src="imageServlet?id={{imagem.arquivo.id}}" class="img-thumbnail" />
			</a>
		</div>
      </div>
    </div>
  </div>
</div>
