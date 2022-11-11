 package bookstore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Tanin
 */

public class Owner {
   
    private static Owner owner_instance = null;
    
    private final static String username = "admin";
    private final static String password = "admin";
    public ArrayList<Customers> CustomerList = new ArrayList<Customers>();
    public ArrayList<Books> BookList = new ArrayList<Books>();
    
    File BooksFile = new File("file_books.txt");
    File CustomersFile = new File("file_customers.txt");
    
    private Owner() {
    }
    
    public static Owner getInstance() {
        if (owner_instance == null)
            owner_instance = new Owner();
  
        return owner_instance;
    }
    
    public String getUsername(){
        return username;
    }

    public void addBook(Books book) throws IOException{
    }
    
     public void addCustomer(String name, String password, int points){
        CustomerList.add(new Customers(name, password, points));
    }
    
    public void deleteBook(int index){
        BookList.remove(index);
    }
    
    public void deleteCustomer(int index){
        CustomerList.remove(index);
    }
   
}

