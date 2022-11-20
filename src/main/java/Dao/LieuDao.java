package Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.*;
public class LieuDao {
	private DataSource dataSource;

	public LieuDao(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	public void InsererLieu(Lieu l) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "INSERT INTO lieu(adresse,ville) VALUES(?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, l.getAdresse());
			myStmt.setString(2, l.getVille());
			//myStmt.setInt(3, f.getIdLieu());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			close(myConn,myStmt,null);
		}
	}
	public void SupprimerLieu(int id) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "delete from lieu where id=?";
			
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, id);
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			close(myConn,myStmt,null);
		}
	}
	public void updateLieu(Lieu lieu) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			// get db connection
			myConn = dataSource.getConnection();
			String sql = "update lieu "
						+ "set adresse=?, ville=? where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, lieu.getAdresse());
			myStmt.setString(2, lieu.getVille());
			myStmt.setInt(3, lieu.getId());
			
			// execute SQL statement
			myStmt.execute();
			// create SQL update statement
			
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	public Lieu SelectionnerLieu(int id) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Lieu l= null;
		try {
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "select * from lieu where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, id);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String adresse = myRs.getString("adresse");
				String ville = myRs.getString("ville");
				
				// use the studentId during construction
				l=new Lieu(id,adresse,ville);
			}
			else {
				throw new Exception("Could not find lieu id: " + id);
			}				
			
			return l;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}
	public List<Lieu> getLieux() throws Exception {
		
		List<Lieu> lieux = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from lieu order by id";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String adresse = myRs.getString("adresse");
				String ville= myRs.getString("ville");
				// create new student object
				Lieu tempLieu = new Lieu(id, adresse,ville);
				
				// add it to the list of students
				lieux.add(tempLieu);				
			}
			
			return lieux;		
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

