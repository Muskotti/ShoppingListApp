package fi.tamk.tiko.gui;

import fi.tamk.tiko.jsonparser.JsonParser;
import fi.tamk.tiko.jsonparser.Product;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Gui extends Application {

    @Override
    public void start(Stage primaryStage) {
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

    @Override
    public void stop() {
        System.out.print("TAMA EI TOIMI");
    }


    public GridPane generateGridPane() {
        GridPane tmp = new GridPane();
        // Product text
        Text productText = new Text("Give products name");
        GridPane.setConstraints(productText,0,0,2,1);
        // Product input
        TextField name = new TextField();
        GridPane.setConstraints(name,0,1,2,1);
        // Product text
        Text productCount = new Text("How many products");
        GridPane.setConstraints(productCount,0,2,2,1);
        // Product input
        TextField count = new TextField();
        GridPane.setConstraints(count,0,3,2,1);
        //Defining the Submit button
        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product pro = new Product(0,name.getText(),Integer.parseInt(count.getText()));
                List<String> lines = new ArrayList<>();
                JsonParser tmp = new JsonParser();
                lines.add(tmp.start());
                lines.add(tmp.writeToJson(pro));
                lines.add(tmp.end());
                Path file = Paths.get("the-file-name.txt");
                try {
                    Files.write(file, lines, Charset.forName("UTF-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        GridPane.setConstraints(submit, 0, 4);
        //Defining the Clear button
        Button clear = new Button("Clear");
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                name.clear();
                count.clear();
            }
        });
        GridPane.setConstraints(clear, 1, 4);
        //Adds all to the grid
        tmp.setAlignment(Pos.CENTER);
        tmp.getChildren().addAll(name,productText,productCount,count,submit,clear);
        return tmp;
    }

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
