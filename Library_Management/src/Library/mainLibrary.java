package Library;

import java.util.Scanner;

public class mainLibrary {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		 LibraryManager lm=new LibraryManager();
		  System.out.println("Welcome to the Library Management System!");
	        System.out.println("1.Bookmanagement");
	        System.out.println("2.usermanagement");
	        System.out.println("3.transaction ");
	        int choice=scan.nextInt();
	        switch(choice) {
	        case 1:
	        	lm.bookdetails();
	        	break;
	        case 2:
	        	lm.userdetails();
	        	break;
	        case 3:
	        	lm.transactiondetails();
	        	break;
	        default:
	        	System.out.println("Invalid choice. Try again.");
	        		}
	        scan.close();
	        
	}

	}

