package carcool.com.servlet;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
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

		//Définition du type de contenu de la réponse
		response.setContentType("text/html");
		
		LOGGER.logger_begin(LOGGER.getName());
		
		//Récupération des informations du formulaire
		String email = request.getParameter(PARAM_NAME_EMAIL);
		String password = request.getParameter(PARAM_NAME_PWD);
		
		LOGGER.info("Mail d'authentification saisi par l'utilisateur: " + email);
		LOGGER.info("Mot de passe saisi par l'utilisateur: " + password);
		
		//Récupération de la liste des utilisateurs
		HashSet<Utilisateur> utilisateursEnregistres = MaDao.getUserDao().getUtilisateurs();
		//Si j'ai une liste d'utilisateurs
		if (utilisateursEnregistres.size()>0){
			
			//Recherche d'un utilisateur correspondant aux informations récupérées dans le formulaire		
			Utilisateur utilisateur = utilisateursEnregistres.iterator().next();
			while (!utilisateur.getEmail().equals(email) || !utilisateur.getPassword().equals(password)) {
				if (utilisateursEnregistres.iterator().hasNext()) {
					utilisateur = utilisateursEnregistres.iterator().next();
				}
			}
			
			// L'utilisateur a été trouvé
			if(utilisateur.getEmail().equals(email) && utilisateur.getPassword().equals(password)){
					//On crée un Cookie
					Cookie cookie = new Cookie("usermail", email);
					cookie.setVersion(1);
					cookie.setComment("Authentification OK");
					cookie.setMaxAge(900); //Cookie conservé pendant 15 minutes
					cookie.setHttpOnly(true);
					response.addCookie(cookie);
					
					//On renvoie l'utilisateur à l'index avec message de bienvenue
					RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
		            rd.forward(request, response); 
		            LOGGER.info("Utilisateur trouvé");
			}
			// L'utilisateur n'a pas été trouvé
			else {
				//On retourne à l'authtentification avec un message d'erreur
				RequestDispatcher rd=request.getRequestDispatcher("authentification_error.jsp");  
	            rd.forward(request, response);  
	            LOGGER.info("Utilisateur non trouvé");
			}
		}
		else {
			//On retourne à l'authtentification avec un message d'erreur
			RequestDispatcher rd=request.getRequestDispatcher("authentification_error.jsp");  
            rd.forward(request, response);
            LOGGER.info("Utilisateur non trouvé");
		}
		LOGGER.logger_end();
	}

}
