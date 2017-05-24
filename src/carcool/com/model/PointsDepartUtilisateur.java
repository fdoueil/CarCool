package carcool.com.model;

import java.util.ArrayList;
import java.util.List;

public class PointsDepartUtilisateur {

	// USER STORY 2 - on affiche les points de d�parts sur la carte
	private int idUtilisateur;
	private String nom;
	private List<String> pointsDepart;
	
	public PointsDepartUtilisateur(int idUtilisateur, String nom) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.pointsDepart = new ArrayList<String>();
	}

	public List<String> getPointsDepart() {
		return pointsDepart;
	}

}
