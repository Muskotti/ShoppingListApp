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
     *
     */
    private Scene CurrentScene;

    /**
     *
     */
    private Stage primaryStage;

    /**
     *
     */
    private BorderPane pane = new BorderPane();;
    /**
     * Start method used to initialize the UI
     *
     * @param primaryStage stage where all elements are
     */
    @Override
    public void start(Stage primaryStage) {
        System.out.println("Author: Toni Vanttinen");
        this.primaryStage = primaryStage;
        ToolBar bar = generateToolBar();
        pane.setTop(bar);
        Scene writeScene = makeWriteScene();
        CurrentScene = writeScene;
        this.primaryStage.setTitle("Shopping List");
        this.primaryStage.setScene(CurrentScene);
        this.primaryStage.centerOnScreen();
        this.primaryStage.setOnCloseRequest(e -> Platform.exit());
        this.primaryStage.show();
    }

    /**
     *
     * @return
     */
    private Scene makeWriteScene() {
        WriteScene writeScene = new WriteScene();
        pane.setCenter(writeScene.getGrid());
        Scene tmp = new Scene(pane,600,600);
        return tmp;
    }

    /**
     *
     * @return
     */
    private Scene makeReadScene() {
        ReadScene readScene = new ReadScene();
        pane.setCenter(readScene.getGrid());
        Scene tmp = new Scene(pane,600,600);
        return tmp;
    }

    /**
     * Last method used for debugging
     */
    @Override
    public void stop() {
        System.out.print("Goodbye");
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
        read.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CurrentScene = makeReadScene();
                primaryStage.setScene(CurrentScene);
            }
        });
        modes.getToggles().add(write);
        modes.getToggles().add(read);
        modes.selectToggle(write);

        menu.getItems().addAll(write,read);

        MenuBar menuBar = new MenuBar(menu);
        tmp.getItems().addAll(menuBar);
        return tmp;
    }
}
