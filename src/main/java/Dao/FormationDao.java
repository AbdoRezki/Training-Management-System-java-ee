package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import model.*;
public class FormationDao {
	private DataSource dataSource;

	public FormationDao(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	public void InsererFormation(Formation f) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO formation(theme) VALUES(?)";
			
			myStmt = myConn.prepareStatement(sql);
			//myStmt.setInt(1, f.getId());
			myStmt.setString(1, f.getTheme());
			//myStmt.setInt(3, f.getIdLieu());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			close(myConn,myStmt,null);
		}
	}
	public void SupprimerFormation(int id) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "delete from formation where id=?";
			
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, id);
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			close(myConn,myStmt,null);
		}
	}
	public void updateFormation(Formation formation) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			// get db connection
			myConn = dataSource.getConnection();
			if (formation.getIdlieu()==0) {
				String sql = "update formation "
						+ "set theme=?"
						+ "where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, formation.getTheme());
			myStmt.setInt(2, formation.getId());
			
			// execute SQL statement
			myStmt.execute();
			}
			else {
				String sql = "update formation "
						+ "set theme=?, idlieu=? where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, formation.getTheme());
			myStmt.setInt(2, formation.getIdlieu());
			myStmt.setInt(3, formation.getId());
			
			// execute SQL statement
			myStmt.execute();
			}
			// create SQL update statement
			
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	public Formation SelectionnerFormation(int id) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Formation f= null;
		try {
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from formation where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, id);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String theme = myRs.getString("theme");
				int idlieu=myRs.getInt("idlieu");
				// use the studentId during construction
				f = new Formation(id,theme,idlieu);
			}
			else {
				throw new Exception("Could not find formation id: " + id);
			}				
			
			return f;
		}
		finally {
			// clean up JDBC objects
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
	public void AjouterFormation(Formation f, int idlieu) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO formation(theme,idlieu) VALUES(?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			//myStmt.setInt(1, f.getId());
			myStmt.setString(1, f.getTheme());
			myStmt.setInt(2, idlieu);
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			close(myConn,myStmt,null);
		}
	}
	public List<Formation> getFormations() throws Exception {
		
		List<Formation> formations = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from formation order by id";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String theme = myRs.getString("theme");
				int idlieu= myRs.getInt("idlieu");
				// create new student object
				Formation tempFormation = new Formation(id, theme,idlieu);
				
				// add it to the list of students
				formations.add(tempFormation);				
			}
			
			return formations;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}
}
