package carcool.com.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import carcool.com.dao.MaDao;
import carcool.com.enums.Categorie;
import carcool.com.model.Trajet;
import carcool.com.model.Utilisateur;


/**
 * Servlet implementation class Register
 */
@WebServlet("/registerUser")
public class RegisterUser extends HttpServlet {
	private static final String ACTION_FAILED = "Echec de l'inscription";
	private static final String ACTION_SUCCESS = "Succès de l'inscription";
	private static final String PARAM_NAME_NAME = "nom";
	private static final String PARAM_NAME_PWD2 = "password2";
	private static final String PARAM_NAME_PWD1 = "password1";
	private static final String PARAM_NAME_EMAIL = "email";
	private static final String PARAM_NAME_ADDRESS = "adresse";
	private static final String PARAM_NAME_LONG = "longitude";
	private static final String PARAM_NAME_LAT = "latitude";
	private static final String PARAM_CATEGORIE_USAGER = "categorie";
	
	private static final long serialVersionUID = 1L;
	public static String VIEW_PAGES_URL = "/WEB-INF/register.jsp";

	private Map<String, String> errors;
	
	private String actionMessage;
	private String actionResult;

	private HttpSession session;
	
	private LogsServlets LOGGER = new LogsServlets (RegisterUser.class.getName(),null, RegisterUser.class.getName());
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterUser() {
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
		LOGGER.logger_begin(LOGGER.getName());
		LOGGER.config("Initialisation de la Servlet.");
		super.init();
		LOGGER.config("Fin de l'initialisation de la Servlet.");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.config("Servlet démarrée");
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
		//String pwd1 = RealmBase.Digest(request.getParameter(PARAM_NAME_PWD1),"SHA", pwd1);
		String pwd1 = request.getParameter(PARAM_NAME_PWD1);
		String pwd2 = request.getParameter(PARAM_NAME_PWD2);
		String nom = request.getParameter(PARAM_NAME_NAME);
		String adresse = request.getParameter(PARAM_NAME_ADDRESS); 
		String longitude = request.getParameter(PARAM_NAME_LONG);
		String latitude= request.getParameter(PARAM_NAME_LAT);
		String categorie = request.getParameter(PARAM_CATEGORIE_USAGER);
		
		LOGGER.info("L'utilisateur a saisi l'email: " + email);
		LOGGER.info("L'utilisateur a saisi le mot de passe: " + pwd1);
		LOGGER.info("L'utilisateur a saisi le nom d'utilisateur: " + nom);
		LOGGER.info("L'utilisateur a sélectionné comme type d'usager: " + categorie);
		LOGGER.info("L'utilisateur a sélectionné comme adresse: " + adresse);
		LOGGER.info("L'utilisateur a sélectionné comme latitude: " + latitude);
		LOGGER.info("L'utilisateur a sélectionné comme longitude: " + longitude);

	
		if (MaDao.getUserDao().getUtilisateurs()==null){
			MaDao.getUserDao().setUsers(new HashSet<Utilisateur>());
		}
		int identifiantUtilisateur = MaDao.getUserDao().getUtilisateurs().size()+1;
		Utilisateur newUser = new Utilisateur(identifiantUtilisateur, email, nom, pwd1, pwd2);
		
		String email_validate = newUser.validateEmail();
		String password_validate = newUser.validatePwd();

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
		
		Trajet newTrajet = null;
		
		
		//Récupération de la liste des utilisateurs
		HashSet<Utilisateur> utilisateursEnregistres = MaDao.getUserDao().getUtilisateurs();
		LOGGER.info("Fin récupération liste utilisateurs.");
		//Si j'ai une liste d'utilisateurs
	
		/*LOGGER.info("Début recherche utilisateur.");
		if (!utilisateursEnregistres.isEmpty()){
			//Recherche d'un utilisateur correspondant aux informations récupérées dans le formulaire		
			//Utilisateur utilisateur = null;
			Iterator user_iterator = utilisateursEnregistres.iterator();
			
			LOGGER.info("Début boucle for");
			for (Utilisateur utilisateur : utilisateursEnregistres) {
				LOGGER.info("Boucle for: Traitement sur l'utilisateur " + utilisateur.getNom()
				+". Email: " + utilisateur.getEmail() 
				+ ". Mot de passe: " + utilisateur.getPassword() + ".");
				if(utilisateur.getEmail().equals(email) && utilisateur.getPassword().equals(pwd1)){
					actionResult="0";
					session.setAttribute("authUser", utilisateur);
					break;
				}
			}
		}*/
		//L'utilisateur existe déjà. On ne peut donc le recréer
		if(MaDao.getUserDao().existUser(email, pwd1)){
			actionResult="0";
		}
		
		// OK donc on ajoute l'utilisateur à la liste
		if (actionResult.equals("1")) {
			//Ajout de l'utilisateur à la DAO
			MaDao.getUserDao().getUtilisateurs().add(newUser);
			//Ajout de trajets à l'utilisateur. 
			//Je recherche l'utilisateur créé
			Utilisateur user = MaDao.getUserDao().getUserById(identifiantUtilisateur);
			// Je crée sa liste de trajets initial qui est vide
			HashSet<Trajet> trajetsUtilisateur = new HashSet<Trajet>();
			//Trajet domicile-travail
			newTrajet= new Trajet(identifiantUtilisateur,adresse,"Labège, Rue Edmond Rostand",0,null,null,null);
			//Ajout du trajet domicile-travail de l'utilisateur à la liste de ses trajets
			trajetsUtilisateur.add(newTrajet);
			user.setTrajets(trajetsUtilisateur);
			
			//Enregistrement de la catégorie de l'utilisateur (Conducteur par défaut, ou passager ou les deux)
			if ("Passager".equals(categorie)){
				LOGGER.info("Mise de la catégorie à Passager");
				user.setCategorie(Categorie.P);
			}
			if ("Conducteur ou Passager".equals(categorie)){
				LOGGER.info("Mise de la catégorie à Conducteur ou Passager");
				user.setCategorie(Categorie.BOTH);
			}
			LOGGER.info("######################################");
			LOGGER.info("Catégorie enregistrée: "+ user.getCategorie().toString());
			LOGGER.info("Utilisateur " + newUser.getNom() + " ajouté à la liste DAO des utilisateurs");
		
		
			session.setAttribute("users", users);
			
			//session.setAttribute("authUser", newUser);
			
			// Champs déjà saisi mail + name (on doit pas les perdre)
			request.setAttribute("newUser", newUser);
			newTrajet.setLatDepart(Double.parseDouble(latitude));
			newTrajet.setLongDepart(Double.parseDouble(longitude));
			
			request.setAttribute("newTrajet", newTrajet);
			
			request.setAttribute("errors", errors);
			request.setAttribute("actionMessage", actionMessage);
			request.setAttribute("actionResult", actionResult);
		}
		
		this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).forward(request, response);
	}
	

}
