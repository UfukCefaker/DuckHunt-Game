import javafx.application.Application;
import javafx.stage.Stage;

/***
 * This is the main class of the project.
 */
public class DuckHunt extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /***
     * this variable would handle the size of the window and bird size's.
     */
    public static Double SCALE;

    /**
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     * In SceneController object, every processes will be handled inside of that class.
     */
    @Override
    public void start(Stage primaryStage) {
        SCALE = 3.0;
        SceneController sceneController = new SceneController();
        sceneController.sceneHandling(primaryStage,SCALE);
 
    }

}