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

public class booksTable {
    
    Stage panel;
    Scene scene;
    TableView<Books> Books_Table;
    TextField NameInput, PriceInput;
    Label books_heading;
    Button Add, Delete, Back;
    HBox part_2, part_3;
    VBox layout;
    Owner owner = Owner.getInstance();
    
    public void start(Stage primaryStage, ManageInfo manage) {
        ObservableList<Books> book_data = FXCollections.observableArrayList();
        
        panel = primaryStage;
        panel.setTitle("BookStore Application");
        System.out.println(manage.booknames.size());
         for(int i=0; i < manage.booknames.size(); i++){
           
           book_data.add(new Books(manage.booknames.get(i),manage.bookprices.get(i)));
           System.out.println(manage.booknames.get(i));
       }
          
        books_heading = new Label("List of Books");
        books_heading.setFont(new Font("Arial", 20));

        //Name of Book
        TableColumn<Books, String> bookNameColumn = new TableColumn("Book Name");
        bookNameColumn.setMinWidth(200); 
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        //Book Price
        TableColumn<Books, Double> bookPriceColumn = new TableColumn("Book Price");
        bookPriceColumn.setMinWidth(200); 
        bookPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        NameInput = new TextField();
        NameInput.setPromptText("book name");
        NameInput.setMaxWidth(bookNameColumn.getPrefWidth());
        
        PriceInput = new TextField();
        PriceInput.setMaxWidth(bookPriceColumn.getPrefWidth());
        PriceInput.setPromptText("book price");
   
    //Buttons
        Add = new Button("Add");
        Add.setOnAction(e->addButtonClicked(manage));
       
        Delete = new Button();
        Delete.setText("Delete");
        Delete.setOnAction(e->deleteButtonClicked(manage));

        Back = new Button();
        Back.setText("Back");
        Back.setOnAction(e->backButtonClicked(manage));
    
        Books_Table = new TableView<Books>();
        Books_Table.setItems(book_data);
        Books_Table.getColumns().addAll(bookNameColumn, bookPriceColumn);
        
        part_2 = new HBox();
        part_2.setSpacing(3);
        part_2.getChildren().addAll(NameInput, PriceInput, Add);
        
        part_3 = new HBox();
        part_3.setSpacing(3);
        part_3.getChildren().addAll(Delete, Back);
        
        layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(books_heading, Books_Table, part_2, part_3);
        
        StackPane root = new StackPane();
        root.getChildren().addAll(layout);
        
        scene = new Scene(root, 300, 250);
        panel.setWidth(450);
        panel.setHeight(550);
        panel.setScene(scene);
        panel.show();
    }
    
    public void addButtonClicked(ManageInfo manage){
        Books book ;
        String s_name = NameInput.getText();
        String s_price = PriceInput.getText();
        try
        {
            if ( s_name instanceof String && s_price instanceof String){
                book = new Books(s_name, Double.parseDouble(s_price));
                manage.booknames.add(s_name);
                manage.bookprices.add(Double.parseDouble(s_price));
                
                Books_Table.getItems().add(book);
                
        FileWriter file = new FileWriter("books.txt", false);
        for(int i=0; i < manage.getBooknames().size(); i++){
            file.write("" + manage.getBooknames().get(i) + " " + manage.getBookPrices().get(i) +"\n");
        }
        file.close();
             }
        }
         catch(Exception e)
        {
            System.out.print("Fill out all the book details to add book");
        }

        NameInput.clear();
        PriceInput.clear();
    }
    
    public void deleteButtonClicked(ManageInfo manage){
        ObservableList<Books> BookSelected, allBooks;
        allBooks = Books_Table.getItems();
        BookSelected = Books_Table.getSelectionModel().getSelectedItems();
        
        for(int j=0; j < BookSelected.size(); j++){
        for(int i=0; i < manage.getBooknames().size(); i++){
            if(manage.getBooknames().get(i).equals(BookSelected.get(j).getName())){
                manage.booknames.remove(i);
                manage.bookprices.remove(i);
            }
        }
        }
        try {
        FileWriter file = new FileWriter("books.txt", false);
        for(int i=0; i < manage.getBooknames().size(); i++){
            file.write("" + manage.getBooknames().get(i) + " " + manage.getBookPrices().get(i) +"\n");
        }
        file.close();
             }
        
         catch(Exception e)
        {
            System.out.print("Fill out all the book details to add book");
        }
        BookSelected.forEach(allBooks::remove);

    }
    
    public void backButtonClicked(ManageInfo manage){
        FXOwnerStartPage O_startpage = new FXOwnerStartPage();
        O_startpage.start(panel, manage);
    }
 
}
