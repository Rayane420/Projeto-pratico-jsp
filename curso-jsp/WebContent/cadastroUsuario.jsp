<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuário</title>
<link rel="stylesheet" href="resources/css/cadastro.css">

<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

</head>
<body>
	<a href="acessoliberado.jsp"><img src="resources/img/home2.png"
		alt="Home" title="Voltar para o Inicio" width="40px" height="40px"></a>
	<a href="index.jsp"><img src="resources/img/sair.png" alt="Home"
		title="Sair do sistema" width="40px" height="40px"></a>
	<center>
		<h1>Cadastro De Usuários</h1>
		<h3 style="color: #00FF00">${msgC}</h3>
		<h3 style="color: #FF4500">${msg}</h3>
		<center>
			<form action="salvarUsuario" method="post" id="formUser"
				onsubmit="return validarCampos()? true : false;">
				<ul class="form-style-1">
					<li>
						<table>

							<tr>
								<td>Id do Usuário: :</td>
								<td><input type="text" readonly="readonly" id="id"
									name="id" value="${user.id}" class="field-long"></td>
							</tr>

							<tr>
								<td>Login:</td>
								<td><input type="text" id="login" name="login"
									value="${user.login}" placeholder="Informe o login"></td>
								<td>Senha:</td>
								<td><input type="password" id="senha" name="senha"
									value="${user.senha}" placeholder="Informe a senha"></td>
							</tr>
							<tr>
								<td>Nome:</td>
								<td><input type="text" id="nome" name="nome"
									value="${user.nome}" placeholder="Informe o nome"></td>
								<td>Telefone:</td>
								<td><input type="text" id="telefone" name="telefone"
									value="${user.telefone}" placeholder="Informe telefone"></td>
							</tr>
							<tr>
								<td></p></td>
							</tr>
							<tr>
								<td>Cep:</td>
								<td><input type="text" id="cep" name="cep"
									onblur="consultaCep();" value="${user.cep}" placeholder="Informe um cep Válido"></td>
								<td>Rua:</td>
								<td><input type="text" id="rua" name="rua"
									value="${user.rua}"></td>
							</tr>
							<tr>
								<td>Bairro:</td>
								<td><input type="text" id="bairro" name="bairro"
									value="${user.bairro}"></td>
								<td>Cidade:</td>
								<td><input type="text" id="cidade" name="cidade"
									value="${user.cidade}"></td>
							</tr>
							<tr>
								<td>Uf:</td>
								<td><input type="text" id="estado" name="estado"
									value="${user.estado}"></td>
								<td>IBGE</td>
								<td><input type="text" id="ibge" name="ibge"
									value="${user.ibge}"></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" value="Salvar"> <input
									type="submit" value="Cancelar"
									onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset' "></td>

							</tr>

						</table>
					</li>
				</ul>
				<script type="text/javascript">
					function validarCampos() {
						if (document.getElementById("login").value == '') {
							alert('Por favor informe seu login');
							return false;
						} else if (document.getElementById("senha").value == '') {
							alert('Por favor informe sua senha');
							return false;
						} else if (document.getElementById("nome").value == '') {
							alert('Por favor informe seu nome');
							return false;
						} else if (document.getElementById("telefone").value == '') {
							alert('Por favor informe seu telefone');
							return false;
						}
						return true;
					}

					/**Adicionando o web service de consulta de cep*/
					function consultaCep() {
						var cep = $("#cep").val();

						$.getJSON("https://viacep.com.br/ws/" + cep
								+ "/json/?callback=?", function(dados) {

							if (!("erro" in dados)) {

								$("#rua").val(dados.logradouro);
								$("#bairro").val(dados.bairro);
								$("#cidade").val(dados.localidade);
								$("#estado").val(dados.uf);
								$("#ibge").val(dados.ibge);

							} //end if.
							else {
								//CEP pesquisado não foi encontrado.

								$("#cep").val('');
								$("#rua").val('');
								$("#bairro").val('');
								$("#cidade").val('');
								$("#estado").val('');
								$("#ibge").val('');

								alert("CEP não encontrado.");
							}
						});
					}
				</script>
			</form>
		</center>
		<p />
		<p />

		<table class="container">
			<thead>
				<tr>
					<th><h1>ID</h1></th>
					<th><h1>Login</h1></th>
					<th><h1>Nome</h1></th>
					<th><h1>Telefone</h1></th>
					<th><h1>Exclusão</h1></th>
					<th><h1>Edição</h1></th>
				</tr>
			</thead>
			<p />
			<p />
			<p />
			<p />
			<caption>
				<h1>Usuários Cadastrados</h1>
			</caption>
			<c:forEach items="${usuarios}" var="user">

				<tbody>
					<tr>
						<td style="width: 150px"><c:out value="${user.id}"></c:out></td>
						<td style="width: 150px"><c:out value="${user.login}"></c:out></td>
						<td><c:out value="${user.nome}"></c:out></td>
						<td style="width: 150px"><c:out value="${user.telefone}"></c:out></td>

						<td style="width: 100px"><a
							href="salvarUsuario?acao=delete&user=${user.id}"><img
								src="resources/img/lixeira.png" alt="Excluir"
								title="Excluir Cadastro" width="20px" height="20px"><></a></td>
						<td style="width: 100px"><a
							href="salvarUsuario?acao=editar&user=${user.id}"><img
								src="resources/img/editar.png" alt="Editar"
								title="Editar Cadastro" width="20px" height="20px"></a></td>
						<td style="width: 100px"><a
							href="salvarTelefone?acao=addFone&user=${user.id}"><img
								src="resources/img/phone.png" alt="Telefones"
								title="Telefones" width="20px" height="20px"></a></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
</body>
</html>