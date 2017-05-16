package carcool.com.dao;

import java.util.HashSet;
import java.util.Iterator;

import carcool.com.enums.Categorie;
import carcool.com.model.Trajet;
import carcool.com.model.Utilisateur;

public class UserDao {
	private HashSet<Utilisateur> utilisateurs;

	public UserDao() {
		super();
		utilisateurs = new HashSet<Utilisateur>();
	}

	public HashSet<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}

	public void setUsers(HashSet<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}

	public boolean removeUser(String email) {
		boolean ret=false;
		
		Iterator<Utilisateur> iterator = utilisateurs.iterator();
		while (iterator.hasNext()) {
			Utilisateur element = iterator.next();
			if (element.getEmail().equals(email)) {
				iterator.remove();
				ret=true;
				break;
			}
		}
		
		return ret;
	}
	
	public Utilisateur getUserById(int id) {
		Utilisateur ret=null;
		
		Iterator<Utilisateur> iterator = utilisateurs.iterator();
		while (iterator.hasNext()) {
			Utilisateur element = iterator.next();
			if (element.getIdUtilisateur()==id) {
				ret=element;
				break;
			}
		}
		
		return ret;
	}
	
	public boolean existUser(String email, String password) {
		boolean ret=false;
		
		Iterator<Utilisateur> iterator = utilisateurs.iterator();
		while (iterator.hasNext()) {
			Utilisateur element = iterator.next();
			if (element.getEmail().equals(email) && element.getPassword().equals(password)) {
				ret=true;
				break;
			}
		}
		
		return ret;
	}
	
	// Permet de retourner dans le Javascript un tableau construit dynamiquement
	/* [['Cugnaux', 43.537373, 1.344962, 'François Hollande'],
	    ['Balma', 43.606163, 1.500060, 'Jérome Cahuzac'],
	    ['Roques', 43.506803, 1.351713, 'Manuel Vals']];*/
	public String getTableauJSConducteurs() {
		StringBuilder ret= new StringBuilder();
		
		ret.append("[");
		
		Iterator<Utilisateur> iterator = utilisateurs.iterator();
		while (iterator.hasNext()) {
			Utilisateur element = iterator.next();
			if (element.getCategorie().equals(Categorie.C)) {
				ret.append("['" + ((Trajet)element.getTrajets().toArray()[0]).getDepuisAdresse());
				ret.append("', " + ((Trajet)element.getTrajets().toArray()[0]).getLatDepart());
				ret.append(", " + ((Trajet)element.getTrajets().toArray()[0]).getLongDepart());
				ret.append(", '" + element.getNom() + "'],");
			}
		}
		
		return (ret.substring(0, ret.length()-1) + "]");
	}
	
	// Permet de retourner dans le Javascript un tableau construit dynamiquement
	/*[['Quint-Fonsegrives', 43.585884, 1.544735, 'Christine Lagarde'],
      ['Escalquens', 43.518855, 1.553071, 'Lolo Aibo']]*/
	public String getTableauJSPassagers() {
		StringBuilder ret= new StringBuilder();
		
		ret.append("[");
		
		Iterator<Utilisateur> iterator = utilisateurs.iterator();
		while (iterator.hasNext()) {
			Utilisateur element = iterator.next();
			if (element.getCategorie().equals(Categorie.P)) {
				ret.append("['" + ((Trajet)element.getTrajets().toArray()[0]).getDepuisAdresse());
				ret.append("', " + ((Trajet)element.getTrajets().toArray()[0]).getLatDepart());
				ret.append(", " + ((Trajet)element.getTrajets().toArray()[0]).getLongDepart());
				ret.append(", '" + element.getNom() + "'],");
			}
		}
		
		return (ret.substring(0, ret.length()-1) + "]");
	}
}
