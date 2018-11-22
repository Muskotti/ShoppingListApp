package fi.tamk.tiko.gui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class ReadScene {

    public ReadScene() {

    }

    public BorderPane generateBorderPane() {
        return new BorderPane();
    }

    /**
     *
     * @return
     */
    /*
    public Scene makeWriteScene() {
        WriteScene writeScene = new WriteScene(primaryStage,CurrentScene);
        BorderPane tmpPane = writeScene.generateBorderPane();
        Scene tmp = new Scene(tmpPane,600,600);
        return tmp;
    }
    */
}
