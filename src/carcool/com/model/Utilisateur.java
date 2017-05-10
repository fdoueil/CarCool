package carcool.com.model;

import carcool.com.interfaces.IUserRegisterValidator;

public class Utilisateur implements IUserRegisterValidator{

	private int idUtilisateur;
	private String email;
	private String nom;
	private String password1;
	private String password2;
	private static final String VALIDATE_OK = null;
	
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
		if (!this.password1.equals(this.password2)) {
			return "Les mots de passes saisis ne sont pas identiques !";
		}

		if (!this.password1.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}")) {
			return "Le mot de passe doit : contenir au moins 1 chiffre, une lettre minuscule, une lettre majuscule, avoir une longueur d'au moins 8 caractères, pas d'espaces et un caractère spécial parmi @#$%^&+=";
		}
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
