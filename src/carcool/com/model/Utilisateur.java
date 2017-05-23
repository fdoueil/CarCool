package carcool.com.model;

import java.util.HashSet;

import carcool.com.enums.Categorie;
import carcool.com.interfaces.IUserRegisterValidator;

public class Utilisateur implements IUserRegisterValidator{

	private int idUtilisateur;
	private String email;
	private String nom;
	private String password1;
	private String password2;
	private Categorie categorie;
	
	private static final String VALIDATE_OK = null;
	
	private HashSet<Trajet> trajets;
	
	public Trajet getFirstTrajet() {
		Trajet ret = null;
		
		if (trajets.size()>0) {
			ret= (Trajet) trajets.toArray()[0];
		}
		return ret;
	}
	
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public String getNom() {
		return nom;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password1;
	}

	public Utilisateur(int idUtilisateur, String email, String nom, String password1, String password2) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.email = email;
		this.nom = nom;
		this.password1 = password1;
		this.password2 = password2;
		// Par défaut conducteur
		this.categorie = Categorie.C;
	}

	public HashSet<Trajet> getTrajets() {
		return trajets;
	}

	public String getPassword2() {
		return password2;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setTrajets(HashSet<Trajet> trajets) {
		this.trajets = trajets;
	}

	@Override
	public String validateEmail() {
		String ret = VALIDATE_OK;
		if (this.email != null && this.email.trim().length() != 0) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				return "Veuillez saisir une adresse mail valide !";
			}
		} else {
			return "L'adresse mail est obligatoire !";
		}
		return ret;
	}
	
	@Override
	public String validatePwd() {
		String ret = VALIDATE_OK;
		/**if (!this.password1.equals(this.password2)) {
			return "Les mots de passes saisis ne sont pas identiques !";
		}*/

		/*if (!this.password1.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}")) {
			return "Le mot de passe doit : contenir au moins 1 chiffre, une lettre minuscule, une lettre majuscule, avoir une longueur d'au moins 8 caract�res, pas d'espaces et un caract�re sp�cial parmi @#$%^&+=";
		}*/
		return ret;
		// Password is complex ?
		/*
		 * (?=.*[0-9]) a digit must occur at least once (?=.*[a-z]) a lower case
		 * letter must occur at least once (?=.*[A-Z]) an upper case letter must
		 * occur at least once (?=.*[@#$%^&+=]) a special character must occur
		 * at least once (?=\\S+$) no whitespace allowed in the entire string
		 * .{8,} at least 8 characters
		 */
	}
	
	@Override
	public String validateName() {
		return VALIDATE_OK;
	}
	
}
