package bookstore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;
import javafx.scene.text.Text;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.StackPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Tanin
 */

public class CustomerStart {
        private String username;
        private String password;
        private double prices;
        private int points;
        private Stage stage;
        Scene scene_2;
        private ArrayList<Books> cart;
        ObservableList<Books> books = FXCollections.observableArrayList();

        private double totalcost;

        public CustomerStart(String username, String password) {
            this.username = username;
            this.password = password;
            this.points = 0;     
        }
        
        public CustomerStart() {
            this.username = "";
            this.password = "";
            this.cart = new ArrayList<Books>();
            this.points = 0;   
        }
        
        public double totalCost(ArrayList<Books> x) {
            double cost = 0;
            for(Books book : x){
                cost = cost + book.getPrice();
            }
            this.totalcost = cost;
            return cost;
        }

        public double getTotalCost(){
            return totalcost;
        }
        
        public void setTotalCost(double totalcost){
            this.totalcost = totalcost;
            
        }
        
        public String getUsername(){
            return username;
        }
        
        public void setUsername(String username){
            this.username = username;
        }
        
        public void redeem() {
            Points.redeem(this);
        }
        
        public void setPoints(int points){
            this.points = points;
        }
        
        public String getPassword(){
            return password;
        }
        
        public void setPassword(String password){
            this.password = password;
        }
        public int getPoints(){
            return points;
        }
        
        public String getStatus() {
            if(getPoints() > 1000){
                return "gold";
            }
            else {
                return "silver";
            } 
        }
        
        public ArrayList<Books> getCart(){
            return cart;
        }
        
        public void setCart(ArrayList<Books> cart){
            this.cart = cart;
        }
   
    public void start(Stage primaryStage, ManageInfo manage) {

        this.stage = primaryStage;
        for(int i=0; i < manage.booknames.size(); i++){
           books.add(new Books(manage.booknames.get(i), manage.bookprices.get(i)));
       }

        primaryStage.setTitle("CustomerStart");
        primaryStage.setWidth(800);
        primaryStage.setHeight(500);

        Label label = new Label("Welcome " + this.getUsername() +". You have " + this.getPoints() + " points. Your status is " + this.getStatus() + ".");

        //Buttons
        Button b1 = new Button("Buy");
        Button b2 = new Button ("Redeem and Buy");
        Button b3 = new Button ("Logout");
        HBox setbox = new HBox();
        setbox.setPadding(new Insets(10,10,10,10));
        setbox.setSpacing(10);
        setbox.getChildren().addAll(b1,b2,b3);
        setbox.setAlignment(Pos.CENTER);
        
        TableView<Books> table = new TableView<Books>();
        //Name of Book
        TableColumn<Books,String>nameColumn = new TableColumn<>("Book Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        //Price
        TableColumn<Books,Double>priceColumn = new TableColumn<>("Book Price");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //Select Box
        TableColumn<Books,CheckBox> selectColumn = new TableColumn<Books,CheckBox>("Select");
        selectColumn.setCellValueFactory(new PropertyValueFactory<Books,CheckBox>("select"));

        table.setItems(books);
        table.getColumns().addAll(nameColumn, priceColumn, selectColumn);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.getChildren().addAll(label, table, setbox);

        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);
    
        primaryStage.show();
        
        b1.setOnAction(e -> handleOptions(table, manage));
        b2.setOnAction(e -> redeemBuy(table, manage));
        b3.setOnAction(e -> LogOutButtonClicked(manage));

    }
    
    private void handleOptions(TableView<Books> table, ManageInfo manage){
        for(Books book : table.getItems()){
            if(book.getSelect().isSelected()){
                this.getCart().add(book);
            }
        }
        
        Label label2 = new Label("The total cost of the books is " + this.totalCost(this.cart));
        this.points = this.points+ ((int) totalcost)*10;
        Label label4 = new Label("Your current status is " + this.getStatus());
        Label label3 = new Label("Your total points is " + this.getPoints());
        for(int i=0; i < manage.getPoints().size(); i++){
            if(this.username.equals(manage.getUsernames().get(i)) && this.password.equals(manage.getPasswords().get(i))){
                manage.getPoints().set(i, this.points);
            }
        }
        
        for(int j=0; j < cart.size(); j++){
        for(int i=0; i < manage.getBooknames().size(); i++){
            if(manage.getBooknames().get(i).equals(cart.get(j).getName())){
                manage.booknames.remove(i);
                manage.bookprices.remove(i);
            }
        }
        }

        Button b4 = new Button("Logout");
        VBox vbox = new VBox();
        vbox.getChildren().addAll(label2, label4, label3, b4);
        vbox.setAlignment(Pos.CENTER);
        Scene scene_2 = new Scene(vbox,500,200);
        stage.setTitle("Customer End");
        b4.setOnAction(e -> LogOutButtonClicked(manage));

        stage.setScene(scene_2);
        stage.show();
    }
    
    private void redeemBuy(TableView<Books> table, ManageInfo manage){
        for(Books book : table.getItems()){
            if(book.getSelect().isSelected()){
                this.getCart().add(book);
            }
        }
        
        this.setTotalCost(totalCost(getCart()));
        this.redeem();
        Label label1 = new Label("Thanks for shopping!");
        Label label2 = new Label("The total cost of the books is " + this.getTotalCost());
        Label label4 = new Label("Your current status is " + this.getStatus());
        Label label3 = new Label("Your total points is " + this.getPoints());
        
        for(int i=0; i < manage.getPoints().size(); i++){
            if(this.username.equals(manage.getUsernames().get(i)) && this.password.equals(manage.getPasswords().get(i))){
                manage.getPoints().set(i, this.points);
            }
        }

        for(int j=0; j < cart.size(); j++){
        for(int i=0; i < manage.getBooknames().size(); i++){
            if(manage.getBooknames().get(i).equals(cart.get(j).getName())){
                manage.booknames.remove(i);
                manage.bookprices.remove(i);
            }
        }
        }

        Button b4 = new Button("Logout");
        
        VBox vbox = new VBox();
        vbox.getChildren().addAll(label1, label2, label4, label3, b4);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox,500,200);
        stage.setTitle("Customer End");
        b4.setOnAction(e -> LogOutButtonClicked(manage));

        stage.setScene(scene);
        stage.show();
    }
     
    private void LogOutButtonClicked(ManageInfo manage){
        try {
        FileWriter file = new FileWriter("customer.txt", false);
        for(int i=0; i < manage.getUsernames().size(); i++){
            file.write("" + manage.getUsernames().get(i) + " " + manage.getPasswords().get(i) + " " + manage.getPoints().get(i) + "\n");
        }
        file.close();
       
        }
        catch(IOException e){
            System.out.println("Error");
            
        }
        try {
        FileWriter xfile = new FileWriter("books.txt", false);
        for(int i=0; i < manage.getBooknames().size(); i++){
            xfile.write("" + manage.getBooknames().get(i) + " " + manage.getBookPrices().get(i) +"\n");
        }
        xfile.close();
             }
        
         catch(Exception e)
        {
            System.out.print("Error");
        }
        
        FXLogin login = new FXLogin();
        login.start(stage);
    }

}
