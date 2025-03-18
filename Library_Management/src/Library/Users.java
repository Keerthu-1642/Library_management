package Library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Users {
public void adduser(String username,String role,String contactinfo) {
	if(role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("member")) {
	
	try {
		Connection con=DBconnection.connect();
		String sqlqueries="Insert into users(username,role,contactinfo) values(?,?,?)";
		PreparedStatement st=con.prepareStatement(sqlqueries);
		st.setString(1, username);
		st.setString(2,role);
		st.setString(3, contactinfo);
		int row=st.executeUpdate();
		if(row>0) {
			System.out.println("user added successfully");
			String sqlquery="select userid from users where username=?";
			PreparedStatement sts=con.prepareStatement(sqlquery);
			sts.setString(1, username);
			ResultSet set=sts.executeQuery();
			while(set.next()) {
			int userid=set.getInt("userid");
			System.out.println("your userid ="+userid );
			}
		}else {
			System.out.println("error to add user try again!.");
		}
}catch(Exception e) {
	System.out.println(e.getMessage());
}
}
	else {
		System.out.print("Invalid role. Role must be either 'Admin' or 'Member'.\n try again!");
	}
}
public void viewuser(int userid) {
	try {
		Connection con=DBconnection.connect();
		String sqlqueries="select username,role,contactinfo from users where userid=?";
		PreparedStatement st=con.prepareStatement(sqlqueries);
		st.setInt(1, userid);
		ResultSet set=st.executeQuery();
		System.out.println("userid  | username            | Role             | contackinfo");
        System.out.println("--------------------------------------------------------------");
		while(set.next()) {
			String username=set.getString("username");
			String role=set.getString("role");
			String contactinfo=set.getString("contactinfo");
			
			 System.out.printf("%-8d| %-20s| %-15s  | %-10s",userid, username, role, contactinfo);
		}
		
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
}

public void deleteuser(int userid) {
	try {
		Connection con=DBconnection.connect();
		String sqlqueries="delete from users where userid=?";
		PreparedStatement st=con.prepareStatement(sqlqueries);
		st.setInt(1, userid);
		int row=st.executeUpdate();
		if(row>0) {
			System.out.println("user deleted successfully");
		}else {
			System.out.println("No user found with userId");
		}
}catch(Exception e) {
	System.out.println(e.getMessage());
}
}
public void listallusers() {
	try {
		Connection con=DBconnection.connect();
		String sqlqueries="select * from users";
		PreparedStatement st=con.prepareStatement(sqlqueries);
		ResultSet set=st.executeQuery();
		System.out.println("userid  | username            | Role             | contackinfo");
        System.out.println("--------------------------------------------------------------");
		while(set.next()) {
			int userid=set.getInt("userid");
			String username=set.getString("username");
			String role=set.getString("role");
			String contactinfo=set.getString("contactinfo");
			System.out.printf("%-8d| %-20s| %-15s  | %-10s%n",userid, username, role, contactinfo);
		}
		
}catch(Exception e) {
	System.out.println(e.getMessage());
}
}
}