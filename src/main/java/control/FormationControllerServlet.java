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
public class FormationControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FormationDao fd;
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
			fd = new FormationDao(dataSource);
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
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listFormations(request, response);
				break;
				
			case "ADD":
				addFormation(request, response);
				break;
			case "LOAD":
				loadFormation(request, response);
				break;
				
			case "UPDATE":
				updateFormation(request, response);
				break;
			
			case "DELETE":
				deleteFormation(request, response);
				break;
				
			default:
				listFormations(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}
	private void deleteFormation(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student id from form data
			int fid = Integer.parseInt(request.getParameter("fid"));
			
			// delete student from database
			fd.SupprimerFormation(fid);;
			
			// send them back to "list students" page
			listFormations(request, response);
		}


		private void loadFormation(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read student id from form data
			int fid = Integer.parseInt(request.getParameter("fid"));
			
			// get student from database (db util)
			Formation formation = fd.SelectionnerFormation(fid);
			
			// place student in the request attribute
			request.setAttribute("FORMATION", formation);
			
			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/updateformation.jsp");
			dispatcher.forward(request, response);		
		}

		private void addFormation(HttpServletRequest request, HttpServletResponse response) throws Exception {

			// read student info from form data
			String theme = request.getParameter("theme");
			Formation f = new Formation(theme);
			if (request.getParameter("idlieu")=="") {
				fd.InsererFormation(f);
			}
			else {
				int idlieu = Integer.parseInt(request.getParameter("idlieu"));
				
				// create a new student object
				
				// add the student to the database
				fd.AjouterFormation(f, idlieu);
			}
			
					
			// send back to main page (the student list)
			listFormations(request, response);
		}

		private void listFormations(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// get students from db util
			List<Formation> formations = fd.getFormations();
			
			// add students to the request
			request.setAttribute("FORMATION_LIST", formations);
					
			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("listeformation.jsp");//-with-scriptlets.jsp");
			dispatcher.forward(request, response);
		}
		private void updateFormation(HttpServletRequest request, HttpServletResponse response)
				throws Exception {
				// read student info from form data
				int id = Integer.parseInt(request.getParameter("fid"));
				String theme = request.getParameter("theme");
				int idlieu=Integer.parseInt(request.getParameter("idlieu"));
				
				// create a new student object
				Formation formation = new Formation(id,theme,idlieu);
				
				// perform update on database
				fd.updateFormation(formation);
				
				// send them back to the "list students" page
				listFormations(request, response);
				
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
