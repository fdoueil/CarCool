package carcool.com.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import carcool.com.enums.Categorie;
import carcool.com.model.PointsDepart;
import carcool.com.model.PointsDepartUtilisateur;
import carcool.com.model.Trajet;
import carcool.com.model.Utilisateur;

public class MaDao {
	private static MaDao _instance = null;
	private static UserDao userDao;
	
	private MaDao() {
		//userDao = new UserDao();
	}

	public static MaDao getInstance() {
		if (_instance == null) {
			_instance = new MaDao();
					
		}
		return _instance;
	}

	public static UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao();
			
			// FAKE DAO USERS
			String[] jours = new String[] { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi" };
			List listJours = Arrays.asList(jours);
			
			// User 1 - François Hollande
			userDao.getUtilisateurs().add(new Utilisateur(1, "a@a.com", "François Hollande", "Thai$1ee", "Thai$1ee"));
			Utilisateur a = userDao.getUserById(1);
			HashSet<Trajet> trajetsA = new HashSet<Trajet>();
			Trajet trajetA = new Trajet(1, "Cugnaux", "Labège, Rue Edmond Rostand", 4, listJours, "8h", "17h15");
			trajetA.setLatDepart(43.537373);
			trajetA.setLongDepart(1.344962);
			trajetsA.add(trajetA);
			a.setTrajets(trajetsA);
			
			// User 2 - Jérôme Cahuzac
			userDao.getUtilisateurs().add(new Utilisateur(2, "b@b.com", "Jérome Cahuzac", "Thai$1ee", "Thai$1ee"));
			Utilisateur b = userDao.getUserById(2);
			HashSet<Trajet> trajetsB = new HashSet<Trajet>();
			Trajet trajetB = new Trajet(2, "Balma", "Labège, Rue Edmond Rostand", 4, listJours, "8h", "17h15");
			trajetB.setLatDepart(43.606163);
			trajetB.setLongDepart(1.500060);
			trajetsB.add(trajetB);
			b.setTrajets(trajetsB);
			
			// User 3 - Manuel Vals
			userDao.getUtilisateurs().add(new Utilisateur(3, "c@c.com", "Manuel Vals", "Thai$1ee", "Thai$1ee"));
			Utilisateur c = userDao.getUserById(3);
			HashSet<Trajet> trajetsC = new HashSet<Trajet>();
			Trajet trajetC = new Trajet(3, "Roques", "Labège, Rue Edmond Rostand", 4, listJours, "8h", "17h15");
			trajetC.setLatDepart(43.506803);
			trajetC.setLongDepart(1.351713);
			trajetsC.add(trajetC);
			c.setTrajets(trajetsC);
			
			// User 4 - Christine Lagarde
			userDao.getUtilisateurs().add(new Utilisateur(4, "d@d.com", "Christine Lagarde", "Thai$1ee", "Thai$1ee"));
			Utilisateur d = userDao.getUserById(4);
			d.setCategorie(Categorie.P);
			HashSet<Trajet> trajetsD = new HashSet<Trajet>();
			Trajet trajetD = new Trajet(4, "Quint-Fonsegrives", "Labège, Rue Edmond Rostand", 4, listJours, "8h", "17h15");
			trajetD.setLatDepart(43.585884);
			trajetD.setLongDepart(1.544735);
			trajetsD.add(trajetD);
			d.setTrajets(trajetsD);
			
			// User 5 - Lolo Aibo
			userDao.getUtilisateurs().add(new Utilisateur(5, "e@e.com", "Lolo Aibo", "Thai$1ee", "Thai$1ee"));
			Utilisateur e = userDao.getUserById(5);
			e.setCategorie(Categorie.P);
			HashSet<Trajet> trajetsE = new HashSet<Trajet>();
			Trajet trajetE = new Trajet(5, "Escalquens", "Labège, Rue Edmond Rostand", 4, listJours, "8h", "17h15");
			trajetE.setLatDepart(43.518855);
			trajetE.setLongDepart(1.553071);
			trajetsE.add(trajetE);
			e.setTrajets(trajetsE);
		}
		return userDao;
	}
	
	public static List<PointsDepartUtilisateur> getPointsDepartUtilisateur() {
		List<PointsDepartUtilisateur> pointsDepartU = new ArrayList<PointsDepartUtilisateur>();
		
		for (Utilisateur utilisateur : userDao.getUtilisateurs()) {
			PointsDepartUtilisateur ptU = new PointsDepartUtilisateur(utilisateur.getIdUtilisateur(), utilisateur.getNom());
			pointsDepartU.add(ptU);
			for (Trajet trajet : utilisateur.getTrajets()) {
				ptU.getPointsDepart().add(trajet.getDepuisAdresse());
			}
		}
		
		return pointsDepartU;
	}
	
	public static List<PointsDepart> getPointsDepart() {
		List<PointsDepart> pointsDepart = new ArrayList<PointsDepart>();
		
		for (Utilisateur utilisateur : userDao.getUtilisateurs()) {
			for (Trajet trajet : utilisateur.getTrajets()) {
				pointsDepart.add(new PointsDepart(utilisateur.getIdUtilisateur(), utilisateur.getNom(), trajet.getDepuisAdresse()));
			}
		}
		
		return pointsDepart;
	}
}
