package carcool.com.dao;

import java.util.ArrayList;
import java.util.List;

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
