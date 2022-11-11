package bookstore;

import java.util.ArrayList;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Tanin
 */

public class Books {

    private String bookName;
    private double bookPrice;
    private CheckBox select;
    static double sum = 0;
    
public Books(String bookName, double bookPrice){
    this.bookName = bookName;
    this.bookPrice = bookPrice;
    this.select = new CheckBox();
}

public String getName(){
    return bookName;
}
   
public double getPrice(){
    return bookPrice;
}

public void buyBook(){
    sum = sum + getPrice();
}

public CheckBox getSelect(){
    return select;
}

public void setSelect(CheckBox select){
    this.select = select;
}

}
