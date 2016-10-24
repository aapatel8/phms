package phms.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
//import phms.model.*;

public class PHMSDao {
	static final String jdbcURL 
	= "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	static final String DBuser = "vchawla3";
	static final String DBpassword = "200006054";
	
	public PHMSDao(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Throwable oops){
			oops.printStackTrace();
		}
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
	}
	
	public boolean addDiseaseForPatient(long ssn, String dis){
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = openConnection();
			String SQL = "INSERT INTO PatientDisease VALUES(?,?)";
			stmt = conn.prepareStatement(SQL);
			stmt.setLong(1, ssn);
			stmt.setString(2, dis);
			stmt.executeUpdate(SQL);
			return true;
		} catch(SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		} finally {
			close(stmt);
            close(conn);
		}
	}
	
	public ArrayList<String> getPatientsDiseases(long ssn){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<String> diseases = new ArrayList<String>();
		try{
			conn = openConnection();
			String SQL = "SELECT * FROM PatientDisease WHERE Pd_Patient = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setLong(1, ssn);
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				diseases.add(rs.getString("Pd_DiseaseName"));
			}
			return diseases;
		} catch(SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		} finally {
			close(stmt);
            close(conn);
		}
	}
	
	public ArrayList<String> getAllDiseases(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> diseases = new ArrayList<String>();
		try{
			conn = openConnection();
			stmt = conn.createStatement();
			String SQL = "SELECT * FROM Disease";
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				diseases.add(rs.getString("Dis_DiseaseName"));
			}
			return diseases;
		} catch(SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		} finally {
			close(stmt);
            close(conn);
		}
	}
	
	public Patient patientLogin(long user, String pass) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Patient p = null;
		try{
			conn = openConnection();
			
			String SQL = "SELECT * FROM PERSON P,PATIENT P2 "
					+ "WHERE P.Per_Id = ? "
					+ "AND P2.Pat_Person = ? "
					+ "AND P.Per_Password = ?";
					
			stmt = conn.prepareStatement(SQL);
			stmt.setLong(1, user);
			stmt.setLong(2, user);
			stmt.setString(3, pass);
			rs = stmt.executeQuery(SQL);
			p = new Patient(rs);	
			return p;
		} catch(SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		} finally {
			close(stmt);
            close(conn);
		}
	}
	
	public HealthSupporter healthSupporterLogin(long user, String pass) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		HealthSupporter h = null;
		try{
			conn = openConnection();
			
			String SQL = "SELECT * FROM PERSON P, Health_Supporter H "
					+ "WHERE P.Per_Id = ? "
					+ "AND H.HS_Supporter = ? "
					+ "AND P.Per_Password = ?";
					
			stmt = conn.prepareStatement(SQL);
			stmt.setLong(1, user);
			stmt.setLong(2, user);
			stmt.setString(3, pass);
			rs = stmt.executeQuery(SQL);
			h = new HealthSupporter(rs);	
			return h;
		} catch(SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		} finally {
			close(stmt);
            close(conn);
		}
	}
	
	public boolean editPatient(Patient p){
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			conn = openConnection();
			//Update the person table
			String SQL = "UPDATE PERSON SET "
					+ "Per_FirstName = ?,"
					+ "Per_LastName = ?,"
					+ "Per_DateOfBirth = ?," 
					+ "Per_Address = ?,"
					+ "Per_Phone = ?,"
					+ "Per_Sex = ?,"
					+ "Per_Password = ?"
					+ "WHERE Per_Id = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, p.getFname());
			stmt.setString(2, p.getLname());
			stmt.setDate(3, p.getDOB());
			stmt.setString(4, p.getAddress());
			stmt.setString(5, p.getPhoneNum());
			stmt.setString(6, p.getSex());
			stmt.setString(6, p.getPassword());
			stmt.setLong(7, p.getSsn());
			stmt.executeUpdate(SQL);
			close(stmt);
			
			//update the patient table
			SQL =  "UPDATE PATIENT SET "
				+ "Pat_Sick = ?"
				+ "WHERE Pat_Person = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, p.isSick());
			stmt.setLong(2, p.getSsn());
			stmt.executeUpdate(SQL);	
			return true;
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		} finally {
			close(stmt);
            close(conn);
		}
	}
	
	public boolean addNewPatient(Patient p){
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			conn = openConnection();
			String SQL = "INSERT INTO PERSON "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(SQL);
			stmt.setLong(1, p.getSsn());
			stmt.setString(2, p.getFname());
			stmt.setString(3, p.getLname());
			stmt.setDate(4, p.getDOB());
			stmt.setString(5, p.getAddress());
			stmt.setString(6,  p.getPhoneNum());
			stmt.setString(7,  p.getSex());
			stmt.setString(8,  p.getPassword());
			stmt.executeUpdate();
			close(stmt);
			SQL = "INSERT INTO PATIENT VALUES (?, ?)";
			stmt = conn.prepareStatement(SQL);
			stmt.setFloat(1,  p.getSsn());
			stmt.setFloat(2, p.isSick());
			stmt.executeUpdate(SQL);	
			return true;
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		} finally {
			close(stmt);
            close(conn);
		}
		
		
	}
	
	public ArrayList<String> getAllPatientSSNs(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> ssns = new ArrayList<String>();
		try{
			conn = openConnection();
			stmt = conn.createStatement();
			String SQL = "SELECT Pat_Person FROM PERSON";
			rs = stmt.executeQuery(SQL);
			while (rs.next()) {
			    ssns.add(rs.getString("Pat_Person"));
			}
			return ssns;
		} catch(SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		} finally {
			close(stmt);
            close(conn);
		}
	}
	
	public boolean addNewHS(HealthSupporter p){
		Connection conn = null;
		Statement stmt = null;
		try{
			conn = openConnection();
			stmt = conn.createStatement();
			String SQL = "INSERT INTO PERSON "
					+ "VALUES ("
					+ p.getSsn() + ","
					+ p.getFname() + ","
					+ p.getLname() + ","
					+ p.getDOB() + ","
					+ p.getAddress() + ","
					+ p.getPhoneNum() + ","
					+ p.getSex() + ","
					+ p.getPassword() + ""
					+ ")";
			stmt.executeUpdate(SQL);
			SQL =  "INSERT INTO Health_Supporter "
				+ "VALUES ("
				+ p.getSsn() + ","
				+ p.getSupportingPatientID() + ","
				+ p.getDateAuthorized() + ","
				+ p.getDateUnauthorized()
				+ ")";
			stmt.executeUpdate(SQL);	
			return true;
		} catch(SQLException e){
			e.printStackTrace();
			return false;
		} finally {
			close(stmt);
            close(conn);
		}
	}
	
	
	private Connection openConnection() throws SQLException{
		try {
			Connection conn = DriverManager.getConnection(jdbcURL, DBuser, DBpassword);
			//Connection conn = new Connection();
			
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw e;
		}
	}
	

	
	private void close(Connection conn) {
        if(conn != null) {
            try { conn.close(); } catch(Throwable whatever) {}
        }
    }

    private void close(Statement st) {
        if(st != null) {
            try { st.close(); } catch(Throwable whatever) {}
        }
    }

    private void close(ResultSet rs) {
        if(rs != null) {
            try { rs.close(); } catch(Throwable whatever) {}
        }
    }

  //just testing the DB connection to create/insert/select data, no real use
  	public boolean test(){
  		Connection conn = null;
  		Statement stmt = null;
  		ResultSet rs = null;
  		
  		try{
  			conn = openConnection();
  			stmt = conn.createStatement();
  			//stmt.executeUpdate("create table TEST1(val1 integer)");
  			//stmt.executeUpdate("insert into TEST values(3)");
  			//stmt.executeUpdate("insert into TEST values(4)");
  			//rs = stmt.executeQuery("SELECT * FROM TEST");
  			while (rs.next()) {
  			    int s = rs.getInt("VAL1");
  			    System.out.println(s);
  			}
  			return true;
  		} catch(SQLException e){
  			e.printStackTrace();
  			return false;
  		} finally {
  			close(stmt);
              close(conn);
  		}
  	}
}