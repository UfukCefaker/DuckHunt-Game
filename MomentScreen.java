import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;


import java.awt.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
public class MomentScreen {
    /**
     * scene will be used for momentarly screen in the stage.
     * root will be used for momentarly StackPane in the scene.
     * screenType will be used for momentarly which scene we are on in.
     */
    private Scene scene;
    private StackPane root;
    private Integer screenType;
    MomentScreen(Scene scene,StackPane root,Integer screenType){
        this.scene = scene;
        this.root = root;
        this.screenType = screenType;
    }

    public void setRoot(StackPane root) {
        this.root = root;
    }

    /**
     * screen type will be setted.
     * @param screenType will showed that which scene we are on.
     */
    public void setScreenType(Integer screenType) {
        this.screenType = screenType;
    }

    /**
     * screen type will be getted in here.
     * @return screen type of object.
     */
    public Integer getScreenType() {
        return screenType;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
