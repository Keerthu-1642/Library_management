package Library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LibraryManager {

	public void bookdetails() {
		Books bk=new Books();
		Scanner scan=new Scanner(System.in);
        System.out.println("1.addBook");
        System.out.println("2.viewAllBooks");
        System.out.println("3.searchBook");
        System.out.println("4.deleteBook");
        System.out.println("5.updateBookDetails");
        int choice=scan.nextInt();
        switch(choice) {
        case 1:
        	System.out.println("enter book title:");
        	scan.nextLine();
        	String title=scan.nextLine();
        	System.out.println("enter author name:");
        	String author=scan.nextLine();
        	System.out.println("enter book genre:");
        	String genre=scan.nextLine();
        	boolean availability=true;
        	bk.addbook(title, author, genre, availability);
        	break;
        case 2:
        	bk.viewbooks();
        	break;
        case 3:
        	System.out.println("enter book title:");
        	scan.nextLine();
        	String title1=scan.nextLine();
        	bk.searchbook(title1);
        	break;
        case 4:
        	System.out.println("enter book id:");
        	int bookid=scan.nextInt();
        	bk.deleteBook(bookid);
        	break;
        case 5:
        	System.out.println("enter book id:");
        	int bookid2=scan.nextInt();
        	System.out.println("enter new book title:");
        	scan.nextLine();
        	String title2=scan.nextLine();
        	System.out.println("enter new author name:");
        	String author2=scan.nextLine();
        	System.out.println("enter new book genre:");
        	String genre2=scan.nextLine();
        	boolean availability2=true;
        	bk.updatebook(bookid2, title2, author2, genre2, availability2);
        	break;
        default:
    		System.out.println("Invalid choice. Try again.");
    		}
           scan.close();
        }

	public void userdetails() {
		Users user=new Users();
		Scanner scan=new Scanner(System.in);
        System.out.println("1.adduser");
        System.out.println("2.viewuser");
        System.out.println("3.deleteuser");
        System.out.println("4.Listalluser");
        int choice=scan.nextInt();
        switch(choice) {
        case 1:
        	System.out.println("enter your name:");
        	scan.nextLine();
        	String username=scan.nextLine();
        	System.out.println("enter your role either \"admin\" or \"member\" :");
        	String role=scan.nextLine();
        	System.out.println("enter your area");
        	String contactinfo=scan.nextLine();
        	user.adduser(username, role, contactinfo);
        	break;
        case 2:
        	System.out.println("enter your userid:");
        	int userid=scan.nextInt();
        	user.viewuser(userid);
        	break;
        case 3:
        	System.out.println("enter user id:");
        	int userid2=scan.nextInt();
        	user.deleteuser(userid2);
        	break;
        case 4:
        	user.listallusers();
        	break;
        default:
    		System.out.println("Invalid choice. Try again.");
    		}
           scan.close();
		
	}

	public void transactiondetails() {
		Transaction tran=new Transaction();
		Scanner scan=new Scanner(System.in);
        System.out.println("1.Issuebook");
        System.out.println("2.returnbook");
        System.out.println("3.viewIssuedBookByUser");
        System.out.println("4.viewTransactionHistory");
        System.out.println("5.calculateLateFee");
        System.out.println("6.view Transaction Details");
        int choice=scan.nextInt();
        switch(choice) {
        case 1:
        	System.out.println("enter your userid:");
        	int userid=scan.nextInt();
        	System.out.println("enter book id:");
        	int bookid=scan.nextInt();
        	tran.issuebook(userid, bookid);
        	break;
        case 2:
        	System.out.println("enter your userid:");
        	int userid2=scan.nextInt();
        	System.out.println("enter book id:");
        	int bookid2=scan.nextInt();
        	tran.returnBook(userid2, bookid2);
        	break;
        case 3:
        	System.out.println("enter your userid:");
        	int userid3=scan.nextInt();
        	tran.viewIssuedBookByUser(userid3);
        	break;
        case 4:
        	System.out.println("enter book id:");
        	int bookid3=scan.nextInt();
        	tran.viewTransactionHistory(bookid3);
        	break;
        case 5:
        	System.out.println("enter your userid:");
        	int userid4=scan.nextInt();
        	System.out.println("enter book id:");
        	int bookid4=scan.nextInt();
        	tran.calculateLateFee(bookid4, userid4);
        	break;
        case 6:
        	
        	tran.viewTransactionDetails();
        	break;
        default:
    		System.out.println("Invalid choice. Try again.");
    		}
           scan.close();
		
	}
	}
	
	


