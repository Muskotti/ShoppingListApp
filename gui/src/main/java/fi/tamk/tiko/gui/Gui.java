package fi.tamk.tiko.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Gui for the Shopping list app
 *
 * @author Toni Vänttinen (toni.vanttinen@cs.tamk.fi)
 * @version V1
 * @since 2018-11-19
 */

public class Gui extends Application {

    /**
     * The current scene
     */
    private Scene CurrentScene;

    /**
     * The Stage
     */
    private Stage primaryStage;

    /**
     * Start method used to initialize the UI
     *
     * @param primaryStage stage where all elements are
     */
    @Override
    public void start(Stage primaryStage) {
        System.out.println("Author: Toni Vanttinen");
        this.primaryStage = primaryStage;
        CurrentScene = makeWriteScene();
        primaryStage.setScene(this.CurrentScene);
        this.primaryStage.setTitle("Shopping List");
        this.primaryStage.setScene(CurrentScene);
        this.primaryStage.centerOnScreen();
        this.primaryStage.setOnCloseRequest(e -> Platform.exit());
        this.primaryStage.show();
    }

    /**
     * Makes the Writing scene
     * @return A Writing Scene
     */
    public Scene makeWriteScene() {
        WriteScene writeScene = new WriteScene(primaryStage,CurrentScene);
        BorderPane tmpPane = writeScene.generateBorderPane();
        Scene tmp = new Scene(tmpPane,600,600);
        return tmp;
    }

    /**
     * Last method used for debugging
     */
    @Override
    public void stop() {
        System.out.print("Goodbye");
    }
}
