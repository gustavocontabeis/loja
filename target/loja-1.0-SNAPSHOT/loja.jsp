<script type="text/javascript">
	$(function() {
		$("#menu").menu();
		
		$("#folga").mouseover(function(){
			$("#menu").show();
		});
		
		$("#folga").mouseout(function(){
			$("#menu").hide();
		});
	});
</script>
<style>
.ui-menu {
	width: 200px;
    position: absolute;
    z-index: 1;
}
.ui-widget {
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
    font-size: 14px;
}
.drawer {
}
</style>
<div id="folga" style="padding: 10px; background-color: silver; width: 150px" class="navbar-default">
	<span id="menuDepartamentos">Departamentos</span>
	<ul id="menu" style="display: none;"><!-- style="display: none;" -->
		<li ng-repeat="departamento in departamentos">
			<div ng-click="consultaProdutosPorDepartamento(departamento)">
				<a>{{departamento.nome}}</a>
			</div>
			<ul ng-if="(!!departamento.filhos)">
				<li ng-repeat="filho1 in departamento.filhos">
					<div ng-click="consultaProdutosPorDepartamento(filho1)">
						<a>{{filho1.nome}}</a>
					</div>
					<ul ng-if="(!!filho1.filhos)">
						<li ng-repeat="filho2 in filho1.filhos">
							<div ng-click="consultaProdutosPorDepartamento(filho2)">
								<a>{{filho2.nome}}</a>
							</div>
							<ul ng-if="(!!filho2.filhos)">
								<li ng-repeat="filho3 in filho2.filhos">
									<div ng-click="consultaProdutosPorDepartamento(filho3)">
										<a>{{filho3.nome}}</a>
									</div>
								</li>
							</ul>
						</li>
					</ul>
				</li>
			</ul>
		</li>
	</ul>
</div>
<div class="panel panel-default" style="min-height: 300px;">
	<div class="panel-body">
		<div class="container-fluid">
			<div ng-repeat="produto in produtos" style="cursor: pointer;">
				<div class="col-xs-6 col-md-3" ng-repeat="imagem in produto.imagens">
					<div class="panel panel-default">
						<div class="panel-body">
							<a ng-click="consultarProduto(produto)" class="thumbnail" >
								<img src="data:image/svg+xml;base64,{{imagem.arquivo.dados}}" title="{{imagem.arquivo.id}} {{imagem.arquivo.nome}}"/>
							</a>
						</div>
						<div class="panel-footer">
							<div class="well well-sm">
								<div>{{produto.nome}}</div>
								<div style="text-align:right;">{{produto.valor.valor | currency }}</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

