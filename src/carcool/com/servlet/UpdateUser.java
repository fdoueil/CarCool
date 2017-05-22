package carcool.com.servlet;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	//private static final String PARAM_NAME_PWD2 = "password2";
	//private static final String PARAM_NAME_PWD1 = "password1";
	private static final String PARAM_NAME_EMAIL = "email";
	private static final String PARAM_NAME_ADDRESS = "adresse";
	private static final String PARAM_NAME_LONG = "longitude";
	private static final String PARAM_NAME_LAT = "latitude";
	private static final String PARAM_TYPE_USAGER = "typeusager";
	
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
		Utilisateur newUser=(Utilisateur)request.getSession().getAttribute("authUser");
		request.setAttribute("newUser", newUser);
		Trajet newTrajet=((Trajet)newUser.getTrajets().toArray()[0]);
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
		//String pwd1 = RealmBase.Digest(request.getParameter(PARAM_NAME_PWD1),"SHA", pwd1);
		//String pwd1 = request.getParameter(PARAM_NAME_PWD1);
		//String pwd2 = request.getParameter(PARAM_NAME_PWD2);
		String nom = request.getParameter(PARAM_NAME_NAME);
		String adresse = request.getParameter(PARAM_NAME_ADDRESS); 
		String latitude = request.getParameter(PARAM_NAME_LONG);
		String longitude = request.getParameter(PARAM_NAME_LAT);
		String typeusager = request.getParameter(PARAM_TYPE_USAGER);
	}

}
