package Client;
import model.*;
import Dao.*;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.sql.DataSource;

/**
 * Servlet implementation class Daotest
 */
public class Daotest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private FormationDao fd;
    private FormateurDao fdd;
    private LieuDao ld;
    /**
     * @see HttpServlet#HttpServlet()
     */
    @Resource(name="jdbc/tp2")
	private DataSource datasource;
    /**
     * @see HttpServlet#HttpServlet()
     */

	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			fd = new FormationDao(datasource);
			fdd = new FormateurDao(datasource);
			ld= new LieuDao(datasource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String theme="ba";
		String adresse="aaaaaa";
		String ville="Casa";
		String cin="BK710064";
		String nom="Rezki";
		int age=20;
		Formateur a= new Formateur(cin,nom,age);
		Lieu l= new Lieu(adresse,ville);
		Formation f= new Formation(theme);
		try {
			//fd.InsererFormation(f);
			//fdd.InsererFormateur(a);
			ld.InsererLieu(l);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
