package carcool.com.dao;

public class MaDao {
	private static MaDao _instance = null;
	private static UserDao userDao;

	private MaDao() {
		//userDao = new UserDao();
	}

	public static MaDao getInstance() {
		if (_instance == null) {
			_instance = new MaDao();
		}
		return _instance;
	}

	public static UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}
}
