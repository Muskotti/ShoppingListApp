package fi.tamk.tiko.gui;

import fi.tamk.tiko.jsonparser.JsonParser;
import fi.tamk.tiko.jsonparser.Product;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 * Gui for the Shopping list app
 *
 * @author Toni Vänttinen (toni.vanttinen@cs.tamk.fi)
 * @version V1
 * @since 2018-11-19
 */

public class Gui extends Application {

    /**
     * Private list of products in string form that is used to generate the JSON file
     */
    private List<String> lines = new ArrayList<>();

    /**
     * Private list of products in Product class form
     */
    private List<Product> products = new ArrayList<>();

    /**
     * Json parser tool
     */
    private JsonParser parser = new JsonParser();

    /**
     * Observable list of the items
     */
    private ObservableList<Product> listData = FXCollections.observableArrayList();

    /**
     * The table view of observed list
     */
    private TableView productList;

    /**
     * Start method used to initialize the UI
     *
     * @param primaryStage stage where all elements are
     */
    @Override
    public void start(Stage primaryStage) {
        System.out.println("Author: Toni Vanttinen");
        lines.add(parser.start());
        primaryStage.setTitle("Gui");
        BorderPane pane = new BorderPane();
        ToolBar bar = generateToolBar();
        pane.setTop(bar);
        GridPane grid = generateGridPane();
        pane.setCenter(grid);
        Scene scene = new Scene(pane, 600,600);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        primaryStage.show();
    }

    /**
     * Last method used for debugging
     */
    @Override
    public void stop() {
        System.out.print("Goodbye");
    }


    /**
     * Generate GridPane for UI
     *
     * Generates GridPane to be used in the UI
     * First part is used make the table view of the added items
     * Next part is used to adding items to the table and to the JSON file
     *
     * @return GridPane element
     */
    public GridPane generateGridPane() {
        int tableColumn = 0;
        int addColumn = 1;
        GridPane tmp = new GridPane();
        // Generates visible list of items
        productList = generateProductList();
        GridPane.setConstraints(productList,tableColumn,0);
        // Generates product adding list
        VBox addingList = generateAddingList();
        GridPane.setConstraints(addingList,addColumn,0);
        // Vbox of the file submission
        VBox fileSubmit = generateFileSubmit();
        GridPane.setConstraints(fileSubmit,tableColumn,1,2,1);
        //Adds all to the grid
        tmp.setHgap(10);
        tmp.setVgap(10);
        tmp.setAlignment(Pos.CENTER);
        tmp.getChildren().addAll(productList,addingList,fileSubmit);
        return tmp;
    }

    /**
     * Generates a VBox Gui where items are added
     * @return Vbox of the items in the tmp VBox
     */
    private VBox generateAddingList() {
        VBox tmp = new VBox();
        // Product text
        Text productText = new Text("Give products name:");
        // Product input
        TextField name = new TextField();
        // Product count text
        Text productCount = new Text("How many products:");
        // Product count input
        TextField count = new TextField();
        //Defining the Add button
        Button add = new Button("Add");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int productID = 0;
                if(!products.isEmpty()) {
                    lines.add(parser.next());
                    for(int i = 0; i < products.size(); i++) {
                        productID = products.get(i).getId() + 1;
                    }
                }
                Product pro = new Product(productID,name.getText(),Integer.parseInt(count.getText()));
                products.add(pro);
                listData.add(pro);
                lines.add(parser.writeToJson(pro));
                name.clear();
                count.clear();
            }
        });
        //Defining the Clear button
        Button clear = new Button("Clear");
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                name.clear();
                count.clear();
            }
        });
        tmp.setSpacing(10);
        tmp.getChildren().addAll(productText,name,productCount,count,add,clear);
        return tmp;
    }

    /**
     * Generates Vbox that is used to make files
     * @return Vbox of the items in the tmp VBox
     */
    private VBox generateFileSubmit() {
        VBox tmp = new VBox();
        // File text
        Text fileName = new Text("File name:");
        // Files input text
        TextField fileNameInput = new TextField();
        // Submit button
        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lines.add(parser.end());
                Path file = Paths.get(fileNameInput.getText() + ".txt");
                try {
                    Files.write(file, lines, Charset.forName("UTF-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        tmp.getChildren().addAll(fileName,fileNameInput,submit);
        return tmp;
    }

    /**
     * Generates the table view of the items
     * @return TableView of the items in the tmp TableView
     */
    private TableView generateProductList() {
        TableView tmp = new TableView();
        TableColumn productName = new TableColumn ("Product name:");
        TableColumn productCount = new TableColumn("Product count:");
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        tmp.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tmp.setItems(listData);
        tmp.getColumns().addAll(productName,productCount);
        return tmp;
    }

    /**
     *  Generates ToolBar for the UI
     *
     *  Generates ToolBar to be used in the UI
     *  Used to change modes from writing to reading JSON files
     *
     * @return ToolBar element
     */
    public ToolBar generateToolBar() {
        ToolBar tmp = new ToolBar();

        Menu menu = new Menu("Modes");

        ToggleGroup modes = new ToggleGroup();
        RadioMenuItem write = new RadioMenuItem("Write");
        RadioMenuItem read = new RadioMenuItem("Read");
        modes.getToggles().add(write);
        modes.getToggles().add(read);
        modes.selectToggle(write);

        menu.getItems().addAll(write,read);

        MenuBar menuBar = new MenuBar(menu);
        tmp.getItems().addAll(menuBar);
        return tmp;
    }
}
