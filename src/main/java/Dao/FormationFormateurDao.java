package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import model.*;
public class FormationFormateurDao {
	private DataSource dataSource;

	public FormationFormateurDao(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	public void InsererFormationFormateur(FormationFormateur f) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO formationformateur(idF,cin) VALUES(?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, f.getIdF());
			myStmt.setString(2, f.getCin());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			close(myConn,myStmt,null);
		}
	}
	public void updateFormationFormateur(FormationFormateur formationformateur) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			// get db connection
			myConn = dataSource.getConnection();
			String sql = "update formationformateur "
						+ "set idF=?, cin=? where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, formationformateur.getIdF());
			myStmt.setString(2, formationformateur.getCin());
			myStmt.setInt(3, formationformateur.getId());
			
			// execute SQL statement
			myStmt.execute();
			// create SQL update statement
			
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	public void SupprimerFormationFormateur(int id) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "delete from formationformateur where id=?";
			
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, id);
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			close(myConn,myStmt,null);
		}
	}
	public FormationFormateur SelectionnerFormationFormateur(int id) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		FormationFormateur f= null;
		try {
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from formationformateur where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, id);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				int idF = myRs.getInt("idF");
				String cin = myRs.getString("cin");
				
				// use the studentId during construction
				f = new FormationFormateur(id,idF,cin);
			}
			else {
				throw new Exception("Could not find formationformateur id: " + id);
			}				
			
			return f;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
public List<FormationFormateur> getFormationFormateurs() throws Exception {
		
		List<FormationFormateur> ff = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from formationformateur";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				int idF = myRs.getInt("idF");
				String cin= myRs.getString("cin");
				// create new student object
				FormationFormateur tempFormationFormateur = new FormationFormateur(id,idF,cin);
				
				// add it to the list of students
				ff.add(tempFormationFormateur);				
			}
			
			return ff;		
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
