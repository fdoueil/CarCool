package carcool.com.model;

import java.util.List;

public class Trajet {

	private int idConducteur;
	private String depuisAdresse;
	private double latDepart;
	private double longDepart;
	private String versAdresse;
	private double latArrivee;
	private double longArrivee;
	
	public double getLatDepart() {
		return latDepart;
	}

	public void setLatDepart(double latDepart) {
		this.latDepart = latDepart;
	}

	public double getLongDepart() {
		return longDepart;
	}

	public void setLongDepart(double longDepart) {
		this.longDepart = longDepart;
	}

	public double getLatArrivee() {
		return latArrivee;
	}

	public void setLatArrivee(double latArrivee) {
		this.latArrivee = latArrivee;
	}

	public double getLongArrivee() {
		return longArrivee;
	}

	public void setLongArrivee(double longArrivee) {
		this.longArrivee = longArrivee;
	}

	private List<String> etapes;
	private int sieges;

	private List<String> jours;
	private String plageHoraireAller;
	private String plageHoraireRetour;

	public String getDepuisAdresse() {
		return depuisAdresse;
	}

	/**
	 * Constructeur.
	 */
	public Trajet(int idConducteur, String depuisAdresse, String versAdresse, int sieges, List<String> jours,
			String plageHoraireAller, String plageHoraireRetour) {
		this(idConducteur, depuisAdresse, versAdresse, null, sieges, jours, plageHoraireAller, plageHoraireRetour);
	}

	public Trajet(int idConducteur, String depuisAdresse, String versAdresse, List<String> etapes, int sieges,
			List<String> jours, String plageHoraireAller, String plageHoraireRetour) {
		this.idConducteur = idConducteur;
		this.depuisAdresse = depuisAdresse;
		this.etapes = etapes;
		this.sieges = sieges;
		this.jours = jours;
		this.plageHoraireAller = plageHoraireAller;
		this.plageHoraireRetour = plageHoraireRetour;
	}

	@Override
	public String toString() {
		return "Trajet [idConducteur=" + idConducteur + ", depuisAdresse=" + depuisAdresse + ", versAdresse="
				+ versAdresse + ", etapes=" + etapes + ", sieges=" + sieges + ", jours=" + jours
				+ ", plageHoraireAller=" + plageHoraireAller + ", plageHoraireRetour=" + plageHoraireRetour + "]";
	}

}
