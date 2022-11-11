package bookstore;

import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Tanin
 */

public class customersTable {
    
    Stage panel;
    Scene scene;
    TableView<Customers> customers_Table;
    TextField NameInput, PasswordInput;
    Label customers_heading;
    Button Add, Delete, Back;
    HBox part_2, part_3;
    VBox layout;
    ObservableList<Customers> Customer_data = FXCollections.observableArrayList();
    
    public void start(Stage primaryStage, ManageInfo manage) {
        
        panel = primaryStage;
        panel.setTitle("BookStore Application");
        for(int i=0; i < manage.usernames.size(); i++){
           Customer_data.add(new Customers(manage.usernames.get(i), manage.passwords.get(i),manage.points.get(i)));}

        customers_heading = new Label("Customers List");
        customers_heading.setFont(new Font("Arial", 20));

        //Name of Book
        TableColumn<Customers, String> customerNameColumn = new TableColumn("Username");
        customerNameColumn.setMinWidth(150); 
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<Customers, String>("username"));

        //Book Price 
        TableColumn<Customers, String> customerPasswordColumn = new TableColumn("Password");
        customerPasswordColumn.setMinWidth(150); 
        customerPasswordColumn.setCellValueFactory(new PropertyValueFactory<Customers, String>("password"));
        
        //Points
        TableColumn<Customers, Integer> customerPointsColumn = new TableColumn("Points");
        customerPointsColumn.setMinWidth(100); 
        customerPointsColumn.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("points"));
    
        NameInput = new TextField();
        NameInput.setPromptText("Username");
        NameInput.setMaxWidth(customerNameColumn.getPrefWidth());
        
        PasswordInput = new TextField();
        PasswordInput.setMaxWidth(customerPasswordColumn.getPrefWidth());
        PasswordInput.setPromptText("Password");
   
        //Buttons
        Add = new Button("Add");
        Add.setOnAction(e->addButtonClicked(manage));
       
        Delete = new Button();
        Delete.setText("Delete");
        Delete.setOnAction(e->deleteButtonClicked(manage));

        Back = new Button();
        Back.setText("Back");
        Back.setOnAction(e->backButtonClicked(manage));

        
        customers_Table = new TableView<Customers>();
        customers_Table.setItems(Customer_data);
        customers_Table.getColumns().addAll(customerNameColumn, customerPasswordColumn, customerPointsColumn);
        
        part_2 = new HBox();
        part_2.setSpacing(3);
        part_2.getChildren().addAll(NameInput, PasswordInput, Add);
        
        part_3 = new HBox();
        part_3.setSpacing(3);
        part_3.getChildren().addAll(Delete, Back);
        
        layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(customers_heading, customers_Table, part_2, part_3);
        
        StackPane root = new StackPane();
        root.getChildren().addAll(layout);
        
        scene = new Scene(root, 300, 250);
        panel.setWidth(450);
        panel.setHeight(550);
        panel.setScene(scene);
        panel.show();
    }
    
    public void addButtonClicked(ManageInfo manage){
        
        String customer_name = NameInput.getText();
        String customer_password = PasswordInput.getText();
        int customer_points = 0;
        
        try
        {
            if ( customer_name instanceof String && customer_password instanceof String ){
                Customers C = new Customers(customer_name, customer_password, customer_points);
                manage.usernames.add(customer_name);
                manage.passwords.add(customer_password);
                manage.points.add(customer_points);
                customers_Table.getItems().add(C);
                
                FileWriter file = new FileWriter("Customer.txt",false);
        for(int i=0; i < manage.getUsernames().size(); i++){
            file.write("" + manage.getUsernames().get(i) + " " + manage.getPasswords().get(i) + " " + manage.getPoints().get(i) + "\n");
        }
        file.close();
             }
            
        }
        catch(Exception e)
        {
            System.out.print("Fill out all the Customer details to add customer");
        }

        NameInput.clear();
        PasswordInput.clear();
    }
    
    public void deleteButtonClicked(ManageInfo manage){
        ObservableList<Customers> CustomerSelected, allCustomers;
        allCustomers = customers_Table.getItems();
        CustomerSelected = customers_Table.getSelectionModel().getSelectedItems();
        for(int j=0; j < CustomerSelected.size(); j++){
        for(int i=0; i < manage.getUsernames().size(); i++){
            if(manage.getUsernames().get(i).equals(CustomerSelected.get(j).getUsername()) && manage.getPasswords().get(i).equals(CustomerSelected.get(j).getPassword())){
                manage.usernames.remove(i);
                manage.passwords.remove(i);
                manage.points.remove(i);
            }
        }
        }
        
        try {
        FileWriter file = new FileWriter("customer.txt",false);
        for(int i=0; i < manage.getUsernames().size(); i++){
            file.write("" + manage.getUsernames().get(i) + " " + manage.getPasswords().get(i) +" " + manage.getPoints().get(i) + "\n");
        }
        file.close();
             }
        
         catch(Exception e)
        {
            System.out.print("Error");
        }
        CustomerSelected.forEach(allCustomers::remove);
        
    }
    
    public void backButtonClicked(ManageInfo manage){
        FXOwnerStartPage O_startpage = new FXOwnerStartPage();
        O_startpage.start(panel, manage);
    }

}

