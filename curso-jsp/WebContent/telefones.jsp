<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Telefones</title>
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>
	<a href="acessoliberado.jsp"><img src="resources/img/home2.png"
		alt="Home" title="Voltar para o Inicio" width="40px" height="40px"></a>
	<a href="index.jsp"><img src="resources/img/sair.png" alt="Home"
		title="Sair do sistema" width="40px" height="40px"></a>


	<center>
		<h1>Cadastro De Telefones</h1>
		<h3 style="color: #00FF00">${msgC}</h3>
		<h3 style="color: #FF4500">${msg}</h3>
		<center>
			<form action="salvarTelefone" method="post" id="formUser"
				onsubmit="return validarCampos()? true : false;">
				<ul class="form-style-1">
					<li>
						<table>

							<tr>
								<td>Usuário:</td>
								<td><input type="text" id="id" name="id" readonly="readonly" class="field-long"
									value="${userEscolhido.id}"></td>

								<td><input type="text" id="nome" name="nome"
									class="field-long" value="${userEscolhido.nome}"></td>
							</tr>

							<tr>
								<td>Número/Tipo</td>
								<td><input type="text" id="numero" name="numero"></td>
								<td><select id="tipo" name="tipo">
										<option>Residencial</option>
										<option>Celular</option>
										<option>Recado</option>
								</select></td>
							</tr>


							<tr>
								<td></td>
								<td><input type="submit" value="Salvar"></td>

							</tr>

						</table>
					</li>
				</ul>

			</form>
		</center>
		<p />
		<p />

		<table class="container">
			<thead>
				<tr>
					<th><h1>ID</h1></th>
					<th><h1>Número</h1></th>
					<th><h1>Tipo</h1></th>
					<th><h1>Exclusão</h1></th>
				</tr>
			</thead>
			<p />
			<p />
			<p />
			<p />
			<caption>
				<h1>Telefones Cadastrados</h1>
				<h2 style="color: #00FF00">${msgT}</h2>
			</caption>
			<c:forEach items="${telefones}" var="fone">

				<tbody>
					<tr>
						<td style="width: 150px"><c:out value="${fone.id}"></c:out></td>
						<td style="width: 150px"><c:out value="${fone.numero}"></c:out></td>
						<td><c:out value="${fone.tipo}"></c:out></td>

						<td style="width: 100px"><a
							href="salvarTelefone?acao=deleteFone&foneId=${fone.id}"><img
								src="resources/img/lixeira.png" alt="Excluir"
								title="Excluir Cadastro" width="20px" height="20px"><></a></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		<script type="text/javascript">
			function validarCampos() {
				if (document.getElementById("numero").value == '') {
					alert('Informe o número!');
					return false;
				} else if (document.getElementById("tipo").value == '') {
					alert('Informe o tipo!');
					return false;
				}
				return true;
			}
		</script>
</body>
</html>