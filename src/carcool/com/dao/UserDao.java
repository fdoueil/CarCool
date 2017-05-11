package carcool.com.dao;

import java.util.HashSet;
import java.util.Iterator;
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
}
