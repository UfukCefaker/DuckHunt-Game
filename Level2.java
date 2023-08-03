import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.animation.KeyFrame;

public class Level2{
    /**
     * ANIMATION_DURATION will be used for animation milisecond changing.
     * ammoLeft will be showed that how many ammo lefts.
     * ifShooted will be used for if bird is shooted.
     * duck1View ImageView of the duck.
     * duck1Viewfalling ImageView of the falling duck.
     * dx1 will be used for vertical pace of the duck
     * dy1 will be used for horizontal pace of the duck
     * animation will be used for Animation of the duck.
     * isImage1 will be used for changing image of duck.
     * isGameLost will be used for checking if game is lost or not.
     */
    private static final int ANIMATION_DURATION = 4000;
    private Integer ammoLeft;
    private boolean ifShooted = false;
    public Integer getAmmoLeft() {
        return ammoLeft;
    }

    public ImageView duck1View;
    public ImageView duck1Viewfalling;
    public VBox level2Text;
    public VBox VboxWinMessage;
    private double dx = 2, dy = 2;
    private Timeline animation;
    private boolean isImage1 = true;


    public Level2(){
        this.ammoLeft = 3;
    }
    /**
     * This function will be used for moving the bird with respect to dx1 and dy2.
     * @param stage of game.
     */
    protected void moveBird(Stage stage) {
        double sceneWidth = stage.getWidth();
        double sceneHeight = stage.getHeight();

        double currentX = duck1View.getTranslateX();
        double currentY = duck1View.getTranslateY();

        double newX = currentX + dx;
        double newY = currentY + dy;

        if (newX < 0 || newX + duck1View.getBoundsInLocal().getWidth() > sceneWidth) {
            dx *= -1;
        }
        if (newY < 0 || newY + duck1View.getBoundsInLocal().getHeight() > sceneHeight) {
            dy *= -1;
        }

        duck1View.setTranslateX(newX);
        duck1View.setTranslateY(newY);
        if (isImage1) {
            duck1View.setImage(new Image("assets/duck_black/1.png"));
            isImage1 = false;
        } else {
            duck1View.setImage(new Image("assets/duck_black/2.png"));
            isImage1 = true;
        }
    }
    /**
     * This function will be used for creating birds and moving to different ways.
     * @param root of the scene
     * @param keepGoing if it is still going
     * @param foregroundImages images of foregrounds
     * @param choosingBackgroundIndex index of background
     * @param scene of the stage.
     * @param stage of the window.
     * @param Scale for handling size of bird.
     */

    public void setupAnimation(Pane root, boolean keepGoing, ImageView[] foregroundImages, Integer choosingBackgroundIndex,
                               Stage stage,Scene scene,Double Scale) {
        duck1View = new ImageView(new Image("assets/duck_black/1.png"));
        root.getChildren().add(duck1View);


        duck1View.setFitWidth(duck1View.getImage().getWidth() * Scale);
        duck1View.setFitHeight(duck1View.getImage().getHeight() * Scale);

        animation = new Timeline(
                new KeyFrame(Duration.millis(10), e -> moveBird(stage)));

        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
        foregroundImages[choosingBackgroundIndex].setPreserveRatio(false);
        foregroundImages[choosingBackgroundIndex].setFitWidth(scene.getWidth());
        foregroundImages[choosingBackgroundIndex].setFitHeight(scene.getHeight());
        root.getChildren().add(foregroundImages[choosingBackgroundIndex]);

    }
    /**
     * This function will be used for when bird is shooted then this bird's creating the falling animation.
     * @param root of the scene
     * @param coordinateY of the bird's Y
     * @param coordinateX of the bird's X
     * @param foregroundImages images of foregrounds
     * @param choosingBackgroundIndex index of background
     * @param scene of the stage.
     * @param Scale for handling size of bird.
     */
    public void setupAnimationFalling(Pane root,Double coordinateX,Double coordinateY,ImageView[] foregroundImages,
                                      Integer choosingBackgroundIndex,Scene scene,Double Scale) {
        duck1Viewfalling = new ImageView(new Image("assets/duck_black/7.png"));
        duck1Viewfalling.setFitWidth(duck1Viewfalling.getImage().getWidth() * Scale);
        duck1Viewfalling.setFitHeight(duck1Viewfalling.getImage().getHeight() * Scale);


        TranslateTransition moveStay = new TranslateTransition(Duration.millis(100), duck1Viewfalling);
        moveStay.setFromX(coordinateX);
        moveStay.setFromY(coordinateY);

        TranslateTransition moveToptoBottom = new TranslateTransition(Duration.millis(ANIMATION_DURATION), duck1Viewfalling);
        moveToptoBottom.setFromX(coordinateX);
        moveToptoBottom.setFromY(coordinateY);
        moveToptoBottom.setToY(root.getHeight());

        moveStay.setOnFinished(event -> {
            duck1Viewfalling.setImage(new Image("assets/duck_black/8.png"));
            duck1Viewfalling.setScaleX(1);
            moveToptoBottom.play();
        });
        root.getChildren().add(duck1Viewfalling);
        moveStay.play();
        root.getChildren().remove(foregroundImages[choosingBackgroundIndex]);
        root.getChildren().add(foregroundImages[choosingBackgroundIndex]);

    }
    /**
     * This function will be used for adding table top of the scene which shows how many ammo left and which level we are.
     * @param root of scene.
     */
    public void addingTable(Pane root){
        Text level2Text = new Text();
        level2Text.setText("LEVEL 2/6       AMMO LEFT: "+Integer.toString(ammoLeft));

        level2Text.setFont(Font.font("White",20));
        level2Text.setFill(Color.WHITE);
        VBox vbox = new VBox(level2Text);
        vbox.setTranslateX(100);
        vbox.setTranslateY(30);
        this.level2Text = vbox;

        root.getChildren().add(vbox);


    }
    /**
     * This function will be used for creating winning message middle of the scene.
     * @param root
     * @param primaryStage
     * @param Scale
     */
    public void winningMessage(Pane root,Stage primaryStage,Double Scale){
        Text winMessage = new Text();
        winMessage.setText("        YOU WIN\nPress ENTER to play next level");
        winMessage.setFont(Font.font("Yellow",13*Scale));
        winMessage.setFill(Color.YELLOW);
        VBox vbox = new VBox(winMessage);
        vbox.setTranslateX(primaryStage.getWidth()/3);
        vbox.setTranslateY(primaryStage.getHeight()/3);
        this.VboxWinMessage = vbox;

        root.getChildren().add(vbox);

    }
    /**
     * When shooting is happened then one ammo is decreasing
     */
    public void shooting() {
        this.ammoLeft = ammoLeft -1;
    }
    /**
     * When bird is shooted then ifShooted variable setted to true.
     */
    public void shooted() {
        this.ifShooted = true;
    }

    public boolean isIfShooted() {
        return ifShooted;
    }

}
