package carcool.com.tests;

import static org.junit.Assert.*;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import carcool.com.dao.MaDao;
import carcool.com.enums.Categorie;
import carcool.com.model.Point;
import carcool.com.model.PointsDepart;
import carcool.com.model.PointsDepartUtilisateur;
import carcool.com.model.Route;
import carcool.com.model.Trajet;
import carcool.com.model.Utilisateur;
import carcool.com.utils.DistanceBetweenTwoPoints;

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
		assertEquals("[[\"Cugnaux\",43.537373,1.344962,\"François Hollande\"],[\"Balma\",43.606163,1.50006,\"Jérome Cahuzac\"],[\"Roques\",43.506803,1.351713,\"Manuel Vals\"]]",
				MaDao.getUserDao().getTableauJSConducteurs());
		
		//System.out.println(MaDao.getUserDao().getTableauJSONConducteurs());
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
		assertEquals("[[\"Escalquens\",43.518855,1.553071,\"Lolo Aibo\"],[\"Quint-Fonsegrives\",43.585884,1.544735,\"Christine Lagarde\"]]", 
				MaDao.getUserDao().getTableauJSPassagers());
	}
	
	@Test
	public void testDistance() {
		
		System.out.println(DistanceBetweenTwoPoints.getDistance(
				43.585884, 1.544735, 43.518855, 1.553071, 'K') + " Kms\n");
		
	}
	
	@Test
	public void testChemin() {
		Route route = new Route();
		
		String chemin="[{\"lat\":43.58594,\"lng\":1.5447700000000002},{\"lat\":43.58496,\"lng\":1.5473000000000001},{\"lat\":43.584360000000004,\"lng\":1.5488700000000002},{\"lat\":43.58429,\"lng\":1.54912},{\"lat\":43.58415,\"lng\":1.54976},{\"lat\":43.58381000000001,\"lng\":1.552},{\"lat\":43.58372000000001,\"lng\":1.55268},{\"lat\":43.5837,\"lng\":1.5530000000000002},{\"lat\":43.583690000000004,\"lng\":1.5532400000000002},{\"lat\":43.58372000000001,\"lng\":1.55384},{\"lat\":43.583760000000005,\"lng\":1.5544000000000002},{\"lat\":43.58332,\"lng\":1.55427},{\"lat\":43.58315,\"lng\":1.5541900000000002},{\"lat\":43.58306,\"lng\":1.55411},{\"lat\":43.5829,\"lng\":1.55386},{\"lat\":43.58261,\"lng\":1.55332},{\"lat\":43.58243,\"lng\":1.5529100000000002},{\"lat\":43.582060000000006,\"lng\":1.5524600000000002},{\"lat\":43.581720000000004,\"lng\":1.5521500000000001},{\"lat\":43.58147,\"lng\":1.5519900000000002},{\"lat\":43.58137000000001,\"lng\":1.55197},{\"lat\":43.581230000000005,\"lng\":1.5520100000000001},{\"lat\":43.58099000000001,\"lng\":1.5522300000000002},{\"lat\":43.580850000000005,\"lng\":1.5524000000000002},{\"lat\":43.58072000000001,\"lng\":1.5526200000000001},{\"lat\":43.58061,\"lng\":1.55285},{\"lat\":43.580560000000006,\"lng\":1.5529400000000002},{\"lat\":43.58046,\"lng\":1.55309},{\"lat\":43.58025000000001,\"lng\":1.55326},{\"lat\":43.5801,\"lng\":1.5532800000000002},{\"lat\":43.580020000000005,\"lng\":1.55329},{\"lat\":43.57994,\"lng\":1.5532700000000002},{\"lat\":43.579800000000006,\"lng\":1.5531800000000002},{\"lat\":43.57956,\"lng\":1.55289},{\"lat\":43.57931000000001,\"lng\":1.55248},{\"lat\":43.579150000000006,\"lng\":1.5522900000000002},{\"lat\":43.5788,\"lng\":1.55207},{\"lat\":43.57864,\"lng\":1.5519200000000002},{\"lat\":43.578450000000004,\"lng\":1.5516800000000002},{\"lat\":43.57802,\"lng\":1.55099},{\"lat\":43.57795,\"lng\":1.55088},{\"lat\":43.57779,\"lng\":1.5507300000000002},{\"lat\":43.5775,\"lng\":1.5506000000000002},{\"lat\":43.57703,\"lng\":1.5503500000000001},{\"lat\":43.576890000000006,\"lng\":1.5502500000000001},{\"lat\":43.576890000000006,\"lng\":1.55024},{\"lat\":43.576890000000006,\"lng\":1.5502200000000002},{\"lat\":43.57688,\"lng\":1.5501900000000002},{\"lat\":43.576840000000004,\"lng\":1.55014},{\"lat\":43.57679,\"lng\":1.5501300000000002},{\"lat\":43.57677,\"lng\":1.55014},{\"lat\":43.57676,\"lng\":1.5501600000000002},{\"lat\":43.57665,\"lng\":1.5500900000000002},{\"lat\":43.576480000000004,\"lng\":1.5499900000000002},{\"lat\":43.57593000000001,\"lng\":1.5495200000000002},{\"lat\":43.57565,\"lng\":1.54926},{\"lat\":43.57453,\"lng\":1.54837},{\"lat\":43.57437,\"lng\":1.5483300000000002},{\"lat\":43.57303,\"lng\":1.5483500000000001},{\"lat\":43.572680000000005,\"lng\":1.5483200000000001},{\"lat\":43.57255000000001,\"lng\":1.5482500000000001},{\"lat\":43.572230000000005,\"lng\":1.54797},{\"lat\":43.57195,\"lng\":1.54773},{\"lat\":43.571310000000004,\"lng\":1.54723},{\"lat\":43.570980000000006,\"lng\":1.5470000000000002},{\"lat\":43.56992,\"lng\":1.54624},{\"lat\":43.5698,\"lng\":1.5461},{\"lat\":43.56971,\"lng\":1.5459800000000001},{\"lat\":43.569660000000006,\"lng\":1.5458200000000002},{\"lat\":43.56964000000001,\"lng\":1.54556},{\"lat\":43.57,\"lng\":1.5438800000000001},{\"lat\":43.57036,\"lng\":1.5422200000000001},{\"lat\":43.570550000000004,\"lng\":1.5413000000000001},{\"lat\":43.570530000000005,\"lng\":1.5411100000000002},{\"lat\":43.570460000000004,\"lng\":1.5409000000000002},{\"lat\":43.57039,\"lng\":1.5407700000000002},{\"lat\":43.57034,\"lng\":1.5407300000000002},{\"lat\":43.56889,\"lng\":1.5399100000000001},{\"lat\":43.56873,\"lng\":1.5398100000000001},{\"lat\":43.568250000000006,\"lng\":1.5395200000000002},{\"lat\":43.56768,\"lng\":1.53912},{\"lat\":43.567240000000005,\"lng\":1.5389300000000001},{\"lat\":43.566990000000004,\"lng\":1.53888},{\"lat\":43.566900000000004,\"lng\":1.5379600000000002},{\"lat\":43.56683,\"lng\":1.53628},{\"lat\":43.56685,\"lng\":1.53594},{\"lat\":43.566900000000004,\"lng\":1.5356500000000002},{\"lat\":43.56696,\"lng\":1.53513},{\"lat\":43.566970000000005,\"lng\":1.5347300000000001},{\"lat\":43.56703,\"lng\":1.5339600000000002},{\"lat\":43.567,\"lng\":1.5336900000000002},{\"lat\":43.56685,\"lng\":1.53319},{\"lat\":43.566810000000004,\"lng\":1.5328400000000002},{\"lat\":43.5668,\"lng\":1.53261},{\"lat\":43.566720000000004,\"lng\":1.5325900000000001},{\"lat\":43.565650000000005,\"lng\":1.5319900000000002},{\"lat\":43.56486,\"lng\":1.5315200000000002},{\"lat\":43.564130000000006,\"lng\":1.5310100000000002},{\"lat\":43.56356,\"lng\":1.5305900000000001},{\"lat\":43.56338,\"lng\":1.53048},{\"lat\":43.56327,\"lng\":1.5304200000000001},{\"lat\":43.56295,\"lng\":1.53034},{\"lat\":43.56271,\"lng\":1.53028},{\"lat\":43.56212,\"lng\":1.5299500000000001},{\"lat\":43.56159,\"lng\":1.52963},{\"lat\":43.5611,\"lng\":1.5294},{\"lat\":43.56089,\"lng\":1.52929},{\"lat\":43.560810000000004,\"lng\":1.5291800000000002},{\"lat\":43.56062,\"lng\":1.5289400000000002},{\"lat\":43.56056,\"lng\":1.5291400000000002},{\"lat\":43.56054,\"lng\":1.52923},{\"lat\":43.559990000000006,\"lng\":1.52882},{\"lat\":43.559720000000006,\"lng\":1.52858},{\"lat\":43.55953,\"lng\":1.52835},{\"lat\":43.55944,\"lng\":1.52824},{\"lat\":43.55913,\"lng\":1.5279900000000002},{\"lat\":43.55877,\"lng\":1.5277600000000002},{\"lat\":43.558620000000005,\"lng\":1.5277100000000001},{\"lat\":43.55796,\"lng\":1.5277100000000001},{\"lat\":43.557900000000004,\"lng\":1.52783},{\"lat\":43.55781,\"lng\":1.5279500000000001},{\"lat\":43.55738,\"lng\":1.5286300000000002},{\"lat\":43.55713,\"lng\":1.5282300000000002},{\"lat\":43.55698,\"lng\":1.52804},{\"lat\":43.5566,\"lng\":1.5275400000000001},{\"lat\":43.556270000000005,\"lng\":1.5271400000000002},{\"lat\":43.555670000000006,\"lng\":1.52648},{\"lat\":43.554950000000005,\"lng\":1.52577},{\"lat\":43.55447,\"lng\":1.52539},{\"lat\":43.5538,\"lng\":1.5249000000000001},{\"lat\":43.55362,\"lng\":1.5247300000000001},{\"lat\":43.553140000000006,\"lng\":1.5243300000000002},{\"lat\":43.55263,\"lng\":1.52343},{\"lat\":43.55245,\"lng\":1.5231100000000002},{\"lat\":43.552310000000006,\"lng\":1.5229300000000001},{\"lat\":43.552080000000004,\"lng\":1.5227300000000001},{\"lat\":43.551590000000004,\"lng\":1.5223700000000002},{\"lat\":43.551120000000004,\"lng\":1.5219900000000002},{\"lat\":43.55102,\"lng\":1.52187},{\"lat\":43.55096,\"lng\":1.52174},{\"lat\":43.550940000000004,\"lng\":1.52153},{\"lat\":43.55102,\"lng\":1.51937},{\"lat\":43.55109,\"lng\":1.51753},{\"lat\":43.55113,\"lng\":1.51739},{\"lat\":43.55145,\"lng\":1.5166400000000002},{\"lat\":43.551460000000006,\"lng\":1.5164900000000001},{\"lat\":43.55142,\"lng\":1.5163900000000001},{\"lat\":43.551230000000004,\"lng\":1.5160500000000001},{\"lat\":43.55046,\"lng\":1.51472},{\"lat\":43.55042,\"lng\":1.5146700000000002},{\"lat\":43.550380000000004,\"lng\":1.5146600000000001},{\"lat\":43.55028,\"lng\":1.5147100000000002},{\"lat\":43.550050000000006,\"lng\":1.51509},{\"lat\":43.549850000000006,\"lng\":1.5153},{\"lat\":43.54979,\"lng\":1.5153400000000001},{\"lat\":43.54974000000001,\"lng\":1.5153400000000001},{\"lat\":43.5497,\"lng\":1.5153100000000002},{\"lat\":43.549670000000006,\"lng\":1.51526},{\"lat\":43.54961,\"lng\":1.51506},{\"lat\":43.54954,\"lng\":1.5148000000000001},{\"lat\":43.549440000000004,\"lng\":1.5146400000000002},{\"lat\":43.549310000000006,\"lng\":1.51442},{\"lat\":43.549240000000005,\"lng\":1.51432},{\"lat\":43.549170000000004,\"lng\":1.51428},{\"lat\":43.549080000000004,\"lng\":1.51428},{\"lat\":43.54900000000001,\"lng\":1.5142900000000001},{\"lat\":43.54891000000001,\"lng\":1.51431},{\"lat\":43.54881,\"lng\":1.51431},{\"lat\":43.54871000000001,\"lng\":1.51424},{\"lat\":43.548700000000004,\"lng\":1.5142000000000002},{\"lat\":43.548680000000004,\"lng\":1.5141200000000001},{\"lat\":43.54863,\"lng\":1.5140300000000002},{\"lat\":43.54854,\"lng\":1.51397},{\"lat\":43.54845,\"lng\":1.5139500000000001},{\"lat\":43.548370000000006,\"lng\":1.51398},{\"lat\":43.54834,\"lng\":1.51401},{\"lat\":43.54827,\"lng\":1.51397},{\"lat\":43.54816,\"lng\":1.51387},{\"lat\":43.54796,\"lng\":1.5136100000000001},{\"lat\":43.547900000000006,\"lng\":1.51346},{\"lat\":43.54787,\"lng\":1.5132400000000001},{\"lat\":43.547850000000004,\"lng\":1.5131100000000002},{\"lat\":43.547850000000004,\"lng\":1.5129000000000001},{\"lat\":43.547830000000005,\"lng\":1.5127400000000002},{\"lat\":43.54722,\"lng\":1.51156},{\"lat\":43.54704,\"lng\":1.51126},{\"lat\":43.54652,\"lng\":1.5107000000000002},{\"lat\":43.546400000000006,\"lng\":1.51051},{\"lat\":43.54636000000001,\"lng\":1.51041},{\"lat\":43.546310000000005,\"lng\":1.51015},{\"lat\":43.546310000000005,\"lng\":1.5101300000000002},{\"lat\":43.54632,\"lng\":1.5100900000000002},{\"lat\":43.546310000000005,\"lng\":1.51001},{\"lat\":43.546260000000004,\"lng\":1.50997},{\"lat\":43.54621,\"lng\":1.5099600000000002},{\"lat\":43.54618000000001,\"lng\":1.50998},{\"lat\":43.54608,\"lng\":1.5100000000000002},{\"lat\":43.54600000000001,\"lng\":1.51001},{\"lat\":43.545860000000005,\"lng\":1.5100600000000002},{\"lat\":43.544830000000005,\"lng\":1.5108400000000002},{\"lat\":43.54444,\"lng\":1.5111},{\"lat\":43.54431,\"lng\":1.5112},{\"lat\":43.543960000000006,\"lng\":1.5115100000000001},{\"lat\":43.543290000000006,\"lng\":1.51214},{\"lat\":43.543220000000005,\"lng\":1.51224},{\"lat\":43.54317,\"lng\":1.5123600000000001},{\"lat\":43.543150000000004,\"lng\":1.51235},{\"lat\":43.5431,\"lng\":1.51234},{\"lat\":43.543060000000004,\"lng\":1.5123600000000001},{\"lat\":43.543020000000006,\"lng\":1.51242},{\"lat\":43.54198,\"lng\":1.5115800000000001},{\"lat\":43.541990000000006,\"lng\":1.5115200000000002},{\"lat\":43.541970000000006,\"lng\":1.5114400000000001},{\"lat\":43.541940000000004,\"lng\":1.5114},{\"lat\":43.541900000000005,\"lng\":1.5113800000000002},{\"lat\":43.541850000000004,\"lng\":1.5113800000000002},{\"lat\":43.5418,\"lng\":1.5114400000000001},{\"lat\":43.54178,\"lng\":1.5114900000000002},{\"lat\":43.541790000000006,\"lng\":1.51157},{\"lat\":43.54182,\"lng\":1.5116200000000002},{\"lat\":43.541830000000004,\"lng\":1.51163},{\"lat\":43.54159000000001,\"lng\":1.5121900000000001}]";
		
		Gson gson = new Gson();
		Type type = new TypeToken<HashSet<Point>>(){}.getType();
		route.setChemin(gson.fromJson(chemin, type));
		
		for(Point point : route.getChemin()) {
			System.out.println("Lat:" + point.getLatitude() + " / long:" + point.getLongitude());
		}
	}
}
