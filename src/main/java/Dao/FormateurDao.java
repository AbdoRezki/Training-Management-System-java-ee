package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import model.*;
public class FormateurDao {
	private DataSource dataSource;

	public FormateurDao(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	public void InsererFormateur(Formateur f) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO formateur(cin,nom,age) VALUES(?,?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, f.getCin());
			myStmt.setString(2, f.getNom());
			myStmt.setInt(3, f.getAge());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			close(myConn,myStmt,null);
		}
	}
	public void updateFormateur(Formateur formateur) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			// get db connection
			myConn = dataSource.getConnection();
			String sql = "update formateur "
						+ "set nom=?, age=? where cin=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, formateur.getNom());
			myStmt.setInt(2, formateur.getAge());
			myStmt.setString(3, formateur.getCin());
			
			// execute SQL statement
			myStmt.execute();
			// create SQL update statement
			
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	public void SupprimerFormateur(String cin) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "delete from formateur where cin=?";
			
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, cin);
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			close(myConn,myStmt,null);
		}
	}
	public Formateur SelectionnerFormateur(String cin) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Formateur f= null;
		try {
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from formateur where cin=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, cin);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String nom = myRs.getString("nom");
				int age = myRs.getInt("age");
				
				// use the studentId during construction
				f = new Formateur(cin,nom,age);
			}
			else {
				throw new Exception("Could not find formateur cin: " + cin);
			}				
			
			return f;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
public List<Formateur> getFormateurs() throws Exception {
		
		List<Formateur> formateurs = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from formateur order by CIN";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				String cin = myRs.getString("CIN");
				String nom = myRs.getString("nom");
				int age= myRs.getInt("age");
				// create new student object
				Formateur tempFormateur = new Formateur(cin,nom,age);
				
				// add it to the list of students
				formateurs.add(tempFormateur);				
			}
			
			return formateurs;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
