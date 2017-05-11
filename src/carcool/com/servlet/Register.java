package carcool.com.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final String ACTION_FAILED = "Echec de l'inscription";
	private static final String ACTION_SUCCESS = "Succ�s de l'inscription";
	private static final String PARAM_NAME_NAME = "name";
	private static final String PARAM_NAME_PWD2 = "pwd2";
	private static final String PARAM_NAME_PWD1 = "pwd1";
	private static final String PARAM_NAME_EMAIL = "email";
	private static final long serialVersionUID = 1L;
	public static String VIEW_PAGES_URL = "/WEB-INF/register.jsp";

	private Map<String, String> errors;
	private String actionMessage;
	private String actionResult;

	private HttpSession session;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
/*	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		session = request.getSession();
		HashSet<User> users = (HashSet<User>)session.getAttribute("users");
		if (users==null) {
			users = MaDao.getUserDao().getUsers();
		}
		
//		User newUser = null;

		String email = request.getParameter(PARAM_NAME_EMAIL);
		String pwd1 = request.getParameter(PARAM_NAME_PWD1);
		String pwd2 = request.getParameter(PARAM_NAME_PWD2);
		String name = request.getParameter(PARAM_NAME_NAME);

		newUser = new User(email, pwd1, pwd2, name);
		
		String email_validate = newUser.validateEmail();
		String password_validate = newUser.validatePwd();
		// Pas besoin - newUser.validateName();

		actionMessage = ACTION_SUCCESS;
		actionResult = "1";

		errors = new HashMap<String, String>();

		if (email_validate != null) {
			// KO
			errors.put(PARAM_NAME_EMAIL, email_validate);
			actionMessage = ACTION_FAILED;
			actionResult = "0";
		}

		if (password_validate != null) {
			// KO
			errors.put(PARAM_NAME_PWD1, password_validate);
			actionMessage = ACTION_FAILED;
			actionResult = "0";
		}
		
		// OK donc on ajoute l'utilisateur � la liste
		if (actionResult.equals("1")) {
			MaDao.getUserDao().getUsers().add(newUser);
		}
		
		session.setAttribute("users", users);
		
		// Champs d�j� saisi mail + name (on doit pas les perdre)
		request.setAttribute("newUser", newUser);
		
		request.setAttribute("errors", errors);
		request.setAttribute("actionMessage", actionMessage);
		request.setAttribute("actionResult", actionResult);

		// doGet(request, response);
		// request.getRequestDispatcher(VIEW_PAGES_URL).forward(request,
		// response);
		this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).forward(request, response);
	}*/
}
