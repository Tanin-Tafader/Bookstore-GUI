package bookstore;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Tanin
 */

public class FXOwnerStartPage {
    
    Stage panel;
    Scene scene;
    VBox vb;
    Button books_button;
    Button customers_button;
    Button logout_button;
    booksTable book_table = new booksTable();

    public void start(Stage primaryStage, ManageInfo manage) {
        
         System.out.println(manage.booknames.size());
         System.out.println(manage.usernames.size());
        
            panel = primaryStage;
            vb = new VBox(10);
            vb.setAlignment(Pos.CENTER);

            books_button = new Button();
            books_button.setText("Books");

            customers_button = new Button();
            customers_button.setText("Customers");

            logout_button = new Button();
            logout_button.setText("Logout");

            books_button.setOnAction(e->booksButtonClicked(manage)); 
            customers_button.setOnAction(e->customersButtonClicked(manage)); 
            logout_button.setOnAction(e->logoutButtonClicked()); 

            vb.getChildren().addAll(books_button, customers_button, logout_button);
            scene = new Scene(vb, 200, 200);

            panel.setTitle("Owner Start Page");
            panel.setWidth(450);
            panel.setHeight(550);
            panel.setScene(scene);
            panel.show();
    }
    
    public void booksButtonClicked(ManageInfo manage){
        
        book_table.start(panel, manage);
    }
     
    public void customersButtonClicked(ManageInfo manage){
        customersTable customer_table = new customersTable();
        customer_table.start(panel, manage);
    }

    public void logoutButtonClicked(){
        FXLogin login = new FXLogin();
        login.start(panel);
    }

}
