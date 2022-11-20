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
public class FormationFormateurControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FormationFormateurDao ffd;
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
			ffd = new FormationFormateurDao(dataSource);
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
			String theCommand = request.getParameter("com");
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listFormationFormateur(request, response);
				break;
				
			case "ADD":
				addFormationFormateur(request, response);
				break;
			case "LOAD":
				loadFormationFormateur(request, response);
				break;
				
			case "UPDATE":
				updateFormationFormateur(request, response);
				break;
			
			case "DELETE":
				deleteFormationFormateur(request, response);
				break;
				
			default:
				listFormationFormateur(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}
	private void deleteFormationFormateur(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student id from form data
			int id = Integer.parseInt(request.getParameter("id"));
			
			// delete student from database
			ffd.SupprimerFormationFormateur(id);
			
			// send them back to "list students" page
			listFormationFormateur(request, response);
		}

		private void updateFormationFormateur(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student info from form data
			int id = Integer.parseInt(request.getParameter("id"));
			int idF =Integer.parseInt(request.getParameter("idF"));
			String cin = request.getParameter("cin");
			
			// create a new student object
			FormationFormateur ff = new FormationFormateur(id, idF, cin);
			
			// perform update on database
			ffd.updateFormationFormateur(ff);
			
			// send them back to the "list students" page
			listFormationFormateur(request, response);
			
		}

		private void loadFormationFormateur(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read student id from form data
			int id = Integer.parseInt(request.getParameter("id"));
			
			// get student from database (db util)
			FormationFormateur ff= ffd.SelectionnerFormationFormateur(id);
			
			// place student in the request attribute
			request.setAttribute("FORMATIONFORMATEUR", ff);
			
			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/updateformationformateur.jsp");
			dispatcher.forward(request, response);		
		}

		private void addFormationFormateur(HttpServletRequest request, HttpServletResponse response) throws Exception {

			// read student info from form data
			int idF = Integer.parseInt(request.getParameter("idF"));
			String cin = request.getParameter("cin");
			
			// create a new student object
			FormationFormateur ff = new FormationFormateur(idF,cin);
			
			// add the student to the database
			ffd.InsererFormationFormateur(ff);
					
			// send back to main page (the student list)
			listFormationFormateur(request, response);
		}

		private void listFormationFormateur(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// get students from db util
			List<FormationFormateur> ff = ffd.getFormationFormateurs();
			
			// add students to the request
			request.setAttribute("FORMATIONFORMATEUR_LIST", ff);
					
			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("listeformationformateur.jsp");//-with-scriptlets.jsp");
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
