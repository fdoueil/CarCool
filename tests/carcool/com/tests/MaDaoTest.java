package carcool.com.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import carcool.com.dao.MaDao;
import carcool.com.enums.Categorie;
import carcool.com.model.PointsDepart;
import carcool.com.model.PointsDepartUtilisateur;
import carcool.com.model.Trajet;
import carcool.com.model.Utilisateur;

public class MaDaoTest {

	private static final String VALIDATE_OK = null;

	@Before
	public void setUp() throws Exception {
		// purge Dao
		MaDao.getUserDao().setUsers(new HashSet<Utilisateur>());
	}

	@Test
	public void testEmptyDao() {
		assertEquals(0, MaDao.getUserDao().getUtilisateurs().size());
	}

	@Test
	public void testCreateUtilisateurDao() {
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(1, "f@f.com", "FDoueil", "Thai$1ee", "Thai$1ee"));
		assertEquals(1, MaDao.getUserDao().getUtilisateurs().size());
	}

	@Test
	public void testValiderUtilisateurDao() {
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(1, "f@f.com", "FDoueil", "Zzz&eeZZ", "Zzz&eeZZ"));
		assertEquals(1, MaDao.getUserDao().getUtilisateurs().size());

		// HERE SOME VALIDATE TESTS
		Utilisateur f = MaDao.getUserDao().getUserById(1);
		assertEquals(VALIDATE_OK, f.validateEmail());
		assertEquals(VALIDATE_OK, f.validatePwd());
	}

	@Test
	public void testCreateUtilisateursPlusTrajetsDao() {

		String[] jours = new String[] { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi" };
		List listJours = Arrays.asList(jours);

		// User 1 - f@f.com
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(1, "f@f.com", "FDoueil", "Thai$1ee", "Thai$1ee"));
		assertEquals(1, MaDao.getUserDao().getUtilisateurs().size());

		Utilisateur f = MaDao.getUserDao().getUserById(1);
		HashSet<Trajet> trajetsF = new HashSet<Trajet>();

		trajetsF.add(new Trajet(1, "Saint-Pierre de Lages, 4 chemin du Caoulet", "Labège, Rue Edmond Rostand", 4,
				listJours, "8h", "17h15"));
		trajetsF.add(new Trajet(1, "Saint-Pierre de Lages, 4 chemin du Caoulet", "Labège, Rue Edmond Rostand", 4,
				listJours, "8h", "17h15"));

		f.setTrajets(trajetsF);
		assertEquals(2, MaDao.getUserDao().getUserById(1).getTrajets().size());

		// User 2 - g@g.com
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(2, "g@g.com", "BDemblans", "Zzzz£1ee", "Zzzz£1ee"));
		// 2 Utilisateurs
		assertEquals(2, MaDao.getUserDao().getUtilisateurs().size());

		Utilisateur g = MaDao.getUserDao().getUserById(2);
		HashSet<Trajet> trajetsG = new HashSet<Trajet>();

		trajetsG.add(new Trajet(2, "Saint-Pierre de Lages, 4 chemin du Caoulet", "Labège, Rue Edmond Rostand", 4,
				listJours, "8h", "17h15"));
		trajetsG.add(new Trajet(2, "Saint-Pierre de Lages, 4 chemin du Caoulet", "Labège, Rue Edmond Rostand", 4,
				listJours, "8h", "17h15"));

		g.setTrajets(trajetsG);
		assertEquals(2, MaDao.getUserDao().getUserById(2).getTrajets().size());

	}

	@Test
	public void testCreateUtilisateurPlusTrajetsPlusEtapesDao() {
		String[] jours = new String[] { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi" };
		List listJours = Arrays.asList(jours);

		String[] etapes = new String[] { "Aigrefeuille", "Quint Fonsegrives", "Saint-Orens" };
		List listEtapes = Arrays.asList(etapes);

		// User 1 - f@f.com
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(1, "f@f.com", "FDoueil", "Thai$1ee", "Thai$1ee"));
		assertEquals(1, MaDao.getUserDao().getUtilisateurs().size());

		Utilisateur f = MaDao.getUserDao().getUserById(1);
		HashSet<Trajet> trajetsF = new HashSet<Trajet>();

		trajetsF.add(new Trajet(1, "Saint-Pierre de Lages, 4 chemin du Caoulet", "Labège, Rue Edmond Rostand",
				listEtapes, 4, listJours, "8h", "17h15"));
		trajetsF.add(new Trajet(1, "Saint-Pierre de Lages, 4 chemin du Caoulet", "Labège, Rue Edmond Rostand",
				listEtapes, 4, listJours, "8h", "17h15"));

		f.setTrajets(trajetsF);
		assertEquals(2, MaDao.getUserDao().getUserById(1).getTrajets().size());
	}

	@Test
	public void testPointsDepartDao() {
		String[] jours = new String[] { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi" };
		List listJours = Arrays.asList(jours);

		String[] etapes = new String[] { "Aigrefeuille", "Quint Fonsegrives", "Saint-Orens" };
		List listEtapes = Arrays.asList(etapes);

		// User 1 - f@f.com
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(1, "f@f.com", "FDoueil", "Thai$1ee", "Thai$1ee"));

		Utilisateur f = MaDao.getUserDao().getUserById(1);
		HashSet<Trajet> trajetsF = new HashSet<Trajet>();

		trajetsF.add(new Trajet(1, "Saint-Pierre de Lages, 4 chemin du Caoulet", "Labège, Rue Edmond Rostand",
				listEtapes, 4, listJours, "8h", "17h15"));
		trajetsF.add(new Trajet(1, "Saint-Pierre de Lages, 6 chemin du Caoulet", "Labège, Rue Edmond Rostand",
				listEtapes, 4, listJours, "8h", "17h15"));
		f.setTrajets(trajetsF);

		// User 2 - g@g.com
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(2, "g@g.com", "BDemblans", "Zzzz£1ee", "Zzzz£1ee"));
		// 2 Utilisateurs
		Utilisateur g = MaDao.getUserDao().getUserById(2);
		HashSet<Trajet> trajetsG = new HashSet<Trajet>();

		trajetsG.add(new Trajet(2, "Saint-Pierre de Lages, 22 chemin du Caoulet", "Labège, Rue Edmond Rostand", 4,
				listJours, "8h", "17h15"));
		trajetsG.add(new Trajet(2, "Saint-Pierre de Lages, 24 chemin du Caoulet", "Labège, Rue Edmond Rostand", 4,
				listJours, "8h", "17h15"));
		g.setTrajets(trajetsG);
		
		// 2 points de départ utilisateur
		List<PointsDepartUtilisateur> pointsDepartU = MaDao.getPointsDepartUtilisateur();
		assertEquals(2, pointsDepartU.size());
		
		// 4 points de départ au total
		List<PointsDepart> pointsDepart = MaDao.getPointsDepart();
		assertEquals(4, pointsDepart.size());
	}
	
	@Test
	public void testExisteUtilisateurDao() {
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(1, "f@f.com", "FDoueil", "Zzz&eeZZ", "Zzz&eeZZ"));
		assertFalse(MaDao.getUserDao().existUser("g@g.com", "Mypassword"));
		assertTrue(MaDao.getUserDao().existUser("f@f.com", "Zzz&eeZZ"));
	}
	
	@Test
	public void testTableauJSConducteurs() {
		/*['Cugnaux', 43.537373, 1.344962, 'François Hollande'],
	    ['Balma', 43.606163, 1.500060, 'Jérome Cahuzac'],
	    ['Roques', 43.506803, 1.351713, 'Manuel Vals']
	  	];*/
	  	
		String[] jours = new String[] { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi" };
		List listJours = Arrays.asList(jours);
		
		// User 1 - François Hollande
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(1, "a@a.com", "François Hollande", "Thai$1ee", "Thai$1ee"));
		Utilisateur a = MaDao.getUserDao().getUserById(1);
		HashSet<Trajet> trajetsA = new HashSet<Trajet>();
		Trajet trajetA = new Trajet(1, "Cugnaux", "Labège, Rue Edmond Rostand", 4, listJours, "8h", "17h15");
		trajetA.setLatDepart(43.537373);
		trajetA.setLongDepart(1.344962);
		trajetsA.add(trajetA);
		a.setTrajets(trajetsA);
		
		// User 2 - Jérôme Cahuzac
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(2, "b@b.com", "Jérome Cahuzac", "Thai$1ee", "Thai$1ee"));
		Utilisateur b = MaDao.getUserDao().getUserById(2);
		HashSet<Trajet> trajetsB = new HashSet<Trajet>();
		Trajet trajetB = new Trajet(2, "Balma", "Labège, Rue Edmond Rostand", 4, listJours, "8h", "17h15");
		trajetB.setLatDepart(43.606163);
		trajetB.setLongDepart(1.500060);
		trajetsB.add(trajetB);
		b.setTrajets(trajetsB);
		
		// User 3 - Manuel Vals
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(3, "c@c.com", "Manuel Vals", "Thai$1ee", "Thai$1ee"));
		Utilisateur c = MaDao.getUserDao().getUserById(3);
		HashSet<Trajet> trajetsC = new HashSet<Trajet>();
		Trajet trajetC = new Trajet(3, "Roques", "Labège, Rue Edmond Rostand", 4, listJours, "8h", "17h15");
		trajetC.setLatDepart(43.506803);
		trajetC.setLongDepart(1.351713);
		trajetsC.add(trajetC);
		c.setTrajets(trajetsC);
		
		System.out.println(MaDao.getUserDao().getTableauJSConducteurs());
		assertEquals("[['Balma', 43.606163, 1.50006, 'Jérome Cahuzac'],['Roques', 43.506803, 1.351713, 'Manuel Vals'],['Cugnaux', 43.537373, 1.344962, 'François Hollande']]",
				MaDao.getUserDao().getTableauJSConducteurs());
	}
	
	@Test
	public void testTableauJSPassagers() {
		/*[['Quint-Fonsegrives', 43.585884, 1.544735, 'Christine Lagarde'],
	      ['Escalquens', 43.518855, 1.553071, 'Lolo Aibo']]*/
	  	
		String[] jours = new String[] { "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi" };
		List listJours = Arrays.asList(jours);
		
		// User 1 - Christine Lagarde
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(1, "d@d.com", "Christine Lagarde", "Thai$1ee", "Thai$1ee"));
		Utilisateur a = MaDao.getUserDao().getUserById(1);
		a.setCategorie(Categorie.P);
		HashSet<Trajet> trajetsA = new HashSet<Trajet>();
		Trajet trajetA = new Trajet(1, "Quint-Fonsegrives", "Labège, Rue Edmond Rostand", 4, listJours, "8h", "17h15");
		trajetA.setLatDepart(43.585884);
		trajetA.setLongDepart(1.544735);
		trajetsA.add(trajetA);
		a.setTrajets(trajetsA);
		
		// User 2 - Lolo Aibo
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(2, "e@e.com", "Lolo Aibo", "Thai$1ee", "Thai$1ee"));
		Utilisateur b = MaDao.getUserDao().getUserById(2);
		b.setCategorie(Categorie.P);
		HashSet<Trajet> trajetsB = new HashSet<Trajet>();
		Trajet trajetB = new Trajet(2, "Escalquens", "Labège, Rue Edmond Rostand", 4, listJours, "8h", "17h15");
		trajetB.setLatDepart(43.518855);
		trajetB.setLongDepart(1.553071);
		trajetsB.add(trajetB);
		b.setTrajets(trajetsB);
		
		System.out.println(MaDao.getUserDao().getTableauJSPassagers());
		assertEquals("[['Escalquens', 43.518855, 1.553071, 'Lolo Aibo'],['Quint-Fonsegrives', 43.585884, 1.544735, 'Christine Lagarde']]", 
				MaDao.getUserDao().getTableauJSPassagers());
	}
}
