package bookstore;

import java.util.ArrayList;

/**
 *
 * @author Tanin
 */

public class ManageInfo {
    ArrayList<String> usernames = new ArrayList<String>();
    ArrayList<String> passwords = new ArrayList<String>();
    ArrayList<String> booknames = new ArrayList<String>();
    ArrayList<Double> bookprices = new ArrayList<Double>();
    ArrayList<Integer> points = new ArrayList<Integer>();

        
    public ArrayList getUsernames(){
        return usernames;
    }
    
    public void setUsername(String username){
        usernames.add(username);
    }
        
    public ArrayList getPasswords(){
        return passwords;
    }
    
    public void setPassword(String password){
        passwords.add(password);
    }
    
    public ArrayList getBookPrices(){
        return bookprices;
    }
    
    public ArrayList getPoints(){
        return points;
    }
    
    public ArrayList getBooknames(){
        return booknames;
    }
    
    public void setPrice(double price){
        bookprices.add(price);
    }
    
    public void setName(String name){
        booknames.add(name);
    }
    
    public void setPoints(int point){
        points.add(point);
    }
}
