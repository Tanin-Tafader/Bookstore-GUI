package bookstore;

import javafx.application.Application;
import static javafx.application.Application.launch;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author Tanin
 */

public class FXLogin extends Application {
    
    Stage panel;
    GridPane grid;
    Text title;
    Label usernameLabel, passwordLabel;
    TextField usernameText;
    PasswordField passwordText;
    Button LoginButton;
    HBox hbButton;
    Scene scene;
    Owner owner;
    Books books;
    ManageInfo manageinfo = new ManageInfo();
    CustomerStart customer = new CustomerStart();
    FXOwnerStartPage Fxowner = new FXOwnerStartPage();

    public void start(Stage primaryStage) {
        
        try{
            File file = new File("books.txt");
            Scanner scanner = new Scanner(file);
            String line;
            while(scanner.hasNext()){
                line = scanner.next();
                manageinfo.setName(line);
                line = scanner.next();
                manageinfo.setPrice(Double.parseDouble(line));
            }
            
            file = new File("customer.txt");
            scanner = new Scanner(file);
            while(scanner.hasNext()){
                line = scanner.next();
                manageinfo.setUsername(line);
                line = scanner.next();
                manageinfo.setPassword(line);
                line = scanner.next();
                manageinfo.setPoints(Integer.parseInt(line));
            }
    
        }
        catch(IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }
        
        //Panel
        panel = primaryStage;
        panel.setTitle("BookStore Application");
        
        //Grid
            grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);

        //title 
            title = new Text("Welcome to the BookStore Application");
            title.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
            
        //Labels and Buttons
            usernameLabel = new Label("Username:");
            usernameText = new TextField();
            passwordLabel = new Label("Password:");
            passwordText = new PasswordField();
            LoginButton = new Button("Login");
            
            hbButton = new HBox(10);
            hbButton.setAlignment(Pos.BOTTOM_RIGHT);
            hbButton.getChildren().add(LoginButton);
            
            grid.add(title, 0, 0, 2, 1);
            grid.add(usernameLabel, 0, 1);
            grid.add(usernameText, 1, 1);
            grid.add(passwordLabel, 0, 2);
            grid.add(passwordText, 1, 2);
            grid.add(hbButton, 1, 4);
            
            LoginButton.setOnAction(e -> customer_or_owner());
            
            scene = new Scene(grid, 420, 200);
            panel.setScene(scene);
            panel.show();
    }
    
    //Goes to either customer screen or owner screen
    public void customer_or_owner(){
        if(usernameText.getText().equals("admin") && passwordText.getText().equals("admin")){
            Fxowner.start(panel, manageinfo);
        }
        
        for(int i=0; i < manageinfo.getUsernames().size(); i++){
            if(usernameText.getText().equals(manageinfo.getUsernames().get(i)) && passwordText.getText().equals(manageinfo.getPasswords().get(i))){
                customer.setUsername((String)(manageinfo.getUsernames().get(i)));
                customer.setPoints((int)manageinfo.getPoints().get(i));
                customer.setPassword((String)(manageinfo.getPasswords().get(i)));
                customer.start(panel,manageinfo);
                
            }
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
