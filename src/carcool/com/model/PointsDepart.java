package carcool.com.model;

import java.util.ArrayList;
import java.util.List;

public class PointsDepart {

	// USER STORY 2 - on affiche les points de d�parts sur la carte
	private int idUtilisateur;
	private String nom;
	private String pointDepart;
	
	public PointsDepart(int idUtilisateur, String nom, String pointDepart) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.pointDepart = pointDepart;
	}

	public String getNom() {
		return nom;
	}

	public String getPointDepart() {
		return pointDepart;
	}
	
}
