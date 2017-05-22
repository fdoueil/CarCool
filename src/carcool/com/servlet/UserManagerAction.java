package carcool.com.servlet;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carcool.com.dao.MaDao;
import carcool.com.model.Utilisateur;


/**
 * Servlet implementation class UserManagerAction
 */
@WebServlet("/users")
public class UserManagerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String VIEW_PAGES_URL = "/WEB-INF/user/users.jsp";
	private HttpSession session;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserManagerAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession();
		
		HashSet<Utilisateur> users = (HashSet<Utilisateur>)session.getAttribute("users");
		if (users==null) {
			users = MaDao.getUserDao().getUtilisateurs();
			session.setAttribute("users", users);
		}
		//response.getWriter().append("Compte:" + String.valueOf(users.size()));
		this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
