package carcool.com.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import carcool.com.dao.MaDao;
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
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(1, "f@f.com", "Thai$1ee", "Thai$1ee", "FDoueil"));
		assertEquals(1, MaDao.getUserDao().getUtilisateurs().size());
	}

	@Test
	public void testValiderUtilisateurDao() {
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(1, "f@f.com", "Zzz&eeZZ", "Zzz&eeZZ", "FDoueil"));
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
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(1, "f@f.com", "Thai$1ee", "Thai$1ee", "FDoueil"));
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
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(2, "g@g.com", "Zzzz£1ee", "Zzzz£1ee", "BDemblans"));
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
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(1, "f@f.com", "Thai$1ee", "Thai$1ee", "FDoueil"));
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
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(1, "f@f.com", "Thai$1ee", "Thai$1ee", "FDoueil"));

		Utilisateur f = MaDao.getUserDao().getUserById(1);
		HashSet<Trajet> trajetsF = new HashSet<Trajet>();

		trajetsF.add(new Trajet(1, "Saint-Pierre de Lages, 4 chemin du Caoulet", "Labège, Rue Edmond Rostand",
				listEtapes, 4, listJours, "8h", "17h15"));
		trajetsF.add(new Trajet(1, "Saint-Pierre de Lages, 6 chemin du Caoulet", "Labège, Rue Edmond Rostand",
				listEtapes, 4, listJours, "8h", "17h15"));
		f.setTrajets(trajetsF);

		// User 2 - g@g.com
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(2, "g@g.com", "Zzzz£1ee", "Zzzz£1ee", "BDemblans"));
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
		List<String> pointsDepart = MaDao.getPointsDepart();
		assertEquals(4, pointsDepart.size());
	}
}
