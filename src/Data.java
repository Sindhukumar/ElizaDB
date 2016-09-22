import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Data {
	
	public Set<String> hedgeSet = new HashSet<String>();
	
	public Set<String> salutationSet = new HashSet<String>();

	public Set<String> qualifierSet = new HashSet<String>();

	public Map<String, String> replacementMap = new HashMap<String, String>();

	public static Connection con = null;
	public static Statement stmt = null;
	public static ResultSet rs = null;
	
	public Data(){
		generateSets();
		generateMap();
	}
	
	
	public static ResultSet queryDB(String sql) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// con = DriverManager.getConnection("jdbc:oracle:thin:sys as
			// sysdba/oracle@localhost:1521:orcl");
			con = DriverManager.getConnection("jdbc:oracle:thin:ora1/ora1@localhost:1521:orcl");
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return rs;

	}
	
	public void generateSets()  {
		String sql = "Select message,messagetype from Eliza";
		try {
			queryDB(sql);
			while (rs.next()) {
				switch (rs.getString(2).toLowerCase()){
				case "hedge":
					hedgeSet.add(rs.getString(1));
					break;
				case "salutation":
					salutationSet.add(rs.getString(1));
					break;
				case "qualifier":
					qualifierSet.add(rs.getString(1));
					break;
				}
				qualifierSet.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void generateMap()  {
		String sql = "Select firstperson,secondperson from replacements";
		try {
			queryDB(sql);
			while (rs.next()) {
				replacementMap.put(rs.getString(1), rs.getString(2));
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void finalize() {
		try {
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Set<String> getHedgeSet() {
		return hedgeSet;
	}

	public void setHedgeSet(Set<String> hedgeSet) {
		this.hedgeSet = hedgeSet;
	}

	public Set<String> getSalutationSet() {
		return salutationSet;
	}

	public void setSalutationSet(Set<String> salutationSet) {
		this.salutationSet = salutationSet;
	}

	public Set<String> getQualifierSet() {
		return qualifierSet;
	}

	public void setQualifierSet(Set<String> qualifierSet) {
		this.qualifierSet = qualifierSet;
	}

	public Map<String, String> getReplacementMap() {
		return replacementMap;
	}

	public void setReplacementMap(Map<String, String> replacementMap) {
		this.replacementMap = replacementMap;
	}
}
