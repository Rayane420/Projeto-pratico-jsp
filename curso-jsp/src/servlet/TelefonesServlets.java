package servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import beans.Telefones;
import dao.DaoTelefones;
import dao.DaoUsuario;

/**
 * Servlet implementation class TelefonesServlets
 */
@WebServlet("/salvarTelefone")
public class TelefonesServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();
	private DaoTelefones daoTelefones = new DaoTelefones();

	public TelefonesServlets() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			//capturando a ação que veio do formulário
			String acao = request.getParameter("acao");
			if (acao.equalsIgnoreCase("addFone")) {

				String user = request.getParameter("user"); // capturando usuário que veio do form

				BeanCursoJsp usuario = daoUsuario.consultar(user); // consultado usuario pelo id

				request.getSession().setAttribute("userEscolhido", usuario); // salvando usuário na sessão
				request.setAttribute("userEscolhido", usuario);

				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", daoTelefones.listar(usuario.getId()));
				request.setAttribute("msgC", "Salvo com Sucesso!");
				view.forward(request, response);

			}else if (acao.equalsIgnoreCase("deleteFone")) {
				String foneId = request.getParameter("foneId");
				daoTelefones.delete(foneId);
				
				BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");
				
				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
				request.setAttribute("telefones", daoTelefones.listar(beanCursoJsp.getId()));
				
				request.setAttribute("msgT", "Removido com sucesso!");
				view.forward(request, response);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");

			String numero = request.getParameter("numero");

			String tipo = request.getParameter("tipo");

			Telefones telefones = new Telefones();
			telefones.setNumero(numero);
			telefones.setTipo(tipo);
			telefones.setUsuario(beanCursoJsp.getId());

			daoTelefones.salvar(telefones);

			request.getSession().setAttribute("userEscolhido", beanCursoJsp); // salvando usuário na sessão
			request.setAttribute("userEscolhido", beanCursoJsp);

			RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");
			request.setAttribute("telefones", daoTelefones.listar(beanCursoJsp.getId()));
			request.setAttribute("msgC", "Salvo com Sucesso!");
			view.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
