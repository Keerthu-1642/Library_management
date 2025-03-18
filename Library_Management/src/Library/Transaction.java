package Library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.sql.Date;

public class Transaction {
	public void issuebook(int userid,int bookid) {
	try {
		Connection con=DBconnection.connect();
	String checkbookquery="select(isavailable) from books where bookid=? ";
	String issuebook="insert into transactions(BookID,UserID,IssueDate,returndate) values(?,?,?,?)";
	String updatequery="update books set isavailable =false where bookid=?";
	PreparedStatement st=con.prepareStatement(checkbookquery);
	st.setInt(1, bookid);
	ResultSet check=st.executeQuery();
	while(check.next()) {
	Boolean isavailable1=check.getBoolean("isavailable");	
	if(isavailable1) {
		PreparedStatement issue=con.prepareStatement(issuebook);
		issue.setInt(1, bookid);
		issue.setInt(2, userid);
		Date issuedDate = new Date(System.currentTimeMillis());
		issue.setDate(3, issuedDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(issuedDate);
		calendar.add(Calendar.DATE, 15);
		Date returnDate = new Date(calendar.getTimeInMillis());
		issue.setDate(4, returnDate);
		int row=issue.executeUpdate();
		if(row>0) {
			System.out.println("Transaction recorded successfully.");
		}else {
			System.out.println("Failed to record the transaction.");
		}
	}
	}
		PreparedStatement update=con.prepareStatement(updatequery);
		update.setInt(1, bookid);
		int row=update.executeUpdate();
		if(row>0) {
			System.out.println("Book issued successfully!");
		}else {
			System.out.println("Failed to update book availability.");
		}
		
}catch(Exception e) {
	System.out.println(e.getMessage());
}
}
	public void returnBook(int userid,int bookid) {
		try {
			Connection con=DBconnection.connect();
			String update="update books set isavailable=true where bookid=?";
			String delete="delete from transactions where bookid=? and userid=?";
			PreparedStatement st=con.prepareStatement(update);
			PreparedStatement st1=con.prepareStatement(delete);
			st.setInt(1, bookid);
			st1.setInt(1, bookid);
			st1.setInt(2, userid);
			int row=st.executeUpdate();
			if(row>0) {
				System.out.println("Book update successfully!");
			}else {
				System.out.println("Failed to update books");
			}
			int row2=st1.executeUpdate();
			if(row>0) {
				System.out.println("transaction delete successfully!");
			}else {
				System.out.println("Failed to delete transaction");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void viewIssuedBookByUser(int userid) {
		try {
			Connection con =DBconnection.connect();
			String issuebook="select bookid from transactions where userid=?";
			PreparedStatement st=con.prepareStatement(issuebook);
			st.setInt(1, userid);
			ResultSet set=st.executeQuery();
			while(set.next()) {
				int bookId = set.getInt("bookId");
				System.out.println("bookid ="+bookId);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void viewTransactionHistory(int bookid) {
		try {
			Connection con =DBconnection.connect();
			String issuebook="select transactionid, issuedate,returndate from transactions where bookid= ?";
			PreparedStatement st=con.prepareStatement(issuebook);
			st.setInt(1, bookid);
			ResultSet set=st.executeQuery();
			while(set.next()) {
				int id=set.getInt("transactionId");
				String issueDate = set.getString("issuedate");
	            String returnDate = set.getString("returndate");

	        System.out.println("Transaction ID: " + id+", Issue Date: " + issueDate + 
	          ", Return Date: " +returnDate );
	            }
	        } catch (SQLException e) {
	           System.out.println(e.getMessage());
	        }
			}
	public void calculateLateFee(int bookid,int userid) {
		float latefee=0.0f;
		int latefee_per_day=10;
		try {
			
			Connection con =DBconnection.connect();
			String query="select returndate from transactions where userid=? and bookid=?";
			PreparedStatement st=con.prepareStatement(query);
			st.setInt(1, userid);
			st.setInt(2, bookid);
			ResultSet set=st.executeQuery();
			while(set.next()) {
				Date dueDate = set.getDate("returndate");
	            LocalDate currentDate=LocalDate.now();
	            LocalDate dueLocalDate = dueDate.toLocalDate();
	            if(currentDate.isAfter(dueLocalDate)) {
	            Long latedays=ChronoUnit.DAYS.between(currentDate, dueLocalDate);
	            latefee=latedays*latefee_per_day;
	            System.out.println(currentDate);
	            System.out.println(dueLocalDate);
	            System.out.println(latedays);
	            System.out.println("latefee ="+latefee);
	            }else {
	            	System.out.println("No Latefee for the userId="+userid);
	            }
			}
	            String updatequery="update transactions set latefee =? where userid=?";
	            PreparedStatement sts=con.prepareStatement(updatequery);
	            sts.setFloat(1, latefee);
	            sts.setInt(2, userid);
	            int row=sts.executeUpdate();
	            if(row>0) {
	            	System.out.println("latefee update successfully for userid"+userid);
	            }
	            else {
	            	System.out.println("failed to update latefee");
	            }
	            
			}catch(Exception e) {
				System.out.println(e.getMessage());
		}
	}
	public void viewTransactionDetails() {
		try {
			Connection con =DBconnection.connect();
			String query="select * from Transactions";
			PreparedStatement st=con.prepareStatement(query);
			
			System.out.println("TransactionId | BookId| UserId |IssueDate | ReturnDate|LateFee  ");
	        System.out.println("----------------------------------------------------------------");
	        ResultSet rs=st.executeQuery();
	        while(rs.next()) {
				int transactionId = rs.getInt("transactionId");
				int BookId = rs.getInt("bookId");
				int UserId  = rs.getInt("userId");
	            Date IssueDate  = rs.getDate("issueDate");
	            Date ReturnDate  = rs.getDate("returnDate");
	            double latefee=rs.getDouble("latefee");
	            System.out.printf("%-14d| %-6d|%-7d | %td/%<tm/%<ty |   %td/%<tm/%<ty| %f%n",transactionId,BookId,UserId,IssueDate,ReturnDate,latefee);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}