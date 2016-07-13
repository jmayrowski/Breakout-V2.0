package Breakout;

import Breakout.control.BallControl;

/**
 * This project is created by Romano Waschewski and Jasmin Mayrowski
 * As mandatory libraries we use FXGL v. 0.2.4 and antlr v.4.5.3
 */
public class BallPowerUp implements PowerUp {

    @Override
    public void pickedUp(PowerUpType effect) {

        BallControl bc = new BallControl();

        switch (effect){
            case FASTER:
                bc.speedUpBall();
                break;
            case SLOWER:
                bc.slowDownBall();
                break;
            case MULTIBALL:
                bc.multiBall();
                break;
        }
    }
}
