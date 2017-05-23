package carcool.com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import carcool.com.dao.MaDao;

/**
 * Servlet implementation class FetchDrivers
 */
@WebServlet("/fetchdrivers")
public class FetchDrivers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchDrivers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("application/json");
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");

		//JsonObject jsonObject = new JsonObject();
		//JsonArray jsonAuthorsArray = jsonObject.get(MaDao.getUserDao().getTableauJSConducteurs()).getAsJsonArray();

		response.getWriter().write(MaDao.getUserDao().getTableauJSONConducteurs());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
