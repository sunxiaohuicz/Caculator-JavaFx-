package demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by XD.Wang on 2017/9/7.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = UI.getMainPane();
        Scene display = new Scene(pane);
        primaryStage.setScene(display);
        primaryStage.setTitle("计算器");
        primaryStage.setMaximized(false);
        primaryStage.setMinHeight(250);
        primaryStage.setMinWidth(250);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }

}
