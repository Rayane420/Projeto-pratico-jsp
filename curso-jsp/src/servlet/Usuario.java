package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

/**
 * Servlet implementation class Usuario
 */
@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("editar")) {

				BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", beanCursoJsp);
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("listartodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp"); // indicando para qual
																								// tela vai redirecionar
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equalsIgnoreCase("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher(
						"/cadastroUsuario.jsp"); /* falando para permanecer na mesma página após o cadastro */
				request.setAttribute("usuarios", daoUsuario.listar());
				view.forward(request, response); // fazendo redirecionamento da página

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login"); // recebendo o login que vem da requisição
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String telefone = request.getParameter("telefone");
			
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");		
			String ibge = request.getParameter("ibge");

			BeanCursoJsp usuario = new BeanCursoJsp();
			usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setTelefone(telefone);
			
			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setEstado(estado);
			usuario.setIbge(ibge);
			
			try {
				String msgL = null;
				boolean podeInserir = true;

				if (login == null || login.isEmpty()) {
					msgL = "Login deve ser informado";
					podeInserir = false;
				} else if (senha == null || senha.isEmpty()) {
					msgL = "Senha deve ser informado";
					podeInserir = false;
				} else if (nome == null || nome.isEmpty()) {
					msgL = "Nome deve ser informado";
					podeInserir = false;
				} else if (telefone == null || telefone.isEmpty()) {
					msgL = "Telefone deve ser informado";
					podeInserir = false;
				}

				if (msgL != null) {
					request.setAttribute("msg", msgL);
				} else if ((id == null || id.isEmpty()) && daoUsuario.validarLogin(login) && podeInserir) {
					daoUsuario.Salvar(usuario);
					request.setAttribute("msgC", "Usuário cadastrado com sucesso!");

				}

				else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
					request.setAttribute("msg","Já existe um usuário com o login informado, favor informar login diferente!");
					request.setAttribute("user", usuario);
				}

				else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
					request.setAttribute("msg", "Essa senha já existe, favor cadastrar nova senha");
					request.setAttribute("user", usuario); // mantendo dados carregados em tela
				}

				if (id == null || id.isEmpty() && daoUsuario.validarLogin(login) && daoUsuario.validarSenha(senha)
						&& podeInserir) {
					daoUsuario.Salvar(usuario);
					request.setAttribute("msgC", "Usuário cadastrado com sucesso!");

				} else if (id != null && !id.isEmpty()) {
					if (!daoUsuario.validarLoginUpdate(login, id)) {
						request.setAttribute("msg", "Este login já pertence a outro usuário");
						request.setAttribute("user", usuario);
					} else {
						daoUsuario.atualizar(usuario);
						request.setAttribute("msgC", "Usuário atualizado com sucesso!");
					}
				}
				if (!podeInserir) {
					request.setAttribute("user", usuario);
				}

				RequestDispatcher view = request.getRequestDispatcher(
						"/cadastroUsuario.jsp"); /* falando para permanecer na mesma página após o cadastro */
				request.setAttribute("usuarios", daoUsuario.listar());
				request.setAttribute("msgC", "Salvo com sucesso");
				view.forward(request, response); // fazendo redirecionamento da página

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
