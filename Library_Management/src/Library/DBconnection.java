package Library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBconnection {

public static Connection connect() {
	Connection conn=null;
try {
	String url="jdbc:mysql://localhost:3306/library_management";
	String username="root";
	String password="*****";
	conn=DriverManager.getConnection(url, username, password);
	//System.out.println("DB connected successfully");
	
}catch(Exception e) {
	System.out.println(e.getMessage());
}
return conn;
}
}