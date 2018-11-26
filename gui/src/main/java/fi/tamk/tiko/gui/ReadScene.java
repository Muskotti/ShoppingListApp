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
import java.util.ArrayList;
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
     *
     */
    private BorderPane pane = new BorderPane();

    /**
     *
     */
    private Stage primaryStage;

    /**
     *
     */
    private Scene CurrentScene;

    /**
     *
     */
    private JsonParserReader reader = new JsonParserReader();

    /**
     *
     */
    public ReadScene(Stage primaryStage,Scene CurrentScene) {
        this.primaryStage = primaryStage;
        this.CurrentScene = CurrentScene;
    }

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
                        List <Product> proList = new ArrayList<>();
                        Product pro = reader.readFile(file);
                        proList.add(pro);
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
     *
     * @return
     */
    public BorderPane generateBorderPane() {
        pane = new BorderPane();
        pane.setTop(generateToolBar());
        pane.setCenter(generateGridPane());
        return pane;
    }

    /**
     *
     * @return
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
     *
     * @return
     */
    public Scene makeWriteScene() {
        WriteScene writeScene = new WriteScene(primaryStage,CurrentScene);
        BorderPane tmpPane = writeScene.generateBorderPane();
        Scene tmp = new Scene(tmpPane,600,600);
        return tmp;
    }
}
