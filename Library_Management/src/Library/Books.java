package Library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Books {
	public void addbook(String title,String author,String genre,boolean isavailable) {
		try {
			Connection con=DBconnection.connect();
			String sqlquries="Insert into books (title,author,genre,isavailable) values(?,?,?,?)";
			PreparedStatement st=con.prepareStatement(sqlquries);
			st.setString(1, title);
			st.setString(2,author);
			st.setString(3, genre);
			st.setBoolean(4, isavailable);
			int row=st.executeUpdate();
			if(row>0) {
			System.out.println("book added successfully");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void viewbooks() {
		
		try{
			Connection con=DBconnection.connect();
		String sqlQuries="select * from books";
		PreparedStatement st=con.prepareStatement(sqlQuries);
		ResultSet rs=st.executeQuery();
		System.out.println("Book ID | Title               | Author           | Genre      | Availability");
        System.out.println("--------------------------------------------------------------------------");
		while(rs.next()) {
			int bookId = rs.getInt("bookId");
            String title = rs.getString("title");
            String author = rs.getString("author");
            String genre = rs.getString("genre");
            boolean isAvailable = rs.getBoolean("isAvailable");
            System.out.printf("%-8d| %-20s| %-15s  | %-10s | %s%n",bookId, title, author, genre, isAvailable ? "Available" : "Issued");
		}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void updatebook(int bookid,String title,String author,String genre,boolean isavailable){
		try {
			Connection con=DBconnection.connect();
			String query="update books set title=?, author=?,genre=?,isavailable=? where bookid=?";
			PreparedStatement st=con.prepareStatement(query);
			st.setString(1, title);
			st.setString(2, author);
			st.setString(3, genre);
			st.setBoolean(4, isavailable);
			st.setInt(5, bookid);
			int rowaffected =st.executeUpdate();
			if(rowaffected>0) {
				System.out.println("book updated successfully");
			}
			else {
				System.out.println("No book find with given id");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void deleteBook(int bookid) {
		try {
			Connection con=DBconnection.connect();
			String query="delete from books where bookid=?";
			PreparedStatement st=con.prepareStatement(query);
			st.setInt(1,bookid);
			int row=st.executeUpdate();
			if(row>0) {
				System.out.println("book deleted successfully");
			}
			else {
				System.out.println("No book find with given id");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		}
	public void searchbook(String keyword) {
		try {
			Connection con=DBconnection.connect();
			String query="SELECT * FROM books WHERE title=? ";
			PreparedStatement st=con.prepareStatement(query);
			st.setString(1,keyword);
			ResultSet rs=st.executeQuery();
			System.out.println("search book result:");
			boolean found=false;
			System.out.println("Book ID | Title               | Author           | Genre      | Availability");
		    System.out.println("--------------------------------------------------------------------------");
		    while(rs.next()) {
		    	int bookId = rs.getInt("bookId");
		        String title = rs.getString("title");
		        String author = rs.getString("author");
		        String genre = rs.getString("genre");
		        boolean isAvailable = rs.getBoolean("isAvailable");
		        System.out.printf("%-8d| %-20s| %-15s  | %-10s | %s%n",bookId, title, author, genre, isAvailable ? "Available" : "Issued");
			    found=true;
			}
			if(!found) {
				System.out.println("No books found matching the title: " + keyword);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	}

