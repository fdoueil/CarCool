package carcool.com.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	private HttpSession session;
	
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

		session = request.getSession();
		
		//Définition du type de contenu de la réponse
		response.setContentType("text/html");
		
		LOGGER.logger_begin(LOGGER.getName());
		
		//Récupération des informations du formulaire
		String email = request.getParameter(PARAM_NAME_EMAIL);
		String password = request.getParameter(PARAM_NAME_PWD);
		
		LOGGER.info("Mail d'authentification saisi par l'utilisateur: " + email);
		LOGGER.info("Mot de passe saisi par l'utilisateur: " + password);
		
		LOGGER.info("Début récupération liste utilisateurs.");
		//Récupération de la liste des utilisateurs
		HashSet<Utilisateur> utilisateursEnregistres = MaDao.getUserDao().getUtilisateurs();
		LOGGER.info("Fin récupération liste utilisateurs.");
		//Si j'ai une liste d'utilisateurs
		
		LOGGER.info("Début recherche utilisateur.");
		if (!utilisateursEnregistres.isEmpty()){

			//Recherche de l'existence d'un utilisateur ayant l'email et le mot de passe donnés
			boolean utilisateurTrouve = MaDao.getUserDao().existUser(email, password);
		
			// L'utilisateur a été trouvé
			if(utilisateurTrouve){
					int idUserAuthentifie = MaDao.getUserDao().recupererIdUser(email, password);
					Utilisateur newUser = MaDao.getUserDao().getUserById(idUserAuthentifie);
					
		            //On ajoute utilisateurTrouve pour exploitation dans la jsp afin d'afficher le message de bienvenue.
		            request.setAttribute("findUser", utilisateurTrouve);
		            // Ajouts des sous-menus
		            session.setAttribute("utilisateurConnecte", "1");
		            session.setAttribute("authUser", newUser);
		            
					//On renvoie l'utilisateur à l'index avec ajout du message de bienvenue
					RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
		            rd.forward(request, response); 
		            LOGGER.info("Utilisateur trouvé");
		            		            
			}
			// L'utilisateur n'a pas été trouvé
			else {
				//On retourne à l'authentification avec un message d'erreur
				RequestDispatcher rd=request.getRequestDispatcher("authentification_error.jsp");  
	            rd.forward(request, response);  
	            LOGGER.info("Utilisateur non trouvé");
			}
		}
		else {
			//La liste des utilisateurs est vide. On retourne à l'authentification avec un message d'erreur
			RequestDispatcher rd=request.getRequestDispatcher("authentification_error.jsp");  
            rd.include(request, response);
            LOGGER.info("Utilisateur non trouvé");
		}
		LOGGER.info("Fin recherche utilisateur.");
		LOGGER.logger_end();
	}

}
