package Breakout;

import Breakout.control.BatControl;

/**
 * This project is created by Romano Waschewski and Jasmin Mayrowski
 * As mandatory libraries we use FXGL v. 0.2.4 and antlr v.4.5.3
 */
public class BatPowerUp implements PowerUp{

    public void pickedUp(PowerUpType effect) {

        BatControl bc = Breakout.batControl;

        switch (effect){

            case BIGGER:
                bc.increaseBatWidth();
                break;

            case SMALLER:
                bc.decreaseBatWidth();
                break;
        }
    }
}
