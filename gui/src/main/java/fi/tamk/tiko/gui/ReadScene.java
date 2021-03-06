package fi.tamk.tiko.gui;

import fi.tamk.tiko.jsonparser.JsonParserReader;
import fi.tamk.tiko.jsonparser.Product;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadScene {

    /**
     * Observable list of the items
     */
    private ObservableList<Product> listData = FXCollections.observableArrayList();

    /**
     * The table view of observed list
     */
    private TableView productList;

    /**
     * BorderPane of the overall ui
     */
    private BorderPane pane = new BorderPane();

    /**
     * The stage for all of the ui
     */
    private Stage primaryStage;

    /**
     * the current active scene
     */
    private Scene CurrentScene;

    /**
     * JsonParserReader reads the json files
     */
    private JsonParserReader reader = new JsonParserReader();

    /**
     * Sets the stage and scene
     * @param primaryStage the stage
     * @param CurrentScene the scene
     */
    public ReadScene(Stage primaryStage,Scene CurrentScene) {
        this.primaryStage = primaryStage;
        this.CurrentScene = CurrentScene;
    }

    /**
     * Generate GridPane for UI
     *
     * Generates GridPane to be used in the UI
     * First part is used make the table view of the items
     * Next part is used to selecting the file
     *
     * @return GridPane element
     */
    public GridPane generateGridPane() {
        GridPane tmp = new GridPane();
        productList = generateProductList();
        GridPane.setConstraints(productList,0,0);
        VBox fileSelect = generateFileSelect();
        GridPane.setConstraints(fileSelect,0,1);
        tmp.setHgap(10);
        tmp.setVgap(10);
        tmp.setAlignment(Pos.CENTER);
        tmp.getChildren().addAll(productList,fileSelect);
        return tmp;
    }

    /**
     * Generates a VBox and adds a button to it
     *
     * The Button is used to make a list of the items that are shown in the list
     *
     * @return a VBox with a button
     */
    private VBox generateFileSelect() {
        VBox tmp = new VBox();
        Button Select = new Button("Select a File");
        Select.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    try {
                        List<Product> proList = reader.readFile(file);
                        for(int i = 0; i < proList.size(); i++){
                            listData.add(proList.get(i));
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        tmp.getChildren().addAll(Select);
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
     * Generates the BorderPane
     * @return the generated BorderPane
     */
    public BorderPane generateBorderPane() {
        pane = new BorderPane();
        pane.setTop(generateToolBar());
        pane.setCenter(generateGridPane());
        return pane;
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
        write.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CurrentScene = makeWriteScene();
                primaryStage.setScene(CurrentScene);
            }
        });
        RadioMenuItem read = new RadioMenuItem("Read");
        modes.getToggles().add(write);
        modes.getToggles().add(read);
        modes.selectToggle(read);

        menu.getItems().addAll(write,read);

        MenuBar menuBar = new MenuBar(menu);
        tmp.getItems().addAll(menuBar);
        return tmp;
    }

    /**
     * Makes a WriteScene and returns it
     * @return a WriteScene
     */
    public Scene makeWriteScene() {
        WriteScene writeScene = new WriteScene(primaryStage,CurrentScene);
        BorderPane tmpPane = writeScene.generateBorderPane();
        Scene tmp = new Scene(tmpPane,600,600);
        return tmp;
    }
}
