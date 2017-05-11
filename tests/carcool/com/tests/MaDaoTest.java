package carcool.com.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import carcool.com.dao.MaDao;
import carcool.com.model.Utilisateur;

public class MaDaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetUserDao() {
		assertEquals(0, MaDao.getUserDao().getUtilisateurs().size());
	}

	@Test
	public void testGetUserCrDao() {
		MaDao.getUserDao().getUtilisateurs().add(new Utilisateur(1,"f@f.com","Thai$1ee","Thai$1ee","FDoueil"));
		assertEquals(1, MaDao.getUserDao().getUtilisateurs().size());
	}

}
