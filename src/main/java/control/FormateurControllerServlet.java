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
public class FormateurControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FormateurDao fd;
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
			fd = new FormateurDao(dataSource);
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
			String theCommand = request.getParameter("commande");
			
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listFormateurs(request, response);
				break;
				
			case "ADD":
				addFormateur(request, response);
				break;
			case "LOAD":
				loadFormateur(request, response);
				break;
				
			case "UPDATE":
				updateFormateur(request, response);
				break;
			
			case "DELETE":
				deleteFormateur(request, response);
				break;
				
			default:
				listFormateurs(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}
	private void deleteFormateur(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student id from form data
			String cin = request.getParameter("cin");
			
			// delete student from database
			fd.SupprimerFormateur(cin);
			
			// send them back to "list students" page
			listFormateurs(request, response);
		}

		private void updateFormateur(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// read student info from form data
			String cin = request.getParameter("cin");
			String nom = request.getParameter("nom");
			int age = Integer.parseInt(request.getParameter("age"));
			
			// create a new student object
			Formateur formateur = new Formateur(cin,nom,age);
			
			// perform update on database
			fd.updateFormateur(formateur);
			
			// send them back to the "list students" page
			listFormateurs(request, response);
			
		}

		private void loadFormateur(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// read student id from form data
			String cin = request.getParameter("cin");
			
			// get student from database (db util)
			Formateur f = fd.SelectionnerFormateur(cin);
			
			// place student in the request attribute
			request.setAttribute("FORMATEUR", f);
			
			// send to jsp page: update-student-form.jsp
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/updateformateur.jsp");
			dispatcher.forward(request, response);		
		}

		private void addFormateur(HttpServletRequest request, HttpServletResponse response) throws Exception {

			// read student info from form data
			String cin = request.getParameter("cin");	
			String nom = request.getParameter("nom");	
			int age = Integer.parseInt(request.getParameter("age"));	
			
			// create a new student object
			Formateur f = new Formateur(cin,nom,age);
			
			// add the student to the database
			fd.InsererFormateur(f);
					
			// send back to main page (the student list)
			listFormateurs(request, response);
		}

		private void listFormateurs(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

			// get students from db util
			List<Formateur> formateurs = fd.getFormateurs();
			
			// add students to the request
			request.setAttribute("FORMATEUR_LIST", formateurs);
					
			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("listeformateur.jsp");//-with-scriptlets.jsp");
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
