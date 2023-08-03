import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.application.Platform;

/***
 * All processes will be handled at here.
 */
public class SceneController {
    /***
     * choosingBackgroundIndex variable will be used for all backgrounds index to choossing one of them.
     * choosingCrosshairIndex variable will be used for all crosshairs index to choossing one of them.
     * finalCrosshair variable will be used for choosen crosshair.
     * mediaPlayer will be used for starting menu sound.
     */
    public static Integer choosingBackgroundIndex =0;
    public static Integer choosingCrosshairIndex =0;

    public static ImageView finalCrosshair = new ImageView();
    //public static Boolean isLevelFinished = false;
    //private static final int WIDTH = 800;
    //private static final int ANIMATION_DURATION = 4000;
    public static MediaPlayer mediaPlayer;
    public static boolean canEnterBePressed = false;

    /***
     *
     * @param background background will be putted.
     * @param root  Stackpane root will be used for scene.
     * @param scene scene is the putted one to stage.
     */
    public void startingScreenHandling(BackgroundImage background,StackPane root,Scene scene){
        root.getChildren().clear();
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        root.setBackground(new Background(background));
    }

    /***
     * this function will be used for clearing scene and putting background.
     * @param root
     * @param crossViews
     * @param choosingCrosshairIndex
     * @param backgroundImages
     * @param momentarlyScreen object' scene and root will be changed.
     * @param scene
     */
    public void changingNextLevel(StackPane root,ImageView[] crossViews,Integer choosingCrosshairIndex,
                                  BackgroundImage[] backgroundImages,MomentScreen momentarlyScreen,Scene scene){
        root.getChildren().clear();
        finalCrosshair = crossViews[choosingCrosshairIndex];
        root.getChildren().add(finalCrosshair);
        root.setBackground(new Background(backgroundImages[choosingBackgroundIndex]));
        momentarlyScreen.setScene(scene);
        momentarlyScreen.setRoot(root);
    }

    /***
     * This function will be used for starting game again.
     * @param primaryStage
     */
    public void gameStartingAgain(Stage primaryStage,StackPane root){
        Platform.runLater(() -> {
            DuckHunt duckHunt = new DuckHunt();
            root.getChildren().clear();
            duckHunt.start(primaryStage);
        });
    }

    /**
     * In this function all processes will be handled and managing.
     * @param primaryStage stage of the window.
     * @param Scale is the size of the stage.
     */
    public void sceneHandling(Stage primaryStage,Double Scale){
        Image image = new Image("assets/favicon/1.png");
        /**
         * backgroundSize will be size of the backgrounds.
         */
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        Image cross1 = new Image("assets/crosshair/1.png");
        ImageView cross1View = new ImageView(cross1);

        /**
         * In this arrays will be created at creatingImages class.
         * All of the backgrounds, foregrounds and crossViews will be created at there and putting that variables.
         */
        BackgroundImage[] backgroundImages = creatingImages.returnsBackgroundImages();
        ImageView[] foregroundImages = creatingImages.returnsForegroundImages();
        ImageView[] crossViews = creatingImages.returnsCrosshairImages();

        BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,backgroundSize);
        StackPane root = new StackPane();
        root.setBackground(new Background(background));

        /**
         * scene will be created by getting size of Scale.
         */
        Scene scene = new Scene(root,300*Scale, 200*Scale);
        /**
         * Menu text will be created and putted into menu root.
         */
        Text startedScreentext1 = new Text();
        startedScreentext1.setText("PRESS ENTER TO START\n     PRESS ESC TO EXIT");

        startedScreentext1.setFont(Font.font("Yellow",15*Scale));
        startedScreentext1.setFill(Color.YELLOW);
        VBox vbox = new VBox(startedScreentext1);
        vbox.setTranslateX(75*Scale);
        vbox.setTranslateY(140*Scale);

        root.getChildren().add(vbox);

