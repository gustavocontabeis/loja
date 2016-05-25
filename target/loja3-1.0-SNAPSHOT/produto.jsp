<div class="panel panel-default">
	<div class="panel-body">
		<div>
			<div>
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<button type="button" class="btn btn-primary navbar-btn" data-toggle="modal" data-target="#myModal">Novo</button>
					</div>
				</nav>
				
				<div class="panel panel-default">
					<div class="panel-heading">Listagem de produtos</div>
					<div ng-hide="produtos.length > 0">Nenhum produto na tabela.</div>
					<table class="table table-striped" ng-show="produtos.length > 0">
						<tr>
							<th>Sel.</th>
							<th>Cod.</th>
							<th>Nome</th>
							<th>Descrição</th>
							<th>Cod.Ref.Fabricante</th>
							<th>Ativo</th>
							<th>Departamento</th>
							<th>Fabricante</th>
						</tr>
						<tr ng-repeat="produto in produtos">
							<td><input type="checkbox" ng-model="produto.selecionado" /></td>
							<td ng-click="selecionar(produto)" style="cursor: pointer;">{{produto.id}}</td>
							<td ng-click="selecionar(produto)" style="cursor: pointer;">{{produto.nome}}</td>
							<td ng-click="selecionar(produto)" style="cursor: pointer;">{{produto.descricao}}</td>
							<td ng-click="selecionar(produto)" style="cursor: pointer;">{{produto.codReferenciaFabricante}}</td>
							<td ng-click="selecionar(produto)" style="cursor: pointer;">
								<div class="checkbox"> 
									<input type="checkbox" ng-checked="produto.ativo">
								</div>
							</td>
							<td ng-click="selecionar(produto)" style="cursor: pointer;">{{produto.departamento.nome}}</td>
							<td ng-click="selecionar(produto)" style="cursor: pointer;">{{produto.fabricante.nome}}</td>
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
									<h4 class="modal-title" id="myModalLabel">Cadastro de Produtos</h4>
								</div>
								<div class="modal-body">
								<script type="text/javascript">
								<!--
								$('.nav-tabs a').click(function (e) {
								    e.preventDefault();
								    $(this).tab('show');
								});
								//-->
								</script>
								<form name="produtoForm">
									<ul class="nav nav-tabs">
										<li role="presentation" class="active"><a data-toggle="tab" href="#prod" role="tab" aria-controls="prod">Produto</a></li>
										<li role="presentation"><a data-toggle="tab" href="#imagens" role="tab" aria-controls="imagens">Imagens</a></li>
										<li role="presentation"><a data-toggle="tab" href="#valores" role="tab" aria-controls="valores">Valores</a></li>
										<li role="presentation"><a data-toggle="tab" href="#caracteristicas" role="tab" aria-controls="caracteristicas">Caracteristicas</a></li>
										<li role="presentation" ng-hide="!(!!produto.id)"><a data-toggle="tab" href="#adicionarImagens" role="tab" aria-controls="adicionarImagens">Adicionar Imagens</a></li>
									</ul>
									<div class="tab-content">
										<div id="prod" class="tab-pane fade in active" role="tabpanel">
											<div class="container-fluid">
												<br />
												<input class="form-group form-control" ng-model="produto.id" name="id" placeholder="Cod." readonly /> 
												<input class="form-group form-control" ng-model="produto.nome" name="nome" placeholder="Nome do produto" ng-required="true" ng-minLength="3" />
												<input class="form-group form-control" ng-model="produto.descricao" name="descricao" placeholder="Descrição" ng-required="true" ng-minLength="3" />
												<input class="form-group form-control" ng-model="produto.codReferenciaFabricante" name="codReferenciaFabricante" placeholder="codReferenciaFabricante" ng-required="true" ng-minLength="3" />
												<div class="autocomplete form-group form-control" ng-show="exibeAutocomplDepto">
													<a class="link form-group form-control" href="pesquisa/empresa/" ng-repeat="departamento in departamentos"> </a>
												</div>
												<div class="checkbox form-group form-control">
													<label><input ng-model="produto.ativo" name="ativo" type="checkbox" />Ativo</label>
												</div> 
												<select class="form-group form-control" ng-model="produto.fabricante.id" ng-required="true" ng-options="fabricante.id as fabricante.nome for fabricante in fabricantes">
													<option value="">Selecione um fabricante</option>
												</select>
												<select class="form-group form-control" ng-model="produto.departamento.id" ng-required="true" ng-options="departamento.id as (departamento.estrutura+'-'+departamento.nome) for departamento in departamentos">
													<option value="">Selecione um departamento</option>
												</select>
											</div>
										</div>
										<div id="imagens" class="tab-pane fade" role="tabpanel">
											<div class="container-fluid">
												<div class="col-xs-6 col-md-3" ng-repeat="imagem in produto.imagens">
												    <a class="thumbnail">
												    	<img src="data:image/svg+xml;base64,{{imagem.arquivo.dados}}" class="img-thumbnail" />
													    <div>{{imagem.arquivo.id}} - {{imagem.arquivo.nome}}</div>
												    	<div ng-click="excluirImagem(produto, imagem)"><span class="glyphicon glyphicon-trash"></span></div>
												    </a>
												</div>
											</div>
										</div>
										<div id="valores" class="tab-pane fade" role="tabpanel">
											<div class="container-fluid">
												<input class="form-group form-control" ng-model="produto.valor.id" name="idValor" placeholder="Cod." readonly /> 
												<input class="form-group form-control" ng-model="produto.valor.valor" name="valor" placeholder="Valor do produto" ng-required="true" />
												<input class="form-group form-control" ng-model="produto.valor.dtInicio" name="dtInicio" placeholder="Data de início de vigência" ui-date />
												<input class="form-group form-control" ng-model="produto.valor.dtFim" name="dtFim" placeholder="Data do fim de vigência" ui-date />
												<input class="form-group form-control" ng-model="produto.valor.ativo" name="ativo" type="checkbox" />
											</div>
										</div>
										<div id="caracteristicas" class="tab-pane fade" role="tabpanel">
											<div class="container-fluid">
												<br />
												<div class="form-horizontal">
													<table class="table">
														<tr ng-repeat="car in produto.caracteristicas">
															<td><input ng-model="car.tipo" class="form-control" /></td>
															<td><input ng-model="car.valor" class="form-control" /></td>
														</tr>
													</table>
												</div>
												<button ng-click="novaCaracteristica()" class="btn btn-primary" ng-disabled="produtoForm.$invalid">Adicionar característica</button>
											</div>
										</div>
										<div id="adicionarImagens" class="tab-pane fade" role="tabpanel">
											<div class="container-fluid">
												<div>
													<div>
														<table class="table">
															<tr>
																<td style="width: 20%">
																	<label for="nomeDoArquivo" class="control-label">Tipo de imagem</label>
																</td>
																<td>
																	<select id="nomeDoArquivo" name="nomeDoArquivo" class="form-control">
																		<option value="Capa">Capa</option>
																		<option value="Frontal">Frontal</option>
																		<option value="Lateral">Lateral</option>
																		<option value="LateralD">Lateral Direito</option>
																		<option value="LateralE">Lateral Esquerdo</option>
																		<option value="Traseiro">Traseiro</option>
																		<option value="Cima">Cima</option>
																		<option value="Detalhe">Detalhe</option>
																	</select>
																</td>
															</tr>
														</table>
													    <span class="btn btn-success fileinput-button">
													        <i class="glyphicon glyphicon-plus"></i>
													        <span>Selecione o arquivo...</span>
													        <!-- The file input field used as target for the file upload widget -->
													        <input id="fileupload" type="file" name="files[]">
													    </span>
													    <div id="progress" class="progress" style="margin-top: 10px">
													        <div class="progress-bar progress-bar-success"></div>
													    </div>
													    <!-- The container for the uploaded files -->
													    <div id="files" class="files"></div>
													</div>
												</div>
											</div>
											<script>
											/*jslint unparam: true */
											/*global window, $ */
											$(function () {
											    'use strict';
											    // Change this to the location of your server-side upload handler:
											    	var url = '/loja/UploadServlet';
											    $('#fileupload').fileupload({
											        url: url,
											        dataType: 'json',
											        done: function (e, data) {
											            $.each(data.result.files, function (index, file) {
											                $('<p/>').text(file.name).appendTo('#files');
											            });
											        },
											        progressall: function (e, data) {
											            var progress = parseInt(data.loaded / data.total * 100, 10);
											            $('#progress .progress-bar').css('width', progress + '%');
											        }
											    }).prop('disabled', !$.support.fileInput).parent().addClass($.support.fileInput ? undefined : 'disabled');
											});
											</script>
										</div>
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button ng-click="limpar()" class="btn btn-primary" ng-disabled="produtoForm.$invalid">Novo</button>
								<button ng-click="salvar(produto)" class="btn btn-primary " ng-disabled="produtoForm.$invalid">Salvar</button>
								<button ng-click="excluir(produto)" class="btn btn-primary form-group" ng-disabled="!(!!produto.id)">Excluir</button>
								<button ng-click="adicionarImagens(produto)" class="btn btn-primary form-group" ng-disabled="!(!!produto.id)" ng-if="false">Adicionar imagens</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
