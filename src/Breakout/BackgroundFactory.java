package Breakout;

/**
 * This project is created by Romano Waschewski and Jasmin Mayrowski
 * As mandatory libraries we use FXGL v. 0.2.4 and antlr v.4.5.3
 */
public class BackgroundFactory {

    public String getBackground(int index){

        String BGTexture;

        switch (index){
            case 0: BGTexture = "background/background.jpg";
                break;
            case 1: BGTexture = "background/backgroundGalactic.png";
                break;
            case 2: BGTexture = "background/backgroundGreyWall.png";
                break;
            default: BGTexture = "background/background.jpg";
                break;
        }
        return BGTexture;
    }
}

