package control;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.*;
import javax.sql.DataSource;

import Dao.*;

/**
 * Servlet implementation class FormationControllerServlet
 */
public class LieuControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LieuDao ld;
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Resource(name="jdbc/tp2")
	private DataSource dataSource;
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			ld = new LieuDao(dataSource);
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
		try {
			// read the "command" parameter
			String theCommand = request.getParameter("comm");
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listLieux(request, response);
				break;
				
			case "ADD":
				addLieu(request, response);
				break;
			case "LOAD":
				loadLieu(request, response);
				break;
				
			case "UPDATE":
				updateLieu(request, response);
				break;
			
			case "DELETE":
				deleteLieu(request, response);
				break;
				
			default:
				listLieux(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}
	private void deleteLieu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student id from form data
			int lid = Integer.parseInt(request.getParameter("lid"));
			
			// delete student from database
			ld.SupprimerLieu(lid);
			
			// send them back to "list students" page
			listLieux(request, response);
		}

		private void updateLieu(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student info from form data
			int id = Integer.parseInt(request.getParameter("lid"));
			String adresse = request.getParameter("adresse");
			String ville = request.getParameter("ville");
			
			// create a new student object
			Lieu l = new Lieu(id, adresse, ville);
			
			// perform update on database
			ld.updateLieu(l);
			
			// send them back to the "list students" page
			listLieux(request, response);
			
		}

		private void loadLieu(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read student id from form data
			int id = Integer.parseInt(request.getParameter("lid"));
			
			// get student from database (db util)
			Lieu lieu= ld.SelectionnerLieu(id);
			
			// place student in the request attribute
			request.setAttribute("LIEU", lieu);
			
			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/updatelieu.jsp");
			dispatcher.forward(request, response);		
		}

		private void addLieu(HttpServletRequest request, HttpServletResponse response) throws Exception {

			// read student info from form data
			String adresse = request.getParameter("adresse");
			String ville = request.getParameter("ville");
			
			// create a new student object
			Lieu l = new Lieu(adresse,ville);
			
			// add the student to the database
			ld.InsererLieu(l);
					
			// send back to main page (the student list)
			listLieux(request, response);
		}

		private void listLieux(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// get students from db util
			List<Lieu> lieux = ld.getLieux();
			
			// add students to the request
			request.setAttribute("LIEU_LIST", lieux);
					
			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("listelieu.jsp");//-with-scriptlets.jsp");
			dispatcher.forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
