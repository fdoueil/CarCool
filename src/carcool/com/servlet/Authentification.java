package carcool.com.servlet;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import carcool.com.dao.MaDao;
import carcool.com.model.Utilisateur;

/**
 * Servlet implementation class Authentification
 */
@WebServlet("/authentification")
public class Authentification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String PARAM_NAME_EMAIL = "email";
	private final String PARAM_NAME_PWD = "password";
    
	private LogsServlets LOGGER = new LogsServlets (Authentification.class.getName(),null, Authentification.class.getName());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authentification() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		LOGGER.logger_begin(LOGGER.getName());
		String email = request.getParameter(PARAM_NAME_EMAIL);
		String password = request.getParameter(PARAM_NAME_PWD);
		
		LOGGER.info("Mail d'authentification saisi par l'utilisateur: " + email);
		LOGGER.info("Mot de passe saisi par l'utilisateur: " + password);
		
		HashSet<Utilisateur> utilisateursEnregistres = MaDao.getUserDao().getUtilisateurs();
		for (Utilisateur utilisateur : utilisateursEnregistres) {
			if(utilisateur.getEmail().equals(email)){
				if("motDePasse".equals(password)){
					//Authentification réussie. On crée un Cookie
					Cookie cookie = new Cookie("usermail", email);
					cookie.setVersion(1);
					cookie.setComment("Authentification OK");
					cookie.setMaxAge(900); //Cookie conservé pendant 15 minutes
					cookie.setHttpOnly(true);
					response.addCookie(cookie);
					
					//Pour afficher le nom d'utilisateur dans l'interface
					
				}
			}
		}
		LOGGER.logger_end();
	}

}
