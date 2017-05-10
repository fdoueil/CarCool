package carcool.com.model;

import java.util.List;

public class Trajet {

	private int idConducteur;
	private String depuisAdresse;
	private String versAdresse;
	private List<String> etapes;
	private int sieges;
	
	/**
     * Constructeur.
     */
	public Trajet(int idConducteur, String depuisAdresse, String versAdresse, int sieges) {
		this(idConducteur, depuisAdresse, versAdresse, null, sieges);
	}
	
	public Trajet(int idConducteur, String depuisAdresse, String versAdresse, List<String> etapes, int sieges) {
		this.idConducteur=idConducteur;
		this.depuisAdresse= depuisAdresse;
		this.etapes = etapes;
		this.sieges = sieges;
	}

	@Override
	public String toString() {
		return "Trajet [idConducteur=" + idConducteur + ", depuisAdresse=" + depuisAdresse + ", versAdresse="
				+ versAdresse + ", etapes=" + etapes + ", sieges=" + sieges + "]";
	}
	
}
