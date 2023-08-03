import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class creatingImages {
    /**
     * This function will create a array which includes all backgrounds inside of that
     * @return Background[] array
     */
    public static BackgroundImage[] returnsBackgroundImages(){
        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        Image Image1 = new Image("assets/background/1.png");
        BackgroundImage backgroundImage1 = new BackgroundImage(Image1 , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,backgroundSize);
        Image Image2 = new Image("assets/background/2.png");
        BackgroundImage backgroundImage2 = new BackgroundImage(Image2 , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,backgroundSize);
        Image Image3 = new Image("assets/background/3.png");
        BackgroundImage backgroundImage3 = new BackgroundImage(Image3 , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,backgroundSize);
        Image Image4 = new Image("assets/background/4.png");
        BackgroundImage backgroundImage4 = new BackgroundImage(Image4 , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,backgroundSize);
        Image Image5 = new Image("assets/background/5.png");
        BackgroundImage backgroundImage5 = new BackgroundImage(Image5 , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,backgroundSize);
        Image Image6 = new Image("assets/background/6.png");
        BackgroundImage backgroundImage6 = new BackgroundImage(Image6 , BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,backgroundSize);

        BackgroundImage[] backgroundImages = {backgroundImage1,backgroundImage2,backgroundImage3,backgroundImage4,backgroundImage5,backgroundImage6};
        return backgroundImages;
    }
    /**
     * This function will create a array which includes all foreground imageViews inside of that
     * @return ImageView[] array
     */
    public static ImageView[] returnsForegroundImages(){
        Image Image1foreground = new Image("assets/foreground/1.png");
        ImageView foregroundImage1 = new ImageView(Image1foreground);
        Image Image2foreground = new Image("assets/foreground/2.png");
        ImageView foregroundImage2 = new ImageView(Image2foreground);
        Image Image3foreground = new Image("assets/foreground/3.png");
        ImageView foregroundImage3 = new ImageView(Image3foreground);
        Image Image4foreground = new Image("assets/foreground/4.png");
        ImageView foregroundImage4 = new ImageView(Image4foreground);
        Image Image5foreground = new Image("assets/foreground/5.png");
        ImageView foregroundImage5 = new ImageView(Image5foreground);
        Image Image6foreground = new Image("assets/foreground/6.png");
        ImageView foregroundImage6 = new ImageView(Image6foreground);

        ImageView[] foregroundImages = {foregroundImage1,foregroundImage2,foregroundImage3,foregroundImage4,foregroundImage5,foregroundImage6};
        return foregroundImages;
    }
    /**
     * This function will create a array which includes all crosshair imageViews inside of that
     * @return ImageView[] array
     */
    public static ImageView[] returnsCrosshairImages(){
        Image cross1 = new Image("assets/crosshair/1.png");
        ImageView cross1View = new ImageView(cross1);
        Image cross2 = new Image("assets/crosshair/2.png");
        ImageView cross2View = new ImageView(cross2);
        Image cross3 = new Image("assets/crosshair/3.png");
        ImageView cross3View = new ImageView(cross3);
        Image cross4 = new Image("assets/crosshair/4.png");
        ImageView cross4View = new ImageView(cross4);
        Image cross5 = new Image("assets/crosshair/5.png");
        ImageView cross5View = new ImageView(cross5);
        Image cross6 = new Image("assets/crosshair/6.png");
        ImageView cross6View = new ImageView(cross6);

        ImageView[] crossViews = {cross1View,cross2View,cross3View,cross4View,cross5View,cross6View};
        return crossViews;
    }
}
