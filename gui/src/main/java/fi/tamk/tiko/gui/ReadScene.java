package fi.tamk.tiko.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReadScene {

    /**
     *
     */
    private BorderPane pane = new BorderPane();

    /**
     *
     */
    private GridPane grid;

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
    public ReadScene(Stage primaryStage,Scene CurrentScene) {
        this.primaryStage = primaryStage;
        this.CurrentScene = CurrentScene;
        grid = generateGridPane();
    }

    public GridPane generateGridPane() {
        GridPane tmp = new GridPane();
        return tmp;
    }

    /**
     *
     * @return
     */
    public BorderPane generateBorderPane() {
        pane = new BorderPane();
        pane.setTop(generateToolBar());
        pane.setCenter(grid);
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
