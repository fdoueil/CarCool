package carcool.com.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import carcool.com.enums.Categorie;
import carcool.com.model.Trajet;
import carcool.com.model.Utilisateur;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/updateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//private static final String ACTION_FAILED = "Echec de l'inscription";
	//private static final String ACTION_SUCCESS = "Succès de l'inscription";
	private static final String PARAM_NAME_NAME = "nom";
	private static final String PARAM_NAME_PWD2 = "password2";
	private static final String PARAM_NAME_PWD1 = "password1";
	private static final String PARAM_NAME_EMAIL = "email";
	private static final String PARAM_NAME_ADDRESS = "adresse";
	private static final String PARAM_NAME_LONG = "longitude";
	private static final String PARAM_NAME_LAT = "latitude";
	private static final String PARAM_CATEGORIE_USAGER = "categorie";
	
	private Utilisateur newUser=null;
	private Trajet newTrajet=null;
	
	public static String VIEW_PAGES_URL = "/WEB-INF/update.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		newUser=(Utilisateur)request.getSession().getAttribute("authUser");
		request.setAttribute("newUser", newUser);
		newTrajet=((Trajet)newUser.getTrajets().toArray()[0]);
		request.setAttribute("newTrajet", newTrajet);
		this.getServletContext().getRequestDispatcher(VIEW_PAGES_URL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String email = request.getParameter(PARAM_NAME_EMAIL);
		String pwd1 = request.getParameter(PARAM_NAME_PWD1);
		String pwd2 = request.getParameter(PARAM_NAME_PWD2);
		String nom = request.getParameter(PARAM_NAME_NAME);
		String adresse = request.getParameter(PARAM_NAME_ADDRESS); 
		String longitude = request.getParameter(PARAM_NAME_LONG);
		String latitude = request.getParameter(PARAM_NAME_LAT);
		String categorie = request.getParameter(PARAM_CATEGORIE_USAGER);
		
		System.out.println("################################");
		System.out.println("Catégorie actuelle: "+ newUser.getCategorie().toString());

		//Mise à jour de l'utilisateur à partir des informations récupérées sur le formulaire
		if (email != newUser.getEmail()){
			newUser.setEmail(email);	
		}
		if (pwd1 != newUser.getPassword1() && pwd1.equals(pwd2)){
			newUser.setPassword1(pwd1);
			newUser.setPassword2(pwd2);
		}
		
		if (nom != newUser.getNom()){
			newUser.setNom(nom);
		}
		if (adresse != newTrajet.getDepuisAdresse()){
			// Création d'un trajet
			HashSet<Trajet> trajetsUtilisateur = new HashSet<Trajet>();
			//Trajet domicile-travail
			newTrajet= new Trajet(newUser.getIdUtilisateur(),adresse,"Labège, Rue Edmond Rostand",0,null,null,null);
			trajetsUtilisateur.add(newTrajet);
			newUser.getTrajets().clear();
			newUser.setTrajets(trajetsUtilisateur);
			//newUser.getTrajets().toArray()[0]=adresse;
		}
		
		if (latitude != String.valueOf(newTrajet.getLatDepart())){
			newTrajet.setLatDepart(Double.parseDouble(latitude));
		}
		
		if (longitude != String.valueOf(newTrajet.getLongDepart())){
			newTrajet.setLongDepart(Double.parseDouble(longitude));
		}
		
		if (categorie != newUser.getCategorie().toString()){
			if (categorie == "Conducteur"){
				newUser.setCategorie(Categorie.C);
			}
			if (categorie == "Passager"){
				newUser.setCategorie(Categorie.P);
			}
			if (categorie == "Conducteur ou Passager"){
				newUser.setCategorie(Categorie.BOTH);
			}
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
            rd.include(request, response);
	}
}
