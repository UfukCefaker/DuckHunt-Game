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
public class Level6 {
    /**
     * ANIMATION_DURATION will be used for animation milisecond changing.
     * ammoLeft will be showed that how many ammo lefts.
     * ifShooted will be used for if bird is shooted.
     * duck1View ImageView of the duck1.
     * duck2View ImageView of the duck2.
     * duck3View ImageView of the duck3.
     * duck1Viewfalling ImageView of the falling duck1.
     * duck2Viewfalling ImageView of the falling duck2.
     * duck3Viewfalling ImageView of the falling duck3.
     * dx1 will be used for vertical pace of the duck1
     * dx2 will be used for vertical pace of the duck2
     * dx3 will be used for vertical pace of the duck3
     * dy1 will be used for horizontal pace of the duck1
     * dy2 will be used for horizontal pace of the duck2
     * dy3 will be used for horizontal pace of the duck3
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
    public ImageView duck2View;
    public ImageView duck3View;
    public ImageView duck1Viewfalling;
    public ImageView duck2Viewfalling;
    public ImageView duck3Viewfalling;
    public VBox level6Text;
    public VBox VboxWinMessage;
    private double dx1 = 4,dx2=4,dx3=4;
    private double dy1=4,dy2=4, dy3=4;
    private Timeline animation;
    private boolean isImage1 = true,isImage2 = true,isImage3 = true;
    public boolean isDuck1Falls = false,isDuck2Falls = false,isDuck3Falls = false;
    public Level6(){
        this.ammoLeft =9;
    }
    /**
     * This function will be used for moving the 3 bird with respect to dx1,dx2,dx3,dy1,dy2 and dy3.
     * @param stage of game.
     */
    protected void moveBird(Stage stage) {
        double sceneWidth = stage.getWidth();
        double sceneHeight = stage.getHeight();

        double currentX1 = duck1View.getTranslateX();
        double currentY1 = duck1View.getTranslateY();
        double currentX2 = duck2View.getTranslateX();
        double currentY2 = duck2View.getTranslateY();
        double currentX3 = duck3View.getTranslateX();
        double currentY3 = duck3View.getTranslateY();


        double newX1 = currentX1 + dx1;
        double newY1 = currentY1 + dy1;
        double newX2 = currentX2 + dx2;
        double newY2 = currentY2 + dy2;
        double newX3 = currentX3 + dx3;
        double newY3 = currentY3 + dy3;

        if (newX1 < 0 || newX1 + duck1View.getBoundsInLocal().getWidth() > sceneWidth) {
            dx1 *= -1;
        }
        if (newY1 < 0 || newY1 + duck1View.getBoundsInLocal().getHeight() > sceneHeight) {
            dy1 *= -1;
        }
        if (newX2 < 0 || newX2 + duck2View.getBoundsInLocal().getWidth() > sceneWidth) {
            dx2 *= -1;
        }
        if (newY2 < 0 || newY2 + duck2View.getBoundsInLocal().getHeight() > sceneHeight) {
            dy2 *= -1;
        }
        if (newX3 < 0 || newX3 + duck3View.getBoundsInLocal().getWidth() > sceneWidth) {
            dx3 *= -1;
        }
        if (newY3 < 0 || newY3 + duck3View.getBoundsInLocal().getHeight() > sceneHeight) {
            dy3 *= -1;
        }

        duck1View.setTranslateX(newX1);
        duck1View.setTranslateY(newY1);
        duck2View.setTranslateX(newX2);
        duck2View.setTranslateY(newY2);
        duck3View.setTranslateX(newX3);
        duck3View.setTranslateY(newY3);

        if (isImage1) {
            duck1View.setImage(new Image("assets/duck_red/5.png"));
            isImage1 = false;
        } else {
            duck1View.setImage(new Image("assets/duck_red/6.png"));
            isImage1 = true;
        }
        if (isImage2) {
            duck2View.setImage(new Image("assets/duck_blue/5.png"));
            isImage2 = false;
        } else {
            duck2View.setImage(new Image("assets/duck_blue/6.png"));
            isImage2 = true;
        }
        if (isImage3) {
            duck3View.setImage(new Image("assets/duck_black/5.png"));
            isImage3 = false;
        } else {
            duck3View.setImage(new Image("assets/duck_black/6.png"));
            isImage3 = true;
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
        duck1View = new ImageView(new Image("assets/duck_red/5.png"));
        duck2View = new ImageView(new Image("assets/duck_blue/5.png"));
        duck3View = new ImageView(new Image("assets/duck_black/5.png"));
        root.getChildren().add(duck1View);
        root.getChildren().add(duck2View);
        root.getChildren().add(duck3View);


        duck1View.setFitWidth(duck1View.getImage().getWidth() * Scale);
        duck1View.setFitHeight(duck1View.getImage().getHeight() * Scale);
        duck2View.setFitWidth(duck2View.getImage().getWidth() * Scale);
        duck2View.setFitHeight(duck2View.getImage().getHeight() * Scale);
        duck3View.setFitWidth(duck3View.getImage().getWidth() * Scale);
        duck3View.setFitHeight(duck3View.getImage().getHeight() * Scale);

        duck1View.setTranslateX(0);
        duck1View.setTranslateY(100);
        duck2View.setTranslateX(scene.getWidth() - duck2View.getFitWidth());
        duck2View.setTranslateY(150);
        //duck3View.setTranslateY(150);


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
     * @param duckSelecter will be used for detecting which duck is shooted.
     */
    public void setupAnimationFalling(Pane root,Double coordinateX,Double coordinateY,ImageView[] foregroundImages,
                                      Integer choosingBackgroundIndex,Scene scene,Integer duckSelecter,Double Scale) {
        duck1Viewfalling = new ImageView(new Image("assets/duck_red/7.png"));
        duck1Viewfalling.setFitWidth(duck1Viewfalling.getImage().getWidth() * Scale);
        duck1Viewfalling.setFitHeight(duck1Viewfalling.getImage().getHeight() * Scale);

        duck2Viewfalling = new ImageView(new Image("assets/duck_blue/7.png"));
        duck2Viewfalling.setFitWidth(duck2Viewfalling.getImage().getWidth() * Scale);
        duck2Viewfalling.setFitHeight(duck2Viewfalling.getImage().getHeight() * Scale);

        duck3Viewfalling = new ImageView(new Image("assets/duck_black/7.png"));
        duck3Viewfalling.setFitWidth(duck3Viewfalling.getImage().getWidth() * Scale);
        duck3Viewfalling.setFitHeight(duck3Viewfalling.getImage().getHeight() * Scale);

        TranslateTransition moveStay;
        if (duckSelecter ==1){
            isDuck1Falls = true;
            moveStay = new TranslateTransition(Duration.millis(100), duck1Viewfalling);
        }else if (duckSelecter ==2){
            isDuck2Falls = true;
            moveStay = new TranslateTransition(Duration.millis(100), duck2Viewfalling);
        }else {
            isDuck3Falls = true;
            moveStay = new TranslateTransition(Duration.millis(100), duck3Viewfalling);
        }

        moveStay.setFromX(coordinateX);
        moveStay.setFromY(coordinateY);

        TranslateTransition moveToptoBottom;
        if (duckSelecter == 1){
            moveToptoBottom = new TranslateTransition(Duration.millis(ANIMATION_DURATION), duck1Viewfalling);
        }else if (duckSelecter == 2){
            moveToptoBottom = new TranslateTransition(Duration.millis(ANIMATION_DURATION), duck2Viewfalling);
        }else {
            moveToptoBottom = new TranslateTransition(Duration.millis(ANIMATION_DURATION), duck3Viewfalling);
        }

        moveToptoBottom.setFromX(coordinateX);
        moveToptoBottom.setFromY(coordinateY);
        moveToptoBottom.setToY(root.getHeight());

        moveStay.setOnFinished(event -> {
            if (duckSelecter == 1){
                duck1Viewfalling.setImage(new Image("assets/duck_red/8.png"));
                duck1Viewfalling.setScaleX(1);
            }else if (duckSelecter == 2){
                duck2Viewfalling.setImage(new Image("assets/duck_blue/8.png"));
                duck2Viewfalling.setScaleX(1);
            }else {
                duck3Viewfalling.setImage(new Image("assets/duck_black/8.png"));
                duck3Viewfalling.setScaleX(1);
            }
            moveToptoBottom.play();
        });
        if (duckSelecter == 1){
            root.getChildren().add(duck1Viewfalling);
        }else if (duckSelecter == 2){
            root.getChildren().add(duck2Viewfalling);
        }else {
            root.getChildren().add(duck3Viewfalling);
        }
        moveStay.play();
        root.getChildren().remove(foregroundImages[choosingBackgroundIndex]);
        root.getChildren().add(foregroundImages[choosingBackgroundIndex]);
    }
    /**
     * This function will be used for adding table top of the scene which shows how many ammo left and which level we are.
     * @param root of scene.
     */
    public void addingTable(Pane root){
        Text level6Text = new Text();
        level6Text.setText("LEVEL 6/6       AMMO LEFT: "+Integer.toString(ammoLeft));

        level6Text.setFont(Font.font("White",20));
        level6Text.setFill(Color.WHITE);
        VBox vbox = new VBox(level6Text);
        vbox.setTranslateX(100);
        vbox.setTranslateY(30);
        this.level6Text = vbox;

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
        winMessage.setText("      You have completed the game!\nPress ENTER to play again\n    Press ESC to exit");
        winMessage.setFont(Font.font("Yellow",13 * Scale));
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

}
