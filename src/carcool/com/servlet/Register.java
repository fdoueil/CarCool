package carcool.com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carcool.com.dao.MaDao;
import carcool.com.model.Utilisateur;


/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final String ACTION_FAILED = "Echec de l'inscription";
	private static final String ACTION_SUCCESS = "Succès de l'inscription";
	private static final String PARAM_NAME_NAME = "nom";
	private static final String PARAM_NAME_PWD2 = "password2";
	private static final String PARAM_NAME_PWD1 = "password1";
	private static final String PARAM_NAME_EMAIL = "email";
	
	private static final String PARAM_TYPE_USAGER = "typeusager";
	
	private static final long serialVersionUID = 1L;
	public static String VIEW_PAGES_URL = "/WEB-INF/register.jsp";

	private Map<String, String> errors;
	private String actionMessage;
	private String actionResult;

	private HttpSession session;
	
	private LogsServlets LOGGER = new LogsServlets (Register.class.getName(),null, Register.class.getName());
	
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		LOGGER.logger_end();
		super.destroy();

	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		LOGGER.logger_begin(LOGGER.getName());
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		session = request.getSession();
		HashSet<Utilisateur> users = (HashSet<Utilisateur>)session.getAttribute("users");
		if (users==null) {
			users = MaDao.getUserDao().getUtilisateurs();
		}
		
//		User newUser = null;

		String email = request.getParameter(PARAM_NAME_EMAIL);
		String pwd1 = request.getParameter(PARAM_NAME_PWD1);
		String pwd2 = request.getParameter(PARAM_NAME_PWD2);
		String nom = request.getParameter(PARAM_NAME_NAME);
		String typeusager = request.getParameter(PARAM_TYPE_USAGER);
		
		LOGGER.info("L'utilisateur a saisi l'email: " + email);
		LOGGER.info("L'utilisateur a saisi le mot de passe: " + pwd1);
		LOGGER.info("L'utilisateur a saisi le nom d'utilisateur: " + nom);
		LOGGER.info("L'utilisateur a sélectionné comme type d'usager: " + typeusager);

		Utilisateur newUser = new Utilisateur(1, email, pwd1, pwd2, nom);
		
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
		
		// OK donc on ajoute l'utilisateur à la liste
		if (actionResult.equals("1")) {
			MaDao.getUserDao().getUtilisateurs().add(newUser);
			LOGGER.info("Utilisateur " + newUser.getNom() + " ajouté à la liste des utilisateurs");
		}
		
		session.setAttribute("users", users);
		
		// Champs déjà saisi mail + name (on doit pas les perdre)
		request.setAttribute("newUser", newUser);
		
		request.setAttribute("errors", errors);
		request.setAttribute("actionMessage", actionMessage);
		request.setAttribute("actionResult", actionResult);

		this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).forward(request, response);
	}
	

}
