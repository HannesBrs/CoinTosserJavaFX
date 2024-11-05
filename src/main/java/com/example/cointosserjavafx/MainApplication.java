package com.example.cointosserjavafx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class starts the GUI of the application
 * @author Hannes
 */
public class MainApplication extends Application {

    private static final String STAGE_TITLE = "CoinTosser - JavaFX";
    private static final String SCENE_FXML_FILENAME = "application-view.fxml";
    private static final String APP_ICON_FILENAME = "coin-icon.png";

    @FXML
    private static Scene scene;

    /**
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(SCENE_FXML_FILENAME));
        scene = new Scene(fxmlLoader.load(), 800, 550);
        changeTheme(ThemeChanger.getNextStylesheet());
        stage.setResizable(false);
        stage.setTitle(STAGE_TITLE);
        Image icon = new Image(getClass().getResourceAsStream(APP_ICON_FILENAME));
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method changes the theme (stylesheet) of the app during runtime
     * @param stylesheet path of the stylesheet that will be changed to
     */
    public static void changeTheme(String stylesheet) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(MainApplication.class.getResource(stylesheet).toExternalForm());
    }

    /**
     * The main method launches the application
     * @param args not provided
     */
    public static void main(String[] args) {
        launch();
    }
}