        /**
         * For shining FadeTransition will be used.
         */
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), startedScreentext1);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.setAutoReverse(true);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            fadeTransition.playFromStart();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        /**
         * Stage's tittle will be setted to HUBBM Duck Hunt.
         * Stage's icon will be setted to in assets favicon 1.png.
         */
        primaryStage.setTitle("HUBBM Duck Hunt");
        primaryStage.getIcons().add(new Image("assets/favicon/1.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
        /**
         * for controlling the scene , momentarlyScreen object will be created and its screenType variable will be setted to 1.
         */
        MomentScreen momentarlyScreen = new MomentScreen(scene,root,1);

        /**
         * All level class objects will be created.
         */
        Level1 level1 = new Level1();
        Level2 level2 = new Level2();
        Level3 level3 = new Level3();
        Level4 level4 = new Level4();
        Level5 level5 = new Level5();
        Level6 level6 = new Level6();

        Media sound = new Media(getClass().getResource("assets/effects/Title.mp3").toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));

        /**
         * Gunshot and DuckFalls sounds will be created into that variables.
         */
        Media soundShooted = new Media(getClass().getResource("assets/effects/Gunshot.mp3").toString());
        MediaPlayer mediaPlayerShooted = new MediaPlayer(soundShooted);

        Media soundFalls = new Media(getClass().getResource("assets/effects/DuckFalls.mp3").toString());
        MediaPlayer mediaPlayerFalls = new MediaPlayer(soundFalls);

        Media soundPassingNextLevel = new Media(getClass().getResource("assets/effects/LevelCompleted.mp3").toString());
        MediaPlayer mediaPlayerPassNextLevel = new MediaPlayer(soundPassingNextLevel);

        Media soundGameOver = new Media(getClass().getResource("assets/effects/GameOver.mp3").toString());
        MediaPlayer mediaPlayerGameOver = new MediaPlayer(soundGameOver);

        Media soundGameCompleted = new Media(getClass().getResource("assets/effects/GameCompleted.mp3").toString());
        MediaPlayer mediaPlayerGameCompleted = new MediaPlayer(soundGameCompleted);


        /**
         * For any pressing key on the keyboard:
         */
        scene.setOnKeyPressed(event -> {
            /**
             * If it is first screen:
             */
            if  (momentarlyScreen.getScreenType() == 1){
                /**
                 * If ESC is pressed on the keyboard:
                 * stage will be closed.
                 */
                if (event.getCode() == KeyCode.ESCAPE) {
                    primaryStage.close();
                }
                /**
                 * If ENTER is pressed on the keyboard:
                 * backgroundselection and crosshair selection screen will be created.
                 */
                else if (event.getCode() == KeyCode.ENTER) {
                    /**
                     * firstly root is being cleaned, then first background is putted into root.
                     * Then momentarlyScreen screenType will be setted 2.
                     */
                    root.getChildren().clear();
                    Image imageStarted2ndTime = new Image("assets/background/1.png");
                    BackgroundImage background2ndStart = new BackgroundImage(imageStarted2ndTime , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,backgroundSize);
                    root.setBackground(new Background(background2ndStart));
                    root.getChildren().add(cross1View);

                    Text startedScreentext2 = new Text();
                    startedScreentext2.setText("USE ARROW KEYS TO NAVIGATE\n    PRESS ENTER TO START");

                    startedScreentext2.setFont(Font.font("White",40));
                    startedScreentext2.setFill(Color.WHITE);
                    VBox vbox2 = new VBox(startedScreentext1);
                    vbox2.setTranslateX(200);
                    vbox2.setTranslateY(50);
                    root.getChildren().add(vbox2);
                    fadeTransition.stop();
                    startedScreentext2.setOpacity(1.0);

                    momentarlyScreen.setRoot(root);
                    momentarlyScreen.setScreenType(2);
                }
            } else if (momentarlyScreen.getScreenType() == 2) {
                /**
                 *  In second screen if escape is pressed then it is going back to starting menu.
                 *
                 */
                if (event.getCode() == KeyCode.ESCAPE){
                    startingScreenHandling(background,root,scene);
                    momentarlyScreen.setScreenType(1);
                    root.getChildren().add(vbox);
                }else if (event.getCode()== KeyCode.ENTER){
                    mediaPlayer.stop();
                    Media IntroSound = new Media(getClass().getResource("assets/effects/Intro.mp3").toString());
                    MediaPlayer mediaPlayerIntro = new MediaPlayer(IntroSound);
                    mediaPlayerIntro.play();
                    mediaPlayerIntro.setOnEndOfMedia(() -> {
                        root.getChildren().clear();

                        finalCrosshair = crossViews[choosingCrosshairIndex];
                        root.getChildren().add(finalCrosshair);
                        root.setBackground(new Background(backgroundImages[choosingBackgroundIndex]));
                        momentarlyScreen.setScreenType(3);
                        momentarlyScreen.setScene(scene);
                        momentarlyScreen.setRoot(root);
                        level1.setupAnimation(root,true,foregroundImages,choosingBackgroundIndex,scene,primaryStage,Scale);
                        level1.addingTable(root);

                    });

                    /**
                     * When right and left is pressed then background is changing
                     * When up and down is pressed then crosshair is changing.
                      */
                }else if (event.getCode() == KeyCode.RIGHT){
                    if (choosingBackgroundIndex == backgroundImages.length -1){
                        root.setBackground(new Background(backgroundImages[0]));
                        momentarlyScreen.setRoot(root);
                        choosingBackgroundIndex=0;
                    }else{
                        root.setBackground(new Background(backgroundImages[choosingBackgroundIndex+1]));
                        momentarlyScreen.setRoot(root);
                        choosingBackgroundIndex +=1;
                    }
                } else if (event.getCode()== KeyCode.LEFT) {
                    if (choosingBackgroundIndex ==0){
                        root.setBackground(new Background(backgroundImages[backgroundImages.length-1]));
                        momentarlyScreen.setRoot(root);
                        choosingBackgroundIndex = backgroundImages.length-1;
                    }else {
                        root.setBackground(new Background(backgroundImages[choosingBackgroundIndex-1]));
                        momentarlyScreen.setRoot(root);
                        choosingBackgroundIndex -=1;
                    }
                } else if (event.getCode()== KeyCode.UP) {
                    if (choosingCrosshairIndex == crossViews.length-1){
                        root.getChildren().clear();
                        root.getChildren().add(crossViews[0]);
                        choosingCrosshairIndex =0;
                    }else {
                        root.getChildren().clear();
                        root.getChildren().add(crossViews[choosingCrosshairIndex+1]);
                        choosingCrosshairIndex +=1;
                    }
                } else if (event.getCode()==KeyCode.DOWN) {
                    if (choosingCrosshairIndex ==0){
                        root.getChildren().clear();
                        root.getChildren().add(crossViews[crossViews.length-1]);
                        choosingCrosshairIndex =crossViews.length-1;
                    }else {
                        root.getChildren().clear();
                        root.getChildren().add(crossViews[choosingCrosshairIndex-1]);
                        choosingCrosshairIndex -=1;
                    }
                }
                /**
                 * In 4'th screen which mean First level is completed, When pressing the enter then second level is handling.
                 * Also canEnterBePressed boolen variable is setted to false because in the next level when we press the
                 * enter it should not start over again.
                 */
            } else if (momentarlyScreen.getScreenType() == 4) {
                if (event.getCode() == KeyCode.ENTER && canEnterBePressed){
                    mediaPlayerPassNextLevel.stop();
                    changingNextLevel(root,crossViews,choosingCrosshairIndex,backgroundImages,momentarlyScreen,scene);
                    level2.setupAnimation(root,true,foregroundImages,choosingBackgroundIndex,primaryStage,scene,Scale);
                    level2.addingTable(root);
                    canEnterBePressed = false;
                }
            }
            /**
             * In 5'th screen which mean Second level is completed, When pressing the enter then third level is handling.
             */
            else if (momentarlyScreen.getScreenType() == 5) {
                if (event.getCode() == KeyCode.ENTER && canEnterBePressed){
                    mediaPlayerPassNextLevel.stop();
                    changingNextLevel(root,crossViews,choosingCrosshairIndex,backgroundImages,momentarlyScreen,scene);
                    level3.setupAnimation(root,true,foregroundImages,choosingBackgroundIndex,primaryStage,scene,Scale);
                    level3.addingTable(root);
                    canEnterBePressed = false;
                }
            }
            /**
             * In 6'th screen which mean Third level is completed, When pressing the enter then fourth level is handling.
             */
            else if (momentarlyScreen.getScreenType() == 6) {
                if (event.getCode() == KeyCode.ENTER && canEnterBePressed){
                    mediaPlayerPassNextLevel.stop();
                    changingNextLevel(root,crossViews,choosingCrosshairIndex,backgroundImages,momentarlyScreen,scene);
                    level4.setupAnimation(root,true,foregroundImages,choosingBackgroundIndex,primaryStage,scene,Scale);
                    level4.addingTable(root);
                    canEnterBePressed = false;
                }
            }
            /**
             * In 7'th screen which mean Fourth level is completed, When pressing the enter then fifth level is handling.
             */
            else if (momentarlyScreen.getScreenType() == 7) {
                if (event.getCode() == KeyCode.ENTER && canEnterBePressed){
                    mediaPlayerPassNextLevel.stop();
                    changingNextLevel(root,crossViews,choosingCrosshairIndex,backgroundImages,momentarlyScreen,scene);
                    level5.setupAnimation(root,true,foregroundImages,choosingBackgroundIndex,primaryStage,scene,Scale);
                    level5.addingTable(root);
                    canEnterBePressed = false;
                }
            }
            /**
             * In 8'th screen which mean Fifth level is completed, When pressing the enter then sixth level is handling.
             */
            else if (momentarlyScreen.getScreenType() == 8) {
                if (event.getCode() == KeyCode.ENTER && canEnterBePressed){
                    mediaPlayerPassNextLevel.stop();
                    changingNextLevel(root,crossViews,choosingCrosshairIndex,backgroundImages,momentarlyScreen,scene);
                    level6.setupAnimation(root,true,foregroundImages,choosingBackgroundIndex,primaryStage,scene,Scale);
                    level6.addingTable(root);
                    canEnterBePressed = false;
                }
            }
            /**
             * In 9'th screen which mean Sixth level is completed and game is over, When we press the enter game starting all over
             * again with gameStartingAgain function. If we press escape then stage is closed.
             */
            else if (momentarlyScreen.getScreenType() == 9) {
                if (event.getCode() == KeyCode.ENTER && canEnterBePressed){
                    mediaPlayerPassNextLevel.stop();
                    gameStartingAgain(primaryStage,root);
                    canEnterBePressed = false;
                }else if (event.getCode() == KeyCode.ESCAPE){
                    primaryStage.close();
                }
            }
            /**
             * This 99'th scene will be used for, in all levels if we failed then screen will be setted to 99.
             * And if we press enter game starts all over again, if we press escape then stage is closed.
             */
            else if (momentarlyScreen.getScreenType() ==99) {
                if (event.getCode() == KeyCode.ENTER){
                    mediaPlayerGameCompleted.stop();
                    gameStartingAgain(primaryStage,root);
                } else if (event.getCode() == KeyCode.ESCAPE) {
                    primaryStage.close();
                }
            }
        });
        /**
         * When we move the mouse then after and include third level crosshair comes with mouse.
         */
        scene.setOnMouseMoved(event -> {
            if (momentarlyScreen.getScreenType() >= 3) {
                double mouseX = event.getX();
                double mouseY = event.getY();

                // Bind crosshair layout coordinates to mouse cursor coordinates
                finalCrosshair.layoutXProperty().bind(scene.widthProperty().multiply(mouseX / scene.getWidth()).subtract(finalCrosshair.getBoundsInLocal().getWidth() / 2));
                finalCrosshair.layoutYProperty().bind(scene.heightProperty().multiply(mouseY / scene.getHeight()).subtract(finalCrosshair.getBoundsInLocal().getHeight() / 2));
            }


        });
        /**
         * For all clicking the mouse:
         */
        scene.setOnMouseClicked(event -> {
            /**
             * In 3'th screen shooting will be handled which mean it is first level.
             */
            if (momentarlyScreen.getScreenType() ==3){
                mediaPlayerShooted.stop();
                mediaPlayerShooted.play();
                double mouseX = event.getX();
                double mouseY = event.getY();
                /**
                 * If we shoot bird in the same coordinate with mouse and bird.
                 * level1 functions will be handled
                 * canEnterBePressed setting to true because we can press the enter now.
                 */
                if (level1.duck1View.getBoundsInParent().contains(mouseX, mouseY)) {
                    mediaPlayerPassNextLevel.play();
                    mediaPlayerFalls.play();
                    root.getChildren().remove(level1.duck1View);
                    level1.setupAnimationFalling(root,mouseX,mouseY,foregroundImages,choosingBackgroundIndex,scene,Scale);
                    level1.shooted();
                    level1.winningMessage(root,primaryStage,Scale);
                    momentarlyScreen.setScreenType(4);
                    canEnterBePressed = true;
                }
                level1.shooting();
                /**
                 * table is uptading.
                 */
                root.getChildren().remove(level1.level1Text);
                level1.addingTable(root);
                /**
                 * If we dont have any ammo left:
                 * Game is lost. and screen will be setted to 99
                 */
                if (level1.getAmmoLeft() <= 0 && !canEnterBePressed){
                    mediaPlayerGameOver.play();
                    Text finishText = new Text();
                    finishText.setText("     GAME OVER!\nPress ENTER to play again\n   Press ESC to exit");
                    finishText.setFont(Font.font("Yellow",40));
                    finishText.setFill(Color.YELLOW);
                    VBox level1TextV = new VBox(finishText);
                    level1TextV.setTranslateX(primaryStage.getWidth()/3);
                    level1TextV.setTranslateY(primaryStage.getHeight()/3);
                    root.getChildren().remove(level1.duck1View);
                    root.getChildren().remove(level1.level1Text);
                    root.getChildren().add(level1TextV);
                    level1.gameLost();
                    momentarlyScreen.setScreenType(99);
                }

            }
            /**
             * In 4'th screen shooting will be handled which mean it is second level.
             * Same processes will be handled to second level.
             */
            else if (momentarlyScreen.getScreenType() ==4) {
                mediaPlayerShooted.stop();
                mediaPlayerShooted.play();
                double mouseX = event.getX();
                double mouseY = event.getY();

                if (level2.duck1View.getBoundsInParent().contains(mouseX, mouseY)) {
                    mediaPlayerFalls.stop();
                    mediaPlayerFalls.play();
                    mediaPlayerPassNextLevel.stop();
                    mediaPlayerPassNextLevel.play();
                    root.getChildren().remove(level2.duck1View);
                    level2.setupAnimationFalling(root,mouseX,mouseY,foregroundImages,choosingBackgroundIndex,scene,Scale);
                    level2.shooted();
                    level2.winningMessage(root,primaryStage,Scale);
                    momentarlyScreen.setScreenType(5);
                    canEnterBePressed = true;
                }
                level2.shooting();
                root.getChildren().remove(level2.level2Text);
                level2.addingTable(root);
                if (level2.getAmmoLeft() <= 0 && !canEnterBePressed){
                    mediaPlayerGameOver.play();
                    Text finishText = new Text();
                    finishText.setText("     GAME OVER!\nPress ENTER to play again\n   Press ESC to exit");
                    finishText.setFont(Font.font("Yellow",40));
                    finishText.setFill(Color.YELLOW);
                    VBox level1TextV = new VBox(finishText);
                    level1TextV.setTranslateX(primaryStage.getWidth()/3);
                    level1TextV.setTranslateY(primaryStage.getHeight()/3);
                    root.getChildren().remove(level2.duck1View);
                    root.getChildren().remove(level2.level2Text);
                    root.getChildren().add(level1TextV);
                    momentarlyScreen.setScreenType(99);
                }

            }
            /**
             * In 5'th screen shooting will be handled which mean it is third level.
             * Same processes will be handled to second level but in here there is a little difference which is
             * there is 2 bird and if we complete the round 2 bird must be shooted.
             */
            else if (momentarlyScreen.getScreenType() == 5){
                mediaPlayerShooted.stop();
                mediaPlayerShooted.play();
                double mouseX = event.getX();
                double mouseY = event.getY();

                if (level3.duck1View.getBoundsInParent().contains(mouseX, mouseY) || level3.duck2View.getBoundsInParent().contains(mouseX, mouseY)) {
                    mediaPlayerFalls.stop();
                    mediaPlayerFalls.play();
                    if (level3.duck1View.getBoundsInParent().contains(mouseX, mouseY)){
                        root.getChildren().remove(level3.duck1View);
                        level3.setupAnimationFalling(root,mouseX,mouseY,foregroundImages,choosingBackgroundIndex,scene,1,Scale);
                        level3.shooted();

                    } else{
                        root.getChildren().remove(level3.duck2View);
                        level3.setupAnimationFalling(root,mouseX,mouseY,foregroundImages,choosingBackgroundIndex,scene,2,Scale);
                        level3.shooted();
                    }
                }
                level3.shooting();
                root.getChildren().remove(level3.level3Text);
                level3.addingTable(root);
                if (level3.isDuck2Falls && level3.isDuck1Falls){
                    mediaPlayerPassNextLevel.stop();
                    mediaPlayerPassNextLevel.play();
                    level3.winningMessage(root,primaryStage,Scale);
                    momentarlyScreen.setScreenType(6);
                    canEnterBePressed = true;
                }
                if (level3.getAmmoLeft() <= 0  && !canEnterBePressed){
                    mediaPlayerGameOver.play();
                    Text finishText = new Text();
                    finishText.setText("     GAME OVER!\nPress ENTER to play again\n   Press ESC to exit");
                    finishText.setFont(Font.font("Yellow",40));
                    finishText.setFill(Color.YELLOW);
                    VBox level1TextV = new VBox(finishText);
                    level1TextV.setTranslateX(primaryStage.getWidth()/3);
                    level1TextV.setTranslateY(primaryStage.getHeight()/3);
                    root.getChildren().remove(level3.duck1View);
                    root.getChildren().remove(level3.duck2View);
                    root.getChildren().remove(level3.level3Text);
                    root.getChildren().add(level1TextV);
                    momentarlyScreen.setScreenType(99);
                }

            }
            /**
             * In 6'th screen shooting will be handled which mean it is fourth level.
             * Same processes will be handled to fourth level.
             */
            else if (momentarlyScreen.getScreenType() == 6){
                mediaPlayerShooted.stop();
                mediaPlayerShooted.play();
                double mouseX = event.getX();
                double mouseY = event.getY();

                if (level4.duck1View.getBoundsInParent().contains(mouseX, mouseY) || level4.duck2View.getBoundsInParent().contains(mouseX, mouseY)) {
                    mediaPlayerFalls.stop();
                    mediaPlayerFalls.play();
                    if (level4.duck1View.getBoundsInParent().contains(mouseX, mouseY)){
                        root.getChildren().remove(level4.duck1View);
                        level4.setupAnimationFalling(root,mouseX,mouseY,foregroundImages,choosingBackgroundIndex,scene,1,Scale);
                        level4.shooted();

                    } else{
                        root.getChildren().remove(level4.duck2View);
                        level4.setupAnimationFalling(root,mouseX,mouseY,foregroundImages,choosingBackgroundIndex,scene,2,Scale);
                        level4.shooted();
                    }
                }
                level4.shooting();
                root.getChildren().remove(level4.level4Text);
                level4.addingTable(root);
                if (level4.isDuck2Falls && level4.isDuck1Falls){
                    mediaPlayerPassNextLevel.stop();
                    mediaPlayerPassNextLevel.play();
                    level4.winningMessage(root,primaryStage,Scale);
                    momentarlyScreen.setScreenType(7);
                    canEnterBePressed = true;
                }
                if (level4.getAmmoLeft() <= 0 && !canEnterBePressed){
                    mediaPlayerGameOver.play();
                    Text finishText = new Text();
                    finishText.setText("     GAME OVER!\nPress ENTER to play again\n   Press ESC to exit");
                    finishText.setFont(Font.font("Yellow",40));
                    finishText.setFill(Color.YELLOW);
                    VBox level1TextV = new VBox(finishText);
                    level1TextV.setTranslateX(primaryStage.getWidth()/3);
                    level1TextV.setTranslateY(primaryStage.getHeight()/3);
                    root.getChildren().remove(level4.duck1View);
                    root.getChildren().remove(level4.duck2View);
                    root.getChildren().remove(level4.level4Text);
                    root.getChildren().add(level1TextV);
                    momentarlyScreen.setScreenType(99);
                }
            }
            /**
             * In 7'th screen shooting will be handled which mean it is fifth level.
             * Same processes will be handled to fifth level.
             */
            else if (momentarlyScreen.getScreenType() == 7){
                mediaPlayerShooted.stop();
                mediaPlayerShooted.play();
                double mouseX = event.getX();
                double mouseY = event.getY();

                if (level5.duck1View.getBoundsInParent().contains(mouseX, mouseY) || level5.duck2View.getBoundsInParent().contains(mouseX, mouseY)
                        || level5.duck3View.getBoundsInParent().contains(mouseX, mouseY)) {
                    mediaPlayerFalls.stop();
                    mediaPlayerFalls.play();
                    if (level5.duck1View.getBoundsInParent().contains(mouseX, mouseY)){
                        root.getChildren().remove(level5.duck1View);
                        level5.setupAnimationFalling(root,mouseX,mouseY,foregroundImages,choosingBackgroundIndex,scene,1,Scale);
                        level5.shooted();

                    } else if (level5.duck2View.getBoundsInParent().contains(mouseX, mouseY)){
                        root.getChildren().remove(level5.duck2View);
                        level5.setupAnimationFalling(root,mouseX,mouseY,foregroundImages,choosingBackgroundIndex,scene,2,Scale);
                        level5.shooted();
                    }else {
                        root.getChildren().remove(level5.duck3View);
                        level5.setupAnimationFalling(root,mouseX,mouseY,foregroundImages,choosingBackgroundIndex,scene,3,Scale);
                        level5.shooted();
                    }
                }
                level5.shooting();
                root.getChildren().remove(level5.level5Text);
                level5.addingTable(root);
                if (level5.isDuck2Falls && level5.isDuck1Falls && level5.isDuck3Falls){
                    mediaPlayerPassNextLevel.stop();
                    mediaPlayerPassNextLevel.play();
                    level5.winningMessage(root,primaryStage,Scale);
                    momentarlyScreen.setScreenType(8);
                    canEnterBePressed = true;
                }
                if (level5.getAmmoLeft() <= 0 && !canEnterBePressed){
                    mediaPlayerGameOver.play();
                    Text finishText = new Text();
                    finishText.setText("     GAME OVER!\nPress ENTER to play again\n   Press ESC to exit");
                    finishText.setFont(Font.font("Yellow",40));
                    finishText.setFill(Color.YELLOW);
                    VBox level1TextV = new VBox(finishText);
                    level1TextV.setTranslateX(primaryStage.getWidth()/3);
                    level1TextV.setTranslateY(primaryStage.getHeight()/3);
                    root.getChildren().remove(level5.duck1View);
                    root.getChildren().remove(level5.duck2View);
                    root.getChildren().remove(level5.duck3View);
                    root.getChildren().remove(level5.level5Text);
                    root.getChildren().add(level1TextV);
                    momentarlyScreen.setScreenType(99);
                }
            }
            /**
             * In 8'th screen shooting will be handled which mean it is sixth level.
             * Same processes will be handled to sixth level.But this is the final level of the game and if we shoot
             * all of the birds then there would be 2 choice starts all over again or quit the game.
             */
            else if (momentarlyScreen.getScreenType() == 8){
                mediaPlayerShooted.stop();
                mediaPlayerShooted.play();
                double mouseX = event.getX();
                double mouseY = event.getY();

                if (level6.duck1View.getBoundsInParent().contains(mouseX, mouseY) || level6.duck2View.getBoundsInParent().contains(mouseX, mouseY)
                        || level6.duck3View.getBoundsInParent().contains(mouseX, mouseY)) {
                    mediaPlayerFalls.stop();
                    mediaPlayerFalls.play();
                    if (level6.duck1View.getBoundsInParent().contains(mouseX, mouseY)){
                        root.getChildren().remove(level6.duck1View);
                        level6.setupAnimationFalling(root,mouseX,mouseY,foregroundImages,choosingBackgroundIndex,scene,1,Scale);
                        level6.shooted();

                    } else if (level6.duck2View.getBoundsInParent().contains(mouseX, mouseY)){
                        root.getChildren().remove(level6.duck2View);
                        level6.setupAnimationFalling(root,mouseX,mouseY,foregroundImages,choosingBackgroundIndex,scene,2,Scale);
                        level6.shooted();
                    }else {
                        root.getChildren().remove(level6.duck3View);
                        level6.setupAnimationFalling(root,mouseX,mouseY,foregroundImages,choosingBackgroundIndex,scene,3,Scale);
                        level6.shooted();
                    }
                }
                level6.shooting();
                root.getChildren().remove(level6.level6Text);
                level6.addingTable(root);
                if (level6.isDuck2Falls && level6.isDuck1Falls && level6.isDuck3Falls){
                    mediaPlayerPassNextLevel.stop();
                    mediaPlayerGameCompleted.play();
                    level6.winningMessage(root,primaryStage,Scale);
                    momentarlyScreen.setScreenType(99);
                    canEnterBePressed = true;
                }
                if (level6.getAmmoLeft() <= 0 && !canEnterBePressed){
                    mediaPlayerGameOver.play();
                    Text finishText = new Text();
                    finishText.setText("     GAME OVER!\nPress ENTER to play again\n   Press ESC to exit");
                    finishText.setFont(Font.font("Yellow",40));
                    finishText.setFill(Color.YELLOW);
                    VBox level1TextV = new VBox(finishText);
                    level1TextV.setTranslateX(primaryStage.getWidth()/3);
                    level1TextV.setTranslateY(primaryStage.getHeight()/3);
                    root.getChildren().remove(level6.duck1View);
                    root.getChildren().remove(level6.duck2View);
                    root.getChildren().remove(level6.duck3View);
                    root.getChildren().remove(level6.level6Text);
                    root.getChildren().add(level1TextV);
                    momentarlyScreen.setScreenType(99);
                }
            }

        });

    }

}
