<div class="panel panel-default">
	<div class="panel-body">
		<div>
			<div>
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<button ng-click="limpar()" class="btn btn-primary navbar-btn" ng-disabled="clienteForm.$invalid">Novo</button>
						<button ng-click="salvar(cliente)" class="btn btn-primary navbar-btn" ng-disabled="clienteForm.$invalid">Salvar</button>
						<button ng-click="excluir(cliente)" class="btn btn-primary navbar-btn" ng-disabled="!(!!clienteForm.id)">Excluir</button>
					</div>
				</nav>
				<div class="panel panel-default">
					<form name="clienteForm">
						<input class="form-group form-control" ng-model="cliente.id" name="id" placeholder="Cod." readonly title="Cod." /> 
						<input class="form-group form-control" ng-model="cliente.usuario.nome" name="nome" placeholder="Nome" title="Nome" required ng-minLength="3" /> 
						<input class="form-group form-control" ng-model="cliente.usuario.email" name="email" placeholder="E-mail" title="E-mail" required ng-minLength="3" /> 
						<input class="form-group form-control" ng-model="cliente.telefones" name="telefones" placeholder="Telefore(s)" title="Telefore(s)" required ng-minLength="3" /> 
						<input class="form-group form-control" ng-model="cliente.cpfCnpj" name="cpfCnpj" placeholder="CPF/CNPJ" title="CPF/CNPJ" required ng-minLength="3" /> 
						<input class="form-group form-control" ng-model="cliente.dtNascimento" name="dtNascimento" placeholder="Data de nascimento" title="Data de nascimento" mask="39/99/9999" /> 
						<input class="form-group form-control" ng-model="cliente.usuario.login" name="login" placeholder="Login" title="Login" required ng-minLength="3" /> 
						<input class="form-group form-control" ng-model="cliente.usuario.senha" name="senha" placeholder="Senha" title="Senha" required ng-minLength="3" />
					</form>
					<div class="panel panel-default">
						<div class="panel-heading">Endereços</div>
						<div class="panel-body">
							<table class="table table-striped">
								<tr ng-repeat="endereco in cliente.enderecos" ng-click="selecionarEndereco(endereco)" style="cursor: pointer;">
									<td>{{endereco.logradouro}}</td>
									<td>{{endereco.cidade}}</td>
									<td>{{endereco.uf}}</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="panel panel-default" ng-show="endereco">
						<div class="panel-heading">Endereço de entrega</div>
						<div class="panel-body">
							<form name="enderecoForm">
								<input class="form-group form-control" ng-model="endereco.id" name="id" placeholder="Cod." readonly> 
								<input class="form-group form-control" ng-model="endereco.contato" name="contato" placeholder="Contato" required ng-minLenght="3">
								<input class="form-group form-control" ng-model="endereco.logradouro" name="logradouro" placeholder="Logradouro" required ng-minLength="3"> 
								<input class="form-group form-control" ng-model="endereco.cidade" name="cidade" placeholder="Cidade" required ng-minLength="3">
								<input class="form-group form-control" ng-model="endereco.cep" name="cep" placeholder="CEP" required ng-minLength="3" mask="99999-999">
								<input class="form-group form-control" ng-model="endereco.uf" name="uf" placeholder="UF" required mask="w?s?" repeat="2" restrict="reject"> 
								<button ng-click="enderecoOK(endereco)" class="btn btn-primary" ng-disabled="enderecoForm.$invalid">OK</button>
							</form>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>
